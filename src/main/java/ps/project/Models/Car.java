package ps.project.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@Entity
@Table( name = "CARS" )
public class Car  implements Serializable {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    private String type;
    private String brand;
    private String model;
    private int price;
    private int stock;
    private int soldNumber;
    private byte[] image;
    private float rating=0;
    private int noEvaluation=0;
    private HashMap<String, Integer> userRated;
    private ArrayList<Comment> comment;


    public Car(){
        // this form used by Hibernate
    }

    public Car(String type, String brand,String model, int price, int stock, int soldNumber) {
        this.type = type;
        this.brand = brand;
        this.model=model;
        this.price = price;
        this.stock = stock;
        this.soldNumber = soldNumber;
        userRated=new HashMap<>();
        comment=new ArrayList<>();
    }

    public Car(String type, String brand,String model, int price, int stock, int soldNumber, byte[] image) {
        this.type = type;
        this.brand = brand;
        this.model=model;
        this.price = price;
        this.stock = stock;
        this.soldNumber = soldNumber;
        this.image=image;
        userRated=new HashMap<>();
        comment=new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(int soldNumber) {
        this.soldNumber = soldNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNoEvaluation() {
        return noEvaluation;
    }

    public void setNoEvaluation(int noEvaluation) {
        this.noEvaluation = noEvaluation;
    }

    public HashMap<String, Integer> getUserRated() {
        return userRated;
    }

    public void setUserRated(HashMap<String, Integer> userRated) {
        this.userRated = userRated;
    }

    public void addUserRated(String username, Integer rate){
        this.userRated.put(username,rate);
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ArrayList<Comment> getComment() {
        return comment;
    }

    public void setComment(ArrayList<Comment> comment) {
        this.comment = comment;
    }

    public void addComment(Comment comment){
        this.comment.add(comment);
    }

    public String toString(){
        return "Id:"+this.id+"  type:"+this.type+"  brand:"+this.brand+"  model:"+this.model+"  price:"+this.price+"  stock:"+this.stock+"  soldNumber:"+this.soldNumber;
    }
}
