import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        
		if (args.length < 3) {
			System.out.println("Usage:  client <host> <port> <message>");
			return;
		}
		
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
		
		// establish socket connection to server
		Socket socket;
		try {
			socket = new Socket(hostName, portNumber);
		} catch (UnknownHostException e) {
			System.out.println("Couldn't establish socket connection");
			return;
		} catch (IOException e) {
			System.out.println("IO exception on attempting to connect socket");
			return;
		}
 		String message = "";
 		for (int i = 2; i < args.length;i++){
 			message += args[i];
 			if (i != args.length - 1){
 				message += " ";
 			}
 		}
        // write to socket using OutputStream
		try {
			OutputStream os;
			os = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(os);
			writer.println(message);  // 11
			writer.flush();
			InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
			BufferedReader reader = new BufferedReader(streamReader);
			String responseMessage = reader.readLine();
			System.out.println(responseMessage);
			reader.close();
			writer.close();
			os.close();
			
		} catch (IOException e) {
			System.out.println("IO exception trying to write to socket");
			return;
		}

    }
}
