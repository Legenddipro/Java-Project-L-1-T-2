import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
public class Food implements Serializable {
private int Restaurant_Id;
private String Category;
private String restaurant_name;
private String Name;
private double price;
public int count;
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    //Person person = (Person) o;
    Food f = (Food) o;
  //  return age == person.age &&
         //   Objects.equals(name, person.name);
         if(Objects.equals(Name,f.getName() ))//f.getName().equals(Name)
         {
            return true;
         }
         else{
            return false;
         }
}
@Override
public int hashCode() {
    return Objects.hash(Name);
}
public Food(String line)
{
    String [] array = line.split(",", -1);
    int id = Integer.parseInt(array[0]);
    double price_1 =Double.parseDouble(array[3]);
    this.Restaurant_Id = id;
    this.Category = array[1];
    this.Name = array[2];
    this.price = price_1;
    this.count = 0;
}
public Food(int id,String category, String name, double price_1)
{
    this.Restaurant_Id = id;
    this.Category = category;
    this.Name = name;
    this.price = price_1;
}
public void setRestaurant_name(String restaurant_name)
{
   this.restaurant_name = restaurant_name;
}
public  void show_details_food()
{
    System.out.println("Food Name: "+Name);
    System.out.println("Restaurant: "+restaurant_name);
    System.out.println("Price: "+price);
    System.out.println("Category: "+Category);
    System.out.println(" ");
}
    public String getCategory() {
        return Category;
    }

    public String getName() {
        return Name;
    }
    public Double getPrice()
    {
        return price;
    }
    public int getRestaurant_Id()
    {
        return Restaurant_Id;
    }
    public String getRestaurant_name()
    {
        return restaurant_name;
    }
}
