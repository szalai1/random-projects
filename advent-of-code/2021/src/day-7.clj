;; 👋 

(defn first-dist [a b]
  (Math/abs (- a b)))

(defn second-dist [a b]
  (let [abs-dist (first-dist a b)]
    (/ (* (inc abs-dist) abs-dist) 2)))

(defn all-travel-to-point [point dist-func input]
  (->> input
      (map #(dist-func %1 point))
      (reduce +)))

(def solve [dist-func input]
  (let [max-dist (reduce max input)]
    (->> (range max-dist)
         (reduce #(min
                    (all-travel-to-point %2 dist-func input)
                    %1)
                 (all-travel-to-point 0 dist-func input)))))

(pr "#1: " (solve first-dist input))
(pr "#2: " (solve second-dist input))

;; O(n*log(n) + n) solution
;; closed form solution of the first start
;; this approach doesn't work for the second, but it's really fast
(defn first-star [input]
  (let [input (sort input)
        mid-point (int (/ (count input) 2))
        first-half (nthnext input mid-point)
        second-half (nthnext (reverse input) mid-point)]
    (reduce #(+ (- (first %2) (second %2)) %1)
            0
            (map vector first-half second-half))))
