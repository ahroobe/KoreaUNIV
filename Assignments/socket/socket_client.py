#file name = socket_client.py
from socket import * #for sockets


#create an AF INET, STRAM socket (TCP)
clientSocket = socket(AF_INET, SOCK_STREAM)
#output
print 'Socket Created'

#get host ip
local_ip = gethostbyname(gethostname())
remotehost = 'www.google.com'
#get google's ip
remote_ip = gethostbyname(remotehost)

#print local ip and remote ip
print 'Ip address of ' + remotehost + ' is ' + remote_ip
print 'Ip address of local host is ' + local_ip

#port 80 for www.google.com_error
port = 80

#Connect to remote server
clientSocket.connect((remotehost,port))

print 'Socket Connected to '+ remotehost + ' on ip '+ remote_ip

#Send some data to remote server
message= "GET/HTTP/1.1\r\n\r\n"

#send_handle
clientSocket.sendall(message)
print'Message sent'

#Receiver
reply = clientSocket.recv(4096)
print 'reply received'

#Close connection
clientSocket.close()
print 'connection closed'