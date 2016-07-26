angular.module('yourWeather', ['ngRoute','ngResource', 'ngMessages'])

    .config( ['$routeProvider', function($routeProvider) {
        var sourceDir = '/resources/templates/';
        $routeProvider
            .when('/search', {
                controller:'weatherCtrl',
                templateUrl: sourceDir + 'search.html'
            })
            .when('/userstatistic', {
                controller:'userInfoCtrl',
                templateUrl: sourceDir + 'userstatistic.html'
            })
            .otherwise({
                redirectTo: '/search'
            });
    }])

    .factory("weatherFactory", function ($http) {
        var weatherFactory = {};
        var baseUrl = '/weather';

        weatherFactory.getWeatherByLocation = function(location) {
            return $http.get(baseUrl + '/location', {
                params: {location: location}
            });
        };

        weatherFactory.getWeatherByCoordinates = function(latitude, longitude) {
            return $http.get(baseUrl + '/coordinate', {
                params: {latitude: latitude, longitude: longitude}
            });
        };

        return weatherFactory;
    })

    .factory("userInfoFactory", function ($http) {
        var userInfoFactory = {};
        var baseUrl = '/user';

        userInfoFactory.getUserStatistic = function() {
            return $http.get(baseUrl + '/statistic');
        };

        userInfoFactory.createUser = function(user) {
            return $http.post(baseUrl + '/create', user);
        };

        return userInfoFactory;
    })

    .controller('weatherCtrl', ['$scope','$window', 'weatherFactory', function ($scope, $window, weatherFactory) {
        $scope.showWeatherForm = false;
        $scope.waitMessage = false;

        $scope.getWeatherByLocation = function () {
            var location = $scope.searchLocationName;
            $scope.waitMessage = true;

            if (validateLocationName(location)) {
                weatherFactory.getWeatherByLocation(location)
                    .then(function (response) {
                        $scope.weather = response.data;
                        $scope.searchSource = location;
                        $scope.showWeatherForm = true;
                    }, function (error) {
                        $window.alert('Location not found');
                    });
            }
            $scope.waitMessage = false;
        };

        function validateLocationName(locationName)  {
            if (locationName && locationName.length > 0) {
                return true;
            }
            $window.alert("Please fill location name correctly");
            return false;
        }

        $scope.getWeatherByCoordinates = function () {
            var latitude = $scope.searchLatitude;
            var longitude = $scope.searchLongitude;
            $scope.waitMessage = true;

            if (validateCoordinate(latitude) && validateCoordinate(longitude)) {
                weatherFactory.getWeatherByCoordinates(latitude, longitude)
                    .then(function (response) {
                        $scope.weather = response.data;
                        $scope.searchSource = latitude + ' / ' + longitude;
                        $scope.showWeatherForm = true;
                    }, function (error) {
                        $window.alert('Coordinates not found');
                    });
            }
            $scope.waitMessage = false;
        };

        function validateCoordinate(coordinate)  {
            var num = Number(coordinate);
            if (num && !isNaN(num) && num > 0 && num < 90) {
                return true;
            }
            $window.alert("Please fill coordinates correctly");
            return false;
        }
    }])

    .controller("userInfoCtrl", ['$scope','$rootScope','$window', 'userInfoFactory', function ($scope, $rootScope, $window, userInfoFactory) {
        $rootScope.createUser = function () {
            var userName = $scope.registrationUserName;
            var password = $scope.registrationPass;
            var confirmPassword = $scope.registrationConfirmPass;

            if (validateRegisteredUser(password, confirmPassword)) {
                var user = {
                    name: userName,
                    password: password
                };
                userInfoFactory.createUser(user)
                    .then(function (response) {
                        $window.alert("User registered. Congratulations!");
                    }, function (error) {
                        $window.alert(error.data.message);
                    });
            }
        };

        function validateRegisteredUser(password, confirmPassword) {
            if (Object.keys($scope.registrationForm.$error).length == 0) {
                if (password && confirmPassword && password === confirmPassword) {
                    return true;
                }
                $window.alert("Passwords not confirm")
            }
            return false;
        }

        $rootScope.getUserStatistic = function () {
            userInfoFactory.getUserStatistic()
                .then(function (response) {
                    $rootScope.userStatistic = response.data;
                }, function (error) {
                    $window.alert('Coordinates not found');
                });
        }
    }]);


