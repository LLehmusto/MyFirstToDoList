
function nouda() {
    var noudettava = document.getElementById('tulosNouda');
    noudettava.innerHTML = '';

    axios.get('http://localhost:8080/api/todot')
        .then(function (response) {
            noudettava.innerHTML = generateSuccessHTMLOutput(response);
        })
        .catch(function (error) {
            noudettava.innerHTML = generateErrorHTMLOutput(error);
        });
}

function generateSuccessHTMLOutput(response){
    return response.data.map(function (todo) {
        return (
            '<ul class="list" style="padding-left: 50px">' +
            '<li class="row">' + 'Tehtävä numero ' + todo.id + ' on: ' + todo.task + '</li>' +
            '</ul>'
        );
    }).join('');
}

function generateErrorHTMLOutput(error) {
    return '<h5>Tapahtui jokin kamala virhe!</h5>';
}

document.getElementById('todoInput').addEventListener('submit', performPostRequest);

function performPostRequest(e) {
    var postattava = document.getElementById('tulosPostaa');
    var todoTeksti = document.getElementById('todoTeksti').value;
    postattava.innerHTML = '';

    axios.post('http://localhost:8080/api/todot', {
        task: todoTeksti
    })
        .then(function(response) {
            postattava.innerHTML = 'Tehtävää riittää... Lisäsit juuri uuden tehtävän To Do-listallesi.';
        })
        .catch(function(error) {
            postattava.innerHTML = generateErrorHTMLOutput(error);
        });
    e.preventDefault();
}

function poistaTehtava() {
    var poistettava = document.getElementById('poista');
    var todoId = document.getElementById('todoId').value;
    poistettava.innerHTML = '';

    axios.delete('http://localhost:8080/api/todot/' + todoId, {
        id:todoId
    })
        .then(function (response) {
            poistettava.innerHTML = 'Poistit tehtävän onnistuneesti.';
        })
        .catch(function (error) {
            poistettava.innerHTML = generateErrorHTMLOutput(error);
        });
    e.preventDefault();
}


function clearOutput() {
    var noudettava = document.getElementById('tulosNouda');
    noudettava.innerHTML = '';
}