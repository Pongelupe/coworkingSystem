angular.module('coworkingApp')
    .controller('Clientes', ["$state", "$stateParams", "svcCliente", function ($state, $stateParams, svcCliente) {
        var vm = this;

        vm.clientes = [
            {
                idCliente: 1,
                tipoCliente: "fisica",
                nome: "Ana Paula dos Santos",
                dtNascimento: new Date("03/11/1996"),
                cpfCnpj: "09487213669",
                rg: "16587487",
                cnh: "26256523",
                telefone: "31975335875",
                inscricaoMunicipal: "",
                inscricaoEstadual: "",
                endereco: "Rua estrela 195, Floramar, BH-MG",
                email: "ana@teste.com.br"
            },
            {
                idCliente: 2,
                tipoCliente: "fisica",
                nome: "Maria do Rosário",
                dtNascimento: new Date("06/10/1973"),
                cpfCnpj: "09487213669",
                rg: "16587487",
                cnh: "26256523",
                telefone: "31975335875",
                inscricaoMunicipal: "A6S5A88",
                inscricaoEstadual: "AS21A54SA4",
                endereco: "Rua estrela 195, Floramar, BH-MG",
                email: "ana@teste.com.br"
            },
            {
                idCliente: 3,
                tipoCliente: "juridica",
                nome: "Companhia Coworking System",
                dtNascimento: new Date("01/01/1999"),
                cpfCnpj: "10125698000147",
                rg: "",
                cnh: "",
                telefone: "3134357858",
                inscricaoMunicipal: "A6S5A88",
                inscricaoEstadual: "AS21A54SA4",
                endereco: "Rua estrela 659, Floramar, BH-MG",
                email: "emp@teste.com.br"
            },
            {
                idCliente: 4,
                tipoCliente: "fisica",
                nome: "Gladston Monteiro de Carvalho",
                dtNascimento: new Date("11/03/1996"),
                cpfCnpj: "09547458448",
                rg: "26598",
                cnh: "95900",
                telefone: "31999554762",
                inscricaoMunicipal: "",
                inscricaoEstadual: "",
                endereco: "Rua estrela 195, Floramar, BH-MG",
                email: "ga@teste.com.br"
            },
            {
                idCliente: 5,
                tipoCliente: "fisica",
                nome: "Breno Silva",
                dtNascimento: new Date("11/03/1996"),
                cpfCnpj: "09487213669",
                rg: "16587487",
                cnh: "26256523",
                telefone: "31975335875",
                inscricaoMunicipal: "A6S5A88",
                inscricaoEstadual: "AS21A54SA4",
                endereco: "Rua estrela 195, Floramar, BH-MG",
                email: "ana@teste.com.br"
            },
        ]

        vm.carregarClientes = function () {
            svcCliente.clientes()
                .then(function (res) {
                    if (res.data.data != undefined)
                        vm.clientes = res.data.data;
                })
        };

        vm.cadastrarCliente = function () {
            $state.go('cliente');
        };


        vm.updateCliente = function (cliente) {
            cliente.atualizar = true;
            $state.go('cliente', { cliente: cliente });
        };

        vm.deletarCliente = function (cliente) {
            svcCliente.deletarCliente(cliente)
                .then(function (res) {
                    vm.carregarClientes();
                    swal({
                        text: "Cliente excluso com sucesso",
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