import util.SocketWrapper;
import java.util.ArrayList;
import java.io.IOException;
public class Read_thread_restaurant implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    private Client_restaurant client_restaurant ;
  
    public Read_thread_restaurant(SocketWrapper socketWrapper,Client_restaurant client_restaurant) {
        this.client_restaurant = client_restaurant;
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
   
    }
    
    public void run() {
        try {
            while(true){
            Object o = socketWrapper.read();
            if(o instanceof Restaurant)
            {
               this. client_restaurant.flag =1;
                Restaurant r1 = (Restaurant) o;
                client_restaurant.setRestaurnt(r1);
                r1.show_details();
              //  System.out.println(this.client_restaurant.flag);
                //to show food lists
            /*  ArrayList<Food> food_list1 = r1.Foods_list;
                for(int i =0 ;i<food_list1.size();i++)
                {
                    food_list1.get(i).show_details_food();
                }*/

            }  
            else if(o instanceof String)
            {
                String s = (String) o;
                System.out.println(s);
            }
            else if (o instanceof Food)
            {
                 
                Food f = (Food) o;
              client_restaurant.my_restaurant.order_list.add(f);  
                /*
                System.out.println("ordered food  :");
                f.show_details_food();
                f.count = f.count +1;
             System.out.println("ordered times  "+f.count);*/
            }
        }
        } catch (Exception e) {
            System.out.println("read thread of res except");
           System.out.println(e);
        }
       
}
}
