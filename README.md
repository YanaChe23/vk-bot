# Demo chatbot for VK messenger
## Description 
The project was developed to train my knowledge of Java during the study process. As it's a demo project, it has a slightly more complicated structure then requires for this type of echo bot. 

The chatbot can be connected to a VK public via Callback API. The only thing it can do is echo text messages.
Please go to main/resources/application.properties and provide: 
 - endpoint verification string (request.callbackApiConfirmation);
 - access token (request.token).
 
You can use https://ngrok.com/ as a server to connect chatbot via Callback API.
