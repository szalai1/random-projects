(ns playground)

(require '[reagent.core :as r]
         '[reagent.dom :as rdom])

(def state (r/atom {:names nil
                    :deleted-names (sorted-map)
                    :filters {:no-hu false :only-uk false}
                    :uk-names #{}}
                   ))

(defn process-names [names]
  (->> names  (map-indexed
   (fn [idx name-str] [idx {:name name-str
                   :id idx
                   :length (count name-str)
                   :vowels (count (re-seq #"[aeiou]" name-str))
                   :deleted false
                   :uk (contains? (:uk-names @state) name-str)
                   :hu-letters (count (re-seq #"[áéíóöőúüű]" (clojure.string/lower-case name-str)))
                   :consonants (count (re-seq #"[bcdfghjklmnpqrstvwxyz]" name-str))}]))
         (into (sorted-map))))

(defn load-names [state]
  (-> (.fetch js/window "http://localhost:1341/hu.txt")
      (.then #(.json %))
      (.then #(swap! state assoc :names (process-names %)))))

(defn init! [state]
  (-> (.fetch js/window "http://localhost:1341/uk.txt")
      (.then #(.json %))
      (.then #(swap! state assoc :uk-names  (into #{} %)))
      (.then #(load-names state))))
(init! state)




(defn delete-name [id]
  (let [name (get-in @state [:names id])]
    (swap! state update-in  [:names id :deleted] not)))

(defn name-view [name]
  (let [name-str  (:name name)
        vowels    (:vowels name)
        id       (:id name)
        uk      (:uk name)
        hu-letters (:hu-letters name)]
    [:tr [:td name-str]
     [:td hu-letters]
     [:td (if uk "🇬🇧" "")]
     [:td [:button.rounded-full.bg-purple-100 {
                                  :on-click #(delete-name id)  } ">>"]]]))

(defn names-view [names]
  [:div.container.mx-auto.px-12
   [:table.table-auto.border-collapse.border-2.border-gray-900]
   [:tr [:th "Name"] [:th "HU letters"]]
     (for [[id name] names]
       (name-view name))])

(defn deleted-names-view [deleted-names]
  [:div#container.mx-auto.px-12
   [:table.table-auto]
   [:tr [:th "Filtered out names"] ]
     (for [[id name] deleted-names]
       [:tr [:td (:name name)]] )])

;;filter component that allows to filter names by criterias
(defn filters []
  [:div.container.mx-auto.px-12
  [:fieldset.boarder.border-solid.border-2.border-gray-900
   [:legend "Filters"]
   [:div [:input
          {:type "checkbox"
           :name "vowels"
           :id "no-hu"
           :on-change #(swap! state update-in [:filters :no-hu] not)}]
    [:label {:for "no-hu"} "no Hungarian letters"]]
   [:div [:input
           {:type "checkbox"
            :name "uk"
            :id "uk"
            :on-change #(swap! state update-in [:filters :only-uk] not)}]
     [:label {:for "uk"} "only UK names"]]]])

(defn hu-filter [name]
  (not (and (get-in @state [:filters :no-hu]) (> (:hu-letters name) 0))))

(defn uk-filter [name]
  (not (and (get-in @state [:filters :only-uk]) (not (:uk name)))))

(defn main []
 (let [grouped-names (group-by
             #(and (hu-filter (second %)) 
                  (uk-filter (second %)) 
                  (not (:deleted (second %)))) 
              (:names @state))
       deleted-names (get grouped-names false)
       names (get grouped-names true)]
  [:div.container
    [filters]
  [:div.container.mx-auto.px-4.columns-2.shadow-2xl
   [:div.shadow-xl [names-view names]]
   [:div.break-after-column]
   [:div.shadow-xl [deleted-names-view deleted-names]]]]))

(rdom/render [main] (.getElementById js/document "app"))
