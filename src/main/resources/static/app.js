

var stompClient = null;

$(document).ready(function() {
    console.log( "ready!" );
    connect();
    $("button").click(function () {
        var elem = document.documentElement;
        openFullscreen(elem);

    });
    setInterval(function(){ getScene() }, 33);

});

$(document).keypress(function (event) {

    input(event);
});

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/position1', function (player1) {
            var pos = JSON.parse(player1.body).position;
            $("#player1").get(0).setAttribute('position', '-5 ' + pos+' -5')
        });

        stompClient.subscribe('/topic/position2', function (player2) {
            var pos = JSON.parse(player2.body).position;
            $("#player2").get(0).setAttribute('position', '5 ' + pos+' -5')
        });
        stompClient.subscribe('/topic/scene', function (scene){
           var pos = JSON.parse(scene.body).position;

           $("#ball").get(0).setAttribute('position', pos[0]+' '+pos[1]+' -5')
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function input(event){
    var key = event.which || event.code;
    switch (key) {
        case(119):
            moveUp1();
            break;
        case(115):
            moveDown1();
            break;
        case(105):
            moveUp2();
            break;
        case(107):
            moveDown2();
            break;
        case(98):
            stompClient.send("/app/player1",{},"start")

    }
}

function moveUp1() {
    stompClient.send("/app/player1",{},"up")
}

function moveDown1(){
    stompClient.send("/app/player1",{},"down")
}

function moveUp2() {
    stompClient.send("/app/player2",{},"up")
}

function moveDown2(){
    stompClient.send("/app/player2",{},"down")
}

function openFullscreen(elem) {

    if (elem.requestFullscreen) {
        elem.requestFullscreen();
    } else if (elem.mozRequestFullScreen) { /* Firefox */
        elem.mozRequestFullScreen();
    } else if (elem.webkitRequestFullscreen) { /* Chrome, Safari and Opera */
        elem.webkitRequestFullscreen();
    } else if (elem.msRequestFullscreen) { /* IE/Edge */
        elem.msRequestFullscreen();
    }
}

function getScene(){
    stompClient.send("/app/scene",{})
}