package ps.project;

import ps.project.Models.Administrator;
import ps.project.Models.Car;
import ps.project.Models.RegularUser;
import ps.project.Models.User;

import java.io.IOException;
import java.util.List;

public class ClientServices implements IClientServices {

    private static Client.Connection connection = null;

    @Override
    public void setConnection(Client.Connection connection) {
        this.connection = connection;
    }

    @Override
    public Client.Connection getConnection(){
        return connection;
    }

    @Override
    public List<User> findByColumnMessage(String column, String username) throws IOException, ClassNotFoundException {

        connection.sendMessageToServer("findByColumnMessage");
        connection.sendMessageToServer(column);
        connection.sendMessageToServer(username);


        List<User> userList = (List<User>) connection.getMessage();
        return userList;

    }

    @Override
    public List<Administrator> findAdministrator(User user) throws IOException, ClassNotFoundException {

        connection.sendMessageToServer("findAdministratorByUser");
        connection.sendMessageToServer(user);
        List<Administrator> administratorList = (List<Administrator>) connection.getMessage();

        return administratorList;
    }

    @Override
    public List<RegularUser> findRegularUser(User user) throws IOException, ClassNotFoundException {

        connection.sendMessageToServer("findRegularUserByUser");
        connection.sendMessageToServer(user);
        List<RegularUser> regularUserList = (List<RegularUser>) connection.getMessage();

        return regularUserList;
    }

    @Override
    public List<Car> findAllCar() throws IOException, ClassNotFoundException {

        connection.sendMessageToServer("findAllCar");
        List<Car> carList=(List<Car>)connection.getMessage();
        return carList;

    }

    @Override
    public List<Car> findAllCarMostBuyed() throws IOException, ClassNotFoundException {
        connection.sendMessageToServer("findAllCarMostBuyed");
        List<Car> carList=(List<Car>)connection.getMessage();
        return carList;
    }

    @Override
    public List<Car> findAllCarPriceAsc() throws IOException, ClassNotFoundException {
        connection.sendMessageToServer("findAllCarPriceAsc");
        List<Car> carList=(List<Car>)connection.getMessage();
        return carList;
    }

    @Override
    public List<Car> findAllCarPriceDesc() throws IOException, ClassNotFoundException {
        connection.sendMessageToServer("findAllCarPriceDesc");
        List<Car> carList=(List<Car>)connection.getMessage();
        return carList;
    }

    @Override
    public List<Car> findAllCarOrderRating() throws IOException, ClassNotFoundException {
        connection.sendMessageToServer("findAllCarOrderRating");
        List<Car> carList=(List<Car>)connection.getMessage();
        return carList;

    }

    @Override
    public List<Car> findByColumn(String column, String value) throws IOException, ClassNotFoundException {

        connection.sendMessageToServer("findByColumnCar");
        connection.sendMessageToServer(column);
        connection.sendMessageToServer(value);


        List<Car> userList = (List<Car>) connection.getMessage();
        return userList;

    }

    @Override
    public void updateUser(RegularUser regularUser) throws IOException {
        connection.sendMessageToServer("updateUser");
        connection.sendMessageToServer(regularUser);
    }

    @Override
    public void updateCar(Car car) throws IOException {
        connection.sendMessageToServer("updateCar");
        connection.sendMessageToServer(car);
    }

    @Override
    public List<RegularUser> findAllUsers() throws IOException, ClassNotFoundException {

        connection.sendMessageToServer("findAllUsers");
        List<RegularUser> regularUserList=(List<RegularUser>) connection.getMessage();
        return regularUserList;

    }

    @Override
    public void insertRegularUser(RegularUser regularUser) throws IOException, ClassNotFoundException {
        System.out.println("!!!!!!!!!!!!!!!!!! AICI SE AJUNGE");
        connection.sendMessageToServer("insertRegularUser");
        connection.sendMessageToServer(regularUser);
    }

    @Override
    public List<RegularUser> findByColumnRegularUser(String column, String value) throws IOException, ClassNotFoundException {
        connection.sendMessageToServer("findByColumnRegularUser");
        connection.sendMessageToServer(column);
        connection.sendMessageToServer(value);

        List<RegularUser> regularUserList=(List<RegularUser>)connection.getMessage();
        return regularUserList;
    }

    @Override
    public void insertCar(Car car) throws IOException {
        connection.sendMessageToServer("insertCar");
        connection.sendMessageToServer(car);
    }

    @Override
    public void updateCarByCondition(String column,String value,String setColumn,String setValue) throws IOException {
        connection.sendMessageToServer("updateCarByCondition");
        connection.sendMessageToServer(column);
        connection.sendMessageToServer(value);
        connection.sendMessageToServer(setColumn);
        connection.sendMessageToServer(setValue);

    }

    @Override
    public void updateRegularUserByCondition(String column,String value,String setColumn,String setValue) throws IOException {
        connection.sendMessageToServer("updateRegularUserByCondition");
        connection.sendMessageToServer(column);
        connection.sendMessageToServer(value);
        connection.sendMessageToServer(setColumn);
        connection.sendMessageToServer(setValue);
    }

    @Override
    public void deleteCarByCondition(String column,String value) throws IOException {
        connection.sendMessageToServer("deleteCarByCondition");
        connection.sendMessageToServer(column);
        connection.sendMessageToServer(value);
    }

    @Override
    public void deleteRegularUserByCondition(String column,String value) throws IOException {
        connection.sendMessageToServer("deleteRegularUserByCondition");
        connection.sendMessageToServer(column);
        connection.sendMessageToServer(value);
    }




    }
