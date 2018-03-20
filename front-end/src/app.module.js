'use strict';

angular
    .module('coworkingApp', [
        "ui.router",
        "angularMoment",
        "angular-loading-bar",
        "ngAnimate",
        "ngDialog"
    ]);

angular
    .module('coworkingApp')
    .config(['cfpLoadingBarProvider', function (cfpLoadingBarProvider) {
        cfpLoadingBarProvider.includeSpinner = false;
    }])

var URL_REQ = "http://localhost:9000/coworking/";

var cwsHeaders = {
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
    }
};
