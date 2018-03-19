angular.module('coworkingApp')
    .controller('Consumo', ["$rootScope", "$state", "$stateParams", "svcConsumo", "svcSala", "svcCliente","Notify",
        function ($rootScope, $state, $stateParams, svcConsumo, svcSala, svcCliente, Notify) {

            var vm = this;

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
                        if (res.data.data != undefined)
                            vm.consumos = res.data.data;
                    })
            };

            vm.carregarSalas();

            vm.cadastrarConsumo = function (consumo) {

                var obj = {};
                obj.salaCliente = vm.salas.filter(function (obj) {
                    return (obj.id == consumo.id)
                })[0];

                obj.cliente = vm.clientes.filter(function (obj) {
                    return (obj.id == consumo.idCliente)
                })[0];

                obj.tipoConsumo = "AVULSO";
                obj.faturado = false;
                obj.dataInicial = moment(consumo.dataInicial).format();

                svcConsumo.cadastrarConsumo(obj)
                    .then(function (res) {
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

                var obj = {};
                obj.sala = vm.salas.filter(function (obj) {
                    return (obj.id == consumo.id)
                })[0];

                obj.solicitante = vm.clientes.filter(function (obj) {
                    return (obj.id == consumo.idCliente)
                })[0];

                obj.tipoConsumo = "AVULSO";
                obj.faturado = false;
                obj.dataInicial = moment(consumo.dataInicial).format();

                svcConsumo.cadastrarConsumo(obj)
                    .then(function (res) {
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


            vm.carregarSalasComConsumo = function (consumos) {
                vm.salasComConsumo = vm.salas;

                for (var i = 0; i < salasComConsumo.length; i++) {

                    salasComConsumo[i].consumos = vm.consumos.filter(function (obj) {
                        return (obj.sala.id == salasComConsumo[i].id)
                    })[0];

                    for (var j = 0; j < salasComConsumo[i].consumos.length; j++) {
                        salasComConsumo[i].consumos[j].tempoConsumo = calcularDiasDiferenca(new Date(), new Date(salasComConsumo[i].consumos[j]));
                        salasComConsumo[i].consumos[j].valorGasto = calcularGasto(salasComConsumo[i].consumos[j].tempoConsumo);
                    }
                }

            };

            vm.calcularGasto = function (consumo) {
                var valorDia = consumo.valorHora * 24;
                var valorHora = consumo.valorHora;
                var valorMinuto = consumo.valorHora / 60;
                var tempoConsumo = consumo.tempoConsumo;

                var valorGasto = parseFloat(tempoConsumo.dias * valorDia + tempoConsumo.horas * valorHora + tempoConsumo.minutos * valorMinuto);
            }


            vm.openModalFinalizarConsumo = function (consumo) {
                return Notify.openModal("components/consumo/finalizarConsumo.html", consumo, "50%")
            }

        }]);