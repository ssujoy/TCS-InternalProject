(ns utils.middleware
  (:require [noir.session :as session]))

(declare ^:dynamic ACCESS-DETAILS)

(defn set-ACCESS-DETAILS []
  (binding [ACCESS-DETAILS "sujoy"]))


(defn get-ACCESS-DETAILS []
  "HELLO")

