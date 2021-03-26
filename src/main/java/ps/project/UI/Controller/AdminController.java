package ps.project.UI.Controller;

import ps.project.BusinessLogic.AdministratorBll;
import ps.project.ClientServices;
import ps.project.IClientServices;
import ps.project.Models.Administrator;
import ps.project.UI.View.IAdminView;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AdminController {

    private final IAdminView adminView;
    private String table="RegularUser";
    private final Administrator administrator;
    private final AdministratorBll administratorBll;
    //private final ClientServices clientServices;

    public AdminController(IAdminView adminView, Administrator administrator, AdministratorBll administratorBll, IClientServices clientServices){

        //this.clientServices=clientServices;
        this.administratorBll=administratorBll;
        this.adminView=adminView;
        this.administrator=administrator;
        adminView.accountInformation(administrator.getName());
        administratorBll.setClientServices(clientServices);
    }

    public void tableOption(){
        String tableName=adminView.getTableOption();
        if(tableName.equals("Car")) {
            adminView.setCarViewCreate();
            table="Car";
        }
        if(tableName.equals("RegularUser")) {
            adminView.setRegularUserViewCreate();
            table="RegularUser";
        }
    }

    public void updateColumnOption(){

    }

    public void viewButtonListener(){
        if(table.equals("Car")){
            try {
                adminView.showCarTable(this.retrieveProperties(administratorBll.findAllCar()));
                adminView.showCarList(administratorBll.findAllCar());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                adminView.showErrorMessage("Comunication server-client error");
            }
        }
        if(table.equals("RegularUser")){
            try {
                adminView.showRegularUserTable(this.retrieveProperties(administratorBll.findAllRegularUser()));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                adminView.showErrorMessage("Comunication server-client error");

            }
        }
    }

    public void createButtonListener(){
        if(table.equals("Car")){
            try {
                administratorBll.addCar(adminView.getTypeText(), adminView.getBrandText(), adminView.getModelText(), adminView.getPriceText(), adminView.getStockText(), adminView.getSoldNumberText());
            } catch (Exception e) {
                adminView.showErrorMessage(e.getMessage());
            }
        }
        if(table.equals("RegularUser")){
            try {
                administratorBll.addRegularUser(adminView.getUsernameText(), adminView.getPasswordText(), adminView.getNameText(), adminView.getAccountCreationText(),adminView.getWalletAmountText());
            } catch (Exception e) {
                adminView.showErrorMessage(e.getMessage());
            }
        }

    }

    public void updateButtonListener(){
        if(table.equals("Car")){
            try {
                administratorBll.updateCar(adminView.getUpdateColumnCarOption(), adminView.getTextUpdateValueColumn(),adminView.getUpdateSetColumnCarOption(),adminView.getTextUpdateSetValueColumn());
            } catch (Exception e) {
                adminView.showErrorMessage(e.getMessage());
            }
        }
        if(table.equals("RegularUser")){
            try {
                administratorBll.updateRegularUser(adminView.getUpdateColumnRegularUserOption(), adminView.getTextUpdateValueColumn(),adminView.getUpdateSetColumnRegularUserOption(),adminView.getTextUpdateSetValueColumn());
            } catch (Exception e) {
                adminView.showErrorMessage(e.getMessage());
            }

        }
    }

    public void deleteButtonListener(){
        if(table.equals("Car")){
            try {
                administratorBll.deleteCar(adminView.getDeleteColumnCarOption(), adminView.getTextDeleteColumnValue());
            } catch (Exception e) {
                adminView.showErrorMessage(e.getMessage());
            }
        }
        if(table.equals("RegularUser")){
            try {
                administratorBll.deleteRegularUser(adminView.getDeleteColumnRegularUserOption(), adminView.getTextDeleteColumnValue());
            } catch (Exception e) {
                adminView.showErrorMessage(e.getMessage());
            }

        }
    }

    public void choosePathSave(){
        adminView.showChooseFileDialog();
        String path=adminView.getChooseSavePath();
        System.out.println(path);
        String fileFormat=adminView.getFileFormat();
        System.out.println(fileFormat);

        try {
            administratorBll.generateReport(path,fileFormat);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            adminView.showErrorMessage("Comunication server-client error");
        }


    }

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    public <T> DefaultTableModel retrieveProperties(java.util.List<T> lista) {

        DefaultTableModel model = new DefaultTableModel();

        int i=0;
        int ok=1;

        for(Object object:lista) {
            String[] data =new String[100];
//            for(Field field:object.getClass().getDeclaredFields()) {
            for(Field field: getAllFields(new LinkedList<>(), object.getClass()) ) {
                field.setAccessible(true);
                Object value;
                try {
                    value=field.get(object);
                    if(ok==1 && !field.getName().equals("image"))
                        model.addColumn(field.getName());
                    if(!field.getName().equals("image") && value!=null) {
                        data[i] = value.toString();
                        i++;
                    }


                }
                catch(IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(data);
            i=0;
            ok=0;
        }
        return model;
    }
}
