import java.io.IOException;
import java.util.Scanner;

import util.SocketWrapper;
public class Write_thread_restaurant implements Runnable {
    private Thread thr;
    static  Scanner scanner = new Scanner(System.in);
    private SocketWrapper socketWrapper;
    private String restaurant_name2;
    private Client_restaurant client_restaurant;
    public Write_thread_restaurant(SocketWrapper socketWrapper,String restaurant_name2,Client_restaurant client_restaurant) {
        this.socketWrapper = socketWrapper;
        this.client_restaurant = client_restaurant;
        this.restaurant_name2 = restaurant_name2;
        this.thr = new Thread(this);
        thr.start();
    }
    public void run()
    {
    try {
        Scanner input = new Scanner(System.in);
  System.out.println("enter id");   
  String s = input.nextLine();
  int id1 = Integer.parseInt(s);
System.out.println("enter pass");
String s1 = input.nextLine();

  Id a = new Id();
  a.setId(id1);
  a.setPassword(s1);
  a.setrestaurant_name(restaurant_name2);
  socketWrapper.write(a);

  while(this.client_restaurant.flag ==-1)
  {
   Thread.sleep(300);
  int i =this.client_restaurant.flag;
  }
  String choice;
  int choice_int;
  do
  {
    System.out.println("1 SHOW FOOD LIST");
     System.out.println("2 SHOW ORDER LIST");
     System.out.println("3) Exit System");
     System.out.println("enter option of main menu");
     choice = scanner.nextLine();
     choice_int = Integer.parseInt(choice);
     switch(choice_int)
     {
        case 1:
        client_restaurant.my_restaurant.show_food_list();
        break;
        case 2:
       client_restaurant.my_restaurant.show_order_list(); 
        break;
        case 3:
         System.out.println("exit");
         scanner.close();
        break;
        default:
        System.out.println("invalid option");
     }
  }while (choice_int != 3);
    } catch (Exception e) {
        System.out.println("write thred exception of restaurant");
        System.out.println(e);
    }
   
    }
}
