var app = angular.module("bookingApp", []);

app.controller("dateCtrl", function($scope, $http) {
    $http.get('http://localhost:8080/api/v2/reservations').then(function(response) {
        $scope.reservations = response.data;
    });
});

app.controller("customerCtrl", function($scope, $http) {
    $scope.getCustomers = function(date) {
        $http.get('http://localhost:8080/api/v2/customers/search?date=' + date).then(function(response) {
            $scope.customers = response.data;
        });
    }
});
