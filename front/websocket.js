const socket = new SockJS('http://localhost:8080/ws-orders');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);

    const orderId = "67f44dbb02a5ec1d8a277489"; //pedido
    const clientId = "67ce12bab9e68530c6a47544"; //cliente

    stompClient.subscribe(`/topic/order-status/${orderId}/client/${clientId}`, function (message) {
        console.log('Mensagem recebida:', message.body);

        const orderStatus = message.body;

        const messagesDiv = document.getElementById('messages');
        const messageElement = document.createElement('div');
        messageElement.innerText = `Pedido ${orderId}: ${orderStatus}`;
        messagesDiv.appendChild(messageElement);
    });
}, function (error) {
    console.error('Erro ao conectar ao WebSocket:', error);
});