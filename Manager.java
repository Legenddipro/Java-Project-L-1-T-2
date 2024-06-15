import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager implements Serializable{
   public ArrayList<Restaurant>restaurant_Array = new ArrayList<>();
    private static final String OUTPUT_FILE_NAME = "restaurant.txt";
    private static final String OUTPUT_FILE_NAME_2 = "menu.txt";
      ArrayList<String>Categories = new ArrayList<>();
    public  ArrayList<Food>Carts = new ArrayList<>();
   static Scanner scn1 = new Scanner(System.in);
           //to make order................
    public  ArrayList<Food> make_order(ArrayList<Food>foods4)
    {
        while(true)
        {
       System.out.println("enter index or if you want to stop enter -1");
     String id4 = scn1.nextLine();
     int id5 = Integer.parseInt(id4);
     if(id5 == -1)
     {
        break;
     }
     Carts.add(foods4.get(id5));
        }
        return Carts;
    }
      //to print foodlist of restaurant.............
      
      //to find whether password is correct.................
      public boolean is_correct_pass(int id2,String pass)
      {
        int flag =-1;
        for(int i=0;i<restaurant_Array.size();i++)
        {
            if(restaurant_Array.get(i).getId() == id2)
            {
                if(restaurant_Array.get(i).getPassword().equals(pass))
                {
                    
                    return true;
                }
            }
        }
       
            return false;
        
      }
      public Restaurant find_restaurant_by_id(int id3)
      {
       for(int i=0;i<restaurant_Array.size();i++)
       {
         if(restaurant_Array.get(i).getId() == id3)
         {
            return restaurant_Array.get(i);
         }
       } 
       return null;
      }
    //seach RESTAURANT by NAME
public Restaurant search_restaurant_name(String restaurant_name)
{
    int search_index = -1;
    for(int i =0;i< restaurant_Array.size();i++)
    {
        Restaurant r = restaurant_Array.get(i);
        if(r.getName().equalsIgnoreCase(restaurant_name))
        {
            search_index = i;
            return r;
        }
    }
   return null;
}
//seach RESTAURANT by range
public ArrayList<Restaurant> search_restaurant_Range(double low, double high)
{
    ArrayList<Restaurant> restaurant_list = new ArrayList<>();
    for(int i =0;i< restaurant_Array.size();i++)
    {
        Restaurant r = restaurant_Array.get(i);
        double p = r.getScore();
        if(p>=low && p<= high)
        {
           // search_index = i;
            restaurant_list.add(r);
        }

    }
    return restaurant_list;

}
//search restaurant by category
public ArrayList<Restaurant> search_restaurant_category(String category1)
{
    ArrayList<Restaurant> restaurant_list = new ArrayList<>();
    for(int i =0;i< restaurant_Array.size();i++)
    {
        Restaurant r = restaurant_Array.get(i);

        if(r.Get_Category1().equalsIgnoreCase(category1)) {
        restaurant_list.add(r);
        }
if(r.Is_category2())
{
    if(r.Get_Category2().equalsIgnoreCase(category1))
    {
        restaurant_list.add(r);
    }
}
        if(r.Is_category3())
        {
            if(r.Get_Category3().equalsIgnoreCase(category1))
            {
                restaurant_list.add(r);
            }
        }

    }
    return restaurant_list;

}
//Search restaurant by price
public ArrayList<Restaurant> search_restaurant_price(String price1)
{
    ArrayList<Restaurant> restaurant_list = new ArrayList<>();
    for(int i =0;i< restaurant_Array.size();i++)
    {
        Restaurant r = restaurant_Array.get(i);

        if(r.getPrice().equalsIgnoreCase(price1)) {
            restaurant_list.add(r);
        }


    }
    return restaurant_list;

}
////Search restaurant by code
public ArrayList<Restaurant> search_restaurant_code(String code)
{
    ArrayList<Restaurant> restaurant_list = new ArrayList<>();
    for(int i =0;i< restaurant_Array.size();i++)
    {
        Restaurant r = restaurant_Array.get(i);

        if(r.getZipCode().equalsIgnoreCase(code)) {
            restaurant_list.add(r);
        }


    }
    return restaurant_list;

}
//add category name
   public void add_category_name(String category_name)
    {
       //  ArrayList<String>Categories = new ArrayList<>();
        int flag1 =0;
        for(int i =0;i<Categories.size();i++)
        {
            if(Categories.get(i).equalsIgnoreCase(category_name))
            {
                flag1 =1;
                break;
            }

        }
        if(flag1 == 0)
        {
            Categories.add(category_name);
        }
    }
    //to print  category wise restaurant name
    public  void print_category_wise_restaurant()
    {
       // System.out.println("size "+Categories.size());
      //  System.out.println(" ");
        for(int i =0;i<Categories.size();i++)
        {
            ArrayList<Restaurant>r = search_restaurant_category(Categories.get(i));
            System.out.print(Categories.get(i)+":");
            for(int p =0;p<r.size();p++)
            {
                System.out.print(r.get(p).getName()+",");

            }
            System.out.println(" ");
        }
    }
//add restaurant
    public void Add_restaurant(Restaurant r1)
    {
        restaurant_Array.add(r1);
    }
    //SEE WHETHER THIS ID OF RESTAURANT WHETHER EXISTS
    public boolean check_id_restaurant(int id)
    {
        int p=-1;
        // String [] array = line.split(",", -1);
        //int id = Integer.parseInt(array[0]);
        for (int i =0;i<restaurant_Array.size();i++)
        {
            if(restaurant_Array.get(i).getId() == id) {
                p =i;
                break;
            }

        }
        if(p!=-1)
        {
            return true;
        }
        else {
            return false;
        }
    }
    //add food from file
    public  void  add_food_file(int id,Food food)
    {
        int p=-1;
       // String [] array = line.split(",", -1);
        //int id = Integer.parseInt(array[0]);
        for (int i =0;i<restaurant_Array.size();i++)
        {
            if(restaurant_Array.get(i).getId() == id) {
                p =i;
                break;
            }

        }
        if(p!=-1)
        {
            int flag =0;
            ArrayList<Food>foods1 = restaurant_Array.get(p).Foods_list;
for(int j =0;j<foods1.size();j++)
{

    if(foods1.get(j).getName().equalsIgnoreCase(food.getName()) && foods1.get(j).getCategory().equalsIgnoreCase(food.getCategory()))
    {
        flag =1;
        break;
    }
}
if(flag == 0)
{
    food.setRestaurant_name(restaurant_Array.get(p).getName());
      restaurant_Array.get(p).add_food_restaurant(food);
     // System.out.println("food item added succesfully");
}
if(flag == 1)
{
    System.out.println("food item did not add succesfully");
}

        }
    }
    //search food by name
    public ArrayList<Food> search_food_name1(String food_name2)
    {
        String modified_food_name2 = food_name2.toUpperCase();
    ArrayList<Food>food_names_searched = new ArrayList<>();
        ArrayList<Food>food_lists = new ArrayList<>();
    for(int i =0;i<restaurant_Array.size();i++)
    {
        food_lists = restaurant_Array.get(i).Foods_list;
        for(int j =0; j<food_lists.size();j++)
        {
            String get_name = food_lists.get(j).getName().toUpperCase();
            if(get_name.contains(modified_food_name2))
            {
                food_names_searched.add(food_lists.get(j));
            }
        }
    }
    return  food_names_searched;
    }
public  ArrayList<Food> search_food_in_given_restaurant(String food_name,String restaurant_name2)
{
    ArrayList<Food> searched_foods_in_restaurant = new ArrayList<>();
    int p =-1;
for(int i =0;i<restaurant_Array.size();i++)
{
    if(restaurant_Array.get(i).getName().equalsIgnoreCase(restaurant_name2))
    {
        p = i;
        break;
    }
}
if(p !=-1)
{
 searched_foods_in_restaurant = restaurant_Array.get(p).is_food_name(food_name);
}
return  searched_foods_in_restaurant;
}
//SERCH FOOD BY CATEGORY
    public ArrayList<Food> search_foods_with_category(String category)
    {
        ArrayList<Food>foods_in_category = new ArrayList<>();
        ArrayList<Food>food_lists = new ArrayList<>();
        for(int i =0;i<restaurant_Array.size();i++)
        {
            food_lists = restaurant_Array.get(i).Foods_list;
            for(int j =0; j<food_lists.size();j++)
            {
                if(food_lists.get(j).getCategory().equalsIgnoreCase(category))
                {
                    foods_in_category.add(food_lists.get(j));
                }
            }
        }
        return foods_in_category;
    }
    public  ArrayList<Food> search_food_by_category_in_given_restaurant(String food_category,String restaurant_name)
    {
        ArrayList<Food> searched_foods_in_restaurant_by_category = new ArrayList<>();
        int p =-1;
        for(int i =0;i<restaurant_Array.size();i++)
        {
            if(restaurant_Array.get(i).getName().equalsIgnoreCase(restaurant_name))
            {
                p = i;
                break;
            }
        }
        if(p!=-1)
        {
            searched_foods_in_restaurant_by_category = restaurant_Array.get(p).is_food_category(food_category);
        }
        return searched_foods_in_restaurant_by_category;
    }
    //SEARCH FOODS WITHIN GIVEN RANGE OF PRICE
    public  ArrayList<Food> search_foods_range_price(Double low,Double high)
    {
        ArrayList<Food>foods_in_range = new ArrayList<>();
        ArrayList<Food>food_lists = new ArrayList<>();
        for(int i =0;i<restaurant_Array.size();i++)
        {
            food_lists = restaurant_Array.get(i).Foods_list;
            for(int j =0; j<food_lists.size();j++)
            {
               /* if(food_lists.get(j).getCategory().equalsIgnoreCase(category))
                {
                    foods_in_category.add(food_lists.get(j));
                }*/
            Double p =    food_lists.get(j).getPrice();
            if(p>=low && p<=high)
            {
                foods_in_range.add(food_lists.get(j));
            }
            }
        }
        return foods_in_range;
    }
    //SEARCH FOODS WITHIN PRICE IN RESTAURANT
    public  ArrayList<Food> search_foods_range_price_in_restaurant(Double low,Double high,String restaurant_name) {
        ArrayList<Food> searched_foods_in_price_range_in_restaurant = new ArrayList<>();
        ArrayList<Food> food_lists = new ArrayList<>();
        int q = find_index_of_restaurant_by_name(restaurant_name);
        if (q != -1) {
            food_lists = restaurant_Array.get(q).Foods_list;
            for (int j = 0; j < food_lists.size(); j++) {
               /* if(food_lists.get(j).getCategory().equalsIgnoreCase(category))
                {
                    foods_in_category.add(food_lists.get(j));
                }*/
                Double p = food_lists.get(j).getPrice();
                if (p >= low && p <= high) {
                    searched_foods_in_price_range_in_restaurant.add(food_lists.get(j));
                }
            }
        }
        return  searched_foods_in_price_range_in_restaurant;
    }
    //FIND THE INDEX OF RESTAURANT BY USING NAME
    public int find_index_of_restaurant_by_name(String name)
    {
        int p=-1;
        for(int i =0;i<restaurant_Array.size();i++)
        {
            if(restaurant_Array.get(i).getName().equalsIgnoreCase(name))
            {
                p = i;
                break;
            }
        }
        if(p!=-1)
        {
            return p;

        }
        else {
            System.out.println("No restaurant exists of this name");
            return -1;
        }
    }
    //to find whether this name of restaurant exists or not
   public int is_restaurant_name(String name)
   {
       int p=-1;
       for(int i =0;i<restaurant_Array.size();i++)
       {
           if(restaurant_Array.get(i).getName().equalsIgnoreCase(name))
           {
               p = i;
               break;
           }
       }
       if(p!=-1)
       {
           return p;

       }
       else {
         //  System.out.println("No restaurant exists of this name");
           return -1;
       }
   }
    ////TO GET COSTLIEST ITEMS ON A RESTAURANT
    public ArrayList<Food> costliest_items_in_restaurant(String restaurant_name)
    {
        ArrayList<Food>costly_foods = new ArrayList<>();
        int p = find_index_of_restaurant_by_name(restaurant_name);
        double max_price = restaurant_Array.get(p).Foods_list.get(0).getPrice();
ArrayList<Food> foods_list = restaurant_Array.get(p).Foods_list;
for(int i =1;i<foods_list.size();i++)
{
    double price = foods_list.get(i).getPrice();
    if(price>max_price)
    {
        max_price = price;
    }
}
for(int i =0;i<foods_list.size();i++)
{
    double price1 = foods_list.get(i).getPrice();
    if(price1 == max_price)
    {
        costly_foods.add(foods_list.get(i));
    }
}
return costly_foods;
    }
//PRINT NUMBER OF ITEMS IN RESTAURANT
    public void number_of_foods_in_restaurant()
    {
        for(int i =0;i<restaurant_Array.size();i++)
        {
            System.out.print(restaurant_Array.get(i).getName()+" :");
            System.out.println(restaurant_Array.get(i).Foods_list.size());
        }
    }
    //RETURN THE ATTRIBUTES AS STRING OF RESTAURANT
    public String convert_restaurant_to_string(Restaurant r)
    {
        String id = String.valueOf(r.getId());
        String score = String.valueOf(r.getScore());
        String result = id+","+r.getName()+","+score+","+r.getPrice()+","+r.getZipCode()+","+r.Get_Category1();
        if(r.Is_category2() == true && r.Is_category3()== true)
        {
                    result = result+","+r.Get_Category2()+","+r.Get_Category3();
        } else if (r.Is_category2() == true) {
            result = result +","+r.Get_Category2();
        }
        return result;
    }
    public void write_to_file_in_restaurant()
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
            for(int i =0;i<restaurant_Array.size();i++)
            {
                String s = convert_restaurant_to_string(restaurant_Array.get(i));
bw.write(s);
                bw.write(System.lineSeparator());
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public String convert_food_to_string(Food food4)
    {
        String id = String.valueOf(food4.getRestaurant_Id());
        String price = String.valueOf(food4.getPrice());
        String result = id+","+food4.getCategory()+","+food4.getName()+","+price;
        return result;
    }

    public void write_to_file_in_menu()
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME_2));
            for(int i =0;i<restaurant_Array.size();i++)
            {
               /* String s = convert_restaurant_to_string(restaurant_Array.get(i));
                bw.write(s);
                bw.write(System.lineSeparator());*/
                ArrayList<Food>foods4 = restaurant_Array.get(i).Foods_list;
                for(int j=0;j<foods4.size();j++)
                {
                    String s = convert_food_to_string(foods4.get(j));
                    bw.write(s);
                    bw.write(System.lineSeparator());
                }
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
