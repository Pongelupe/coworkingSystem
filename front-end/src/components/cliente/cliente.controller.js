angular.module('coworkingApp')
    .controller('Cliente', [function () {
        var vm = this;

        vm.clientes = [
            {
                nome: "Ana Paula dos Santos",
                cpfCnpj: "09487213669",
                rg: "16587487",
                endereco: "Rua estrela 195, Floramar, BH-MG"
            },
            {
                nome: "Ana Paula dos Santos",
                cpfCnpj: "09487213669",
                rg: "16587487",
                endereco: "Rua estrela 195, Floramar, BH-MG"
            },
            {
                nome: "Ana Paula dos Santos",
                cpfCnpj: "09487213669",
                rg: "16587487",
                endereco: "Rua estrela 195, Floramar, BH-MG"
            },
            {
                nome: "Gladston Monteiro",
                cpfCnpj: "09475412669",
                rg: "18754252",
                endereco: "Rua estrela 195, Floramar, BH-MG"
            }
        ]


    }]);