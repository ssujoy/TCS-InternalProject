(ns mind_storm.models.login
(:use [incanter.core]
        [incanter.excel]
        [dk.ative.docjure.spreadsheet]))

;////////////////////////////////////////////////////////////////////////////////
; Reading the GK answers from the excel
;///////////////////////////////////////////////////////////////////////////////
(defn get-row-with-id-and-password [total-list emp-id password]
  (filter #(and (= (str (:emp_id %)) emp-id) (= (:password %) password)) total-list))


(defn check-login
  [emp-id password]
  (get-row-with-id-and-password (->> (load-workbook ".//data//mind-storm.xls")
        (select-sheet "EMPLOYEE")
        (select-columns {:A :sl, :B :first_name, :C :last_name, :D :emp_id, :E :password})) (str emp-id ".0") password))

;(check-login "739957" "sahsu")









