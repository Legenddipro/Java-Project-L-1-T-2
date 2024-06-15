import java.io.Serializable;
import java.util.ArrayList;
public class Class_carts implements Serializable{
    private ArrayList<Food>carts1;
    private String customer_name;
    public void setCartlist(ArrayList<Food>carts1)
    {
        this.carts1 = carts1;
    }
    public ArrayList<Food> getCartlist()
    {
        return carts1;
    }
  public void setCustomer_name5(String customer_name)
    {
        this.customer_name = customer_name;
    }
    public String getCustomer_name5()
    {
        return customer_name;
    }
}
