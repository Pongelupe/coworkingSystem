angular.module('coworkingApp')
    .controller('Consumo', ["$rootScope", "$state", "$stateParams", "svcConsumo", "svcSala", function ($rootScope, $state, $stateParams, svcConsumo, svcSala) {
        var vm = this;

        vm.carregarSalas = function () {
            svcSala.salas()
                .then(function (res) {
                    if (res.data.data != undefined)
                        vm.salas = res.data.data;
                })
        };

        vm.carregarConsumos = function () {
            svcConsumo.consumos()
                .then(function (res) {
                    if (res.data.data != undefined)
                        vm.consumos = res.data.data;
                })
        };

        vm.carregarSalas();

        vm.cadastrarConsumo = function () {
            $state.go('consumo');
        };


        vm.updateConsumo = function (consumo) {
            consumo.atualizar = true;
            $state.go('consulo', { consumo: consumo });
        };

        vm.deletarConsumo = function (consumo) {
            alertaConfirmarExclusao()
                .then(function (res) {
                    if (res.value) {
                        svcConsumo.deletarConsumo(consumo)
                            .then(function (res) {
                                vm.carregarConsumos();
                                swal({
                                    text: "Consumo excluso com sucesso",
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