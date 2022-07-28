angular.module('market-front').controller('orderController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'api/v1/orders')
            .then(function (response) {
                $scope.myOrders = response.data;
            });
    }
    $scope.loadOrders();
});