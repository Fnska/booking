var app = angular.module("bookingApp", []);

app.service('ReservationService', function($http) {
    this.getAllReservations = function() {
        return $http({
            method : 'GET',
            url : 'api/v2/reservations'
        });
    }

    this.addReservation = function (reservationDate) {
        return $http({
            method : 'POST',
            url : 'api/v2/reservations',
            data : {
                reservationDate: reservationDate
            }
        });
    }
});

app.service('CustomerService', function($http) {
    this.getCustomersByDate = function (date) {
        return $http({
            method : 'GET',
            url : 'api/v2/customers/search?date=' + date
        });
    }

    this.addCustomerOnDate = function (name, surname, email, date) {
        return $http({
            method : 'POST',
            url : 'api/v2/customers/addOnDate?date=' + date,
            data : {
                name: name,
                surname: surname,
                email: email
            }
        });
    }

    this.deleteCustomerFromDate = function (email, date) {
        return $http({
            method : 'PUT',
            url : 'api/v2/customers/deleteFromDate?date=' + date,
            data : {
                email: email
            }
        });
    }
});

app.controller("dateCtrl", function($scope, ReservationService) {
    $scope.addReservationDate = function(reservationDate) {
        ReservationService.addReservation(reservationDate).then(function(response) {
            ReservationService.getAllReservations().then(function(response) {
                $scope.reservations = response.data;
            });
        });
    };
    ReservationService.getAllReservations().then(function(response) {
        $scope.reservations = response.data;
    });
});

app.controller("customerCtrl", function($scope, $http, CustomerService) {
    $scope.getCustomersByDate = function(date) {
        CustomerService.getCustomersByDate(date).then(function(response) {
            $scope.customers = response.data;
            $scope.resDate = date;
        });
    }

    $scope.addCustomerOnDate = function(name, surname, email, date) {
        CustomerService.addCustomerOnDate(name, surname, email, date).then(function(response) {
            CustomerService.getCustomersByDate(date).then(function(response) {
                $scope.customers = response.data;
                $scope.resDate = date;
            });
        });
    }

    $scope.deleteCustomerFromDate = function(email, date) {
        CustomerService.deleteCustomerFromDate(email, date).then(function(response) {
            CustomerService.getCustomersByDate(date).then(function(response) {
                $scope.customers = response.data;
                $scope.resDate = date;
            });
        });
    }
});
