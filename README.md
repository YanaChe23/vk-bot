# Echo bot for VK messenger
Simple Java chatbot for VK.com
## Table of content 

 - [General info](#general-info) 
 - [Technologies](#technologies)
 - [Features](#features)
 - [How to connect the chatbot to a VK public](#how-to-connect-the-chatbot-to-a-V-public)
 - [How to install and run](#How-to-install-and-run)

## General info
The project is a simple echo bot that can be used as a base for further chatbot development. 
The bot can be connected to a VK public via Callback API. The only thing it can do is echo text messages. 

## How to connect the chatbot to a VK public
Check [this guide](https://dev.vk.com/ru/api/bots/getting-started) on how to connect a chatbot to a public. 
Please use [Callback API connection](https://dev.vk.com/ru/api/bots/getting-started#Callback%20API) to connect the chatbot. 
As a result, you will get an access token (as a rule, it starts with "vk1. ...") and a confirmation string to 
be returned from Callback API; this info is required to run the application. 

## How to install and run 
Pull the image from Docker Hub:
> docker pull chuprunovayana/vk_echo_chatbot:latest

Or clone this repository and build the image:
> docker build -t chuprunovayana/vk_echo_chatbot . 

Once the image is ready,  run the container:
> docker run -e API_TOKEN=${TOKEN} -e CALLBACK_CONFIRMATION=${CONFIRMATION} -p 80:8080 chuprunovayana/vk_echo_chatbot:latest



Where:

-TOKEN - your vk.com public access token

-CONFIRMATION - a confirmation string to be returned from Callback API section

For example, 
>docker run -e API_TOKEN=vk1.a.P-Ya40zx0BDgGB -e CALLBACK_CONFIRMATION=0af7a0f9 -p 80:8080 chuprunovayana/vk_echo_chatbot:latest

If everything works fine, this message will appear.

<img width="1392" alt="Screenshot 2023-08-21 at 23 40 56" src="https://github.com/YanaChe23/vk-bot/assets/103109416/4c965052-9e34-418d-ba20-cd6b8a8feb24">


You can use [ngrok](https://ngrok.com/docs/getting-started/) as a server to connect the locally running application with a vk.com public.
Use port 80 to run ngrok 
> ngrok http 80

To connect the chatbot and a vk.com public, go to Callback API section and provide server (or ngrok) URL, then press "Confirm".
In case of a successful connection, you will see a message "Server URL saved successfully". 
Now it's time to test a chatbot: send a message to the connected public, and the bot will reply. 


## Technologies
- Java 18 
- Spring Boot
- Spring MVC
- JUnit 5
- Docker

## Features 
The bot can be connected to a VK public, and it will echo text messages. When send something different to text, it will inform you that this option is not available yet. 

<img width="440" alt="Screenshot 2023-07-19 at 16 11 35" src="https://github.com/YanaChe23/vk-bot/assets/103109416/49592587-70cf-4680-9c5d-7fddd922e69a">

