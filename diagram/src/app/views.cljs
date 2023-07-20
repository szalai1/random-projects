(ns app.views
  (:require [app.state :refer [app-state viz-obj]]
            [cljstache.core :refer [render]]
            [dorothy.core :as viz]
            ))

(defn renderSVG [^js/viz.Viz viz-o dot]
  (let [res (try (.renderString viz-o dot #js {:format "svg"})
                 (catch :default e (js/console.log e)))]
    (if (empty? res) "not a valid graph" res)))


(defn generate-dot [data]
  (let [nodes (->> (:nodes data)
                   (map (fn [n]
                         [(:id n) {:label (:label n)}] )))
        dot-str  (viz/dot (viz/digraph [nodes]))]
      dot-str
    ))


(defn viz-canvas []
    (let [viz-o @viz-obj
          input-data (:input-data @app-state)
          dot-str (generate-dot input-data)
          svg-str (renderSVG viz-o dot-str)]
    [:div 
     (if (nil? viz-o)
       [:p "viz-o not rendered"]
       [:div {:dangerouslySetInnerHTML 
              {:__html svg-str }}]
       )]))
 

(defn data-view []
  (let [input-data (with-out-str (cljs.pprint/pprint (:input-data @app-state)))]
    [:div.border-solid.border-2.border-gray-900.flex
     [:textarea.font-mono.flex {:rows 20 :cols 40 :default-value input-data }  ]]))
(with-out-str (cljs.pprint/pprint (:input-data @app-state)))

(let [config {}
      input-data {
  :nodes [
          {:id "a1xx" :label "xxxa"} 
          {:id "b" :label "b"}
          {:id "c" :label "cica"}
          {:id "x" :label "cica"}
          ]
  :edges [{:from "a" :to "b"}]
}]
  (swap! app-state assoc :input-data  input-data))

(generate-dot (:input-data @app-state))

(defn dot-view []
  (let [dot-str (generate-dot (:input-data @app-state))]
    [:div.border-solid.border-2.border-gray-900.flex-auto
     [:pre dot-str]]))

(defn app []
    [:div.container.flex.px-12
     [:div.felx 
    [:div.flex [data-view]]
     [:div.break-after-column.flex [dot-view]]]
     [:div.flex-auto.border-4 [viz-canvas]]
     ]
    )

