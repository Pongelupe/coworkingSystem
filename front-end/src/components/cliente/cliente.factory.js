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
        retorno.deletarCliente = function (dados) {
            var urlRequest = URL_REQ + "cliente/" + dados.id;
            urlRequest = urlRequest.toString();
            return $http({
                url: urlRequest,
                method: 'DELETE',
                data: dados,
                cwsHeaders
            });
        };
        return retorno;
    })