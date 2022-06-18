(require '[clojure.string :as st])


(defn transpose [xs]
  (apply map list xs))


(def input (clojure.string/split-lines
            (slurp "./resources/3.txt")))

(def freqs (map frequencies
  (apply transpose input) ) )

(defn sort-map-by-freq [x]
  (into (sorted-map-by (fn [k1 k2]
                   (compare [(get x k2) k2]
                            [(get x k1) k1]))) x))


(def first-star (apply * 
                       (map #(Integer/parseInt % 2) 
                            (map
                              #(apply str %)
                              (apply transpose 
                                     (map #(map key %)
                                          (map sort-map-by-freq  freqs)))))))


(defn second-star [input solution]
   (if (= (count (first input)) 1) (conj solution (first (first input)))
   (let [firsts (map first input)
        frequent-char (-> firsts frequencies sort-map-by-freq reverse first key)
        next-inp-plus-first (filter #(= (first %) frequent-char) input)
         next-inp (map rest next-inp-plus-first)]
  (recur next-inp (conj solution frequent-char)))))

(Integer/parseInt (apply str (second-star input []))  2)

(-> (map first input) frequencies sort-map-by-freq first key)
(first input)
