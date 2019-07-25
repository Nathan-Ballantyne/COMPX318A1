

import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.sql.*;


public class Server implements Runnable {

	Socket socket;
	String data;
	String[] command;
	Server(Socket socket) {
		this.socket = socket;
		this.data = "";
		
	}

	public static void main(String args[]) {
		
		if (args.length != 1) {
			System.out.println("Usage:  server <port>");
			return;
		}
		try {
			int portNumber = Integer.parseInt(args[0]);
			InetAddress host = InetAddress.getLocalHost();
			String hostName = host.getHostName();
			System.out.println("Local host is " + host.toString());
			System.out.println("Local host name is " + hostName);
			System.out.println("Port is " + portNumber);
			ServerSocket server;
			server = new ServerSocket(portNumber);
			System.out.println("Waiting for a client request");
			while (true) {
				Socket socket;
				socket = server.accept();
				System.out.println("Connected");
				new Thread(new Server(socket)).start();
			}
		} catch (IOException e) {
			System.out.println("Error");
			return;
		}
	}



	public void run() {
		InputStream is;
		try {
			is = socket.getInputStream();
			String tmpData = "";
			InputStreamReader streamReader = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(streamReader);                
			tmpData = reader.readLine();
			synchronized(tmpData){
				Response(tmpData);
			}
			is.close();
			socket.close();
			System.out.println("Closing socket");
		} catch (IOException e) {
			System.out.println("Error");
			return;
		}				
	}









	private void Response(String msg){

		command = msg.split("\\s+");
		String SqlUsername = "CompUser";
		String SqlPassword = "CompPass";
		String SqlServer = "jdbc:mariadb://localhost:3306/comp";
		String response = "";
		try{
			Connection conn = DriverManager.getConnection(SqlServer,SqlUsername,SqlPassword);
			if (new String("get").equals(command[0])){
				response = SqlGet(conn);
			}
			else if (new String("add").equals(command[0])){
				if (new String("user").equals(command[1]) && command.length == 4){
					//check if username taken
					response = SqlAddUser(conn);
				}
				else if (new String("event").equals(command[1]) && command.length == 6){
					response = SqlAddEvent(conn);
				}

			}
			else if (new String("del").equals(command[0])){
				//user
			}
			else if (new String("mod").equals(command[0])){
			if (new String("event").equals(command[1]) && command.length == 7){
				response = SqlModEvent(conn);
			}
			else if (new String("attend").equals(command[1]) && command.length == 5){
response = SqlModAddend(conn);
			}
		}
			else {
				response = "1";
			}
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println(response);
			writer.close();

			conn.close();
		} catch (IOException | SQLException e) {
			System.out.println(e.toString());
		}
	}


	private String SqlGet(Connection conn){
		try{
			String query = BuildQuery();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String response = BuildResponse(rs);
			rs.close();
			stmt.close();
			return response;
		} catch (SQLException e){return e.toString();}
	}


	private String SqlAddUser(Connection conn){
		try{
			
				Calendar calendar = Calendar.getInstance();
				java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
				String query = " insert into users (username, password, join_date)" + " values (?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString (1, command[2]);
				preparedStmt.setString (2, command[3]);
				preparedStmt.setDate   (3, startDate);
				int i = preparedStmt.executeUpdate();
				if (i > 0) {
					return "0";
				} else {
					return "1";
				}
		} catch (SQLException e){return "1";}
	}
	private String SqlAddEvent(Connection conn){
		try{
			String query = " insert into event (loc_name, start_time, end_time, user_id)" + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString (1, command[2]);
				preparedStmt.setInt (2, Integer.parseInt(command[3]));
				preparedStmt.setInt (3, Integer.parseInt(command[4]));
				preparedStmt.setInt (4, Integer.parseInt(command[5]));
				int i = preparedStmt.executeUpdate();
				if (i > 0) {
					return "0";
				} else {
					return "1";
				}
		} catch (SQLException e){return "1";}
	}
	private String SqlModAddend(Connection conn){
try{
		String query = "INSERT INTO attend (user_id, event_id, attending) VALUES("+ command[2] +", "+ command[3] +", "+ command[4] +") ON DUPLICATE KEY UPDATE attending=" + command[4];
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
			//String response = BuildResponse(rs);
			rs.close();
			stmt.close();
			return "0";
		
		} catch (SQLException e){return "1";}
	}

