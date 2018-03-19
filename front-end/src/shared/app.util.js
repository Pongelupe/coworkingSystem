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
};

function alertaConfirmarExclusao(acao) {
    var title = acao == undefined ? "excluir" : acao;
    return swal({
        title: 'Tem certeza que deseja ' + title + '?',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Prosseguir',
        cancelButtonText: 'Cancelar',
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
    })
};

function alertaConfirmarExclusao() {
    return swal({
        title: 'Tem certeza que deseja excluir?',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Excluir',
        cancelButtonText: 'Cancelar',
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
    })
};

intervaloHoras = [
    "00:00",
    "01:00",
    "02:00",
    "03:00",
    "04:00",
    "05:00",
    "06:00",
    "07:00",
    "08:00",
    "09:00",
    "10:00",
    "11:00",
    "12:00",
    "13:00",
    "14:00",
    "15:00",
    "16:00",
    "17:00",
    "18:00",
    "19:00",
    "20:00",
    "21:00",
    "22:00",
    "23:00"
];

function calcularDiasDiferenca(dataAtual, dataPassada) {
    var one_day = 1000 * 60 * 60 * 24;

    // Convert both dates to milliseconds
    var date1_ms = dataPassada.getTime();
    var date2_ms = dataAtual.getTime();

    // Calculate the difference in milliseconds
    var difference_ms = date2_ms - date1_ms;
    //take out milliseconds
    difference_ms = difference_ms / 1000;
    var seconds = Math.floor(difference_ms % 60);
    difference_ms = difference_ms / 60;
    var minutes = Math.floor(difference_ms % 60);
    difference_ms = difference_ms / 60;
    var hours = Math.floor(difference_ms % 24);
    var days = Math.floor(difference_ms / 24);

    var tempo = {
        dias: days,
        horas: hours,
        minutos: minutes
    };

    return tempo;
};
