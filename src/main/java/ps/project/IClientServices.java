package ps.project;

import ps.project.Models.Administrator;
import ps.project.Models.Car;
import ps.project.Models.RegularUser;
import ps.project.Models.User;

import java.io.IOException;
import java.util.List;

public interface IClientServices {

    void setConnection(Client.Connection connection);
    Client.Connection getConnection();
    List<User> findByColumnMessage(String column, String username) throws IOException, ClassNotFoundException;
    List<Administrator> findAdministrator(User user) throws IOException, ClassNotFoundException;
    List<RegularUser> findRegularUser(User user) throws IOException, ClassNotFoundException;
    List<Car> findAllCar() throws IOException, ClassNotFoundException;
    List<Car> findAllCarMostBuyed() throws IOException, ClassNotFoundException;
    List<Car> findAllCarPriceAsc() throws IOException, ClassNotFoundException;
    List<Car> findAllCarPriceDesc() throws IOException, ClassNotFoundException;
    List<Car> findByColumn(String column, String value) throws IOException, ClassNotFoundException;
    void updateUser(RegularUser regularUser) throws IOException;
    void updateCar(Car car) throws IOException;
    List<RegularUser> findAllUsers() throws IOException, ClassNotFoundException;
    void insertRegularUser(RegularUser regularUser) throws IOException, ClassNotFoundException;
    List<RegularUser> findByColumnRegularUser(String column, String value) throws IOException, ClassNotFoundException;
    void insertCar(Car car) throws IOException;
    void updateCarByCondition(String column,String value,String setColumn,String setValue) throws IOException;
    void updateRegularUserByCondition(String column,String value,String setColumn,String setValue) throws IOException;
    void deleteCarByCondition(String column,String value) throws IOException;
    void deleteRegularUserByCondition(String column,String value) throws IOException;
    List<Car> findAllCarOrderRating() throws IOException, ClassNotFoundException;
}
