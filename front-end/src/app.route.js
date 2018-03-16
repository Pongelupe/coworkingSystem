
'use strict';

angular
    .module('coworkingApp').config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
    $urlRouterProvider.otherwise("/app");

    $stateProvider
        .state("app", {
            url: "/app",
            views: {
                'app@': {
                    templateUrl: 'components/home/home.html',
                    controller: 'Main',

                },
                'menu@app':{
                    templateUrl: 'components/menu/menu.html'
                },
                'contentApp@app': {
                    template: "<p> Você está na página inicial"
                }
            }

        })
        .state("cliente", {
            parent: "app",
            url: "/cliente",
            views: {
                'contentApp@app': {
                    template: "<p> Você está na aba cliente <p>"
                }
            }

        })
        .state("sala", {
            parent: "app",
            url: "/sala",
            views: {
                'contentApp@app': {
                    template: "<p> Você está na aba sala <p>"
                }
            }

        })
        .state("faturamento", {
            parent: "app",
            url: "/faturamento",
            views: {
                'contentApp@app': {
                    template: "<p> Você está na aba faturamento <p>"
                }
            }

        })
        .state("pagamento", {
            parent: "app",
            url: "/pagamento",
            views: {
                'contentApp@app': {
                    template: "<p> Você está na aba pagamento <p>"
                }
            }

        })

});