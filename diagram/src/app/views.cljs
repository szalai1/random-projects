(ns app.views
  (:require [app.state :refer [app-state viz-obj]]
            [app.events :refer [increment decrement]]
            ))

(defn renderSVG [viz-o dot]
  (.renderString viz-o dot #js {:format "svg"}))

(defn viz-canvas [viz-o]
    [:div [:p "test"]
     (if (nil? viz-o)
       [:p "viz-o not rendered"]
       [:div {:dangerouslySetInnerHTML {:__html (renderSVG viz-o "digraph { a -> b }")}}]
       )])


(defn app []
  (let [viz-o @viz-obj]
    [:div
     [viz-canvas viz-o]]))

