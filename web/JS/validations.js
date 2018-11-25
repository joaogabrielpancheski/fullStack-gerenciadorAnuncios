
function validaString(valor, minimo, maximo) {
//    if (typeof valor === 'string' && valor.length >= minimo) {
//        return true;
//    } else {
//        return false;
//    }

    return typeof valor === 'string' && valor.length >= minimo && valor.length <= maximo;
}

function validaNumber(valor, minimo, maximo) {
    var valorNumerico = Number(valor);
    return typeof valorNumerico === 'number' && valor >= minimo && valor <= maximo;
}

function validaData(valor, minimo, maximo) {
    var data = moment(valor, 'YYYY-MM-DD', true);
    return data.isValid() && data.isSameOrBefore(maximo) && data.isSameOrAfter(minimo);
//    return data.isValid() && data.isBetween(minimo, maximo);
}

function validaEmail(email) {
    var regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regex.test(email);
}