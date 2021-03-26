package ps.project.BusinessLogic;

import ps.project.Models.*;

import java.io.IOException;
import java.util.List;

public interface IAdministratorBll {

    List<Car> findAllCar() throws IOException, ClassNotFoundException;

    List<RegularUser> findAllRegularUser() throws IOException, ClassNotFoundException;

    void addRegularUser(String username, String password, String name, String accountCreationDate, String walletA) throws Exception;

    void addCar(String type, String brand, String model, String pr,String st, String soldN) throws Exception;

    void updateCar(String column, String value, String setColumn, String setValue) throws Exception;

    void updateRegularUser(String column, String value, String setColumn, String setValue) throws Exception;

    void deleteCar(String column, String value) throws Exception;

    void deleteRegularUser(String column, String value) throws Exception;

    boolean isEmpty(String str);

    void generateReport(String path, String fileFormat) throws IOException, ClassNotFoundException;
}
