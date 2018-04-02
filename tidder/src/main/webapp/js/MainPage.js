(function() {
    'use strict';  
    var data;
    var myApp = angular.module('Tidder',['ui.bootstrap']);

    myApp.controller('MainPageController', ['$scope','$http', function($scope,$http) {
      $scope.double = function(value) { return value * 2; };
      
        function GetAllPosts () {
          $http({method: 'GET', url: 'http://localhost:8080/tidder/GetAllPosts.json'})
          .then( function(response) {
        	  
        	  $scope.posts = response.data;
          
          }, function errorCallback(response) {
        	  
        	  	alert('ERROR !! ' + response.status);
        		})
          
        }
 
        $scope.mydata='null';
        var config = {
        		 params: data,
        		 headers : {'Accept' : 'application/json'}
        		};
 
        $http.get('http://localhost:8080/tidder/webapi/post/all', config).then(function(data) {
        	
        	console.log(data);
        	$scope.mydata=data.data;
        	
        }, function() {
        	alert('failure');
        });
          GetAllPosts();
        
    
    }]);
    
    myApp.controller('PaginationMyController', function($scope,$http) {
        var config = {
       		 params: data,
       		 headers : {'Accept' : 'application/json'}
       		};
        $scope.myData="";
        $scope.ItemsOnPageAmount = 5;
        $scope.currentPage=1;
        getAllPosts();
        
        function setCurrentPage(page) {
        	$scope.currentPage = page;
        	alert('start' + currentPage-1*itemsOnPageAmount + ' end ' + currentPage*itemsOnPageAmount);
        }
        
        function getAllPosts() {
            $scope.mydata='null';
            var config = {
            		 params: data,
            		 headers : {'Accept' : 'application/json'}
            		};
     
            $http.get('http://localhost:8080/tidder/webapi/post/all', config).then(function(data) {
            	
            	console.log(data);
            	$scope.mydata=data.data;
            	
            }, function() {
            	alert('failure');
            });
        }
        
    });
    
    
}());