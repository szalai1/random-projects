(ns playground)

(require '[reagent.core :as r]
         '[reagent.dom :as rdom])

(def state (r/atom {:mark -1 :number-of-words 0 :text ""}))

(defn update-text-event [text]
  (let [num-of-words (count (clojure.string/split text #" "))]
    (swap! state assoc :text text)
    (swap! state assoc :number-of-words num-of-words)))

(defn update-mark-event [mark]

  (swap! state assoc :mark (js/parseInt mark)))

(update-text-event "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")

;;; state ^  lib  v 

(defn count-possible-marks [text]
  (let [mark-count (:number-of-words @state)]
    (if (zero? mark-count)
      0
      (- mark-count 1))))



(defn mark-text
  [settings text mark]
  (if (= mark -1)
    text
    (if (< mark (count-possible-marks text))
      (let [words (clojure.string/split text #" ")
            markth (str (nth words mark) " ")
            words (assoc words mark markth)]
        (clojure.string/join " " words))
      nil)))

(mark-text {} "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
           1)
;;;; views

(defn text-input-view []
  (let [text (:text @state)
        signed (mark-text {} text (:mark @state))]
        (js/console.log signed)
    [:div.flex-auto
     [:div [:h3 "number of words"]
      [:p (:number-of-words @state)]]

     [:textarea.border {:type "text" :rows 10 :cols 80
                        :default-value text
                        :on-change #(update-text-event (-> % .-target .-value))}]
     [:h1.bold "Result"]
     [:div.whitespace-pre-wrap signed]]))

(defn settings-view []
  [:div.flex-auto
   [:h1 "Settings"]
   [:div [:h3 "mark"]
    [:label {:for "mark"} "mark"]
    [:input {:id "mark" 
             :type "range"
             :default-value (:mark @state)
             :min -1
             :max (count-possible-marks (:text @state))
             :on-change #(update-mark-event (-> % .-target .-value))}]]])

(defn main []
  [:div.container.flex 
   [:div.break-after-column [settings-view]]
   [text-input-view]])

(rdom/render [main] (.getElementById js/document "app"))