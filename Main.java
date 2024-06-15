import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import util.SocketWrapper;
public class Main {
    public static Manager manager;
    public static SocketWrapper socketWrapper;
    public static int Customer_id;
    public static String Customer_name;
    private static final String INPUT_FILE_NAME = "restaurant.txt";
    private static final String INPUT_FILE_NAME_2 = "menu.txt";
    static  Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
       // Manager manager = new Manager();
        String choice;
        int choice_int;
   /*     
 //  load_input_restaurant
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            //  System.out.println(line);
            String [] array = line.split(",",-1);
            

            
           
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
        //
//end input menu
*/
       // Scanner scanner = new Scanner(System.in);
       // System.out.println(manager.restaurant_Array.size());
        do {
            System.out.println("Main Menu:");
            System.out.println("1) Search Restaurants");
            System.out.println("2) Search Food Items & order");
         //   System.out.println("3) Add Restaurant");
          //  System.out.println("4) Add Food Item to the Menu");
            System.out.println("3) Exit System");
            System.out.println("4)PLACE ORDER");
            System.out.println("enter option of main menu");
            choice = scanner.nextLine();
            choice_int = Integer.parseInt(choice);
            switch (choice_int) {
                case 1:
                    Handle_restaurant_search();

                    break;
                case 2:
Handle_Food_Search();
                    break;
                      case 4:
  Place_order();
                      break;
                    /* 
                case 3:
handle_restaurant_add();
                    break;
                case 4:
handle_food_add();
                    break;
                     */
                case 3:
                    System.out.println("exit");
//manager.write_to_file_in_restaurant();
//manager.write_to_file_in_menu();
                    scanner.close();
                    break;
                default:
                    System.out.println("invalid option");
            }
        } while (choice_int != 3);
    }
    //PLACING ORDER.........................
    public static void Place_order()
    {
  try {
  /*  Id_customer a2 = new Id_customer();
    a2.setCustomer_name(Customer_name);
    a2.setId(Customer_id);
    a2.setCarts(manager.Carts);*/
   Class_carts o = new Class_carts();
   o.setCustomer_name5(Customer_name);
   o.setCartlist(manager.Carts);
 /* System.out.println("Carts    :");
  for(int i =0;i<manager.Carts.size();i++)
  {
      manager.Carts.get(i).show_details_food();
  }*/
  socketWrapper.write(o);
  manager.Carts.clear();
  Object o1= socketWrapper.read();
  if(o1 instanceof String){
    String s2 = (String ) o1;
  System.out.println(s2);
  }
} catch (Exception e) {
    System.out.println("cart transfer to server from customer exception");
    e.printStackTrace();
}      
    }
    //HANDLING RESTAURANT SEARCH
        public  static void Handle_restaurant_search()
        {
            //Scanner scanner = new Scanner(System.in);
            String search_choice;
            int search_choice_int;
            do {
                System.out.println("Restaurant Searching Options:");
                System.out.println("1) By Name");
                System.out.println("2) By Score");
                System.out.println("3) By Category");
                System.out.println("4) By Price");
                System.out.println("5) By Zip Code");
                System.out.println("6) Different Category Wise List of Restaurants");
                System.out.println("7) Back to Main Menu");
                System.out.println(" ");
                System.out.println("enter option of search");
                //System.out.println("Enter choice for search");
                search_choice = scanner.nextLine();
                search_choice_int = Integer.parseInt(search_choice);
                switch (search_choice_int)
                {
                    case 1:
                        System.out.println("Enter restaurant name");
                        String restaurant_name = scanner.nextLine();
                        Restaurant r1 = manager.search_restaurant_name(restaurant_name);
                        if(r1 == null)
                        {
                            System.out.println("No restaurant exists of this name");
                        }
                        else
                     r1.show_details();
                        break;
                    case  2:
                        System.out.println("Enter low Range of Restaurant");

                        String low_range = scanner.nextLine();
                        System.out.println("Enter high Range of Restaurant");
                        String high_range = scanner.nextLine();
         double low_range_double = Double.parseDouble(low_range);
                        double high_range_double = Double.parseDouble(high_range);
//use arraylist
 ArrayList<Restaurant>r2 = manager.search_restaurant_Range(low_range_double,high_range_double);
                        if(r2.size() == 0)
                        {
                            System.out.println("No restaurant exists of this range");
                        }
                        else
                        {
                            System.out.println("Restaurant in Ranges");
                            for(int i =0;i<r2.size();i++)
                            {
                                r2.get(i).show_details();

                            }
                        }

                        break;
                    case 3:
                        System.out.println("Enter Restaurant Category");
                        String in_category = scanner.nextLine();
                ArrayList <Restaurant> r3 = manager.search_restaurant_category(in_category);
                        if(r3.size() == 0)
                        {
                            System.out.println("No restaurant exists of this category");
                        }
                        else
                        {
                            System.out.println("Restaurant in Category:");
                            for(int i =0;i<r3.size();i++)
                            {
                                r3.get(i).show_details();

                            }
                        }

                        break;
                    case 4:
                        System.out.println("Enter price");
                        String in_price = scanner.nextLine();
                        ArrayList <Restaurant> r4 = manager.search_restaurant_price(in_price);
                        if(r4.size() == 0)
                        {
                            System.out.println("No restaurant exists of this price");
                        }
                        else
                        {
                            System.out.println("Restaurant in Price:");
                            for(int i =0;i<r4.size();i++)
                            {
                                r4.get(i).show_details();

                            }
                        }
                        break;
                    case 5:
                        System.out.println("Enter zipcode");
                        String in_code = scanner.nextLine();
                        ArrayList <Restaurant> r5 = manager.search_restaurant_code(in_code);
                        if(r5.size() == 0)
                        {
                            System.out.println("No restaurant exists of this code");
                        }
                        else
                        {
                            System.out.println("Restaurant in Code:");
                            for(int i =0;i<r5.size();i++)
                            {
                                r5.get(i).show_details();

                            }
                        }
                        break;
                    case 6:
 /*        //             //  System.out.println("Categories size"+Categories.size());
for(int i =0;i<Categories.size();i++)
{
    System.out.print(Categories.get(i)+":");
    ArrayList <Restaurant> r6 = manager.search_restaurant_category(Categories.get(i));
    for(int j =0;j<r6.size();j++)
    {
        System.out.print(r6.get(j).getName()+",");

    }
    System.out.println(" ");
}*/
                        manager.print_category_wise_restaurant();
                        break;
                    case 7:
                        System.out.println("Backed to Main menu");
                        break;
                    default:
                        System.out.println("invalid option of search");
                }
            }while (search_choice_int!=7);

            // break;
        }
