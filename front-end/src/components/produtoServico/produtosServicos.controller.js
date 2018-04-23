angular.module('coworkingApp')
    .controller('ProdutosServicos', ["$rootScope", "$state", "$stateParams", "svcProdutoServico", function ($rootScope, $state, $stateParams, svcProdutoServico) {
        var vm = this;
        $rootScope.mainTitle = "PRODUTO/SERVIÇO";
        $rootScope.subTitle = "Config. Gerais / Listar Produtos/Serviços";

        vm.carregarProdutosServicos = function () {
            svcProdutoServico.produtosServicos()
                .then(function (res) {
                    if (res.data.data != undefined)
                        vm.produtosServicos = res.data.data;
                })
        };

        vm.cadastrarProdutoServico = function () {
            $state.go('cadastroProdutoServico');
        };

        vm.updateProdutoServico= function (produtoServico) {
            produtoServico.atualizar = true;
            $state.go('cadastroProdutoServico', { produtoServico: produtoServico });
        };

        vm.deletarProdutoServico = function (produtoServico) {
            alertaConfirmarExclusao()
                .then(function (res) {
                    if (res.value) {
                        svcProdutoServico.deletarProdutoServico(produtoServico.id)
                            .then(function (res) {
                                vm.carregarProdutosServicos();
                                swal({
                                    text: "Produto/Serviço excluso com sucesso",
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