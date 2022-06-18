(def input (->> (slurp "./resources/10.txt")
     st/split-lines
     (map vec)))

(def pairs
  {\{ \}
   \[ \]
   \( \)
   \< \>})

(def first-points
 {\) 3
  \] 57
  \} 1197
  \> 25137} )

(def second-points
  {\( 1
   \[ 2
   \{ 3
   \< 4} )

(defn pair? [o c]
  (= (get pairs o) c))

(defn find-bad-char [line]
  (loop [inp  line
         stack '()]
    (let [next-char (first inp)
          opening? (contains? pairs next-char)]
      (if (empty? inp)
        nil
        (if (not opening?)
          (if (pair? (first stack) next-char)
            (recur (rest inp) (rest stack))
            next-char)
          (recur (rest inp) (conj stack next-char)))))))

(defn calc-comp-score-on-stack [stack]
  (reduce #(+ (* %1 5 ) (get second-points %2)) 0 stack))

(defn completion-score [line]
  (loop [inp  line
         stack '()]
    (let [next-char (first inp)
          opening? (contains? pairs next-char)
          over? (empty? inp)]
      (if over?
        (calc-comp-score-on-stack stack)
        (if opening?
          (recur (rest inp) (conj stack next-char))
          (recur (rest inp) (rest stack)))))))

(def incomplete-lines (filter #(= nil (find-bad-char %)) input))

(prn "#1: " (reduce #(+ %1 (get first-points (find-bad-char %2) 0)) 0 input))
(prn "#2: " (nth (->> incomplete-lines (map completion-score) sort) 24))
