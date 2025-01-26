<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f1f1f1;
        }
        #chat-container {
            max-width: 600px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        #messages {
            height: 300px;
            overflow-y: scroll;
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 5px;
        }
        #message-input {
            width: 80%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
        }
        #send-btn {
            padding: 10px;
            border: none;
            background: #007bff;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<div id="chat-container">
    <h2>Chat Room</h2>
    <div id="messages"></div>
    <input type="text" id="message-input" placeholder="Type a message...">
    <button id="send-btn">Send</button>
</div>

<script src="/socket.io/socket.io.js"></script>
<script>
    const socket = io();

    const messageInput = document.getElementById('message-input');
    const messagesDiv = document.getElementById('messages');
    const sendBtn = document.getElementById('send-btn');

    // Send message to server
    sendBtn.addEventListener('click', () => {
        const message = messageInput.value.trim();
        if (message) {
            socket.emit('chatMessage', message);
            messageInput.value = '';
        }
    });

    // Receive messages from server
    socket.on('message', (msg) => {
        const newMessage = document.createElement('p');
        newMessage.textContent = msg;
        messagesDiv.appendChild(newMessage);
        messagesDiv.scrollTop = messagesDiv.scrollHeight;
    });
</script>

</body>
</html>
	
