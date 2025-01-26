const express = require('express');
const http = require('http');
const socketIo = require('socket.io');

// Create Express app and server
const app = express();
const server = http.createServer(app);
const io = socketIo(server);

// Serve static files
app.use(express.static(__dirname + '/public'));

// Handle client connection
io.on('connection', (socket) => {
    console.log('New user connected');

    // Send welcome message
    socket.emit('message', 'Welcome to the chat!');

    // Broadcast when a user connects
    socket.broadcast.emit('message', 'A new user has joined the chat');

    // Listen for chat messages
    socket.on('chatMessage', (msg) => {
        io.emit('message', msg);  // Broadcast to all users
    });

    // Disconnect event
    socket.on('disconnect', () => {
        io.emit('message', 'A user has left the chat');
    });
});

// Start server
const PORT = process.env.PORT || 3000;
server.listen(PORT, () => console.log(`Server running on port ${PORT}`));

