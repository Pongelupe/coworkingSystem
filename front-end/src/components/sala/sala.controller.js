angular.module('coworkingApp')
    .controller('Sala', ["$rootScope", "$stateParams", "svcSala", function ($rootScope, $stateParams, svcSala) {
        var vm = this;
        $rootScope.mainTitle = "SALA";
        $rootScope.subTitle = "Sala / Nova Sala";

        vm.novoSala = function () {
            vm.sala = {
                tipo: "COWORKING",
                nome: "",
                ramal: null,
                valorHora: null,
                quantidadeEstacoes: null,
                horarioInicial: null,
                horarioFinal: null,
                estaLivre: true
            };
            vm.title = "Cadastrar Nova Sala";
            vm.acao = "Salvar Sala";
        };

        if ($stateParams.sala.length == 0) {
            vm.title = "Cadastrar Nova Sala";
            vm.acao = "Salvar Sala";
            vm.novoSala();
        } else {
            vm.sala = $stateParams.sala;
            vm.sala.horarioInicial = parseTime(vm.sala.horarioInicial);
            vm.sala.horarioFinal = parseTime(vm.sala.horarioFinal);
            vm.title = "Atualizar Dados da Sala: " + vm.sala.nome;
            vm.acao = "Atualizar Sala";
        }

        vm.validarSala = function () {
            if (vm.sala.nome == "") {
                swal({
                    html: 'O <span class="font-weight-bold"> nome </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.sala.horarioInicial == undefined) {
                swal({
                    html: 'O <span class="font-weight-bold"> horário inicial de funcionamento </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.sala.horarioFinal == undefined) {
                swal({
                    html: 'O <span class="font-weight-bold"> horário final de funcionamento </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.sala.valorHora == undefined) {
                swal({
                    html: 'O <span class="font-weight-bold"> valor da hora </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.sala.quantidadeEstacoes == undefined) {
                swal({
                    html: 'A <span class="font-weight-bold"> quantidade de estações </span> não foi informada.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            return true;

        }

        vm.cadastrarSala = function () {
            if (vm.validarSala()) {
                svcSala.cadastrarSala(vm.sala)
                    .then(function (res) {
                        vm.novoSala();
                        swal({
                            text: 'Cadastro realizado com sucesso',
                            type: 'success',
                            showConfirmButton: false,
                            timer: 2000
                        })
                    })
                    .catch(function (err) {
                        alertaErroRequisicao(err);
                    })
            };
        };

        vm.updateSala = function () {
            svcSala.updateSala(vm.sala)
                .then(function (res) {
                    swal({
                        text: "Sala atualizado com sucesso",
                        type: 'success',
                        showConfirmButton: false,
                        timer: 2000
                    })
                    vm.novoSala();
                })
                .catch(function (err) {
                    alertaErroRequisicao(err);
                })
        };


    }]);