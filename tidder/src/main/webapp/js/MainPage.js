(function() {
    'use strict';  
    var data;
    var myApp = angular.module('Tidder',['ui.bootstrap','ngAnimate']);
    
    myApp.controller('PaginationMyController', function($scope,$http,$q) {
        
    	var vm = this;
 
    	var config = {
       		 params: data,
       		 headers : {'Accept' : 'application/json'}
       		};
        $scope.init = function() {
        	$scope.commentVisible = false;
      
        	$scope.commentByPostId ={};
        	$scope.myData="0,1";
            $scope.ItemsOnPageAmount = 5;
            $scope.currentPage=1;
      
            $scope.visibleComment = false;
            getAllPosts();
        };
        

        
        
        function setCurrentPage(page) {
        	$scope.currentPage = page;
        }
        
        function getAllPosts() {

            var config = {
            		 params: data,
            		 headers : {'Accept' : 'application/json'}
            		};
     
            $http.get('http://localhost:8080/tidder/webapi/post/all', config).then(function(data) {    	
            	$scope.mydata=data.data;
      
            		angular.forEach($scope.mydata, function(obj) {
            			
            		
            			obj.commentsData = $scope.getCommentById(obj.id);
            			/*vm.mydata[obj.id] = $scope.getCommentById(obj.id);*/
            		});
            			
            	/*	console.log($scope.mydata)*/
            	
            }, function(res) {
            	alert('failure' + res);
            });
   
        };
     /*  $scope.test  = $scope.getCommentById(12);*/       
        
        $scope.getCommentById = function(postId) {
        	
            var config = {
            		 params: data,
            		 headers : {'Accept' : 'application/json'}
            		};
     
           var resultset=  $http.get('http://localhost:8080/tidder/webapi/post/'+postId)
            .then(function(result) {
            	if ($scope.mydata[postId] == null || $scope.mydata[postId] ==='undefined') {
            		$scope.mydata[postId] = {};
            	}
            	$scope.commentByPostId[postId] = result.data;
            	return result.data;
            	
            }, function(res) {
            	alert('failure' + res);
            });
            return resultset;
        }
        
        
    });
    
    
}());