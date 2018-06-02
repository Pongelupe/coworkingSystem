angular.module('coworkingApp')
    .controller('Planos', ["$rootScope", "$state", "$stateParams", "svcPlano", function ($rootScope, $state, $stateParams, svcPlano) {
        var vm = this;
        $rootScope.mainTitle = "PLANO";
        $rootScope.subTitle = "Config. Gerais / Listar Planos";

        vm.carregarPlanos = function () {
            svcPlano.planos()
                .then(function (res) {
                    if (res.data.data != undefined)
                        vm.planos = res.data.data;
                })
        };

        vm.cadastrarPlano = function () {
            $state.go('cadastroPlano');
        };

        vm.updatePlano= function (plano) {
            plano.atualizar = true;
            $state.go('cadastroPlano', { plano: plano });
        };

        vm.deletarPlano = function (plano) {
            alertaConfirmarExclusao()
                .then(function (res) {
                    if (res.value) {
                        svcPlano.deletarPlano(plano.id)
                            .then(function (res) {
                                vm.carregarPlanos();
                                swal({
                                    text: "Plano excluso com sucesso",
                                    type: 'success',
                                    showConfirmButton: false,
                                    timer: 2000
                                })
                            })
                            .catch(function (err) {
                                alertaErroRequisicao(err);
                            })
                    } 
                })
        };


    }]);