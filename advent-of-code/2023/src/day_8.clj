(ns day-8) 

(def data (-> (slurp "resources/8.txt")
              (clojure.string/split-lines)))

(def left-right (first data))

(defn parse-line [l]
  (let [letters (re-seq #"[A-Z]{3}" l)]
    letters))

(def data-map 
  (->> data rest rest 
       (map parse-line)
  (reduce #(assoc %1 (first %2) (rest %2)) {})))
  
(count left-right)

(loop [i 0 postion "AAA"]
  (let [ii (mod i (count left-right))
        next-step (get left-right ii)
        directions (get data-map postion)
        next-postion (if (= next-step \R)
                      (second directions) 
                       (first directions))]
    (if (= postion "ZZZ") i
      (recur (inc i) next-postion))))





                                            