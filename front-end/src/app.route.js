
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
                        templateUrl: 'components/consumo/salaConsumo.html',
                        controller: 'Consumo',
                        controllerAs: 'vm'
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
            .state("faturamentos", {
                parent: "app",
                url: "/faturamentos",
                views: {
                    'contentApp@app': {
                        templateUrl: "components/faturamento/faturamentos.html",
                        controller: 'Faturamentos',
                        controllerAs: 'vm'
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
            .state("produtosServicos", {
                parent: "app",
                url: "/produtosServicos",
                views: {
                    'contentApp@app': {
                        templateUrl: "components/produtoServico/produtosServicos.html",
                        controller: 'ProdutosServicos',
                        controllerAs: 'vm'
                    }
                }

            })
            .state("cadastroProdutoServico", {
                parent: "app",
                params: {
                    produtoServico: [],
                },
                url: "/cadastroProdutoServico",
                views: {
                    'contentApp@app': {
                        templateUrl: 'components/produtoServico/cadastroProdutoServico.html',
                        controller: 'ProdutoServico',
                        controllerAs: 'vm'
                    }
                }

            })
            .state("pacotesHoras", {
                parent: "app",
                url: "/pacotesHoras",
                views: {
                    'contentApp@app': {
                        templateUrl: 'components/pacoteHora/pacotesHoras.html',
                        controller: 'PacotesHoras',
                        controllerAs: 'vm'
                    }
                }

            })

            .state("cadastroPacoteHora", {
                parent: "app",
                params: {
                    pacoteHora: [],
                },
                url: "/cadastroPacoteHora",
                views: {
                    'contentApp@app': {
                        templateUrl: 'components/pacoteHora/cadastroPacoteHora.html',
                        controller: 'PacoteHora',
                        controllerAs: 'vm'
                    }
                }

            })
            .state("planos", {
                parent: "app",
                url: "/planos",
                views: {
                    'contentApp@app': {
                        template: "<p> Você está na aba planos <p>"
                    }
                }

            })
    });