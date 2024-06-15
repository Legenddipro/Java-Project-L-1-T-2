import util.SocketWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
public class Read_thread_server implements Runnable{
    private Thread thr;
    private SocketWrapper socketWrapper;
    private Manager manager1;
    static int customers;
    public HashMap<String, SocketWrapper> clientMap;
    public Read_thread_server(SocketWrapper socketWrapper,Manager manager1,HashMap<String, SocketWrapper> map) {
         this.manager1 = manager1;
         this.clientMap = map;
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
       
    }
    public void run() {
        try {
            
            while (true) {
     /*    static {
            customers = 0;
         }*/
         customers = 0;
                Object o = socketWrapper.read();
   if(o instanceof Id)
   {
    Id a1 = (Id) o;
    String s =    a1.getrestaurant_name();
        SocketWrapper nu = clientMap.get(s.toUpperCase());
    if(manager1.is_correct_pass(a1.getId(),a1.getPassword())){
        Restaurant r = manager1.find_restaurant_by_id(a1.getId());
        if(r!=null){
          
      //  socketWrapper.write(r);
      if (nu != null) {
        nu.write(r);
    }
        }
    }
    else{
      //  System.out.println("invalid id or password");
     String s1 = "invalid id or password";
     // socketWrapper.write(s1);
      if (nu != null) {
        nu.write(s1);
    }
    }

   }   
   else if(o instanceof Id_customer)
   {
   customers++;
   Id_customer a2 = (Id_customer)o;
   String s1 = a2.getCustomer_name();
   SocketWrapper nu1 = clientMap.get(s1.toUpperCase());
   System.out.println("customer found ");
   if(nu1 != null)
   {
   nu1.write(manager1);
   }
   
   }   
   else if(o instanceof Class_carts)
   {
    Class_carts c = (Class_carts)o;
    ArrayList<Food>Carts2 = new ArrayList<>();
    Carts2 = c.getCartlist();
    System.out.println("Carts  of  :"+c.getCustomer_name5());
    for(int i =0;i<Carts2.size();i++)
    {
        Carts2.get(i).show_details_food();
        String restaurant_name = Carts2.get(i).getRestaurant_name().toUpperCase();
        SocketWrapper nu3 = clientMap.get(restaurant_name);
        nu3.write(Carts2.get(i));
    }
     SocketWrapper nu2 = clientMap.get(c.getCustomer_name5().toUpperCase());
     String s = "ordered placed succesfully";
      if(nu2 != null)
   {
   nu2.write(s);
   }
   }                
            }
        } catch (Exception e) {
            System.out.println("read thread of server except");
            System.out.println(e);
        }
        finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
