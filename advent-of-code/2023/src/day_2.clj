
(def data (-> (slurp "resources/2.txt")
              (clojure.string/split-lines)))

(defn parse-game [line]
  (let [parsed (re-seq #" ([0-9]+) ([a-z]+)" line)
        num (map #(Integer/parseInt (second %)) parsed)
        color (map #(keyword (nth % 2)) parsed)]
    (into {:red 0 :green 0 :blue 0} (zipmap color num))))


(defn parse-line [l]
  (let [id-games (clojure.string/split l #":")
        id (->> id-games first (re-find #"[0-9].*") Integer/parseInt)
        x  (-> id-games
               second
               (clojure.string/split #";"))]
    {:games (map parse-game x)
     :id id}))


(def parsed_data (->> data (map parse-line)))

;; part 1
(defn possible? [game] (and (<= (:red game) 12) (<= (:green game) 13) (<= (:blue game) 14)))
(defn possible-games? [games]
  (every? possible? games))

(->> parsed_data
     (filter #(possible-games? (:games %)))
     (map :id)
        (reduce +))

;;part 2

(defn min-cubes [games]
  (reduce (fn [o i] {:red (max (:red o) (:red i))
                              :green (max (:green o) (:green i))
                                :blue (max (:blue o) (:blue i))})
                               {:red 0 :green 0 :blue 0} games)
          )

(min-cubes  (:games (first parsed_data)))

(->> parsed_data
     (map :games) 
     (map min-cubes)
     (map (fn [o] (reduce * (vals o))))
     (reduce +))


