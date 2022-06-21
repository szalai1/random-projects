(ns day-16)

(def puzzle-input "420D4900B8F31EFE7BD9DA455401AB80021504A2745E1007A21C1C862801F54AD0765BE833D8B9F4CE8564B9BE6C5CC011E00D5C001098F11A232080391521E4799FC5BB3EE1A8C010A00AE256F4963B33391DEE57DA748F5DCC011D00461A4FDC823C900659387DA00A49F5226A54EC378615002A47B364921C201236803349B856119B34C76BD8FB50B6C266EACE400424883880513B62687F38A13BCBEF127782A600B7002A923D4F959A0C94F740A969D0B4C016D00540010B8B70E226080331961C411950F3004F001579BA884DD45A59B40005D8362011C7198C4D0A4B8F73F3348AE40183CC7C86C017997F9BC6A35C220001BD367D08080287914B984D9A46932699675006A702E4E3BCF9EA5EE32600ACBEADC1CD00466446644A6FBC82F9002B734331D261F08020192459B24937D9664200B427963801A094A41CE529075200D5F4013988529EF82CEFED3699F469C8717E6675466007FE67BE815C9E84E2F300257224B256139A9E73637700B6334C63719E71D689B5F91F7BFF9F6EE33D5D72BE210013BCC01882111E31980391423FC4920042E39C7282E4028480021111E1BC6310066374638B200085C2C8DB05540119D229323700924BE0F3F1B527D89E4DB14AD253BFC30C01391F815002A539BA9C4BADB80152692A012CDCF20F35FDF635A9CCC71F261A080356B00565674FBE4ACE9F7C95EC19080371A009025B59BE05E5B59BE04E69322310020724FD3832401D14B4A34D1FE80233578CD224B9181F4C729E97508C017E005F2569D1D92D894BFE76FAC4C5FDDBA990097B2FBF704B40111006A1FC43898200E419859079C00C7003900B8D1002100A49700340090A40216CC00F1002900688201775400A3002C8040B50035802CC60087CC00E1002A4F35815900903285B401AA880391E61144C0004363445583A200CC2C939D3D1A41C66EC40")
(def first-tests [{:input "D2FE28" :result 6}
                  {:input "38006F45291200" :result 9}
                  {:input "EE00D40C823060" :result 14}
                  {:input "8A004A801A8002F478" :result 16}
                  {:input "620080001611562C8802118E34" :result 12}
                  {:input "C0015000016115A2E0802F182340" :result 23}
                  {:input "A0016C880162017C3686B18A3D4780" :result 31}])

(defn hex-to-bits [hex-str]
  (let [char-to-bits {\0 "0000"
                      \1 "0001"
                      \2 "0010"
                      \3 "0011"
                      \4 "0100"
                      \5 "0101"
                      \6 "0110"
                      \7 "0111"
                      \8 "1000"
                      \9 "1001"
                      \A "1010"
                      \B "1011"
                      \C "1100"
                      \D "1101"
                      \E "1110"
                      \F "1111"}]
    (->> hex-str
         (partition-all 4 4)
         flatten
         (map char-to-bits)
         concat
         (clojure.string/join "")
         seq)))


(defn bits-to-decimal [bits-seq]
  (reduce (fn [s bit]
            (let [n (if (= bit \1) 1 0)]
              (+ (* s 2) n)))
          0 bits-seq))

(defn get-literal-packet [bits-seq]
  ;; takes a sequence of bits (chars)
  ;; returns the literal packatet and the rest of the bits 
  (let [partitioned (partition 5 5 bits-seq)
        [lieteral n] (reduce (fn [[packet n] bits-part]
                               (let [start-bit (first bits-part)
                                     rest-bits (rest bits-part)
                                     packet (into packet rest-bits)]
                                 (if (= start-bit \1)
                                   [packet (inc n)]
                                   (reduced [(reverse packet) (inc n)])))) ['() 0]
                             partitioned)
        packet-length (* n 5)
        unused-bits (nthrest bits-seq packet-length)]
    [lieteral unused-bits]))

(declare parse-packet)

(defn parse-all-bits [bits]
;; takes bits 
;; parses the bits until it parsed all of them 
;; returns with the vector of the packets  
  (loop [packets []
         packets-bits bits]
    (if (empty? packets-bits)
      packets
      (let [[packet rest-bits] (parse-packet packets-bits)
            packets (conj packets packet)]
        (recur packets rest-bits)))))

(defn parse-n-packets [n bits]
  (loop [packets []
         n n
         rest-bits bits]
    (if (= n 0)
      [packets rest-bits]
      (let [[next-packet rest-bits] (parse-packet rest-bits)
            packets (conj packets next-packet)]
        (recur packets (dec n) rest-bits)))))

(defn parse-packet
  [bits]
  (let [[packet-version-bits rest-bits] (split-at 3 bits)
        packet-version (bits-to-decimal packet-version-bits)
        [type-id rest-bits] (split-at 3 rest-bits)
        type-id (bits-to-decimal type-id)]
    #_(debug-prn  packet-version packet-version-bits type-id bits)
    (if (= type-id 4)
      (let [[literal-packet rest-bits] (get-literal-packet rest-bits)
            literal (bits-to-decimal literal-packet)]
        [{:version packet-version
          :type type-id
          :literal literal}
         rest-bits])
      (let [length-type-id (first rest-bits)
            rest-bits (rest rest-bits)]
        (if (= length-type-id \0)
            ;; next 15 bits is the total length of bits 
          (let [lenght-of-bits (bits-to-decimal (take 15 rest-bits))
                rest-bits (nthrest rest-bits 15)
                packets-bits (take lenght-of-bits rest-bits)
                rest-bits (nthrest rest-bits lenght-of-bits)
                packets (parse-all-bits packets-bits)]
            [{:version packet-version
              :type type-id
              :packets packets}
             rest-bits])
              ;; next 11 bits is the number of subpackats
          (let [num-of-subpackets (bits-to-decimal (take 11 rest-bits))
                rest-bits (nthrest rest-bits 11)
                [packets rest-bits] (parse-n-packets num-of-subpackets rest-bits)]
            [{:version packet-version
              :type type-id
              :packets packets}
             rest-bits]))))))


(defn parse-raw-packet [hex]
  (-> hex hex-to-bits parse-packet first))


(defn sum-version [packet]
  (if (= (:type-id packet) 4)
    (:version packet)
    (reduce + (:version packet)
            (map sum-version (:packets packet)))))


(defn evaluate-packet [p]
  (let [typ (:type p)]
    (if (= typ 4)
      (:literal p)
      (let [vals (map evaluate-packet (:packets p))
            res (case typ
                  0 (reduce + vals)
                  1 (reduce * vals)
                  2 (apply min vals)
                  3 (apply max vals)
                  5 (if (> (first vals) (second vals))
                      1 0)
                  6 (if (< (first vals) (second vals))
                      1 0)
                  7 (if (= (first vals) (second vals))
                      1 0))]
        res))))

(prn "first star:" (sum-version (parse-raw-packet puzzle-input)))
(prn "second star:" (evaluate-packet (parse-raw-packet puzzle-input)))

