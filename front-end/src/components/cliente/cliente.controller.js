angular.module('coworkingApp')
    .controller('Cliente', ["$rootScope", "$stateParams", "svcCliente", function ($rootScope, $stateParams, svcCliente) {
        var vm = this;
        $rootScope.mainTitle = "CLIENTE";
        $rootScope.subTitle = "Cliente / Novo Cliente";

        vm.novoCliente = function () {
            vm.cliente = {
                tipoCliente: "fisica",
                nome: "",
                cpfCnpj: "",
                rg: "",
                cnh: "",
                telefone: "",
                email: "",
                inscricalMunicipal: "",
                inscricaoEstadual: "",
                dtNascimento: null,
                endereco: ""
            };
        };

        if ($stateParams.cliente.length == 0) {
            vm.title = "Cadastrar Novo Cliente";
            vm.acao = "Salvar Cliente";
            vm.novoCliente();
        } else {
            vm.cliente = $stateParams.cliente;
            vm.cliente.dtNascimento = new Date(vm.cliente.dtNascimento);
            vm.title = "Atualizar Dados do Cliente: " + vm.cliente.nome;
            vm.acao = "Atualizar Cliente";
        }

        vm.validarCliente = function () {
            if (vm.cliente.nome == "") {
                swal({
                    html: 'O <span class="font-weight-bold"> nome </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.cliente.cpfCnpj == "") {
                swal({
                    html: 'O <span class="font-weight-bold"> cpf/cnpj </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.cliente.telefone == "") {
                swal({
                    html: 'O <span class="font-weight-bold"> telefone principal </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.cliente.email == "") {
                swal({
                    html: 'O <span class="font-weight-bold"> email </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.cliente.email == undefined) {
                swal({
                    html: 'O <span class="font-weight-bold"> email </span> informado é inválido.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            return true;

        }

        vm.cadastrarCliente = function () {
            if (vm.validarCliente()) {
                if (vm.cliente.dtNascimento != undefined)
                    vm.dtNascimento = moment(vm.cliente.dtNascimento).format();

                svcCliente.cadastrarCliente(vm.cliente)
                    .then(function (res) {
                        vm.novoCliente();
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

        vm.updateCliente = function () {

            svcCliente.updateCliente(vm.cliente)
                .then(function (res) {
                    vm.carregarClientes();
                    swal({
                        text: "Cliente atualizado com sucesso",
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