#file name = socket_server.py
from socket import * #for sockets

#create an AF INET, STREAM socket(TCP)
serverSocket = socket(AF_INET, SOCK_STREAM)

#output
print 'Socket Created'

HOST = '' #Symbolic name meaning all available interfaces
PORT = 8000 #Arbitrary non-privileged PORT
#Bind HOST to port
serverSocket.bind((HOST,PORT))

print 'Socket bind complete'

#Listen to incoming Connection
serverSocket.listen(10)
print 'Socket now listening'

#wait to accept a connection - blocking Call
conn, addr= serverSocket.accept()

#display client information
print 'Connected with ' + addr[0]+ ':' +str(addr[1])

#Receive
data = conn.recv(1024)

#Echo what the client sends
conn.sendall(data)
conn.close()
print 'connection closed'
    
