function alertaErroRequisicao(err) {
    if (err.status == 400) {
        swal({
            text: err.data.messages[0],
            type: 'error',
            showConfirmButton: false,
            timer: 5000
        })
    } else {
        err.data != undefined ? console.log(err.data.message) : "";
        swal({
            text: 'Desculpe, não conseguimos processar sua solicitação. Verifique os dados e tente novamente.',
            type: 'error',
            showConfirmButton: false,
            timer: 5000
        })
    }
}