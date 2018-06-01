//Sala 
angular
    .module('coworkingApp').factory('svcPacoteHora', function ($http) {
        var retorno = {};
        //Carregar salas
        retorno.pacotesHoras  = function () {
            return $http({
                url: URL_REQ + "pacotes",
                method: 'GET',
                cwsHeaders
            });
        };

        //Criar sala
        retorno.cadastrarPacoteHora = function (dados) {
            return $http({
                url: URL_REQ + "pacote",
                method: 'POST',
                data: dados,
                cwsHeaders
            });
        };

        //Editar sala
        retorno.updatePacoteHora = function (dados) {
            return $http({
                url: URL_REQ + "pacote",
                method: 'PUT',
                data: dados,
                params: {
                    idPacote: dados.id
                },
                cwsHeaders
            });
        };

        //Deletar sala
        retorno.deletarPacoteHora = function (idPacoteHora) {
            return $http({
                url: URL_REQ + "pacote",
                method: 'DELETE',
                params: {
                    idPacoteHora: idPacoteHora
                },
                cwsHeaders
            });
        };
        return retorno;
    })