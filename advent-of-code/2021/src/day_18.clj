(ns day-18
  (:require [clojure.data.json :as json]))


(def test-cases [{:input "[[1,2],[[3,4],5]]"
                  :result 143}
                 {:input "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"
                  :result 1384}])

(def parse json/read-str)
(def puzzle-input (->> (slurp "./resources/day-18-input.txt")
                       clojure.string/split-lines
                       (map json/read-str)))

;; split 
;; n > 0 -> [(int (Math/floor (/ n /2))), (int (Math/ceil (/ n /2)))]
(defn split [n]
  [(int (Math/floor (/ n 2))) (int (Math/ceil (/ n 2)))])

(defn split? [n] (> n 9))

(defn add-to-left-most [n to-add]
  (cond
    (nil? to-add) n
    (int? n) (+ n to-add)
    :else (let [[l r] n]
            [(add-to-left-most l to-add) r])))

(defn add-to-right-most [n to-add]
  (cond
    (nil? to-add) n
    (int? n) (+ n to-add)
    :else (let [[l r] n]
            [l (add-to-right-most r to-add)])))

;; explode 
;; [l, r] -> 0
;; l is added to the first number on the left 
;; r is added to the first number on the right
(defn explode-num
  ([num] (explode-num num 0))
  ([n depth]
   (if (int? n)
     [n nil]
     (let [[l r] n]
       (if (and (int? l) (int? r) (> depth 3)) ;; condition to explode
         [0 n]
         (let [[l to-distribute] (explode-num l (inc depth))]
           (if (some? to-distribute) ;; todo handle left case
             (let [[x y] to-distribute
                   r (add-to-left-most r y)]
               [[l r] [x nil]])
             (let [[r to-distribute] (explode-num r (inc depth))]
               (if (some? to-distribute)
                 (let [[x y] to-distribute
                       l (add-to-right-most l x)]
                   [[l r] [nil y]])
                 [n nil])))))))))

(defn split-num [n]
  (if (int? n)
    (if (split? n)
      [(split n) true]
      [n false])
    (let [[l r] n
          [l prop] (split-num l)]
      (if prop
        [[l r] true]
        (let [[r prop] (split-num r)]
          [[l r] prop])))))

(defn reduce-num [n]
  (loop [prev-n nil
         n n]
    (if (= prev-n n)
      n
      (let [prev-n n
            [n r] (explode-num n)]
        (if (nil? r)
          (let [[n _] (split-num n)]
            (recur prev-n n))
          (recur prev-n n))))))


(defn sum-nums [a b]
  (reduce-num [a b]))

(defn magnitude [n]
  (if (int? n)
    n
    (+ (* 3 (magnitude (first n)))
       (* 2 (magnitude (second n))))))

(def all-num-pairs (for [i (range (count puzzle-input))]
                     (for [j (range  i (count puzzle-input))]
                       (let [x (nth puzzle-input i)
                             y (nth puzzle-input j)]
                         [(magnitude (sum-nums x y))
                          (magnitude (sum-nums y x))]))))

(prn "first star:" (->> puzzle-input (reduce sum-nums) magnitude))
(prn "second start:" (->> all-num-pairs flatten (apply max)))