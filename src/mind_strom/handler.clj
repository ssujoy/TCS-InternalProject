(ns mind-strom.handler
  (:require [mind-strom.partials :as partials]
            [compojure.core :as cc]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty])
  (:gen-class))

(cc/defroutes main-routes
  (cc/GET "/" [] (partials/index))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site main-routes))

(defn -main []
  (jetty/run-jetty #'app {:port 8081 :join? false}))
