## Login Details

```
SSH Server: 116.251.192.118 port 22, username: compx318 password: compx318
database mariaDB: http://116.251.192.118/phpmyadmin, username: compx318 password: compx318
only ports 4040-4049 are open for Server

will log in directly to folder with java code, can use PuTTy/WinSCP on windows, or terminal if on linux
```
## Compile and Run
```
on server:
javac Server.java
java -cp ".:mariadb-java-client-2.4.2.jar" Server <port>

on own computer/uni
javac Client.java
java Client <ip> <port> <method> <command> <data>

e.g. java Client 116.251.192.118 4040 add user <user> <pass>
```
## Methods
```
methods <get,del,add,del>

add methods: add (currently only one)

add user <username> <password> 
Returns "success" or "error" on return to client
```

