(ns app.core
  (:require [reagent.dom :as rd]
            [app.views :as views]
            [app.state :refer [init-state]]))

(defn ^:dev/after-load start
  []
  (init-state)
  (rd/render [views/app]
                      (.getElementById js/document "app")))

(defn ^:export main
  []
  (start))