	private String SqlModEvent(Connection conn){
try{
		String query = "INSERT INTO event (id, loc_name, start_time,end_time,user_id) VALUES("+ command[2] +", '"+ command[3] +"', "+ command[4] +", "+ command[5] +", "+ command[6] +") ON DUPLICATE KEY UPDATE loc_name='" + command[3] + "', start_time=" + command[4] + ", end_time=" + command[5]+", user_id=" + command[6];
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
			//String response = BuildResponse(rs);
			rs.close();
			stmt.close();
			return "0";
		
		} catch (SQLException e){return e.toString();}
	}








//username,userid,eventbyloc,eventbyid,attendancelist, allEventsbyowner
	private String BuildQuery(){
		String query = "";
		if (new String("eventOwner").equals(command[1])){
			query = "SELECT username FROM users u, event e WHERE u.id=e.user_id AND e.id=" + Integer.parseInt(command[2]);
		}
		else if (new String("username").equals(command[1])){
			query = "SELECT username FROM users WHERE id=" + Integer.parseInt(command[2]);
		}
		else if (new String("userid").equals(command[1])){
			query = "SELECT id FROM users WHERE username='" + command[2] + "'";
		}
		else if (new String("eventByLoc").equals(command[1])){
			query = "SELECT id, start_time, end_time, user_id FROM event WHERE loc_name='" + command[2] + "'";
		}
		else if (new String("eventById").equals(command[1])){
			query = "SELECT * FROM event WHERE id=" + command[2];
		}
		else if (new String("attendance").equals(command[1])){
			query = "SELECT username,attending FROM event e, users u, attend a WHERE u.id=a.user_id AND e.id=a.event_id AND e.id=" + command[2];
		}
		else if (new String("eventsByOwner").equals(command[1])){
			query = "SELECT id FROM event WHERE user_id=" + command[2];
		}
		else if (new String("eventsByTime").equals(command[1])){
			query = "SELECT id,loc_name,start_time,end_time,user_id FROM event WHERE start_time BETWEEN " + command[2] + " AND " + command[3];
		}
		else if (new String("EventsByAttend").equals(command[1])){//events user attending
			//not complete
		}
		else if (new String("allEvents").equals(command[1])){
			query = "SELECT * from event";
		}
		return query;
	}
	
	private String BuildResponse(ResultSet rs){
		try{
			String d = "0 ";
			if (new String("eventOwner").equals(command[1])){
				while(rs.next()){d += rs.getString("username");}
					//d += "test ID= "+rs.getInt("id") + " data = " + rs.getString("data") + ", ";
			}
			else if (new String("username").equals(command[1])){
				while(rs.next()){d += rs.getString("username");}
			}
			else if (new String("userid").equals(command[1])){
				while(rs.next()){d += rs.getInt("id");}
			}
			else if (new String("eventByLoc").equals(command[1])){
				while(rs.next()){d += rs.getInt("id") + "," + rs.getInt("start_time") + "," + rs.getInt("end_time") + "," + rs.getInt("user_id");}
			}
			else if (new String("eventById").equals(command[1])){
				while(rs.next()){d += rs.getString("loc_name") + "," + rs.getInt("start_time") + "," + rs.getInt("end_time") + "," + rs.getInt("user_id");}
			}
			else if (new String("attendance").equals(command[1])){
				while(rs.next()){d += rs.getString("username") + "," + rs.getInt("attending") + ",";}
			}
			else if (new String("eventsByOwner").equals(command[1])){
			while(rs.next()){d += rs.getInt("id") + ",";}
		}
		else if (new String("eventsByTime").equals(command[1])){
			while(rs.next()){d += rs.getInt("id") + "," +rs.getString("loc_name") + "," + rs.getInt("start_time") + ","+ rs.getInt("end_time") + ","+ rs.getInt("user_id") + ",";}
		}
		else if (new String("allEvents").equals(command[1])){
			while(rs.next()){d += rs.getInt("id") + "," +rs.getString("loc_name") + "," + rs.getInt("start_time") + ","+ rs.getInt("end_time") + ","+ rs.getInt("user_id") + ",";}
		}
			else{
				d = "1";
			}
			
			return d;
		} catch (SQLException e){return "1";}
	}
}



