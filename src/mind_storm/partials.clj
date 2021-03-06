(ns mind-storm.partials
  (:require [hiccup.page :as hic-p])
  (:use [incanter.core]
        [incanter.excel]
        [dk.ative.docjure.spreadsheet]))

(defn page-header [title]
  [:head
   [:title title]
   (hic-p/include-css "/css/base-style.css")
   (hic-p/include-js "https://ajax.googleapis.com/ajax/libs/angularjs/1.2.28/angular.min.js")
   (hic-p/include-js "https://ajax.googleapis.com/ajax/libs/angularjs/1.2.28/angular-resource.min.js")
   (hic-p/include-js "/app/login.js")])


(defn base-html[title top-content left-content right-content]
  (hic-p/html5
  (page-header title)
  [:body
   [:div {:ng-class "top-div" :style "left:0;top:0;z-index:100 ;height : 40px;width : 100%;position : fixed;border :solid thin black;background-color : black;"} top-content]
   [:div {:style "left:0;top:0;height:auto;min-height : 5000px;width : 100%;position : relative;margin-top: 42px;"}
    [:div {:style "display:inline-block;height:auto;min-height : 5000px;width : 20%;position : relative;border-right :solid thin black;background-color : rgba(251, 253, 234, 0.99);"} left-content]
    [:div {:style "padding: 10px;margin-left:10px;display:inline-block;height:auto;min-height : 5000px;width : 77%;position : relative;background-color : rgba(237, 249, 255, 0.62);vertical-align: top;"} right-content]]
   ]))

(defn index[]
  (base-html "TCS Internal Application" [:label {:style "color:white;font-weight:bold"} "Hello Top Content"] "Hello Left Content" "Hello Right Content"))

(defn login[]
  (hic-p/html5
  (page-header "Login to Mind Storm")
   [:body {:ng-app "login" :ng-controller "loginController"}
    [:div {:class "top-div" :style "left:0;top:0;z-index:100 ;height : 40px;width : 100%;position : fixed;border :solid thin black;background-color : black;"}
     [:label {:style "color:white;font-weight:bold"} "Hello Top Content"]]
    [:div {:style "width: 400px; left :50%;top : 30%;position: absolute;margin-left: -200px;padding: 40px 20px;font-weight: bold;box-shadow: 0px 7px 21px;border-radius: 8px;"}
     [:label "Employee ID"][:br][:br]
     [:input {:type "text" :style "width: 97%;" :ng-model "employee_id"}][:br][:br]
     [:label "Password"][:br][:br]
     [:input {:type "text" :style "width: 97%;" :ng-model "password"}][:br][:br]
     [:input {:type "button" :value "Login" :style "width: 97%;" :ng-click "loginCheck()"}]]]))



(defn take-quiz-page[]
  (base-html "TCS Internal Application" [:label {:style "color:white;font-weight:bold"} "Hello Top Content"] "Hello Left Content"
     [:span {:ng-app "takeQuiz" :ng-controller "takeQuizController"}
      [:div {:style ""} "Select the correct option"
       [:label {:style "float:right"} "Time Remaining : {{timer}} "]]
      [:div {:style ""}
        [:div {:style "background-color: white;box-shadow: 1px 1px 15px;border-radius: 4px;margin-top:45px;min-height:100px;padding: 15px 20px;" :ng-repeat "each_qsn in question_list"}
         [:label {:style ""} "{{each_qsn.question}}"]

         [:table {:style "width: 100%;margin-top: 20px;"}
          [:tr
           [:td [:input {:type "radio" :ng-model "options[each_qsn.sl]" :value "{{each_qsn.op_a}}"}] "{{each_qsn.op_a}}"]
           [:td [:input {:type "radio" :ng-model "options[each_qsn.sl]" :value "{{each_qsn.op_b}}"}] "{{each_qsn.op_b}}"]]
          [:tr
           [:td [:input {:type "radio" :ng-model "options[each_qsn.sl]" :value "{{each_qsn.op_c}}"}] "{{each_qsn.op_c}}"]
           [:td [:input {:type "radio" :ng-model "options[each_qsn.sl]" :value "{{each_qsn.op_d}}"}] "{{each_qsn.op_d}}"]]]]
       [:input {:type "button" :ng-click "submitAnswer()" :value "Submit Quiz" :style "float: right;margin: 35px 0px;width: 180px;height: 50px;border: thin solid #008000;background-color: #C3ECB9;font-size: 19px;font-family: Arial;color: #165C32;cursor:pointer"}]]]))
