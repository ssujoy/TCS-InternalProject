(ns utils.http
  (:require [cheshire.core :as json]))

(defn json-response
  [status data]
  {:status status
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})
