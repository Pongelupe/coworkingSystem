//Sala 
angular
    .module('coworkingApp').factory('svcSala', function ($http) {
        var retorno = {};
        //Carregar salas
        retorno.salas = function () {
            return $http({
                url: URL_REQ + "salas",
                method: 'GET',
                cwsHeaders
            });
        };

        //Criar sala
        retorno.cadastrarSala = function (dados) {
            return $http({
                url: URL_REQ + "sala",
                method: 'POST',
                data: dados,
                cwsHeaders
            });
        };

        //Editar sala
        retorno.updateSala = function (dados) {
            return $http({
                url: URL_REQ + "sala",
                method: 'PUT',
                data: dados,
                params: {
                    idSala: dados.idSala
                },
                cwsHeaders
            });
        };

        //Deletar sala
        retorno.deletarSala = function (dados) {
            return $http({
                url: URL_REQ + "sala",
                method: 'DELETE',
                params: {
                    idSala: dados.idSala
                },
                cwsHeaders
            });
        };
        return retorno;
    })