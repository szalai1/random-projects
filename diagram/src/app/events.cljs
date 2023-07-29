(ns app.events
  (:require [app.state :refer [app-state]]
             [clojure.edn :as edn]))

(defn input-changes [input]
  (let [input-edn (edn/read-string input)]
  (js/console.log "input-changes" input input-edn)
    (swap! app-state assoc :input-data input-edn)))
