
import java.util.Scanner;

import util.SocketWrapper;

public class Client_restaurant {
    public Restaurant my_restaurant;
    public int flag = -1;
     public Client_restaurant(String serverAddress, int serverPort) {
        try {
                System.out.print("Enter name of the client restaurant: ");
            Scanner scanner = new Scanner(System.in);
            String clientName = scanner.nextLine();
            SocketWrapper socketWrapper = new SocketWrapper(serverAddress, serverPort);
        
            socketWrapper.write(clientName);
            new Read_thread_restaurant(socketWrapper,this);
            new Write_thread_restaurant(socketWrapper,clientName,this);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setRestaurnt(Restaurant r)
    {
this.my_restaurant = r;
    }
     public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 40000;
        new Client_restaurant(serverAddress, serverPort);
    }
}
