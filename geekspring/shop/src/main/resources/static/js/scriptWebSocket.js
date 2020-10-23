var stompClient = null;

window.onload = connect();

function connect() {
    var socket = new SockJS('/getTotalOrderItems');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        getTotalOrderItems();
        stompClient.subscribe('/topic/totalOrderItems', function(greeting){
//            showGreeting(JSON.parse(greeting.body).content);
            showTotalOrderItems(JSON.parse(greeting.body).totalOrderItems);
        });
    });
}

function getTotalOrderItems() {
    var name = 'getCurrentOrderTotalItems';
    stompClient.send("/app/getTotalOrderItems", {}, JSON.stringify({ 'name': name }));
}


function showGreeting(message) {
    console.log(message);
    document.getElementById("resultInput").value=message;
}

function showTotalOrderItems(totalOrderItems) {
    console.log(totalOrderItems);
    document.getElementById("totalOrderItems").textContent=totalOrderItems + " ";
}