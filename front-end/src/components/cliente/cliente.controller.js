angular.module('coworkingApp')
    .controller('Cliente', ["$rootScope", "$stateParams", "svcCliente", function ($rootScope, $stateParams, svcCliente) {
        var vm = this;
        $rootScope.mainTitle = "CLIENTE";
        $rootScope.subTitle = "Cliente / Novo Cliente";

        vm.novoCliente = function () {
            vm.cliente = {
                dataCadastro: new Date(),
                observacoes: null,
                pessoa: {
                    ehPessoaJuridica: false,
                    nome: null,
                    cpfCnpj: null,
                    rg: null,
                    cnh: null,
                    telefonePrincipal: null,
                    email: null,
                    inscricalMunicipal: null,
                    inscricaoEstadual: null,
                    data: null,
                    endereco: {}
                }
            };
        };

        if ($stateParams.cliente.length == 0) {
            vm.title = "Cadastrar Novo Cliente";
            vm.acao = "Salvar Cliente";
            vm.novoCliente();
        } else {
            vm.cliente = $stateParams.cliente;
            if (!isNullOrEmpty(vm.cliente.pessoa.data))
                vm.cliente.pessoa.data = parseDate(vm.cliente.pessoa.data);

            vm.cliente.dataCadastro = parseDate(vm.cliente.dataCadastro);
            vm.cliente.pessoa.endereco.cep = parseInt(vm.cliente.pessoa.endereco.cep);
            vm.title = "Atualizar Dados do Cliente: " + vm.cliente.pessoa.nome;
            vm.acao = "Atualizar Cliente";
        }

        vm.validarCliente = function () {

            if (campoNaoInformado("nome", vm.cliente.pessoa.nome))
                return;
            if (campoNaoInformado("CPF/CNPJ", vm.cliente.pessoa.cpfCnpj))
                return;
            if (campoNaoInformado("telefone principal", vm.cliente.pessoa.telefonePrincipal))
                return;
            if (campoNaoInformado("e-mail", vm.cliente.pessoa.email))
                return;
            if (campoNaoInformado("cep", vm.cliente.pessoa.endereco.cep))
                return;
            if (campoNaoInformado("rua", vm.cliente.pessoa.endereco.rua))
                return;
            if (campoNaoInformado("numero", vm.cliente.pessoa.endereco.numero))
                return;
            if (campoNaoInformado("bairro", vm.cliente.pessoa.endereco.bairro))
                return;
            if (campoNaoInformado("cidade", vm.cliente.pessoa.endereco.cidade))
                return;
            if (campoNaoInformado("estado", vm.cliente.pessoa.endereco.estado))
                return;
            if (campoNaoInformado("pais", vm.cliente.pessoa.endereco.pais))
                return;

            return true;

        }

        vm.cadastrarCliente = function () {
            if (vm.validarCliente()) {
                if (vm.cliente.pessoa.data != undefined)
                    vm.data = moment(vm.cliente.pessoa.data).format();

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
                    vm.novoCliente();
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
