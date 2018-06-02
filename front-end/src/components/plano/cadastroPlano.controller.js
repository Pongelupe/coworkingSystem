angular.module('coworkingApp')
    .controller('Plano', function ($rootScope, $state, $stateParams, svcPlano, svcSala, svcProdutoServico) {
        var vm = this;
        $rootScope.mainTitle = "PLANO";
        $rootScope.subTitle = "Config. Gerais / Listar Planos / Cadastros Plano";

        vm.novoPlano = function () {
            vm.plano = {
                nome: null,
                descricao: null,
                valor: null,
                produtosServicos: [],
                salas: []
            };
            vm.title = "Cadastrar Novo Plano";
            vm.acao = "Salvar Plano";
        };

        if ($stateParams.plano.length == 0) {
            vm.title = "Cadastrar Novo Plano";
            vm.acao = "Salvar Plano";
            vm.novoPlano();
        } else {
            vm.plano = $stateParams.plano;
            vm.title = "Atualizar Dados do Plano: " + vm.plano.nome;
            vm.acao = "Atualizar Plano";
        }

        vm.validarPlano = function () {
            if (campoNaoInformado("nome do pacote", vm.plano.nome))
                return;

            if (campoNaoInformado("nome do pacote", vm.plano.valor))
                return;
            return true;
        }

        vm.cadastrarPlano = function () {
            if (vm.validarPlano()) {

                svcPlano.cadastrarPlano(vm.plano)
                    .then(function (res) {
                        vm.novoPlano();
                        swal({
                            text: 'Cadastro realizado com sucesso',
                            type: 'success',
                            showConfirmButton: false,
                            timer: 2000
                        })
                        vm.retornarListaPlanos();
                    })
                    .catch(function (err) {
                        alertaErroRequisicao(err);
                    })
            };
        };

        vm.updatePlano = function () {
            svcPlano.updatePlano(vm.plano)
                .then(function (res) {
                    swal({
                        text: "Plano atualizado com sucesso",
                        type: 'success',
                        showConfirmButton: false,
                        timer: 2000
                    })
                    vm.novoPlano();
                    vm.retornarListaPlanos();
                })
                .catch(function (err) {
                    alertaErroRequisicao(err);
                })
        };

        vm.retornarListaPlanos = function () {
            $state.go('planos');
        };

        vm.adicionarSalaPlano = function () {
            vm.plano.salas.splice(0, 0, {});
        };

        vm.deletarSalaPlano = function (indice) {
            alertaConfirmarExclusao("a sala do plano")
                .then(function (res) {
                    if (res.value) {
                        vm.plano.salas.splice(indice, 1)
                        $(".form-control").trigger("change");
                    }
                })
        };

        vm.adicionarProdutoServicoPlano = function () {
            vm.plano.produtosServicos.splice(0, 0, {});
        };

        vm.deletarProdutoServicoPlano = function (indice) {
            alertaConfirmarExclusao("o produto/serviço do plano")
                .then(function (res) {
                    if (res.value) {
                        vm.plano.produtosServicos.splice(indice, 1)
                        $(".form-control").trigger("change");
                    }
                })
        };

        svcSala.salas()
            .then(function (res) {
                if (res.data.data != undefined) {
                    vm.salas = res.data.data;
                    vm.salas.forEach(function (sala) {
                        sala.horarioInicial = parseTime(sala.horarioInicial);
                        sala.horarioFinal = parseTime(sala.horarioFinal);
                    });

                }
            });

        svcProdutoServico.produtosServicos()
            .then(function (res) {
                if (res.data.data != undefined)
                    vm.produtosServicos = res.data.data;
            })

    });