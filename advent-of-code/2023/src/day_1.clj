(ns day-1  (:import (java.util.regex Pattern)))
(require '[clojure.string :as st])

;; part 1
(defn first-last-number-from-line [line]
  (let [f (re-find #"[0-9]" line)
        l (re-find #"[0-9]" (st/reverse line))]
    (Integer/parseInt (str f l))))

(->>
 (slurp "./resources/1.txt")
 (st/split-lines)
 (map first-last-number-from-line)
 (reduce +))

;; part 2
(defn first-last-number-from-line-part-2 [line]
  (let [digit-name-mapping {"one" "1" "two" "2" "three" "3" "four" "4"
                            "five" "5" "six" "6" "seven" "7" "eight" "8"
                            "nine" "9" "1" "1" "2" "2" "3" "3" "4" "4"
                            "5" "5" "6" "6" "7" "7" "8" "8" "9" "9"}
        rgx  (str "(?=(" (st/join "|" (keys digit-name-mapping)) "))")
        digit-matching (Pattern/compile rgx)
        matches (map digit-name-mapping (map second (re-seq digit-matching line)))]
    (Integer/parseInt (str (first matches) (last matches)))))

(->> (slurp "./resources/1.txt")
     (st/split-lines)
     (map first-last-number-from-line-part-2)
     (reduce +))