angular.module('coworkingApp')
    .controller('Cliente', ["$rootScope", "$stateParams", "svcCliente", function ($rootScope, $stateParams, svcCliente) {
        var vm = this;
        $rootScope.mainTitle = "CLIENTE";
        $rootScope.subTitle = "Cliente / Novo Cliente";

        vm.novoCliente = function () {
            vm.cliente = {
                dataCadastro: new Date(),
                observacoes: null,
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
            };
        };

        if ($stateParams.cliente.length == 0) {
            vm.title = "Cadastrar Novo Cliente";
            vm.acao = "Salvar Cliente";
            vm.novoCliente();
        } else {
            vm.cliente = $stateParams.cliente;
            if (!isNullOrEmpty(vm.cliente.data))
                vm.cliente.data = parseDate(vm.cliente.data);

            vm.cliente.dataCadastro = parseDate(vm.cliente.dataCadastro);
            vm.cliente.endereco.cep = parseInt(vm.cliente.endereco.cep);
            vm.title = "Atualizar Dados do Cliente: " + vm.cliente.nome;
            vm.acao = "Atualizar Cliente";
        }

        vm.validarCliente = function () {

            if (campoNaoInformado("nome", vm.cliente.nome))
                return;
            if (campoNaoInformado("CPF/CNPJ", vm.cliente.cpfCnpj))
                return;
            if (campoNaoInformado("telefone principal", vm.cliente.telefonePrincipal))
                return;
            if (campoNaoInformado("e-mail", vm.cliente.email))
                return;
            if (campoNaoInformado("cep", vm.cliente.endereco.cep))
                return;
            if (campoNaoInformado("rua", vm.cliente.endereco.rua))
                return;
            if (campoNaoInformado("numero", vm.cliente.endereco.numero))
                return;
            if (campoNaoInformado("bairro", vm.cliente.endereco.bairro))
                return;
            if (campoNaoInformado("cidade", vm.cliente.endereco.cidade))
                return;
            if (campoNaoInformado("estado", vm.cliente.endereco.estado))
                return;
            if (campoNaoInformado("pais", vm.cliente.endereco.pais))
                return;

            return true;

        }

        vm.cadastrarCliente = function () {
            if (vm.validarCliente()) {
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
