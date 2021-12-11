(def input
  (->> (slurp "./resources/11.txt")
       st/split-lines
       (mapcat vec)
       (map #(Integer/parseInt (str %)))
       (partition 10 10)
       (map vec)
       vec))


(def all-coordinates (for [x (range 0 10) y (range 0 10)] [x y]))

(defn neighbours [[x y]]
  (->> [[(inc x) y]
        [(inc x) (inc y)]
        [(inc x) (dec y)]
        [x (inc y)]
        [x (dec y)]
        [(dec x) y]
        [(dec x) (dec y)]
        [(dec x) (inc y)]]
       (filter (fn [[x y]] (and
                             (> x -1)
                             (< x 10)
                             (> y -1)
                             (< y 10))))))


(defn increase-elements [m]
  (->> all-coordinates
       (update-in m [] inc)
       (map #(vec (map inc %)))
       vec))


(defn cascade [m]
  (let [loaded (set (filter #(<= 10 (get-in m %)) all-coordinates))
        m (reduce #(update-in %1 %2 (constantly -1)) m loaded)
        flashed (set (filter #(= -1 (get-in m %)) all-coordinates))
        to-inc (filter #((complement contains?) flashed %)
                       (mapcat neighbours loaded))
        updated (reduce #(update-in %1 %2 inc) m to-inc)]
    (if (empty? loaded)
      (let [new-m (reduce
                    #(update-in %1 %2 (constantly 0))
                    updated
                    flashed)]
        (list new-m (count flashed)))
         (recur updated) )))

(defn step [[ m flashes]]
  (let [[new-m new-flashes] (cascade (increase-elements m))]
    [new-m (+ flashes new-flashes)]))


(prn "#1: " (second (nth (iterate step [ input 0]) 100)))
(nth (iterate step [ input 0]) 213)
