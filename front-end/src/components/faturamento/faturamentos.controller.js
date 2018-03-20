angular.module('coworkingApp')
    .controller('Faturamentos', ["$rootScope", "$state", "$stateParams", "svcFaturamento", function ($rootScope, $state, $stateParams, svcFaturamento) {
        var vm = this;
        $rootScope.mainTitle = "COBRANÇA";
        $rootScope.subTitle = "Cobrança / Faturamentos";

        vm.carregarFaturamentos = function () {
            svcFaturamento.faturamentos()
                .then(function (res) {
                    if (res.data.data != undefined)
                        vm.faturamentos = res.data.data;
                })
        };

        vm.cadastrarFaturamento = function () {
            $state.go('faturamento');
        };


        vm.updateFaturamento = function (faturamento) {
            faturamento.atualizar = true;
            $state.go('faturamento', { faturamento: faturamento });
        };

        vm.deletarFaturamento = function (faturamento) {
            alertaConfirmarExclusao()
                .then(function (res) {
                    if (res.value) {
                        svcFaturamento.deletarFaturamento(faturamento)
                            .then(function (res) {
                                vm.carregarFaturamentos();
                                swal({
                                    text: "Faturamento excluso com sucesso",
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