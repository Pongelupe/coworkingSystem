
'use strict';

angular
    .module('coworkingApp').config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
    $urlRouterProvider.otherwise("/home");

    $stateProvider
        .state("home", {
            url: "/home",
            views: {
                'app@': {
                    templateUrl: 'components/home/home.html',
                    controller: 'Main',

                },
                'contentApp@home': {
                    template: "<p> Você está na HOME"
                }
            }

        })

});