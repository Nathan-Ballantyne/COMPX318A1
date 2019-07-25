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
### Commands
```
Code: when returning if code = 0 it was successful, if only returns 0 with nothing else there wasn't any data in database for what was selected

add user <username> <password>
returns <code> if successful

add event <location name> <start time> <end time> <user id>
returns <code> if successful

mod event <event id> <location name> <start time> <end time> <user id>
returns <code> if successful
will add event if theres no id

mod attend <user id> <event id> <attending int>
returns <code> if successful
will create attending in database if a row without user id and event id excists

get eventOwner <event id>
returns <code> <username>

get username <user id>
return <code> <username>

get userid <username>
return <code> <user id>

get eventByLoc <event name>
return <code> {event id,start time, end time, user who created}
{} = list

get eventById <event id>
return <code> event name,start time, end time, user who created

get attendance <event id>
returns <code> {username, int code if going}

get eventsByOwner <user id>
returns <code> {event id}

get eventsByTime <start time min> <start time max>
returns <code> {event id, event name, start time, end time, user id}

get allEvents
returns <code> {event id, event name, start time, end time, user id}
```

