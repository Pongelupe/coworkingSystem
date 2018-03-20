angular.module('coworkingApp')
    .controller('Consumo', ["$rootScope", "$state", "$stateParams", "svcConsumo", "svcSala", "svcCliente","svcFaturamento", "Notify",
        function ($rootScope, $state, $stateParams, svcConsumo, svcSala, svcCliente,svcFaturamento, Notify) {

            var vm = this;

            vm.novoConsumo = function () {
                vm.consumo = {
                    dataInicial: new Date(moment().format())
                }
            };

            vm.novoConsumo();

            vm.carregarSalas = function () {
                svcSala.salas()
                    .then(function (res) {
                        if (res.data.data != undefined)
                            vm.salas = res.data.data;
                    })
            };

            vm.carregarClientes = function () {
                svcCliente.clientes()
                    .then(function (res) {
                        if (res.data.data != undefined)
                            vm.clientes = res.data.data;
                    })
            };

            vm.carregarConsumos = function () {
                svcConsumo.consumos()
                    .then(function (res) {
                        if (res.data.data != undefined) {
                            vm.consumos = res.data.data;
                            vm.atualizarConsumos();
                        }

                    })
            };

            vm.calcularGasto = function (consumo) {
                var valorDia = consumo.sala.valorHora * 24;
                var valorHora = consumo.sala.valorHora;
                var valorMinuto = consumo.sala.valorHora / 60;
                var tempoConsumo = consumo.tempoConsumo;

                var valorGasto = parseFloat(tempoConsumo.dias * valorDia + tempoConsumo.horas * valorHora + tempoConsumo.minutos * valorMinuto);
                return valorGasto;
            }


            vm.atualizarConsumos = function () {
                for (var j = 0; j < vm.consumos.length; j++) {
                    vm.consumos[j].tempoConsumo = calcularDiasDiferenca(new Date(), new Date(vm.consumos[j].dataInicial));
                    vm.consumos[j].valorGasto = vm.calcularGasto(vm.consumos[j]);
                }
            }

            vm.cadastrarConsumo = function () {
                var obj = {};
                obj.idSala = vm.consumo.idSala;
                obj.idCliente = vm.consumo.idCliente;
                obj.consumo = {};
                obj.consumo.tipoConsumo = "AVULSO";
                obj.consumo.faturado = false;
                obj.consumo.dataInicial = moment(vm.consumo.dataInicial).format();

                svcConsumo.cadastrarConsumo(obj)
                    .then(function (res) {
                        vm.novoConsumo();
                        vm.carregarConsumos();
                        swal({
                            title: "Check-In realizado com sucesso",
                            type: "success",
                            showConfirmButton: false,
                            timer: 2000
                        })
                        vm.carregarSalas();
                    })
                    .catch(function (err) {
                        alertaErroRequisicao(err);
                    })
            };

            vm.finalizarConsumo = function (consumo) {

                /*var obj = {};
                obj.idConsumo = consumo.id;
                obj.dataFinal = moment().format();*/

                alertaConfirmarExclusao("realizar Check-Out")
                    .then(function (res) {
                        if (res.value) {
                            svcConsumo.finalizarConsumo(consumo.id)
                                .then(function (res) {
                                    swal({
                                        title: "Check-Out e faturamento realizado com sucesso",
                                        type: "success",
                                        showConfirmButton: false,
                                        timer: 2000
                                    })
                                    vm.carregarSalas();
                                    debugger;
                                    vm.realizarFaturamento(consumo);
                                })
                                .catch(function (err) {
                                    alertaErroRequisicao(err);
                                })
                        }
                    })
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

            vm.realizarFaturamento = function(consumo){
                vm.faturamento = {};

                vm.faturamento.tipoFaturamento = "AVULSO" ;
                vm.faturamento.statusFaturamento = "ABERTO";
                vm.faturamento.dtEmissao = moment().format() ;
                vm.faturamento.dtVencimento = moment().add(30, 'days').format()
                vm.faturamento.valorFaturamento = consumo.valorGasto;
                vm.faturamento.descontoFaturamento = 0;

                svcFaturamento.cadastrarFaturamento(vm.faturamento)
                .then(function(res){
                    vm.carregarConsumos();
                })
                .catch(function(err){
                    console.log(err);
                })
                 
            }


            vm.openModalFinalizarConsumo = function (consumo) {
                return Notify.openModal("components/consumo/finalizarConsumo.html", consumo, "50%")
            }

        }]);