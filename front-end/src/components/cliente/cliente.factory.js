//Cliente 
angular
    .module('coworkingApp').factory('svcCliente', function ($http) {
    var retorno = {};
    //Get clientes
    retorno.clientes = function () {
        return $http({
            url: URL_REQ + "clientes",
            method: 'GET',
            cwsHeaders
        });
    };

    //Create clientes
    retorno.cadastrarCliente = function (dados) {
        return $http({
            url: URL_REQ + "cliente",
            method: 'POST',
            data: dados,
            cwsHeaders
        });
    };
    return retorno;
})