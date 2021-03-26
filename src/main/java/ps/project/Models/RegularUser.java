package ps.project.Models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REGULARUSER")
public class RegularUser extends User implements Serializable {

    private String name;
    private String accountCreationDate;
    private int walletAmount;
    private ArrayList<Car> favoritCars;

    public RegularUser() {
        // this form used by Hibernate
    }

    public RegularUser(String username, String password, String name, String accountCreationDate, int walletAmount) {
        super(username, password);
        this.name = name;
        this.accountCreationDate = accountCreationDate;
        this.walletAmount = walletAmount;
        favoritCars = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(String accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public int getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(int walletAmount) {
        this.walletAmount = walletAmount;
    }

    public List<Car> getFavoritCars() {
        return favoritCars;
    }

    public void addFavoritCar(Car car) {
        favoritCars.add(car);
    }

    public void setFavoritCars(ArrayList<Car> favoritCars) {
        this.favoritCars = favoritCars;
    }

    public void removeFavoritCar(Long id) {
        Car carRemoved = null;
        for (Car car : favoritCars) {
            if (car.getId().equals(id)) {
                carRemoved = car;
            }
        }
        favoritCars.remove(carRemoved);
    }
}
