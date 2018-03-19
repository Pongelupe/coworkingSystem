angular.module('coworkingApp')
    .controller('Consumo', ["$rootScope", "$state", "$stateParams", "svcConsumo", "svcSala", "svcCliente", "Notify",
        function ($rootScope, $state, $stateParams, svcConsumo, svcSala, svcCliente, Notify) {

            var vm = this;

            var consumos = [
                {
                    tipoConsumo: "AVULSO",
                    dataInicial: "2018-03-18T23:45:00",
                    faturado: "false",
                    sala: {
                        "id": 4,
                        "nome": "Coworking 20",
                        "horarioInicial": "08:00:00",
                        "horarioFinal": "18:00:00",
                        "ramal": 0,
                        "valorHora": 20.0,
                        "quantidadeEstacoes": 20,
                        "estaLivre": true,
                        "tipoSala": "COWORKING"
                    },
                    cliente: {
                        "id": 5,
                        "nome": "Ana Paula dos Santos",
                        "tipoCliente": "fisica",
                        "email": "ana@teste.com",
                        "cpfCnpj": "09514114552",
                        "rg": "16188745",
                        "cnh": "1656565",
                        "telefone": "31998574563",
                        "inscricaoMunicipal": null,
                        "inscricaoEstadual": "",
                        "dtNascimento": 826513200000,
                        "endereco": {
                            "cliente": null,
                            "rua": null,
                            "bairro": null,
                            "cidade": null,
                            "complemento": null,
                            "estado": null,
                            "pais": null,
                            "cep": null,
                            "numero": 0
                        },
                        "consumos": [

                        ]
                    },
                },
                {
                    tipoConsumo: "AVULSO",
                    dataInicial: "2018-03-18T20:00:00",
                    faturado: "false",
                    sala: {
                        "id": 1,
                        "nome": "Reunião",
                        "horarioInicial": "08:00:00",
                        "horarioFinal": "18:00:00",
                        "ramal": 6565,
                        "valorHora": 50.0,
                        "quantidadeEstacoes": 0,
                        "estaLivre": true,
                        "tipoSala": "PUBLICA"
                    },
                    cliente: {
                        "id": 6,
                        "nome": "Companhia de Locação Ltda",
                        "tipoCliente": "juridica",
                        "email": "comapnhia@teste.com",
                        "cpfCnpj": "10147988000160",
                        "rg": "",
                        "cnh": "",
                        "telefone": "3134354747",
                        "inscricaoMunicipal": "3265989",
                        "inscricaoEstadual": "89896523",
                        "dtNascimento": 1262311200000,
                        "endereco": {
                            "cliente": null,
                            "rua": null,
                            "bairro": null,
                            "cidade": null,
                            "complemento": null,
                            "estado": null,
                            "pais": null,
                            "cep": null,
                            "numero": 0
                        },
                        "consumos": [

                        ]
                    },
                }
            ];


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
                            vm.carregarSalasComConsumo();
                        }

                    })
            };

            vm.init = function () {
                vm.salasComConsumo = consumos;

                for (var j = 0; j < vm.salasComConsumo.length; j++) {
                    vm.salasComConsumo[j].tempoConsumo = calcularDiasDiferenca(new Date(), new Date(vm.salasComConsumo[j].dataInicial));
                    vm.salasComConsumo[j].valorGasto = vm.calcularGasto(vm.salasComConsumo[j]);
                }

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
                var valorDia = consumo.sala.valorHora * 24;
                var valorHora = consumo.sala.valorHora;
                var valorMinuto = consumo.sala.valorHora / 60;
                var tempoConsumo = consumo.tempoConsumo;

                var valorGasto = parseFloat(tempoConsumo.dias * valorDia + tempoConsumo.horas * valorHora + tempoConsumo.minutos * valorMinuto);
                return valorGasto;
            }


            vm.openModalFinalizarConsumo = function (consumo) {
                return Notify.openModal("components/consumo/finalizarConsumo.html", consumo, "50%")
            }

        }]);