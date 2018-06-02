//Factory Plano
angular
    .module('coworkingApp').factory('svcPlano', function ($http) {
        var retorno = {};
        retorno.planos = function () {
            return $http({
                url: URL_REQ + "planos",
                method: 'GET',
                cwsHeaders
            });
        };

        retorno.getPlano = function (idPlano) {
            return $http({
                url: URL_REQ + "planos",
                method: 'GET',
                params: {
                    idPlano: dados.id
                },
                cwsHeaders
            });
        };


        retorno.cadastrarPlano = function (dados) {
            return $http({
                url: URL_REQ + "plano",
                method: 'POST',
                data: dados,
                cwsHeaders
            });
        };

        retorno.updatePlano = function (dados) {
            return $http({
                url: URL_REQ + "plano",
                method: 'PUT',
                data: dados,
                params: {
                    idPlano: dados.id
                },
                cwsHeaders
            });
        };

        retorno.deletarPlano = function (idPlano) {
            return $http({
                url: URL_REQ + "plano",
                method: 'DELETE',
                params: {
                    idPlano: idPlano
                },
                cwsHeaders
            });
        };
        return retorno;
    })