(ns mind-storm.handler
  (:require [mind-storm.partials :as partials]
            [compojure.core :as cc]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [mind_storm.service.login :as login]
            [mind_storm.service.take_quiz :as take-quiz])
  (:gen-class))

(cc/defroutes main-routes
  (cc/GET "/" [] (partials/index))
  (cc/GET "/login" [] (partials/login))
  (cc/GET "/take-quiz" [] (partials/take-quiz-page))
  (cc/POST "/login-check" {params :params} (login/check-login (:user params) (:password params)))
  (cc/GET "/get-quiz-question" [] (take-quiz/get-quiz-question))
  (cc/GET "/submit-quiz" {params :params} (take-quiz/submit-quiz (:answers params)))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site main-routes))

(defn -main []
  (jetty/run-jetty #'app {:port 8085 :join? false}))


;(login/check-login ("user" params) ("password" params))
