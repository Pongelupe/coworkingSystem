angular.module('coworkingApp')
    .controller('Clientes', ["svcCliente", function (svcCliente) {
        var vm = this;

        vm.clientes = [
            {
                tipoCliente: "fisico",
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
                tipoCliente: "fisico",
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
                tipoCliente: "juridico",
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
                tipoCliente: "fisico",
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
                tipoCliente: "fisico",
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
                    vm.clientes = res.data.data;
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


    }]);