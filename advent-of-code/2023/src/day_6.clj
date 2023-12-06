(def input-data [[51 222] [92 2031] [68 1126] [90 1225]])
(defn solve-second-order [a b c]
  (let [d (- (* b b) (* 4 a c))] 
    (if (< d 0) nil
      (let [x1 (/ (+ (- b) (Math/sqrt d)) (* 2 a))
            x2 (/ (- (- b) (Math/sqrt d)) (* 2 a))]
        (if (= x1 x2)
          [x1]
          [x1 x2])))))


(defn solve-one [time distance]
  (let [[x1 x2] (solve-second-order -1 time (- distance))
        low (min x1 x2)
        high (max x1 x2)
        low-int (Math/ceil low )
        high-int (Math/floor high)
        offset (if (= low-int (Math/floor low)) -1 1)]
    (int (+ offset
            (- high-int 
               low-int)))))
;; part 1 
(->> input-data 
     (map #(apply solve-one %))
     (reduce *))

;;part 2 
(def input-data-2 [51926890 222203111261225])
(apply solve-one input-data-2)