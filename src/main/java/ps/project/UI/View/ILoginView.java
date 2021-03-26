package ps.project.UI.View;

import ps.project.IClientServices;
import ps.project.Models.Administrator;
import ps.project.Models.RegularUser;

interface ILoginDataProvider {

    String getUsername();

    String getPassword();

}

interface IViewProvider {

    void setInvisible();

    void showAdminView(Administrator administrator, IClientServices clientServices);

    void showRegularView(RegularUser regularUser, IClientServices clientServices);

    void showErrorMessage(String message);
}

public interface ILoginView extends ILoginDataProvider, IViewProvider {
}
