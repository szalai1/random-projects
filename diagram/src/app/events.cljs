(ns app.events
  (:require [app.state :refer [app-state]]))

(defn input-changes [input] 
  (swap! app-state assoc :input-data input))

