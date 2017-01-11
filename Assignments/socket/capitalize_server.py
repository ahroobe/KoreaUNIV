#file name = capitalize_server.py
from socket import * #import socket module
serverPort=8888 #Arbitrary non-privileged PORT
serverSocket = socket(AF_INET, SOCK_STREAM)
#Bind HOST to port
serverSocket.bind(('',serverPort))
serverSocket.listen(1)#Listen to incoming Conection

print 'The server is ready to receive'

while 1:
    connectionSocket, addr = serverSocket.accept()
    message=connectionSocket.recv(1024)#Receive
    capitalizedMessage = message.upper() #capitalized
    serverSocket.send(('modified->'+capitalizedMessage))
    #Echo modified version of what the client sends
    connectionSocket.close()