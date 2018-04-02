(function() {
    'use strict';  
    var data;
    var myApp = angular.module('Tidder',['ui.bootstrap']);
    
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