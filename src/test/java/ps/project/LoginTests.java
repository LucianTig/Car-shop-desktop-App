package ps.project;


import org.junit.jupiter.api.Test;
import ps.project.BusinessLogic.LoginBll;
import ps.project.BusinessLogic.RegularUserBll;
import ps.project.Models.Administrator;
import ps.project.Models.Car;
import ps.project.Models.RegularUser;
import ps.project.Models.User;
import ps.project.UI.Controller.LoginController;
import ps.project.UI.Controller.RegularController;
import ps.project.UI.View.ILoginView;
import ps.project.UI.View.IRegularView;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginTests {

    @Test
    public void givenValidAdminUsernameAndPassword_ShouldShowAdminView() {
        ILoginView loginView = mock(ILoginView.class);
        when(loginView.getUsername()).thenReturn("John");
        when(loginView.getPassword()).thenReturn("john");

        IClientServices clientServices=mock(IClientServices.class);

        LoginBll loginBll=new LoginBll();
        loginBll.setClientClientService(clientServices);

        Administrator administrator=new Administrator("John", "john","Lucian Tigarean", "Observatorului", "13.02.2018");
        User user=new User("John","john");
        List<User> userList=new ArrayList<>();
        List<Administrator> administratorList=new ArrayList<>();
        List<RegularUser> regularUserList=new ArrayList<>();
        administratorList.add(administrator);
        userList.add(user);
        try {
            when(clientServices.findByColumnMessage("username","John")).thenReturn(userList);
            when(clientServices.findAdministrator(user)).thenReturn(administratorList);
            when(clientServices.findRegularUser(user)).thenReturn(regularUserList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginController controller = new LoginController(loginView, loginBll,clientServices);
        controller.login();

        verify(loginView).showAdminView(administrator, clientServices);
    }

    @Test
    public void givenValidRegularUsernameAndPassword_ShouldShowRegularView() {
        ILoginView loginView = mock(ILoginView.class);
        when(loginView.getUsername()).thenReturn("Daniel");
        when(loginView.getPassword()).thenReturn("daniel");

        IClientServices clientServices=mock(IClientServices.class);

        LoginBll loginBll=new LoginBll();
        loginBll.setClientClientService(clientServices);

        RegularUser regularUser=new RegularUser("Daniel","daniel","Daniel Dan","24.05.2020",3500);
        User user=new User("Daniel","daniel");
        List<User> userList=new ArrayList<>();
        List<RegularUser> regularUserList=new ArrayList<>();
        List<Administrator> administratorList=new ArrayList<>();
        regularUserList.add(regularUser);
        userList.add(user);
        try {
            when(clientServices.findByColumnMessage("username","Daniel")).thenReturn(userList);
            when(clientServices.findAdministrator(user)).thenReturn(administratorList);
            when(clientServices.findRegularUser(user)).thenReturn(regularUserList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginController controller = new LoginController(loginView, loginBll,clientServices);
        controller.login();

        verify(loginView).showRegularView(regularUser, clientServices);
    }

    @Test
    public void givenInvalidRegularUsernameAndPassword_shouldShowErrorMessage() {
        ILoginView loginView = mock(ILoginView.class);
        when(loginView.getUsername()).thenReturn("Daniel");
        when(loginView.getPassword()).thenReturn("Daniel");

        IClientServices clientServices=mock(IClientServices.class);

        LoginBll loginBll=new LoginBll();
        loginBll.setClientClientService(clientServices);

        RegularUser regularUser=new RegularUser("Daniel","daniel","Daniel Dan","24.05.2020",3500);
        User user=new User("Daniel","daniel");
        List<User> userList=new ArrayList<>();
        List<RegularUser> regularUserList=new ArrayList<>();
        List<Administrator> administratorList=new ArrayList<>();
        regularUserList.add(regularUser);
        userList.add(user);
        try {
            when(clientServices.findByColumnMessage("username","Daniel")).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginController controller = new LoginController(loginView, loginBll,clientServices);
        controller.login();

        String message="Invalid username/password";
        verify(loginView).showErrorMessage(message);
    }

    @Test
    public void givenInvalidAdministratorUsernameAndPassword_shouldShowErrorMessage() {
        ILoginView loginView = mock(ILoginView.class);
        when(loginView.getUsername()).thenReturn("John");
        when(loginView.getPassword()).thenReturn("invalid");

        IClientServices clientServices=mock(IClientServices.class);

        LoginBll loginBll=new LoginBll();
        loginBll.setClientClientService(clientServices);

        Administrator administrator=new Administrator("John", "john","Lucian Tigarean", "Observatorului", "13.02.2018");
        User user=new User("John","john");
        List<User> userList=new ArrayList<>();
        List<Administrator> administratorList=new ArrayList<>();
        List<RegularUser> regularUserList=new ArrayList<>();
        administratorList.add(administrator);
        userList.add(user);
        try {
            when(clientServices.findByColumnMessage("username","John")).thenReturn(userList);
            when(clientServices.findAdministrator(user)).thenReturn(administratorList);
            when(clientServices.findRegularUser(user)).thenReturn(regularUserList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginController controller = new LoginController(loginView, loginBll,clientServices);
        controller.login();

        String message="Invalid username/password";
        verify(loginView).showErrorMessage(message);
    }

    @Test
    public void viewFavoriteCarList_showFavoriteCarList() {

        IRegularView regularView = mock(IRegularView.class);
        IClientServices clientServices=mock(IClientServices.class);

        RegularUserBll regularUserBll=new RegularUserBll();
        regularUserBll.setClientServices(clientServices);
        RegularUser regularUser=new RegularUser("Daniel","daniel","Daniel Dan","24.05.2020",3500);

        ArrayList<Car> carList=new ArrayList<>();
        Car car1=new Car("Wagon","BMW","320D",3500,10,1,null); car1.setId(new Long(1));
        Car car2=new Car("Hatchback","BMW","120D",4000,5,3,null); car2.setId(new Long(2));
        Car car3=new Car("Coupe","BMW","420D",13500,4,0,null); car3.setId(new Long(3));
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);

        regularUser.setFavoritCars(carList);

        List<Car> carList1=new ArrayList<>();
        List<Car> carList2=new ArrayList<>();
        List<Car> carList3=new ArrayList<>();

        carList1.add(car1);
        carList1.add(car2);
        carList1.add(car3);

        try {
            when(clientServices.findByColumn("id",car1.getId().toString())).thenReturn(carList1);
            when(clientServices.findByColumn("id",car2.getId().toString())).thenReturn(carList2);
            when(clientServices.findByColumn("id",car3.getId().toString())).thenReturn(carList3);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //System.out.println("Car id="+car1.getId());
        RegularController controller=new RegularController(regularView,regularUser,regularUserBll,clientServices);
        controller.viewFavoriteCarList();

        DefaultTableModel model=controller.retrieveProperties(carList);

        //verify(regularView).showFavoriteCarList(model);
    }
/*
    @Test
    public void givenRegularUsernameAndPassword_login_showRegularView() {
        ILoginView loginView = mock(ILoginView.class);
        when(loginView.getUsername()).thenReturn("John");
        when(loginView.getPassword()).thenReturn("john");

        ILoginAccountProvider accountProvider=mock(ILoginAccountProvider.class);
        RegularUser regularUser=new RegularUser("LucinaTigarean", "Lucian","Lucian Tigarean", "20.01.2019", 30);
        try {
            when(accountProvider.login("John","john")).thenReturn(regularUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //////////////////////////
        Client client=new Client();
        //////////////////////////

        LoginController controller = new LoginController(loginView, accountProvider, client);

        controller.login();
        verify(loginView).showRegularView(regularUser);

    }


    @Test
    public void givenInvalidUsernameAndPassword_login_showErrorMessage() {
        ILoginView loginView = mock(ILoginView.class);
        when(loginView.getUsername()).thenReturn("notanusername");
        when(loginView.getPassword()).thenReturn("nope");

        //////////////////////////
        Client client=new Client();
        //////////////////////////

        ILoginAccountProvider accountProvider=mock(ILoginAccountProvider.class);
        LoginController controller = new LoginController(loginView,accountProvider, client);

        Administrator administrator=new Administrator();
        try {
            when(accountProvider.login("notanusername","nope")).thenReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        controller.login();
        verify(loginView).showErrorMessage("Invalid username/password");
    }*/
    /*@Test
    public void givenInvalidUsernameAndPassword_login_showErrorMessage2() {
        TestLoginView loginView = new TestLoginView("notanusername", "nope");

        LoginController controller = new LoginController(loginView);

        controller.login();

        assertEquals(loginView.shownErrorMessage, "Invalid username/password");
    }*/

    /*class TestLoginView implements ILoginView, ILoginAccountProvider
    {
        private final String username;
        private final String password;

        TestLoginView(String username, String password)
        {
            this.username = username;
            this.password = password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public String getPassword() {
            return password;
        }

        public String shownErrorMessage;

        @Override
        public void setInvisible() {

        }

        @Override
        public void showAdminView(Administrator administrator, IClientServices clientServices) {

        }

        @Override
        public void showRegularView(RegularUser regularUser, IClientServices clientServices) {

        }

        @Override
        public void showErrorMessage(String message) {
            shownErrorMessage = message;
        }

        @Override
        public Object login(String username, String password) throws Exception {
            return null;
        }

        @Override
        public void setClientClientService(IClientServices clientServices) {

        }

    }*/
}
