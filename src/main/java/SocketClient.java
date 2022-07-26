import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

//            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//            System.out.println("Enter username");
//
//            String userName = myObj.nextLine();  // Read user input
//            System.out.println("Username is: " + userName);  // Output user input

            // Create Scanner
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter message to server:");

            while (true) {
                // Read user input
                String clientMessage = scanner.nextLine();

                //Write and new line and flush
                bufferedWriter.write(clientMessage);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String messageFromSocket = bufferedReader.readLine();
                System.out.println("Socket: " + messageFromSocket + ", Anymore messages?");

                if (clientMessage.equals("bye")) {
                    break;
                }

            }

            socket.close();
            inputStreamReader.close();
            outputStreamWriter.close();
            bufferedReader.close();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
