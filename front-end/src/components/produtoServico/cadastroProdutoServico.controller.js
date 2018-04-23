angular.module('coworkingApp')
    .controller('ProdutoServico', ["$rootScope", "$state", "$stateParams", "svcProdutoServico", function ($rootScope, $state, $stateParams, svcProdutoServico) {
        var vm = this;
        $rootScope.mainTitle = "PRODUTO/SERVIÇO";
        $rootScope.subTitle = "Config. Gerais / Listar Produtos/Serviços / Cadastros Produto/Serviço";

        vm.novoProdutoServico = function () {
            vm.produtoServico = {
                nome: "",
                valor: null,
                ehProduto: "true"
            };
            vm.title = "Cadastrar Novo Produto/Serviço";
            vm.acao = "Salvar Produto/Serviço";
        };

        if ($stateParams.produtoServico.length == 0) {
            vm.title = "Cadastrar Novo Produto/Serviço";
            vm.acao = "Salvar Produto/Serviço";
            vm.novoProdutoServico();
        } else {
            vm.produtoServico = $stateParams.produtoServico;
            vm.produtoServico.ehProduto = vm.produtoServico.ehProduto.toString();
            vm.title = "Atualizar Dados do Produto/Serviço: " + vm.produtoServico.nome;
            vm.acao = "Atualizar Produto/Serviço";
        }

        vm.validarProdutoServico = function () {
            if (vm.produtoServico.nome == "") {
                swal({
                    html: 'O <span class="font-weight-bold"> nome </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            if (vm.produtoServico.valor == undefined) {
                swal({
                    html: 'O <span class="font-weight-bold"> valor </span> não foi informado.',
                    type: 'error',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            return true;
        }

        vm.cadastrarProdutoServico = function () {
            if (vm.validarProdutoServico()) {

                svcProdutoServico.cadastrarProdutoServico(vm.produtoServico)
                    .then(function (res) {
                        vm.novoProdutoServico();
                        swal({
                            text: 'Cadastro realizado com sucesso',
                            type: 'success',
                            showConfirmButton: false,
                            timer: 2000
                        })
                        vm.retornarListaProdutosServicos();
                    })
                    .catch(function (err) {
                        alertaErroRequisicao(err);
                    })
            };
        };

        vm.updateProdutoServico = function () {
            svcProdutoServico.updateProdutoServico(vm.produtoServico)
                .then(function (res) {
                    swal({
                        text: "Produto/Serviço atualizado com sucesso",
                        type: 'success',
                        showConfirmButton: false,
                        timer: 2000
                    })
                    vm.novoProdutoServico();
                    vm.retornarListaProdutosServicos();
                })
                .catch(function (err) {
                    alertaErroRequisicao(err);
                })
        };

        vm.retornarListaProdutosServicos = function(){
            $state.go('produtosServicos');
        }


    }]);