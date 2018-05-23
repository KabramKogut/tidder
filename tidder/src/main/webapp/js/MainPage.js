(function() {
	'use strict';
	var data;
	var myApp = angular.module('Tidder', [ 'ui.bootstrap', 'ngAnimate','blockUI','ngAnimate']);

	myApp.controller(
					'PaginationMyController',
					function($scope, $http, $q, blockUI) {
						$http.defaults.headers.post["Content-Type"] = "application/json";
						var vm = this;
					
						var config = {
							params : data,
							headers : {
								'Accept' : 'application/json'
							}
						};
						$scope.init = function() {
							$scope.commentVisibleOnInit = null;
							$scope.commentVisible = false;
							$scope.commentAdded = false;
							$scope.commentByPostId = {};
							$scope.comment = {};
							$scope.comment.text = "";
							$scope.myData = "0,1";
							$scope.dataPerPage = [];
							$scope.ItemsOnPageAmount = 9;
							$scope.currentPage = 1;
							$scope.allItemsAmount = 300;
							$scope.getPostsAmount();
							$scope.visibleComment = false;
							getPostsPerPage(2);
							
						};
						$scope.$watch("currentPage", function() {
							setCurrentPage($scope.currentPage);
						  });

						function setCurrentPage(page) {
							  blockUI.start();  
							getPostsPerPage(page);
						}

						function getPostsPerPage(page) {
							var config = {
								params : data,
								headers : {
									'Accept' : 'application/json'
								}
							};
							
							$http.get('http://localhost:8080/tidder/webapi/post/page/'+ page, config)
									.then( function(data) {
												$scope.dataPerPageTemp = data.data;
												angular.forEach($scope.dataPerPageTemp,
														function(obj) {
													if(obj.id.length !==0) {
													$scope.dataPerPage.push(obj);
													console.log(obj);
													}
												});
												angular.forEach($scope.dataPerPage, function(obj) {
													obj.commentData = $scope.getCommentById(obj.id)
												});
												console.log($scope.dataPerPage);
												 blockUI.stop();
											}, function(res) {
												alert('failure' + res);
											});
						}

						$scope.likePost = function(postId) {
							blockUI.start();
							$http({
								method : "post",
								url : "http://localhost:8080/tidder/webapi/post/like/post?id=" + postId ,
								data : data
							})
							.then(
									function(response) {
										blockUI.stop();
										
									}, function(response) {
										alert('failure' + response);
									})
						}
						function getAllPosts() {
							var config = {
								params : data,
								headers : {
									'Accept' : 'application/json'
								}
							};

							$http
									.get('http://localhost:8080/tidder/webapi/post/all',
											config)
									.then(
											function(data) {
												$scope.mydata = data.data;
												angular
														.forEach(
																$scope.mydata,
																function(obj) {
																	obj.commentsData = $scope
																			.getCommentById(obj.id);
																});

											}, function(res) {
												alert('failure' + res);
											});

						}
						;
						/* $scope.test = $scope.getCommentById(12); */

						$scope.getPostsAmount = function() {
							$http({		method : "GET",
										url : "http://localhost:8080/tidder/webapi/post/all/amount",
										data : data
									}).then(function(response) {
								$scope.postsTotal = response.data.ammount;
							}, function(response) {
									alert('failure ' + response);
							});
						}

						$scope.sendComment = function(postId, text) {
							var data = JSON.stringify({
								"text" : text
							})
							$http({
										method : "post",
										url : "http://localhost:8080/tidder/webapi/post/"
												+ postId + "/comment",
										data : data
									})
									.then(
											function(response) {
												console
														.log($scope.comment.text);
												$scope.commentByPostId[postId].comments
														.push(response.data.comments[response.data.comments.length - 1])
												$scope.comment.text = "";
												console
														.log($scope.comment.text);
											}, function(response) {

											})
						}

						$scope.getCommentById = function(postId) {

							var config = {
								params : data,
								headers : {
									'Accept' : 'application/json'
								}
							};
							var resultset = $http.get(
											'http://localhost:8080/tidder/webapi/post/'
													+ postId).then(
											function(result) {
												if ($scope.dataPerPage[postId] == null
														|| $scope.mydata[postId] === 'undefined') {
													
												}
												$scope.commentByPostId[postId] = result.data;
												
												return result.data;

											}, function(res) {
												alert('failure' + res);
											});
							return resultset;
						}
						
						

					});
	
	/*jQuery start*/
/*	$('btn').click(function() {
		$this = $(this);
		if($this.css("transform") == "none") {
			$this.css("transform", "rotate(45deg)");
		} else {
			$this.css("transform", "");
		}
	})*/
	
}());