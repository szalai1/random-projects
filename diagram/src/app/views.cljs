(ns app.views
  (:require [app.state :refer [app-state viz-obj]]
            [app.events :refer [increment decrement]]
            ))

(defn renderSVG [^js/viz.Viz viz-o dot]
  (let [res (.renderString viz-o dot #js {:format "svg"})]
    (if (:success res) "a" "b")))

(defn viz-canvas [viz-o]
    [:div 
     (if (nil? viz-o)
       [:p "viz-o not rendered"]
       (let [input-data (:input-data @app-state)]
       [:div {:dangerouslySetInnerHTML 
              {:__html (renderSVG viz-o input-data)}}]
       ))])
 

(defn text-input []
  [:div.max-auto
   [:textarea {:cols "60" :rows "200" :on-change #(swap! app-state assoc :input-data %)
               :value (:input-data @app-state)}]
   ])


(defn app []
  (let [viz-o @viz-obj]
    [:div.container.mx-auto.columns-2
    [:div.break-after-column [text-input]]
     [:div [viz-canvas viz-o]]
     ]
    ))

