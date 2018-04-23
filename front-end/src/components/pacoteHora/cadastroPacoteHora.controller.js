angular.module('coworkingApp')
    .controller('PacoteHora', ["$rootScope", "$state", "$stateParams", "svcPacoteHora", "svcSala", function ($rootScope, $state, $stateParams, svcPacoteHora, svcSala) {
        var vm = this;
        $rootScope.mainTitle = "PACOTE HORA";
        $rootScope.subTitle = "Config. Gerais / Pacotes de Horas / Cadastros Pacote de Hora";

        vm.novoPacoteHora = function () {
            vm.pacoteHora = {
                nome: "",
                valor: null,
                ehProduto: "true"
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
            vm.pacoteHora.ehProduto = vm.pacoteHora.ehProduto.toString();
            vm.title = "Atualizar Dados do Pacote de Hora: " + vm.pacoteHora.nome;
            vm.acao = "Atualizar Pacote de Hora";
        }

        vm.validarPacoteHora = function () {
            if (vm.pacoteHora.nome == "") {
                swal({
                    html: 'O <span class="font-weight-bold"> nome </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.pacoteHora.valorPacote == undefined) {
                swal({
                    html: 'O <span class="font-weight-bold"> valor do pacote</span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            return true;
        }

        vm.cadastrarPacoteHora = function () {
            if (vm.validarPacoteHora()) {
                debugger;
                
                vm.pacoteHora.sala = {};
                vm.pacoteHora.sala.id = vm.salas.filter(function (obj) {
                    return (obj.id == vm.pacoteHoras.idSala)
                })[0].id;
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
                if (res.data.data != undefined)
                    vm.salas = res.data.data;
            })


    }]);