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
            return $http({
                url: URL_REQ + "consumo",
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