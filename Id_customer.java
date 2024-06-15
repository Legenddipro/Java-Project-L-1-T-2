import java.io.Serializable;
import java.util.ArrayList;
public class Id_customer implements Serializable  {
    private int id;
    private String customer_name;
    private ArrayList<Food>carts1 = new ArrayList<>();
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
     public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
       this.customer_name = customer_name;
    }
    public void setCarts(ArrayList<Food>carts1 ) {
       this.carts1 = carts1;
    }
     public ArrayList<Food> getCarts() {
        return carts1;
    }
}