angular.module('coworkingApp')
    .controller('Clientes', ["$rootScope", "$state", "$stateParams", "svcCliente", function ($rootScope, $state, $stateParams, svcCliente) {
        var vm = this;
        $rootScope.mainTitle = "CLIENTE";
        $rootScope.subTitle = "Cliente / Listar Clientes";

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
                        svcCliente.deletarCliente(cliente.id)
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