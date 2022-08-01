angular.module('market-front').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/';

    $scope.loadCart = function () {
        $http({
            url: contextPath + 'cart/api/v1/cart/' + $localStorage.springWebGuestCartId,
            method: 'GET'
        }).then(function (response) {
            $localStorage.cartStorage = response.data;
            $scope.cart = $localStorage.cartStorage;
        });
    };

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.clearCart = function () {
        $http.get(contextPath + 'cart/api/v1/cart/' + $localStorage.springWebGuestCartId + '/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.checkOut = function () {
        $http({
            url: contextPath + 'core/api/v1/orders',
            method: 'POST',
            params: {
                address: $scope.orderDetails.address,
                phone: $scope.orderDetails.phone
            },
             data: $localStorage.cartStorage
        })
            .then(function (response) {
            $scope.clearCart();
            $scope.loadCart();
            $scope.orderDetails = null
        });
    };

    $scope.loadCart();
});