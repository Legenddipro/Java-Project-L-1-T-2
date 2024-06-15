 import util.SocketWrapper;
 import java.util.Scanner;
public class Client_customer {
   private Manager manager;
   public Client_customer(String serverAddress, int serverPort) {
    try {
        System.out.print("Enter name of the client customer: ");
    //    Scanner scanner = new Scanner(System.in);
          Scanner input = new Scanner(System.in);
        String clientName = input.nextLine();
        SocketWrapper socketWrapper = new SocketWrapper(serverAddress, serverPort);
        socketWrapper.write(clientName);
        System.out.println("enter your user id");
      
        int id1 =   input.nextInt();
        Id_customer a1 = new Id_customer();
        a1.setId(id1);   
        a1.setCustomer_name(clientName);
        socketWrapper.write(a1);
        Object o = socketWrapper.read();
        if(o instanceof Manager)
        {
          //  System.out.println();
            System.out.println("search permission granted");
            Manager manager1 = (Manager) o;
            this.manager = manager1;
            Main.manager = this.manager;
            Main.socketWrapper = socketWrapper;
            Main.Customer_name = clientName;
            Main.Customer_id = id1;
            String str[] = {" "," "};
            Main.main(str);
           // this.manager = manager;

            /*
            
             */

        }
    } catch (Exception e) {
        
        System.out.println("constructor of client customer exception");
        System.out.println(e);
    }
}
public static void main(String args[]) {
    String serverAddress = "127.0.0.1";
    int serverPort = 40000;
    new Client_customer(serverAddress, serverPort);
}
}
