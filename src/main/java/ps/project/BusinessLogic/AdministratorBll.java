package ps.project.BusinessLogic;

import ps.project.ClientServices;
import ps.project.IClientServices;
import ps.project.Models.*;

import java.io.IOException;
import java.util.List;

public class AdministratorBll implements IAdministratorBll {

    private IClientServices clientServices=null;

    public void setClientServices(IClientServices clientServices){

        this.clientServices=clientServices;
    }

    @Override
    public List<Car> findAllCar() throws IOException, ClassNotFoundException {
        return clientServices.findAllCar();
    }

    @Override
    public List<RegularUser> findAllRegularUser() throws IOException, ClassNotFoundException {
        return clientServices.findAllUsers();
    }

    @Override
    public void addRegularUser(String username, String password, String name, String accountCreationDate, String walletA) throws Exception{
        if(this.isEmpty(username) || this.isEmpty(password) || this.isEmpty(name) || this.isEmpty(accountCreationDate) || this.isEmpty(walletA)){
            throw new Exception("All fields must be completed!");
        }
        else{
            int walletAmount=Integer.parseInt(walletA);
            if(walletAmount<0 )
                throw new Exception("WalletAmount field must be positive!");
            RegularUser regularUser=new RegularUser(username, password, name, accountCreationDate,walletAmount);
//            if((new RegularUserDAO()).findByColumn("username",username)!=null){
            if(clientServices.findByColumnRegularUser("username",username)!=null){
                throw new Exception("Username already exist!");
            }
//            (new RegularUserDAO()).insertRegularUser(regularUser);
            clientServices.insertRegularUser(regularUser);

        }
    }

    @Override
    public void addCar(String type, String brand, String model, String pr,String st, String soldN) throws Exception{
        if(this.isEmpty(type) || this.isEmpty(brand) || this.isEmpty(model) || this.isEmpty(pr) || this.isEmpty(soldN) || this.isEmpty(st)){
            throw new Exception("All fields must be completed!");
        }
        else{
            int price=Integer.parseInt(pr);
            int soldNumber=Integer.parseInt(soldN);
            int stock=Integer.parseInt(st);
            if(price<0 || soldNumber<0 || stock<0 )
                throw new Exception("Price field or soldNumber field or stock field must be positive!");
            Car car=new Car(type, brand, model, price, stock, soldNumber);
//            (new CarDAO()).insertCar(car);

            clientServices.insertCar(car);

        }
    }

    @Override
    public void updateCar(String column, String value, String setColumn, String setValue) throws Exception{
        if(isEmpty(value) || isEmpty(setValue)){
            throw new Exception("All fields must be completed!");
        }else{
//            (new CarDAO()).updateCarByCondition(column,value,setColumn,setValue);
            clientServices.updateCarByCondition(column,value,setColumn,setValue);

        }
    }

    @Override
    public void updateRegularUser(String column, String value, String setColumn, String setValue) throws Exception{
        if(isEmpty(value) || isEmpty(setValue)){
            throw new Exception("All fields must be completed!");
        }else{
//            (new RegularUserDAO()).updateRegularUserByCondition(column,value,setColumn,setValue);
            clientServices.updateRegularUserByCondition(column,value,setColumn,setValue);

        }
    }

    @Override
    public void deleteCar(String column, String value) throws Exception{
        if(isEmpty(value)){
            throw new Exception("All fields must be completed!");
        }else{
//            (new CarDAO()).deleteCarByCondition(column,value);
            clientServices.deleteCarByCondition(column,value);

        }
    }

    @Override
    public void deleteRegularUser(String column, String value) throws Exception{
        if(isEmpty(value)){
            throw new Exception("All fields must be completed!");
        }else{
//            (new RegularUserDAO()).deleteRegularUserByCondition(column,value);
            clientServices.deleteRegularUserByCondition(column,value);

        }
    }

    @Override
    public boolean isEmpty(String str){
        return (str.equals(""));
    }

    @Override
    public void generateReport(String path, String fileFormat) throws IOException, ClassNotFoundException {

        ///factory method
        List<Car> listCar=clientServices.findAllCar();
        File file=null;

        if(fileFormat.equals("Pdf")){
            file=new PdfFile();
        }
        if(fileFormat.equals("Txt")){
            file=new TxtFile();
        }

        file.generateFile(path,listCar);
    }

}
