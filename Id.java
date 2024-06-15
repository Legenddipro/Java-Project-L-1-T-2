import java.io.Serializable;

public class Id implements Serializable  {
    private int id;
    private String password; 
    private String restaurant_name1;
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password1) {
        this.password = password1;
    }
    public String getrestaurant_name() {
        return restaurant_name1;
    }
    public void setrestaurant_name(String restaurant_name) {
        this.restaurant_name1 = restaurant_name;
    }
}
