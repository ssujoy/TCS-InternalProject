(ns mind_storm.service.login
(:require [mind_storm.models.login :as login]
          [utils.http :as http-util]
          [utils.middleware :as middleware]
          [noir.session :as session]))

;////////////////////////////////////////////////////////////////////////////////
; Reading the GK answers from the excel
;///////////////////////////////////////////////////////////////////////////////
(defn check-login
  [emp-id password]
  (let[data (login/check-login emp-id password)
       data-row (if (> (count data) 0) data nil)]
    (println "[DEBUG]>>>>> " (count data) " === data = " data " ===== data-row " emp-id)
    (println "middleware/ACCESS-DETAILS = " (middleware/get-ACCESS-DETAILS))
    (http-util/json-response 200 {:success true  :data data})))



