import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
public class Restaurant implements Serializable{
    private int Id;
    private double Score;
    private String Name;
    private String Price;
    private String ZipCode;
    private String Category1;
    private String Category2 = null;
    private String Category3 = null;
    private String password;
  public  ArrayList <Food> Foods_list = new ArrayList<>();
  public ArrayList <Food> order_list = new ArrayList<>();
  public int recurrence(Food f, ArrayList <Food>list1 )
  {
    int count =0;
    String name = f.getName();
    for(int i =0;i<list1.size();i++)
    {
        if(list1.get(i).getName().equals(name))
        {
            count++;
        }
    }
    return count;
  }
  public void show_food_list()
  {
    System.out.println("food list of "+Name);
    for(int i =0;i<Foods_list.size();i++)
    {
        Foods_list.get(i).show_details_food();
    }
  }
   public void show_order_list()
  {
    if(order_list.size()==0)
    {
        System.out.println("order list is empty");
    }
    else{
    System.out.println("order list of "+Name);
    HashSet<Food> uniquePersons = new HashSet<>(order_list);

        ArrayList<Food> uniquePersonList = new ArrayList<>(uniquePersons);
    for(int i =0;i<uniquePersonList.size();i++)
    {
      //  order_list.get(i).show_details_food();
    
    int j =  recurrence(uniquePersonList.get(i), order_list);
      System.out.println(uniquePersonList.get(i).getName()+" :ordered times: "+j);
      
    }

}
  }
  public Restaurant(int Id,String Name,double Score,String Price,String ZipCode,String password,String Category1)

  {
this.Id = Id;
this.Name = Name;
this.Score = Score;
this.Price  = Price;
this.ZipCode = ZipCode;
this.Category1 = Category1;
this.password = password;
/*if(Category2_in != null)
{
    this.Category2 = Category2_in;
}
if(Category3 !=null)
{
    this.Category3 = Category3;
}*/
  }
  public void Set_Category2(String Category2)
  {
      this.Category2 = Category2;
  }
    public void Set_Category3(String Category3)
    {
        this.Category3 = Category3;
    }
    public boolean Is_category2()
    {
        if(this.Category2 == null)
        {
            return false;
        }
        return  true;
    }
    public boolean Is_category3()
    {
        if(this.Category3 == null)
        {
            return false;
        }
        return  true;
    }
    public String Get_Category2()
    {
        return  this.Category2;
    }
    public String Get_Category3()
    {
        return this.Category3;
    }
    public String Get_Category1()
    {
        return this.Category1;
    }

    public String getName()
    {
        return Name;
    }
    public double getScore()
    {
        return  Score;
    }
    public String getPrice()
    {
        return Price;
    }
    public String getZipCode()
    {
        return ZipCode;
    }
    public int getId()
    {
        return Id;
    }
    public String getPassword()
    {
        return password;
    }
    public void show_details()
    {

        System.out.println("Details of restaurant:");
        System.out.println("Id "+Id);
        System.out.println("Name "+Name);
        System.out.println("Score "+Score);
        System.out.println("Price "+Price);
        System.out.println("Zipcode "+ZipCode);
        System.out.println("password "+password);
        System.out.println("Category1 "+Category1);
        if(Category2 != null)
        System.out.println("Category2 "+Category2);
        if(Category3 != null)
        System.out.println("Category3 "+Category3);
        System.out.println(" ");
    }
    public void  add_food_restaurant(Food food)
    {
        Foods_list.add(food);
    }
    //food with name whether it exists
    public ArrayList<Food> is_food_name(String food_name)
    {
        ArrayList<Food>is_food_names = new ArrayList<>();
        String modified_food_name = food_name.toUpperCase();
        for (int i =0;i<Foods_list.size();i++)
        {
            String modified_get_name = Foods_list.get(i).getName().toUpperCase();
            if(modified_get_name.contains(modified_food_name))
            {
                is_food_names.add(Foods_list.get(i));
            }
        }
        return is_food_names;
    }
    //FOOD WITH CATEGORY WHETHER IT EXISTS
    public ArrayList<Food> is_food_category(String food_category)
    {
        ArrayList<Food>is_foods_category = new ArrayList<>();
        //String modified_food_name = food_name.toUpperCase();
        for (int i =0;i<Foods_list.size();i++)
        {
           // String modified_get_name = Foods_list.get(i).getName().toUpperCase();
          /*  if(modified_get_name.contains(modified_food_name))
            {
                is_food_names.add(Foods_list.get(i));
            }*/
            if(Foods_list.get(i).getCategory().equalsIgnoreCase(food_category))
            {
                is_foods_category.add(Foods_list.get(i));
            }
        }
        return is_foods_category;
    }

}
