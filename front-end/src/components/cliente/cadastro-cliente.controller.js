angular.module('coworkingApp')
    .controller('CadastroCliente', ["svcCliente", function (svcCliente) {
        var vm = this;

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

        vm.novoCliente();

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
                        if (err.status == 400) {
                            swal(
                                "Erro!",
                                err.data.messages[0],
                                "error"
                            )
                        } else {
                            err.data != undefined ? console.log(err.data.message) : "";
                            swal({
                                text: 'Desculpe, não conseguimos processar sua solicitação. Verifique os dados e tente novamente.',
                                type: 'error',
                                showConfirmButton: false,
                                timer: 5000
                            })
                        }

                    })
            };
        };


    }]);