(ns mind_storm.service.take_quiz
(:require [mind_storm.models.take_quiz :as take-quiz]
          [utils.http :as http-util]
          [utils.middleware :as middleware]
          [noir.session :as session]))

(defn get-quiz-question
  []
  (let[data (take-quiz/get-quiz-question*)]
    (http-util/json-response 200 {:success true  :data data})))

(defn calculate-marks[]
  )

(defn submit-quiz
  [answers]
  (println answers)
  (let[data (take-quiz/submit-quiz)]
    (http-util/json-response 200 {:success true  :data data})))

