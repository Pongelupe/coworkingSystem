angular.module('coworkingApp')
    .controller('CadastroCliente', [function () {
        var vm = this;
        vm.cliente = {
            tipoCliente: "fisica",
            cpfCnpj: "",
            rg: "",
            cnh: "",
            telefone: "",
            email: "",
            inscricalMunicipal: "",
            inscricaoEstadual: "",
            dtNascimento: null,
            endereco: ""
        }


    }]);