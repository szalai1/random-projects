(def data (-> (slurp "resources/3.txt")
              (clojure.string/split-lines)))

(def width (count (first data)))

(def padded-data
  (let [row-padding (apply str (repeat width "."))
        temp (into [row-padding] (conj data row-padding))]
    (map #(str "." % ".") temp)))


(def around-offsets [[-1 0] [1 0] [0 -1] [0 1] [-1 -1] [-1 1] [1 -1] [1 1]])

(defn get-coo [x y]
  (nth (nth padded-data y) x))

