(def data (map (fn [line] 
                 (let [elements (clojure.string/split line #" ")] 
                   (list (keyword (first elements)) (Integer/parseInt (second elements)))))
               (clojure.string/split-lines (slurp "day-2-input-1"))))

 #_(update (group-by first data) :up (fn [values] (reduce + (map second values))))

(def first-star (reduce * (reduce (fn [[loc_x loc_y] data] 
          (let [direction (first data)
                step (second data)]
          (case direction
            :forward [(+ loc_x step) loc_y]
            :up [loc_x (- loc_y step)]
            :down [loc_x (+ step loc_y)])))
        [0 0] data)))

(def second-star 
  (reduce * 
    (butlast (reduce (fn [[loc_x loc_y aim] data] 
      (let [direction (first data)
            step (second data)]
      (case direction
        :forward [(+ loc_x step) (+ loc_y (* aim step)) aim]
        :up [loc_x loc_y (- aim step)]
        :down [loc_x loc_y (+ step aim)])))
    [0 0 0] data))))


(prn "#1: " first-star)
(prn "#2: " second-star)