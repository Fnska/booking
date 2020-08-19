var app = angular.module("bookingApp", []);

app.controller("customersCtrl", function($scope, $http) {
    $scope.name = null;
    $scope.surname = null;
    $scope.email = null;
    $scope.date = null;

    $scope.submit = function(name, surname, email, date) {
        var body = {name: name, surname: surname, email: email, date: date};
        $http.post('http://localhost:8080/api/v1/reservations', JSON.stringify(body)).then(function(response) {
            $http.get('http://localhost:8080/api/v1/reservations').then(function(response) {
                $scope.reservations = response.data;
            });
        });
    };

    $http.get('http://localhost:8080/api/v1/reservations').then(function(response) {
        $scope.reservations = response.data;
    });

});