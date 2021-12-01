(pr "hello")

(def nums (let [nums-str (with-open
                          [rdr (clojure.java.io/reader "day-1-input-1")]
                           (-> (line-seq rdr) vec))]
                (map #(Integer/parseInt %) nums-str)))

(defn first-start
  (reduce #(+ %1 (if (< (first %2) (second %2)) 1 0)) 0 (partition 2 1 nums)))

(defn second-start
    (reduce #(+ %1 (if (< (first %2) (second %2)) 1 0)) 0 (partition 2 1 (map #(reduce + %)  (partition 3 1 nums)))))
