angular.module('coworkingApp')
    .controller('Clientes', ["$state", "$stateParams", "svcCliente", function ($state, $stateParams, svcCliente) {
        var vm = this;

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
            alertaConfirmarExclusao()
                .then(function (res) {
                    if (res.value) {
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
                    } 
                })
        };


    }]);