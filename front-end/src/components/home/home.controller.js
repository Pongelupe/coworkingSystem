angular.module('coworkingApp')
    .controller('Main', ["$rootScope", function ($rootScope) {
        var vm = this;
        $rootScope.mainTitle = "HOME";
        $rootScope.subTitle = "Página Inicial";

        vm.atualizarTitle = function () {
            $rootScope.mainTitle = "HOME";
            $rootScope.subTitle = "Página Inicial";
        }
    }]);