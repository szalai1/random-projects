(def nums (let [nums-str (with-open
                          [rdr (clojure.java.io/reader "day-1-input-1")]
                           (-> (line-seq rdr) vec))]
                (map #(Integer/parseInt %) nums-str)))

(defn number-of-increases [nums]
  (reduce #(+ %1 (if (< (first %2) (second %2)) 1 0)) 0 (partition 2 1 nums)))

(def first-star (number-of-increases nums))
(def second-star (number-of-increases (map #(reduce + %) (partition 3 1 nums))))

(prn "#1: " first-star)
(prn "#2: " second-star)