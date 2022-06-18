(require '[clojure.string :as st])

(def input (->> (slurp "./resources/6.txt")
     st/split-lines
     (mapcat #(st/split % #","))
      (map #(Integer/parseInt %))
     frequencies))

(defn one-step [freqs]
  (let [new-fish (get freqs 0 0)
        seventh (get freqs 7 0)
        freqs (assoc freqs 7 (+ seventh new-fish))]
    (apply array-map
           (flatten (map
                      #(vector (mod (dec (first %)) 9) (second %))
                      freqs)))))

(defn nth-day [input n]
  (reduce
    #(+ (second %2) %1) 0
    (nth (iterate one-step input) n)))

(pr "#1: " (nth-day input 80))
(pr "#2: " (nth-day input 256))
