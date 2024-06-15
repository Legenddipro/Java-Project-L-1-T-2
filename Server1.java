import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.HashMap;


import util.SocketWrapper;

public class Server1 {
    private ServerSocket serverSocket;
    public HashMap<String, SocketWrapper> clientMap;
     static Manager manager = new Manager();
    private static final String INPUT_FILE_NAME = "restaurant.txt";
    private static final String INPUT_FILE_NAME_2 = "menu.txt";
    static  Scanner scanner = new Scanner(System.in);
    //to create server socket................
    Server1() {
        clientMap = new HashMap<>();
        try {
            serverSocket = new ServerSocket(40000);
            System.out.println("waiting");
            int i =0 ;
            while (true) {
                Socket clientSocket = serverSocket.accept();
                i++;
                System.out.println("accepts "+i);
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }
public void serve(Socket clientSocket) throws IOException,ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        try {
            String clientName = (String) socketWrapper.read();

             clientMap.put(clientName.toUpperCase(), socketWrapper);
        } catch (ClassNotFoundException e) {
            System.out.println("client name read exception");
            e.printStackTrace();
        }
       
     new Read_thread_server(socketWrapper,manager,clientMap);
      //  new WriteThread(socketWrapper, "Server");
    }

    public static void main(String[] args) throws Exception {
       // Manager manager = new Manager();
        String choice;
        int choice_int;
 //  load_input_restaurant
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            //  System.out.println(line);
            String [] array = line.split(",",-1);
            /*for (int i = 0; i < array.length; i++) {
                // System.out.println(array[i]);

            }*/
            //int i =0;
            //System.out.print(line);
            //System.out.println(" - - ");
            int p = Integer.parseInt(array[0]);
            double q = Double.parseDouble(array[2]);
            Restaurant r = new Restaurant(p,array[1],q,array[3],array[4],array[5],array[6]);
            manager.add_category_name(array[6]);
            if(array.length>7) {
            r.Set_Category2(array[7]);
            manager.add_category_name(array[7]);
            }
            if(array.length>8)
            {
                r.Set_Category3(array[8]);
                manager.add_category_name(array[8]);
            }
            manager.Add_restaurant(r);

            //end error category
            }

        br.close();
       // Categories.add("Bakery");
        //load_input_menu
        BufferedReader br1 = new BufferedReader(new FileReader(INPUT_FILE_NAME_2));
        //
        while (true) {
            String line1 = br1.readLine();
            if (line1 == null) break;
          //  System.out.println(line);

           Food food = new Food(line1);
           String []array1 = line1.split(",",-1);
           //add food
            if(manager.check_id_restaurant(Integer.parseInt(array1[0])))
            {
                manager.add_food_file(Integer.parseInt(array1[0]),food);
            }
//manager.add_food_file(line1,food);
        }
     br1.close();

new Server1();
    
}
}
