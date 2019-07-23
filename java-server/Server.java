

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
		
		int portNumber = Integer.parseInt(args[0]);
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine(); //you get the IP as a String
			System.out.println("External IP: " + ip);
		} catch(IOException e) {
			System.out.println("Couldn't get local host address");
			return;
		}
		// report the localhost IP address
		try {
			InetAddress host = InetAddress.getLocalHost();
			String hostName = host.getHostName();

			System.out.println("Local host is " + host.toString());
			System.out.println("Local host name is " + hostName);
			System.out.println("Port is " + portNumber);

		} catch (UnknownHostException e) {
			System.out.println("Couldn't get local host address");
			return;
		}

		ServerSocket server;
		try {
			server = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println("IO exception trying to create server socket");
			return;
		}

        // keep listening indefinitely for client requests
		System.out.println("Waiting for a client request");
		while (true) {
			// wait for connection and get input stream
			Socket socket;
			try {
				socket = server.accept();
				System.out.println("Connected");
				new Thread(new Server(socket)).start();
			} catch (IOException e) {
				System.out.println("IO exception waiting for client request");
				return;
			}
		}
	}



	public void run() {
			// get input stream
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
		//System.out.println("split " + command[0]);//add,del,mod,get
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
				response = SqlAdd(conn);
			}
			else if (new String("del").equals(command[0])){
				
			}
			else if (new String("mod").equals(command[0])){
				
			}
			else {
				response = "404";
			}


			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println(response);
			writer.close();

			conn.close();
		} catch (IOException | SQLException e) {
			System.out.println("Error with Response");
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
			} catch (SQLException e){return "error";}
	}
	private String SqlAdd(Connection conn){
		try{
			if (new String("user").equals(command[1]) && command.length == 4){
				Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
				String query = " insert into users (username, password, join_date)" + " values (?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
      	preparedStmt.setString (1, command[2]);
      	preparedStmt.setString (2, command[3]);
      	preparedStmt.setDate   (3, startDate);
     		int i = preparedStmt.executeUpdate();
     		if (i > 0) {
            return "success";
        } else {
            return "error";
        }
      }
      return "error";
        } catch (SQLException e){return "error";}
	}













	private String BuildQuery(){
		String query = "";
		if (new String("test").equals(command[1])){
			query = "SELECT id,data FROM test";
		}
		else if (new String("login").equals(command[1])){
			query = "Access Deined!";
		}
		else {
			query = "Error no such command <" + command[1] + ">";
		}
		return query;


	}
	private String BuildResponse(ResultSet rs){
		try{
					String d = "";
					if (new String("test").equals(command[1])){
						while(rs.next()){
						d += "test ID= "+rs.getInt("id") + " data = " + rs.getString("data") + ", ";
					}
				}
					else{
						d = "404";
					}
			
			return d;
		} catch (SQLException e){return null;}
	}
}



