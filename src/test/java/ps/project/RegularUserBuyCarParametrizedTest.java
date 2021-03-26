package ps.project;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ps.project.BusinessLogic.RegularUserBll;
import ps.project.Models.Car;
import ps.project.Models.RegularUser;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RegularUserBuyCarParametrizedTest {

    static Car[] mpCars() {
        return new Car[]{
                new Car("Wagon", "Audi", "A4", 2500, 10, 1, null),
                new Car("Hatchback", "BMW", "120D", 4000, 5, 3, null),
                new Car("Hatchback", "Citroen", "C4", 5000, 4, 0, null),
                new Car("Hatchback", "VW", "Golf 5", 6000, 4, 0, null),
                new Car("Coupe", "BMW", "420D", 13500, 4, 0, null),
                new Car("Sedan", "Skoda", "Octavia", 20500, 4, 0, null)
        };
    }

    @ParameterizedTest
    @MethodSource("mpCars")
    void test_MethodSource_Object(Car car) {
        IClientServices clientServices=mock(IClientServices.class);
        String id = "12";
        RegularUser regularUser = new RegularUser("Daniel","daniel","Daniel Dan","24.05.2020",6500);

        RegularUserBll regularUserBll = new RegularUserBll();
        regularUserBll.setClientServices(clientServices);

        ArrayList<Car> carList=new ArrayList<>();
        carList.add(car);
        try {
            when(clientServices.findByColumn("id", id)).thenReturn(carList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            RegularUser updateRegularUser = regularUserBll.buyCar(id, new RegularUser(regularUser.getUsername(), regularUser.getPassword(), regularUser.getName(), regularUser.getAccountCreationDate(), regularUser.getWalletAmount()));
            assertEquals( regularUser.getWalletAmount() - car.getPrice(), updateRegularUser.getWalletAmount());
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Insufficient money/stock insufficient");
        }
    }
}
