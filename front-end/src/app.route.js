(function () {
    'use strict';

    angular.module('coworking-app').config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
        $urlRouterProvider.otherwise("/");

    });
})