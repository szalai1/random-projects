(ns huffman.core
  (:gen-class))

(def test-symbols-probability 
  {
   :a 0.1
   :b 0.2
   :c 0.5
   :d 0.2
  })

(def encoder {
    :s1 "00"
    :s2 "10"
    :s3 "11"
    :s4 "010"
    :s5 "011"   
})

(def decoder-tree 
  {
   :0 {:0 :s1 
       :1 {
           :0 :s4
           :1 :s5
       }}
   :1 {
       :0 :s2
       :1 :s3
   }
  })



(defn create-encoder [probabilities] ())

(defn encode [encoder symbols] 
    (clojure.string/join (map #(% encoder) symbols))
)


(defn decode [decoder msg] 
  (reverse (loop [f (first msg)
         r (rest msg)
         tree decoder
         result '()]
    (if (nil? f) result
        (let [sub-tree ((keyword (str f)) tree)
              ff (first r)
              rr (rest r)
              tree (if (keyword? sub-tree) decoder sub-tree)
              res (if (keyword? sub-tree) (conj result sub-tree) result)]
              (recur ff rr tree result)))))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
