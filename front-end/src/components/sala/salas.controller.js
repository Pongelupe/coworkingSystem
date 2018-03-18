angular.module('coworkingApp')
    .controller('Salas', ["$state", "$stateParams", "svcSala", function ($state, $stateParams, svcSala) {
        var vm = this;

        vm.carregarSalas = function () {
            svcSala.salas()
                .then(function (res) {
                    if (res.data.data != undefined)
                        vm.salas = res.data.data;
                })
        };

        vm.cadastrarSala = function () {
            $state.go('sala');
        };


        vm.updateSala = function (sala) {
            sala.atualizar = true;
            $state.go('sala', { sala: sala });
        };

        vm.deletarSala = function (sala) {
            alertaConfirmarExclusao()
                .then(function (res) {
                    if (res.value) {
                        svcSala.deletarSala(sala)
                            .then(function (res) {
                                vm.carregarSalas();
                                swal({
                                    text: "Sala excluso com sucesso",
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