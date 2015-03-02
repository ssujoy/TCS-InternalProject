(ns mind_storm.models.take_quiz
(:use [incanter.core]
        [incanter.excel]
        [dk.ative.docjure.spreadsheet]))


(defn get-quiz-question*
  ^{:Author "Sujoy Saha"
    :Doc "Fetch all the questions and answers from the QUESTIONS sheet."}
  []
  (->> (load-workbook ".//data//mind-storm.xls")
        (select-sheet "QUESTIONS")
        (select-columns {:A :sl, :B :question, :C :op_a, :D :op_b, :E :op_c, :F :op_d})))

(defn submit-quiz
  ^{:Author "Sujoy Saha"
    :Doc "Fetch all the correct answers from the QUESTIONS sheet."}
  []
  (->> (load-workbook ".//data//mind-storm.xls")
        (select-sheet "QUESTIONS")
        (select-columns {:A :sl, :G :answers})))


(get-quiz-question*)

(submit-quiz)
