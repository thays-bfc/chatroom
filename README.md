# Chat Room
I've completed de chatroom application using WebSocket

## Background
WebSocket is a communication protocol that makes it possible to establish a two-way communication channel between a
server and a client.

## Implementation
### Files modified
-> Message model is the message payload that will be exchanged between the client and the server. 
-> Websocket events on the server side (WebSocketChatServer.java)
-> Changes to the application controller to return chat view (WebSocketChatApplication.java)
-> Changes at the javascript login function
-> Changes at the chat HTML page

### Exceptions
Implemented tests using Selenium, which is working but is throwing exception while executing

### Run the application with command
mvn spring-boot:run; Or execute file .jar
For test cases execute File WebTests

