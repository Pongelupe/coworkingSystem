angular.module('coworkingApp')
    .controller('PacoteHora', ["$rootScope", "$state", "$stateParams", "svcPacoteHora", "svcSala", function ($rootScope, $state, $stateParams, svcPacoteHora, svcSala) {
        var vm = this;
        $rootScope.mainTitle = "PACOTE HORA";
        $rootScope.subTitle = "Config. Gerais / Pacotes de Horas / Cadastros Pacote de Hora";

        vm.novoPacoteHora = function () {
            vm.pacoteHora = {
                nome: "",
                valor: null,
                sala: null
            };
            vm.title = "Cadastrar Novo Pacote Hora";
            vm.acao = "Salvar Novo Pacote Hora";
        };

        if ($stateParams.pacoteHora.length == 0) {
            vm.title = "Cadastrar Novo Pacote Hora";
            vm.acao = "Salvar Novo Pacote Hora";
            vm.novoPacoteHora();
        } else {
            vm.pacoteHora = $stateParams.pacoteHora;
            vm.title = "Atualizar Dados do Pacote de Hora: " + vm.pacoteHora.nome;
            vm.acao = "Atualizar Pacote de Hora";
        }

        vm.validarPacoteHora = function () {
            if (campoNaoInformado("nome do pacote", vm.pacoteHora.nome))
                return;

            if (campoNaoInformado("valor do pacote", vm.pacoteHora.valor))
                return;

            if (campoNaoInformado("sala", vm.pacoteHora.sala))
                return;

            return true;
        }

        vm.cadastrarPacoteHora = function () {
            if (vm.validarPacoteHora()) {
                svcPacoteHora.cadastrarPacoteHora(vm.pacoteHora)
                    .then(function (res) {
                        vm.novoPacoteHora();
                        swal({
                            text: 'Cadastro realizado com sucesso',
                            type: 'success',
                            showConfirmButton: false,
                            timer: 2000
                        })
                        vm.retornarListaPacotesHoras();
                    })
                    .catch(function (err) {
                        alertaErroRequisicao(err);
                    })
            };
        };

        vm.updatePacoteHora = function () {
            svcPacoteHora.updatePacoteHora(vm.pacoteHora)
                .then(function (res) {
                    swal({
                        text: "Pacote hora atualizado com sucesso",
                        type: 'success',
                        showConfirmButton: false,
                        timer: 2000
                    })
                    vm.novoPacoteHora();
                    vm.retornarListaPacotesHoras();
                })
                .catch(function (err) {
                    alertaErroRequisicao(err);
                })
        };

        vm.retornarListaPacotesHoras = function () {
            $state.go('pacotesHoras');
        }

        svcSala.salas()
            .then(function (res) {
                if (res.data.data != undefined) {
                    vm.salas = res.data.data;
                    vm.salas.forEach(function (sala) {
                        sala.horarioInicial = parseTime(sala.horarioInicial);
                        sala.horarioFinal = parseTime(sala.horarioFinal);
                    });

                }
            })


    }]);