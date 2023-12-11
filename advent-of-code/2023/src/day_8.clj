(ns day-8) 

(def data (-> (slurp "resources/8.txt")
              (clojure.string/split-lines)))

(def left-right (first data))

(defn parse-line [l]
  (let [letters (re-seq #"[0-9A-Z]{3}" l)]
    letters))

(def data-map 
  (->> data rest rest 
       (map parse-line)
  (reduce #(assoc %1 (first %2) (rest %2)) {})))

(defn walk [postion i] 
  (let [next-step (get left-right (mod i (count left-right)))
        directions (get data-map postion)
        next-postion (if (= next-step \R)
                       (second directions)
                       (first directions))]
      (lazy-seq (cons [postion i] (walk next-postion (+ i 1))))))

;; part 1
(-> (take-while #(not= (first %) "ZZZ") (walk "AAA" 0)) last second)

;; part 2
(def starting-points (->> data-map keys (filter #(= \A (last %)))))

(defn walk-parallel [postions]
  (map #(walk % 0) postions))

(def paths (walk-parallel starting-points))

(defn get-loop [path]
  (loop [p path 
       seen {}
       loop []]
  (let [[position i] (first p)
        i (mod i (count left-right))
        next [position i]
        rest (rest p)]
    (if (contains? seen next)
      loop
      (recur rest (assoc seen next true) (conj loop next))))))


(->> paths first (take 5))
(def loops (->> paths  (map get-loop)))
(def loop-lengths (->> loops (map count)



;; (->> loops  
;;      (map (fn [p] (filter #(= \Z (last (first %))) p )) ) )
;;      (map #(map second %)) )
;;      (map #(map count %)))

;; things to do 
;; ---
;; ✅ make walk to return [postion i]
;; ✅ use the the pair to break the loop 
;; ✅ update part 1 to use the new walk
;; filter for ends with \z and get their index 
;; calc lcm of the lengths
;; calc the index until the lcm  
;; merge the lists and count all the numbers 
;; get the min that has number of paths frequency -> result