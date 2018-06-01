angular.module('coworkingApp')
    .controller('PacotesHoras', ["$rootScope", "$state", "$stateParams", "svcPacoteHora", function ($rootScope, $state, $stateParams, svcPacoteHora) {
        var vm = this;
        $rootScope.mainTitle = "PACOTE DE HORAS";
        $rootScope.subTitle = "Config. Gerais / Pacotes de Horas";

        vm.carregarPacotesHoras = function () {
            svcPacoteHora.pacotesHoras()
                .then(function (res) {
                    if (res.data.data != undefined){
                        vm.pacotesHoras = res.data.data;
                        vm.pacotesHoras.forEach(function(pacote){
                            pacote.sala.horarioInicial = parseTime(pacote.sala.horarioInicial);                            
                            pacote.sala.horarioFinal = parseTime(pacote.sala.horarioFinal);
                        });
                    }
                       
                })
        };

        vm.cadastrarPacoteHora = function () {
            $state.go('cadastroPacoteHora');
        };

        vm.updatePacoteHora= function (pacoteHora) {
            pacoteHora.atualizar = true;
            $state.go('cadastroPacoteHora', { pacoteHora: pacoteHora });
        };

        vm.deletarPacoteHora = function (pacoteHora) {
            alertaConfirmarExclusao()
                .then(function (res) {
                    if (res.value) {
                        svcPacoteHora.deletarPacoteHora(pacoteHora.id)
                            .then(function (res) {
                                vm.carregarPacotesHoras();
                                swal({
                                    text: "Pacote de hora excluso com sucesso",
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