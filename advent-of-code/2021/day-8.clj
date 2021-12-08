(require '[clojure.string :as st])
(require '[clojure.set :as set])

(def input
  (->> (slurp "./resources/8.txt")
       st/split-lines
       (mapcat #(st/split % #"( |\|)"))
       (filter (complement empty?))))

(def first-star
  (->>  (nthnext input 10)
      (partition 4 14)
      flatten
      (filter #(case (count %)
                 2 true
                 3 true
                 7 true
                 4 true
                 false))
      count))

;;; second star ;;
(def unique-patterns
  (->> input
     (map set)
     (partition 10 14)))

(def signals (->>  (nthnext input 10)
                   (partition 4 14)
                   flatten
                   (map set)
                   (partition 4 4)))
(def good-wiring
  {#{\a \b \c \e \f \g}      0
   #{\c \f}                  1
   #{\a \c \d \e \g}         2
   #{\a \c \d \f \g}         3
   #{\b \c \d \f}            4
   #{\a \b \d \f \g}         5
   #{\a \b \d \e \f \g}      6
   #{\a \c \f}               7
   #{\a \b \c \d \e \f \g}   8
   #{\a \b \c \d \f \g}      9})

(defn select-set-by-size [list-of-sets size]
  (->> list-of-sets (filter #(= size (count %))) first))

(defn get-key-by-value [col val]
  (->> col
       (filter #(= (second %) val))
       first
       first))


(defn pattern-to-number [numbers signal]
  (let [letter-frequency (->> numbers (map vec) flatten frequencies)
        [b e f] (map #(get-key-by-value letter-frequency %) [6 4 9])
        [one four seven eight] (map #(select-set-by-size numbers %) [2 4 3 7])
        c (first (set/difference one #{f}))
        d (first (set/difference four #{b c f}))
        a (first (set/difference seven one))
        g (first (set/difference eight four #{a e}))]
    (->> signal
         (map #(map {a \a b \b c \c d \d e \e f \f g \g}  (vec %)))
         (map #(set %))
         (map #(get good-wiring %))
         (reduce #(+ (* 10 %1) %2))
      )))

(prn "#1: " first-star)
(prn "#2: " (reduce +  (map pattern-to-number unique-patterns signals)))
