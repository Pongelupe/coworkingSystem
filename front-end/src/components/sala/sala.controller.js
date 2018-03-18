angular.module('coworkingApp')
    .controller('Sala', ["$stateParams", "svcSala", function ($stateParams, svcSala) {
        var vm = this;

        vm.novoSala = function () {
            vm.sala = {
                tipoSala: "COWORKING",
                nome: "",
                ramal: null,
                valorHora: null,
                quantidadeEstacoes: null,
                horarioInicial: null,
                horarioFinal: null
            };
        };

        if ($stateParams.sala.length == 0) {
            vm.title = "Cadastrar Nova Sala";
            vm.acao = "Salvar Sala";
            vm.novoSala();
        } else {
            vm.sala = $stateParams.sala;
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
                if (vm.sala.dtNascimento != undefined)
                    vm.dtNascimento = moment(vm.sala.dtNascimento).format();

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
                    vm.carregarSalas();
                    swal({
                        text: "Sala atualizado com sucesso",
                        type: 'success',
                        showConfirmButton: false,
                        timer: 2000
                    })
                })
                .catch(function (err) {
                    alertaErroRequisicao(err);
                })
        };


    }]);