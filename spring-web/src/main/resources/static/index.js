angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $rootScope, $localStorage) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    $scope.loadProducts = function (numberPage) {

        if (isNaN(numberPage)){numberPage = 1;}

        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                number_page: numberPage

            }
        }).then(function (response) {
                $scope.ProductsPage = response.data;
            });
    };

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/app/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/carts/add/' + productId)
        .then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + '/carts/clear')
        .then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.loadCart = function () {
        $http.get(contextPath + '/carts')
            .then(function (response) {
                $scope.Cart = response.data;
            });
    };

    $scope.createOrder = function () {
        $http.post(contextPath + '/orders/add')
            .then(function (response) {
                if (response.status == 200) {
                    $scope.clearCart();
                    alert("заказ оформлен")
                }
            });
    };

    $scope.showCurrentUserInfo = function () {
        $http.get(contextPath + '/profile')
            .then(function successCallback(response) {
                alert('MY NAME IS: ' + response.data.username);
            }, function errorCallback(response) {
                alert('UNAUTHORIZED');
            });
    }

    $scope.loadCart();
    $scope.loadProducts();
});