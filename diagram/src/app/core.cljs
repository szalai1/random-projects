(ns app.core
  (:require [reagent.core :as r]
            [app.views :as views]
            [app.state :refer [init-state]]))

(defn ^:dev/after-load start
  []
  (init-state)
  (r/render-component [views/app]
                      (.getElementById js/document "app")))

(defn ^:export main
  []
  (start))
