# Demo chat bot for VK messenger. 
## Decrition 
The project was developed to train my knowlage of Java during study process. As it's a demo project, it has a slightly more complcated structure then it requires for this type of echobot. 

The chatbot can be connected to a VK public via Callback API. The only thing it can do is echoing text messages.
Please go to main/resources/application.properties and provide: 
 - endpoind verefication string (request.callbackApiConfirmation);
 - access token (request.token).
 
You can use https://ngrok.com/ as a server to connect chatbot via Callback API.
