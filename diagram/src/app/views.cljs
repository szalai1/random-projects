(ns app.views
  (:require [app.state :refer [app-state viz-obj]]
            [app.events :refer [increment decrement]]
            ))

(defn renderSVG [^js/viz.Viz viz-o dot]
  (let [res (try (.renderString viz-o dot #js {:format "svg"})
                 (catch :default e (js/console.log e)))]
    (if (empty? res) "not a valid graph" res)))

(defn viz-canvas []
    (let [viz-o @viz-obj
          input-data (:input-data @app-state)]
    [:div
     (if (nil? viz-o)
       [:p "viz-o not rendered"]
       [:div {:dangerouslySetInnerHTML 
              {:__html (renderSVG viz-o input-data)}}]
       )]))
 

(defn text-input []
  [:div
   [:textarea.border-solid.border-2.border-gray-900
    {:cols "60" :rows "200" 
               :on-change 
               #(swap! app-state assoc :input-data (.. % -target -value))
               :default-value (:input-data @app-state)
               }]
   ])


(defn app []
    [:div.container.flex.px-12
    [:div.break-after-column.flex [text-input]]
     [:div.flex-auto [viz-canvas]]
     ]
    )

