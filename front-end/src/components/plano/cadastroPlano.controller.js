angular.module('coworkingApp')
    .controller('Plano', ["$rootScope", "$state", "$stateParams", "svcPlano", function ($rootScope, $state, $stateParams, svcPlano) {
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
        }


    }]);