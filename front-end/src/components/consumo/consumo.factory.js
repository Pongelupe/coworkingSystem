//Consumo 
angular
    .module('coworkingApp').factory('svcConsumo', function ($http) {
        var retorno = {};
        //Carregar consumo
        retorno.consumos = function () {
            return $http({
                url: URL_REQ + "consumos",
                method: 'GET',
                cwsHeaders
            });
        };

        //Criar consumo
        retorno.cadastrarConsumo = function (dados) {
            /*var urlRequest = URL_REQ + "consumo/" + dados.cliente.id + "/" + dados.salaCliente.id;
            urlRequest = urlRequest.toString();*/
            return $http({
                url: URL_REQ + "consumo/",
                method: 'POST',
                data: dados,
                cwsHeaders
            });
        };

        //Editar consumo
        retorno.updateConsumo = function (dados) {
            return $http({
                url: URL_REQ + "consumo",
                method: 'PUT',
                data: dados,
                params: {
                    idConsumo: dados.idConsumo
                },
                cwsHeaders
            });
        };

        //Finalizar consumo
        retorno.finalizarConsumo = function (dados) {
            return $http({
                url: URL_REQ + "finalizarConsumo",
                method: 'POST',
                data: dados,
                cwsHeaders
            });
        };

        //Deletar consumo
        retorno.deletarConsumo = function (dados) {
            return $http({
                url: URL_REQ + "consumo",
                method: 'DELETE',
                params: {
                    idConsumo: dados.idConsumo
                },
                cwsHeaders
            });
        };
        return retorno;
    })