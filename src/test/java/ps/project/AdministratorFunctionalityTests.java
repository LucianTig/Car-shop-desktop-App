package ps.project;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ps.project.BusinessLogic.AdministratorBll;
import ps.project.BusinessLogic.LoginBll;
import ps.project.Models.Administrator;
import ps.project.Models.Car;
import ps.project.Models.RegularUser;
import ps.project.Models.User;
import ps.project.UI.Controller.AdminController;
import ps.project.UI.Controller.LoginController;
import ps.project.UI.View.IAdminView;
import ps.project.UI.View.ILoginView;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AdministratorFunctionalityTests {

    @Test
    public void givenEmptyFieldRegularUserCreate_ShouldShowEmptyFieldMessage() {

        IAdminView adminView = mock(IAdminView.class);
        when(adminView.getUsernameText()).thenReturn("marian");
        when(adminView.getPasswordText()).thenReturn("");
        when(adminView.getNameText()).thenReturn("Marian");
        when(adminView.getAccountCreationText()).thenReturn("13.12.2020");
        when(adminView.getWalletAmountText()).thenReturn("1200");

        IClientServices clientServices=mock(IClientServices.class);

        AdministratorBll administratorBll=new AdministratorBll();
        administratorBll.setClientServices(clientServices);

        Administrator administrator=new Administrator("John", "john","Lucian Tigarean", "Observatorului", "13.02.2018");

        AdminController adminController = new AdminController(adminView, administrator, administratorBll, clientServices);
        adminController.createButtonListener();

        String message = "All fields must be completed!";
        verify(adminView).showErrorMessage(message);
    }

    @Test
    public void givenNegativeWalletAmountFieldRegularUserCreate_ShouldShowNegativeFieldMessage() {

        IAdminView adminView = mock(IAdminView.class);
        when(adminView.getUsernameText()).thenReturn("marian");
        when(adminView.getPasswordText()).thenReturn("marian");
        when(adminView.getNameText()).thenReturn("Marian");
        when(adminView.getAccountCreationText()).thenReturn("13.12.2020");
        when(adminView.getWalletAmountText()).thenReturn("-1200");

        IClientServices clientServices=mock(IClientServices.class);

        AdministratorBll administratorBll=new AdministratorBll();
        administratorBll.setClientServices(clientServices);

        Administrator administrator=new Administrator("John", "john","Lucian Tigarean", "Observatorului", "13.02.2018");

        AdminController adminController = new AdminController(adminView, administrator, administratorBll, clientServices);
        adminController.createButtonListener();

        String message = "WalletAmount field must be positive!";
        verify(adminView).showErrorMessage(message);
    }

    @Test
    public void givenEmptyFieldCarCreate_ShouldShowEmptyFieldMessage() {

        IAdminView adminView = mock(IAdminView.class);
        when(adminView.getTypeText()).thenReturn("hatchback");
        when(adminView.getBrandText()).thenReturn("");
        when(adminView.getModelText()).thenReturn("C4");
        when(adminView.getPriceText()).thenReturn("5000");
        when(adminView.getStockText()).thenReturn("10");
        when(adminView.getSoldNumberText()).thenReturn("0");

        when(adminView.getTableOption()).thenReturn("Car");

        IClientServices clientServices=mock(IClientServices.class);

        AdministratorBll administratorBll=new AdministratorBll();
        administratorBll.setClientServices(clientServices);

        Administrator administrator=new Administrator("John", "john","Lucian Tigarean", "Observatorului", "13.02.2018");

        AdminController adminController = new AdminController(adminView, administrator, administratorBll, clientServices);

        adminController.tableOption();
        adminController.createButtonListener();

        String message = "All fields must be completed!";
        verify(adminView).showErrorMessage(message);
    }

    @Test
    public void givenNegativePriceFieldCarCreate_ShouldShowNegativeFieldMessage() {

        IAdminView adminView = mock(IAdminView.class);
        when(adminView.getTypeText()).thenReturn("hatchback");
        when(adminView.getBrandText()).thenReturn("Citroen");
        when(adminView.getModelText()).thenReturn("C4");
        when(adminView.getPriceText()).thenReturn("-5000");
        when(adminView.getStockText()).thenReturn("10");
        when(adminView.getSoldNumberText()).thenReturn("0");

        when(adminView.getTableOption()).thenReturn("Car");

        IClientServices clientServices=mock(IClientServices.class);

        AdministratorBll administratorBll=new AdministratorBll();
        administratorBll.setClientServices(clientServices);

        Administrator administrator=new Administrator("John", "john","Lucian Tigarean", "Observatorului", "13.02.2018");

        AdminController adminController = new AdminController(adminView, administrator, administratorBll, clientServices);

        adminController.tableOption();
        adminController.createButtonListener();

        String message = "Price field or soldNumber field or stock field must be positive!";
        verify(adminView).showErrorMessage(message);
    }

    @Test
    public void givenValidFieldRegulareUserCreate_ShouldInvokeServerInsertRegularUser() {

        IAdminView adminView = mock(IAdminView.class);
        when(adminView.getUsernameText()).thenReturn("marian");
        when(adminView.getPasswordText()).thenReturn("marian");
        when(adminView.getNameText()).thenReturn("Marian");
        when(adminView.getAccountCreationText()).thenReturn("13.12.2020");
        when(adminView.getWalletAmountText()).thenReturn("1200");

        IClientServices clientServices=mock(IClientServices.class);
        try {
            when(clientServices.findByColumnRegularUser("username", "marian")).thenReturn(null);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        AdministratorBll administratorBll=new AdministratorBll();
        administratorBll.setClientServices(clientServices);

        Administrator administrator=new Administrator("John", "john","Lucian Tigarean", "Observatorului", "13.02.2018");

        AdminController adminController = new AdminController(adminView, administrator, administratorBll, clientServices);
        adminController.createButtonListener();

        try {
            ArgumentCaptor<RegularUser> argument = ArgumentCaptor.forClass(RegularUser.class);
            verify(clientServices).insertRegularUser(argument.capture());
            assertEquals("marian", argument.getValue().getUsername());
            assertEquals("marian", argument.getValue().getPassword());
            assertEquals("Marian", argument.getValue().getName());
            assertEquals("13.12.2020", argument.getValue().getAccountCreationDate());
            assertEquals(1200, argument.getValue().getWalletAmount());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenValidFieldCarCreate_ShouldInvokeSereverInsertCar() {

        IAdminView adminView = mock(IAdminView.class);
        when(adminView.getTypeText()).thenReturn("hatchback");
        when(adminView.getBrandText()).thenReturn("Citroen");
        when(adminView.getModelText()).thenReturn("C4");
        when(adminView.getPriceText()).thenReturn("5000");
        when(adminView.getStockText()).thenReturn("10");
        when(adminView.getSoldNumberText()).thenReturn("0");

        when(adminView.getTableOption()).thenReturn("Car");

        IClientServices clientServices=mock(IClientServices.class);

        AdministratorBll administratorBll=new AdministratorBll();
        administratorBll.setClientServices(clientServices);

        Administrator administrator=new Administrator("John", "john","Lucian Tigarean", "Observatorului", "13.02.2018");

        AdminController adminController = new AdminController(adminView, administrator, administratorBll, clientServices);

        adminController.tableOption();
        adminController.createButtonListener();


        try {
            ArgumentCaptor<Car> argument = ArgumentCaptor.forClass(Car.class);
            verify(clientServices).insertCar(argument.capture());
            assertEquals("hatchback", argument.getValue().getType());
            assertEquals("Citroen", argument.getValue().getBrand());
            assertEquals("C4", argument.getValue().getModel());
            assertEquals(5000, argument.getValue().getPrice());
            assertEquals(10, argument.getValue().getStock());
            assertEquals(0, argument.getValue().getSoldNumber());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addNewCarInDatabase_shouldBeInsertedInRealDatabase() {

        //Server connection
        Client.Connection connection=null;
        try {
            connection=new Client.Connection(new Socket("localhost", 3000));
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.start();

        //Login step
        ILoginView loginView = mock(ILoginView.class);
        when(loginView.getUsername()).thenReturn("John");
        when(loginView.getPassword()).thenReturn("john");


        ClientServices clientServices=new ClientServices();
        clientServices.setConnection(connection);


        LoginBll loginBll=new LoginBll();
        loginBll.setClientClientService(clientServices);

        LoginController controller = new LoginController(loginView, loginBll, clientServices);
        controller.login();

        ArgumentCaptor<Administrator> argument = ArgumentCaptor.forClass(Administrator.class);
        ArgumentCaptor<IClientServices> argument2 = ArgumentCaptor.forClass(IClientServices.class);
        verify(loginView).showAdminView(argument.capture(), argument2.capture());

        assertEquals("Lucian Tigarean", argument.getValue().getName());
        assertEquals("1", argument.getValue().getId().toString());
        assertEquals("john", argument.getValue().getPassword());
        assertEquals("John", argument.getValue().getUsername());


        IAdminView adminView = mock(IAdminView.class);
        when(adminView.getTypeText()).thenReturn("hatchback");
        when(adminView.getBrandText()).thenReturn("Citroen");
        when(adminView.getModelText()).thenReturn("C4-sedan");
        when(adminView.getPriceText()).thenReturn("5000");
        when(adminView.getStockText()).thenReturn("10");
        when(adminView.getSoldNumberText()).thenReturn("0");

        when(adminView.getTableOption()).thenReturn("Car");

        AdministratorBll administratorBll=new AdministratorBll();
        administratorBll.setClientServices(clientServices);

        AdminController adminController = new AdminController(adminView, argument.getValue(), administratorBll, clientServices);

        adminController.tableOption();
        adminController.createButtonListener();
        adminController.viewButtonListener();

        ArgumentCaptor<List<Car>> argumentCaptorCarList = ArgumentCaptor.forClass(List.class);
        verify(adminView).showCarList(argumentCaptorCarList.capture());

        List<Car> carList = argumentCaptorCarList.getValue();
        boolean ok = true;
        for(Car car: carList){
            if(car.getType().equals("hatchback") && car.getBrand().equals("Citroen") && car.getModel().equals("C4-sedan") && car.getPrice() == 5000 && car.getStock() == 10 && car.getSoldNumber() == 0){
                assertTrue(true);
                ok = false;
            }
        }
        if(ok)
            fail("Car not inserted in database");
    }

    @Test
    public void updateCarStep_shouldBeUpdatedInRealDatabase() {

        //Server connection
        Client.Connection connection=null;
        try {
            connection=new Client.Connection(new Socket("localhost", 3000));
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.start();

        //Login step
        ILoginView loginView = mock(ILoginView.class);
        when(loginView.getUsername()).thenReturn("John");
        when(loginView.getPassword()).thenReturn("john");


        ClientServices clientServices=new ClientServices();
        clientServices.setConnection(connection);


        LoginBll loginBll=new LoginBll();
        loginBll.setClientClientService(clientServices);

        LoginController controller = new LoginController(loginView, loginBll, clientServices);
        controller.login();

        ArgumentCaptor<Administrator> argument = ArgumentCaptor.forClass(Administrator.class);
        ArgumentCaptor<IClientServices> argument2 = ArgumentCaptor.forClass(IClientServices.class);
        verify(loginView).showAdminView(argument.capture(), argument2.capture());

        assertEquals("Lucian Tigarean", argument.getValue().getName());
        assertEquals("1", argument.getValue().getId().toString());
        assertEquals("john", argument.getValue().getPassword());
        assertEquals("John", argument.getValue().getUsername());


        IAdminView adminView = mock(IAdminView.class);
        when(adminView.getTypeText()).thenReturn("hatchback");
        when(adminView.getBrandText()).thenReturn("Citroen");
        when(adminView.getModelText()).thenReturn("C4-sedan");
        when(adminView.getPriceText()).thenReturn("5000");
        when(adminView.getStockText()).thenReturn("10");
        when(adminView.getSoldNumberText()).thenReturn("0");

        when(adminView.getTableOption()).thenReturn("Car");

        AdministratorBll administratorBll=new AdministratorBll();
        administratorBll.setClientServices(clientServices);

        AdminController adminController = new AdminController(adminView, argument.getValue(), administratorBll, clientServices);

        adminController.tableOption();
        adminController.createButtonListener();

        when(adminView.getStockText()).thenReturn("5");
        adminController.updateButtonListener();

        adminController.viewButtonListener();

        ArgumentCaptor<List<Car>> argumentCaptorCarList = ArgumentCaptor.forClass(List.class);
        verify(adminView).showCarList(argumentCaptorCarList.capture());

        List<Car> carList = argumentCaptorCarList.getValue();
        boolean ok = true;
        for(Car car: carList){
            if(car.getType().equals("hatchback") && car.getBrand().equals("Citroen") && car.getModel().equals("C4-sedan") && car.getPrice() == 5000 && car.getStock() == 5 && car.getSoldNumber() == 0){
                assertTrue(true);
                ok = false;
            }
        }
        if(ok)
            fail("Car not updated in database");
    }


}
