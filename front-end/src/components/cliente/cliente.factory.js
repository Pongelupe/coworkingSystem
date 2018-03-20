//Cliente 
angular
    .module('coworkingApp').factory('svcCliente', function ($http) {
        var retorno = {};
        //Carregar clientes
        retorno.clientes = function () {
            return $http({
                url: URL_REQ + "clientes",
                method: 'GET',
                cwsHeaders
            });
        };

        //Criar cliente
        retorno.cadastrarCliente = function (dados) {
            return $http({
                url: URL_REQ + "cliente",
                method: 'POST',
                data: dados,
                cwsHeaders
            });
        };

        //Editar cliente
        retorno.updateCliente = function (dados) {
            return $http({
                url: URL_REQ + "cliente/" + dados.id,
                method: 'PUT',
                data: dados,
                cwsHeaders
            });
        };

        //Deletar cliente
        retorno.deletarCliente = function (idCliente) {
            return $http({
                url: URL_REQ + "cliente/",
                method: 'DELETE',
                params: {
                    idCliente: idCliente
                },
                cwsHeaders
            });
        };
        return retorno;
    })