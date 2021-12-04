(require '[clojure.string :as st])

(def input (clojure.string/split-lines
             (slurp "./resources/4.txt")))

(def draw-order (map #(Integer/parseInt %)
                     (st/split (first  input) #",")))

(def boards
  (->> input
       rest
       (partition 6 6)
       (map rest)
       flatten
       (map #(st/split % #" "))
       flatten
       (filter #(not (= "" %)))
       (map #(Integer/parseInt %))
       (partition 25 25)
       (map #(partition 5 5 %))))

(defn bingo-for-rows?  [board]
  (reduce #(or %1 (= %2 [:x :x :x :x :x])) false board))

(defn bingo? [board]
  (or
    (bingo-for-rows? (transpose board))
    (bingo-for-rows? board)))

(defn mark-board [board num]
  (->>
    board
    flatten
    (map #(if (= % num) :x %))
    (partition 5 5)))

(defn first-winner-board [boards draw-order]
  (loop [boards boards
         draw-order draw-order]
    (let [next-draw (first draw-order)
          next-boards (map #(mark-board % next-draw) boards)
          winners (filter bingo? next-boards)]
      (if (empty? winners)
        (recur next-boards (rest draw-order))
        (list (first winners) next-draw)))))


(defn last-winner-board [boards draw-order]
  (loop [boards boards
         draw-order draw-order]
    (let [draw (first draw-order)
          next-boards (map #(mark-board % draw) boards)
          non-winners (filter (complement bingo?) next-boards)
          winners (filter bingo? next-boards)]
      (if (not (and (= (count winners) 1) (= (count next-boards) 1 )))
        (recur non-winners (rest draw-order))
        (list (first winners) draw)))))


(defn calc-board-score [board last-num]
  (let [remaining-nums
        (filter #(not (= :x %)) (flatten board))]
       (* last-num (reduce + remaining-nums))))

(prn "#1: " (apply calc-board-score (first-winner-board boards draw-order)))
(prn "#1: " (apply calc-board-score (last-winner-board boards draw-order)))
