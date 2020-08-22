var app = angular.module("bookingApp", []);

app.controller("dateCtrl", function($scope, $http) {
    $scope.addReservationDate = function(reservationDate) {
        var body = {"reservationDate": reservationDate};
        $http.post('http://localhost:8080/api/v2/reservations/', JSON.stringify(body)).then(function(response) {
            $http.get('http://localhost:8080/api/v2/reservations').then(function(response) {
                $scope.reservations = response.data;
            });
        });
    };
    $http.get('http://localhost:8080/api/v2/reservations').then(function(response) {
        $scope.reservations = response.data;
    });
});

app.controller("customerCtrl", function($scope, $http) {
    $scope.getCustomers = function(date) {
        $http.get('http://localhost:8080/api/v2/customers/search?date=' + date).then(function(response) {
            $scope.customers = response.data;
            $scope.resDate = date;
        });
    }
    $scope.addCustomerOnDate = function(name, surname, email, date) {
        var body = {"name": name, "surname": surname, "email": email};
        $http.post('http://localhost:8080/api/v2/customers/addOnDate?date=' + date, JSON.stringify(body)).then(function(response) {
            $http.get('http://localhost:8080/api/v2/customers/search?date=' + date).then(function(response) {
                $scope.customers = response.data;
                $scope.resDate = date;
            });
        });
    };
});
