package ps.project.UI.Controller;


import ps.project.ClientServices;
import ps.project.IClientServices;
import ps.project.Models.Administrator;
import ps.project.Models.RegularUser;
import ps.project.UI.View.ILoginAccountProvider;
import ps.project.UI.View.ILoginView;


public class LoginController {

    private final ILoginView loginView;
    private final ILoginAccountProvider accountProvider;
    private final IClientServices clientServices;

    public LoginController(ILoginView loginView , ILoginAccountProvider accountProvider, IClientServices clientServices) {

        this.loginView = loginView;
        this.accountProvider=accountProvider;
        this.clientServices=clientServices;

        accountProvider.setClientClientService(clientServices);

    }


    public void login() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();

        try {
            Object user=accountProvider.login(username,password);
            if(user instanceof RegularUser) {
                loginView.showRegularView((RegularUser) user, clientServices);
                loginView.setInvisible();

            }
            if(user instanceof Administrator) {
                //System.out.println(((Administrator) user).getId() +" "+ ((Administrator) user).getName() + " " + ((Administrator) user).getPassword() + " " +((Administrator) user).getUsername());

                loginView.showAdminView((Administrator) user, clientServices);
                loginView.setInvisible();

            }

            if(user==null)
                loginView.showErrorMessage("Invalid username/password");

        } catch (Exception e) {
            loginView.showErrorMessage("Invalid username/password");
            e.printStackTrace();
        }
    }
}
