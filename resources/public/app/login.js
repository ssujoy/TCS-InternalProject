// Your app's root module...
var login = angular.module('login', [], function($httpProvider) {
  // Use x-www-form-urlencoded Content-Type
  $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

  /**
   * The workhorse; converts an object to x-www-form-urlencoded serialization.
   * @param {Object} obj
   * @return {String}
   */
  var param = function(obj) {
    var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

    for(name in obj) {
      value = obj[name];

      if(value instanceof Array) {
        for(i=0; i<value.length; ++i) {
          subValue = value[i];
          fullSubName = name + '[' + i + ']';
          innerObj = {};
          innerObj[fullSubName] = subValue;
          query += param(innerObj) + '&';
        }
      }
      else if(value instanceof Object) {
        for(subName in value) {
          subValue = value[subName];
          fullSubName = name + '[' + subName + ']';
          innerObj = {};
          innerObj[fullSubName] = subValue;
          query += param(innerObj) + '&';
        }
      }
      else if(value !== undefined && value !== null)
        query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
    }

    console.log(query);
    return query.length ? query.substr(0, query.length - 1) : query;
  };

  // Override $http service's default transformRequest
  $httpProvider.defaults.transformRequest = [function(data) {
    return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
  }];
});

//================================== LOGIN CONTROLLER ==================================

login.controller("loginController", function($scope, $http, $window) {
  alert("LOGIN CONTROLLER");
  $scope.loginCheck = function (){
    $http.post('/login-check',
               {user: $scope.employee_id,
                password: $scope.password}).success(function(data, status, headers, config) {
                  console.log(data);
                  if(data.data != null)
                    alert("RIGHT ---> " + data.data[0].first_name + " " + data.data[0].last_name);
                  else
                    alert("WRONG");
                }).error(function(data, status) { // called asynchronously if an error occurs
                  alert("ERROR");
                });
    }

});

//=======================================================================================
var takeQuiz = angular.module('takeQuiz', [], function($httpProvider) {
  // Use x-www-form-urlencoded Content-Type
  $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

  /**
   * The workhorse; converts an object to x-www-form-urlencoded serialization.
   * @param {Object} obj
   * @return {String}
   */
  var param = function(obj) {
    var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

    for(name in obj) {
      value = obj[name];

      if(value instanceof Array) {
        for(i=0; i<value.length; ++i) {
          subValue = value[i];
          fullSubName = name + '[' + i + ']';
          innerObj = {};
          innerObj[fullSubName] = subValue;
          query += param(innerObj) + '&';
        }
      }
      else if(value instanceof Object) {
        for(subName in value) {
          subValue = value[subName];
          fullSubName = name + '[' + subName + ']';
          innerObj = {};
          innerObj[fullSubName] = subValue;
          query += param(innerObj) + '&';
        }
      }
      else if(value !== undefined && value !== null)
        query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
    }

    console.log(query);
    return query.length ? query.substr(0, query.length - 1) : query;
  };

  // Override $http service's default transformRequest
  $httpProvider.defaults.transformRequest = [function(data) {
    return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
  }];
});

//================================== TAKE QUIZ CONTROLLER ==================================

takeQuiz.controller("takeQuizController", function($interval, $scope, $http, $window) {
  alert("QUIZ CONTROLLER");
  $scope.question_list = [];
  $scope.options = {};
  $scope.timer = "00:00:00";

  $scope.getAllQuestions = function (){
    $http.get('/get-quiz-question').success(function(data, status, headers, config) {
                  $scope.question_list = data.data;
                }).error(function(data, status) { // called asynchronously if an error occurs
                  alert("ERROR");
                });
    }
  $scope.getAllQuestions();

  $scope.submitAnswer = function (){
    alert("SEE CONSOLE");
    $http.get('/submit-quiz', {answers: $scope.options}).success(function(data, status, headers, config) {
                  alert(data.data);
                }).error(function(data, status) { // called asynchronously if an error occurs
                  alert("ERROR");
                });
    console.log($scope.options);
  }

  var hh,mm,ss;
  hh=60;
  mm=60;
  ss=60;

  $interval(function(){
    if(ss==0 && mm!=0){
      mm--;
      ss=60;
    }
    if(mm==0 && hh!=0){
      hh--;
      mm = 60;
    }
    ss--;
    if(hh==0 && mm == 0 && ss == 0){
      alert("finished");
    }
    $scope.timer = hh+":"+mm+":"+ss;
  },1000);

});
