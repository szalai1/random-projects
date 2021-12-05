(require '[clojure.string :as st])

(def input
  (->> slurp "./resources/5.txt")
     st/split-lines
     (mapcat #(st/split % #"( -> |,)"))
     (map #(Integer/parseInt %))
     (partition 4 4)))

 (defn coordinate-range [[a b]]
   (let [direction (if (> a b) -1 1)]
     (range a (+ b direction) direction )))

(defn generate-line [x1 y1 x2 y2]
  (let [x (coordinate-range [x1 x2])
        y (coordinate-range [y1 y2])]
    (if (or (= x1 x2) (= y1 y2))
      (if (= 1 (count x))
        (map #(vector (first x) %) y)
        (map #(vector % (first y)) x))
      (map vector x y))))

(defn diagonal? [[x1 y1 x2 y2]]
  (and (not (= x1 x2)) (not (= y1 y2))))

(def non-diags (filter (complement diagonal?) input))

(defn count-intersects [input]
  (->> input
       (mapcat #(apply generate-line %))
       frequencies
       (filter #(> (second %) 1))
       count))

(pr "#1: " (count-intersects non-diags))
(pr "#2: " (count-intersects input))
