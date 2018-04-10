//Faturamento 
angular
    .module('coworkingApp').factory('svcFaturamento', function ($http) {
        var retorno = {};
        //Carregar faturamentos
        retorno.faturamentos = function () {
            return $http({
                url: URL_REQ + "faturamentos",
                method: 'GET',
                cwsHeaders
            });
        };

        //Criar faturamento
        retorno.cadastrarFaturamento = function (dados) {
            return $http({
                url: URL_REQ + "faturamento",
                method: 'POST',
                data: dados,
                cwsHeaders
            });
        };

        //Editar faturamento
        retorno.updateFaturamento = function (dados) {
            return $http({
                url: URL_REQ + "faturamento",
                method: 'PUT',
                data: dados,
                params: {
                    idFaturamento: dados.idFaturamento
                },
                cwsHeaders
            });
        };

				retorno.registrarPagamento = function (idFaturamento) {
					return $http({
						url: URL_REQ + "registrarPagamento",
						method: 'POST',
						data: {},
						params: {
							idFaturamento: idFaturamento
						},
						cwsHeaders
					});
				};

        //Deletar faturamento
        retorno.deletarFaturamento = function (dados) {
            return $http({
                url: URL_REQ + "faturamento",
                method: 'DELETE',
                params: {
                    idFaturamento: dados.idFaturamento
                },
                cwsHeaders
            });
        };
        return retorno;
    })
