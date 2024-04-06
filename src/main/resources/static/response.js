function enviar() {
    let nombre = document.getElementById("nombre").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        document.getElementById("arroba").innerHTML =
            JSON.parse(this.responseText).arroba;
    };
    xhttp.open("GET", "/usuario/" + nombre);
    xhttp.send();
}

function posts() {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        document.getElementById("posts").innerHTML =
        JSON.stringify(JSON.parse(this.responseText).posts);
    };
    xhttp.open("GET", "/hilo");
    xhttp.send();
}

function msg() {
    let mensaje = document.getElementById("mensaje").value;
    let arroba = document.getElementById("arroba").innerHTML;

    let urlPost = "/post";
    let urlHilo = "/hilo";

    let bodyData = {
        'arroba': arroba,
        'mensaje': mensaje
    };

    var formBody = [];

    for (var property in bodyData) {
        var encodedKey = encodeURIComponent(property);
        var encodedValue = encodeURIComponent(bodyData[property]);
        formBody.push(encodedKey + "=" + encodedValue);
    }

    formBody = formBody.join("&");

    fetch(urlPost, {
        method: "POST",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: formBody
    });

    fetch(urlHilo, {
        method: "POST",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: formBody
    })
        .then(posts);
}