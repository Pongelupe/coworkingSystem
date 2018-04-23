//Sala 
angular
    .module('coworkingApp').factory('svcProdutoServico', function ($http) {
        var retorno = {};
        //Carregar salas
        retorno.produtosServicos = function () {
            return $http({
                url: URL_REQ + "produtosServicos",
                method: 'GET',
                cwsHeaders
            });
        };

        //Criar sala
        retorno.cadastrarProdutoServico = function (dados) {
            return $http({
                url: URL_REQ + "produtoServico",
                method: 'POST',
                data: dados,
                cwsHeaders
            });
        };

        //Editar sala
        retorno.updateProdutoServico = function (dados) {
            return $http({
                url: URL_REQ + "produtoServico",
                method: 'PUT',
                data: dados,
                params: {
                    idProdutoServico: dados.id
                },
                cwsHeaders
            });
        };

        //Deletar sala
        retorno.deletarProdutoServico = function (idProdutoServico) {
            return $http({
                url: URL_REQ + "produtoServico",
                method: 'DELETE',
                params: {
                    idProdutoServico: idProdutoServico
                },
                cwsHeaders
            });
        };
        return retorno;
    })