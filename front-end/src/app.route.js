
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
                        controllerAs: 'vm'

                    },
                    'menu@app': {
                        templateUrl: 'components/menu/menu.html',
                        controller: 'Main',
                        controllerAs: 'vm'
                    },
                    'contentApp@app': {
                        template: "<p> Você está na página inicial"
                    }
                }

            })
            .state("clientes", {
                parent: "app",
                url: "/clientes",
                views: {
                    'contentApp@app': {
                        templateUrl: 'components/cliente/clientes.html',
                        controller: 'Clientes',
                        controllerAs: 'vm'
                    }
                }

            })
            .state("cliente", {
                parent: "app",
                params: {
                    cliente: [],
                },
                url: "/cliente",
                views: {
                    'contentApp@app': {
                        templateUrl: 'components/cliente/cliente.html',
                        controller: 'Cliente',
                        controllerAs: 'vm'
                    }
                }

            })
            .state("salas", {
                parent: "app",
                url: "/salas",
                views: {
                    'contentApp@app': {
                        templateUrl: 'components/sala/salas.html',
                        controller: 'Salas',
                        controllerAs: 'vm'
                    }
                }

            })
            .state("sala", {
                parent: "app",
                params: {
                    sala: [],
                },
                url: "/sala",
                views: {
                    'contentApp@app': {
                        templateUrl: 'components/sala/sala.html',
                        controller: 'Sala',
                        controllerAs: 'vm'
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