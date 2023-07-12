(ns app.state
  (:require [reagent.core :refer [atom]]
             ["@viz-js/viz" :as viz]))

(defonce app-state (atom {:input-data "digraph { a -> b }"}))

(defonce viz-obj (atom nil))

(defn ^:dev/after-load init-state []
  (->
   (.instance viz)
   (.then (fn [v] (reset! viz-obj v) (js/console.log "viz-obj" @viz-obj)))))