//for food search handle
    public  static void Handle_Food_Search()
    {
        String search_food_choice;
        int search_food_choice_int;
        do {
            System.out.println("Food Item Searching Options:");
            System.out.println("1) By Name");
            System.out.println("2) By Name in a Given Restauran");
            System.out.println("3) By Category");
            System.out.println("4)By Category in a Given Restaurant ");
            System.out.println("5) By Price Range");
            System.out.println("6) By Price Range in a Given Restaurant");
            System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restauran");
            System.out.println("8)List of Restaurants and Total Food Item on the Menu ");
            System.out.println("9)Back to Main Menu");
            System.out.println(" ");
            System.out.println("enter option of search");
            search_food_choice = scanner.nextLine();
            search_food_choice_int= Integer.parseInt(search_food_choice);
            switch (search_food_choice_int) {
                case 1:
                    search_food_name();
                    break;
                case 2:
search_food_name_with_restaurant();
                    break;
                case 3:
                    search_food_by_category();
                    break;
                case 4:
search_food_by_category_in_restaurant();
                    break;
                case 5:
search_foods_within_price_range();
                    break;
                case 6:
                    search_foods_within_price_range_in_restaurant();
                    break;
                case 7:
                    costliest_item_on_restaurant();
                    break;
                case 8:
                    show_number_of_items();
                    break;
                case 9:
                    System.out.println("Backed to main menu");
                    break;
                default:
                    System.out.println("invalid option");
            }
        }while(search_food_choice_int!=9);
        }
        public static void search_food_name() {
            System.out.println("Enter name of food");
            String food_name = scanner.nextLine();
            ArrayList<Food> searched_food_name = manager.search_food_name1(food_name);
            if (searched_food_name.size() == 0) {
                System.out.println("No food exists of this name");
            } else {
                System.out.println(food_name+" :");
                for (int i = 0; i < searched_food_name.size(); i++) {
                    System.out.println("index:  "+i);
                    searched_food_name.get(i).show_details_food();
                }
              
       handle_food_order(searched_food_name);
            }
        }
     //handle FOOD ORDER...............................
     public  static void handle_food_order( ArrayList<Food>foods5)
     {
 System.out.println("do you want to order if you want,just write 1,if not write -1");
         String bool2 = scanner.nextLine();
         int bool1 = Integer.parseInt(bool2);
         if(bool1!=-1)
         {
           ArrayList<Food>ordered = manager.make_order(foods5);
           System.out.println("ordered foods   ");
            for (int i = 0; i < ordered.size(); i++) {
                    System.out.println("index:  "+i);
                    ordered.get(i).show_details_food();
                }
         }  
     }
        public  static void search_food_name_with_restaurant()
        {
            System.out.println("enter food name");
String food_name_2 = scanner.nextLine();
            System.out.println("enter restaurant name");
            String restaurant_name2 = scanner.nextLine();

 ArrayList<Food>searched_foods_in_restaurant =   manager.search_food_in_given_restaurant(food_name_2,restaurant_name2);
if(searched_foods_in_restaurant.size()==0)
{
    System.out.println("No such food of this name does not exists in restaurant  "+restaurant_name2);
}
else {
    for(int i =0;i<searched_foods_in_restaurant.size();i++)
    {
         System.out.println("index:  "+i);
        searched_foods_in_restaurant.get(i).show_details_food();
    }
    handle_food_order(searched_foods_in_restaurant);
}

        }
        //SEARCH FOOD NAME WITH CATEGORY
    public  static void search_food_by_category()
    {
        System.out.println("enter category");
        String category =scanner.nextLine();
        ArrayList<Food>foods_of_category = manager.search_foods_with_category(category);
        if (foods_of_category.size() == 0) {
            System.out.println("No food exists of this category");
        } else {
            System.out.println(category+" :");
            for (int i = 0; i < foods_of_category.size(); i++) {
                  System.out.println("index:  "+i);
                foods_of_category.get(i).show_details_food();
            }
            handle_food_order(foods_of_category);
        }
    }
    //SEARCH FOOD NAME IN A RESTAURANT BY CATEGORY
    public static void search_food_by_category_in_restaurant()
    {
        System.out.println("enter category");
        String category =scanner.nextLine();
        System.out.println("enter restaurant name");
        String restaurant_name2 = scanner.nextLine();
ArrayList<Food>foods_of_category_in_restaurant = manager.search_food_by_category_in_given_restaurant(category,restaurant_name2);
        if (foods_of_category_in_restaurant.size() == 0) {
            System.out.println("No food exists of this category in this restaurant");
        } else {
            System.out.println(category+"in restaurant "+restaurant_name2+" :");
            for (int i = 0; i < foods_of_category_in_restaurant.size(); i++) {
             System.out.println("index:  "+i);    
                foods_of_category_in_restaurant.get(i).show_details_food();
            }
            handle_food_order(foods_of_category_in_restaurant);
        }
    }
   //SEARCH FOOD ITEMS WITHIN A PRICE RANGE
    public static void search_foods_within_price_range()
    {
        System.out.println("Enter low range of price");
        String low_range = scanner.nextLine();
        System.out.println("enter high range of price");
String high_range = scanner.nextLine();
   Double low_range_price = Double.parseDouble(low_range);
        Double high_range_price = Double.parseDouble(high_range);
        ArrayList<Food> foods_of_range = manager.search_foods_range_price(low_range_price,high_range_price);
        if (foods_of_range.size() == 0) {
            System.out.println("No food exists of this range");
        } else {
            System.out.println("FOODS IN RANGE "+low_range+" - "+high_range+ " :");
            for (int i = 0; i < foods_of_range.size(); i++) {
                 System.out.println("index:  "+i);
                foods_of_range.get(i).show_details_food();
            }
            handle_food_order(foods_of_range);
        }
    }
    //SEARCH FOOD ITEMS WITHIN A PRICE RANGE IN A RESTAURANT
    public static void search_foods_within_price_range_in_restaurant()
    {
        System.out.println("Enter low range of price");
        String low_range = scanner.nextLine();
        System.out.println("enter high range of price");
        String high_range = scanner.nextLine();
        Double low_range_price = Double.parseDouble(low_range);
        Double high_range_price = Double.parseDouble(high_range);
        System.out.println("enter restaurant name");
        String restaurant_name2 = scanner.nextLine();
     ArrayList<Food>foods_within_price_range_in_restaurant =manager.search_foods_range_price_in_restaurant(low_range_price,high_range_price,restaurant_name2);
     if(foods_within_price_range_in_restaurant.size() == 0)
     {
         System.out.println("No food exists of this range in this resataurant");
     }
     else
     {
         System.out.println("Foods wihin price range in this restaurant");
         print_list_foods(foods_within_price_range_in_restaurant);
         handle_food_order(foods_within_price_range_in_restaurant);
     }
    }
    //TO PRINT ARRAYLIST OF FOODS
    public static void print_list_foods(ArrayList<Food>food_list)
    {
        for (int i = 0; i < food_list.size(); i++) {
             System.out.println("index:  "+i);
            food_list.get(i).show_details_food();
        }
    }
    //TO GET COSTLIEST ITEMS ON A RESTAURANT
    public static void costliest_item_on_restaurant()
    {
        System.out.println("enter restaurant name");
        String restaurant_name2 = scanner.nextLine();
  ArrayList<Food>costliest_foods = manager.costliest_items_in_restaurant(restaurant_name2);
        System.out.println("costliest_foods ");
        print_list_foods(costliest_foods);
        handle_food_order(costliest_foods);

    }
    public static void show_number_of_items()
    {
        manager.number_of_foods_in_restaurant();
    }
    //HANDLE RESTAURANT ADD
    public static void handle_restaurant_add()
    {
    //    System.out.println("enter Id");
     //   String id = scanner.nextLine();

       // int id_int = Integer.parseInt(id);
        int id_int = manager.restaurant_Array.size()+1;
        System.out.println("enter name");
        String name = scanner.nextLine();
        if(manager.is_restaurant_name(name) == -1) {
            System.out.println("enter score");
            String score = scanner.nextLine();
            double score_double = Double.parseDouble(score);
            System.out.println("enter price");
            String price = scanner.nextLine();

            System.out.println("enter zipcode");
            String zipcode = scanner.nextLine();
            System.out.println("enter password");
            String password1 = scanner.nextLine();
            System.out.println("Enter number of categories");
            String no = scanner.nextLine();
            int no_int = Integer.parseInt(no);
            System.out.println("Enter Category 1");
            String category_1 = scanner.nextLine();
            manager.add_category_name(category_1);
            Restaurant r = new Restaurant(id_int, name, score_double, price, zipcode,password1, category_1);
            if (no_int > 1) {
                System.out.println("enter category2");
                String category_2 = scanner.nextLine();
                r.Set_Category2(category_2);
                manager.add_category_name(category_2);
            }
            if (no_int > 2) {
                System.out.println("enter category3");
                String category_3 = scanner.nextLine();
                r.Set_Category3(category_3);
                manager.add_category_name(category_3);
            }


            manager.Add_restaurant(r);
        }
        else {
            System.out.println("the inputted name of restaurant alreadY exists");
        }
       // System.out.println("restaurant added succesfully");
    }
    //HANDLE FOOD ADD
    public static void handle_food_add()
    {
        System.out.println("Enter restaurant name");
        String restaurant_name = scanner.nextLine();
      int p =  manager.find_index_of_restaurant_by_name(restaurant_name);
        if(p!=-1)
        {
            int id = p+1;
            System.out.println("enter category");
            String category = scanner.nextLine();
            System.out.println("enter name");
            String name = scanner.nextLine();
            System.out.println("enter price");
            String price = scanner.nextLine();
            double price_double = Double.parseDouble(price);
           Food food_1 = new Food(id,category,name,price_double);
           manager.add_food_file(id,food_1);
          //  System.out.println("food item added succesfully");

          //  manager.restaurant_Array.get(i).getName();
        }
    }
    }




