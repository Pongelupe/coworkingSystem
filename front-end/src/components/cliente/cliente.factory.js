//Cliente 
app.factory('svcCliente', function ($http) {
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
    cliente.cadastrarCliente = function () {
        return $http({
            url: URL_REQ + "cliente",
            method: 'POST',
            cwsHeaders
        });
    };
    return retorno;
})