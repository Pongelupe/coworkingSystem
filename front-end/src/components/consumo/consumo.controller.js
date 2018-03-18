angular.module('coworkingApp')
    .controller('Consumo', ["$rootScope", "$stateParams", "svcConsumo", function ($rootScope, $stateParams, svcConsumo) {
        var vm = this;
        $rootScope.mainTitle = "CONSUMO";
        $rootScope.subTitle = "Consumo / Novo Consumo";

        vm.novoConsumo = function () {
            vm.consumo = {
                tipoConsumo: "AVULSO",
                dataInicial: new Date(),
                dataFinal: new Date(),
                quantidade: null,
                faturado: false,
                cliente: {},
                horarioInicial: null,
                horarioFinal: null
            };
        };

        if ($stateParams.consumo.length == 0) {
            vm.title = "Cadastrar Nova Consumo";
            vm.acao = "Salvar Consumo";
            vm.novoConsumo();
        } else {
            vm.consumo = $stateParams.consumo;
            vm.title = "Atualizar Dados da Consumo: " + vm.consumo.nome;
            vm.acao = "Atualizar Consumo";
        }

        vm.validarConsumo = function () {
            if (vm.consumo.nome == "") {
                swal({
                    html: 'O <span class="font-weight-bold"> nome </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.consumo.horarioInicial == undefined) {
                swal({
                    html: 'O <span class="font-weight-bold"> horário inicial de funcionamento </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.consumo.horarioFinal == undefined) {
                swal({
                    html: 'O <span class="font-weight-bold"> horário final de funcionamento </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.consumo.valorHora == undefined) {
                swal({
                    html: 'O <span class="font-weight-bold"> valor da hora </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.consumo.quantidadeEstacoes == undefined) {
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

        vm.cadastrarConsumo = function () {
            if (vm.validarConsumo()) {
                if (vm.consumo.dtNascimento != undefined)
                    vm.dtNascimento = moment(vm.consumo.dtNascimento).format();

                svcConsumo.cadastrarConsumo(vm.consumo)
                    .then(function (res) {
                        vm.novoConsumo();
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

        vm.updateConsumo = function () {

            svcConsumo.updateConsumo(vm.consumo)
                .then(function (res) {
                    vm.carregarConsumos();
                    swal({
                        text: "Consumo atualizado com sucesso",
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