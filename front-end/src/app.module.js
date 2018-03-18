'use strict';

angular
    .module('coworkingApp', ["ui.router", "angularMoment"]);


var URL_REQ = "http://localhost:9090/coworking";

var cwsHeaders = {
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
    }
};
