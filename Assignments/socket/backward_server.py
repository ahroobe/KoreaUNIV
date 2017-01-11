#file name = backward_server.py
from socket import * #import socket module
serverPort=8008 #Arbitrary non-privileged PORT
serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('',serverPort))#Bind HOST to port
serverSocket.listen(1)
print 'The server is ready to receive'

while 1:
    connectionSocket, addr = serverSocket.accept()
    message=connectionSocket.recv(1024)#Receive
    backwardMessage = message[::-1] #backward
    serverSocket.send(('modified->'+backwardMessage))
    #Echo modified version of what the client sends
    connectionSocket.close()