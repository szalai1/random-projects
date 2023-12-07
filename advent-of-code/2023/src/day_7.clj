(def card-mapping {"2" 2 "3" 3 "4" 4 "5" 5 "6" 6 "7" 7 "8" 8 "9" 9 "T" 10 "J" 11 "Q" 12 "K" 13 "A" 14})
(defn parse-line [l]
  (let [[raw-cards raw-bet] (clojure.string/split l #" ")
        bet (Integer/parseInt raw-bet)
        cards (map #(card-mapping (str %)) raw-cards)
        ]
    {:hand cards :bet bet}))

(def data (->> (slurp "resources/7.txt")
              (clojure.string/split-lines)
              (map parse-line)))

(defn hand-to-score [hand]
  "Scoring function for a hand of cards 
   that can be used for sorting the hands"
  (let [kind-score (->> hand
                        frequencies
                        (map second)
                        (filter (fn [v] (> v 1)))
                        (map (fn [v] (Math/pow 3 v)))
                        (reduce +))
        order-score (reduce #(+ (* %1 15) %2) hand)]
    ;; max value for order-score is less then 1M so we can use it as a multiplier
    (+ (* 1000000 kind-score) order-score)))

;; part 1
(->> data 
     (map #(assoc % :hand-score (hand-to-score (:hand %))))
     (sort-by :hand-score)
     (map-indexed #(* (inc %1) (:bet %2)))
     (reduce +))
                 