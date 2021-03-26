package ps.project.BusinessLogic;

import ps.project.IClientServices;
import ps.project.Models.Administrator;
import ps.project.Models.RegularUser;
import ps.project.Models.User;
import ps.project.UI.View.ILoginAccountProvider;

import java.util.List;

public class LoginBll implements ILoginAccountProvider {

    private static IClientServices clientServices;

    public void setClientClientService(IClientServices clientServices){
        this.clientServices=clientServices;
    }

    @Override
    public Object login(String username, String password) throws Exception{
        User user1=null;
        //List<User> userList=(new UserDAO()).findByColumn("username",username);
        List<User> userList=clientServices.findByColumnMessage("username", username);
        for (User user : userList) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                //loginView.showAdminView();
                user1=user;
            }
        }
        if(user1!=null){
            /*List<Administrator> administratorList=(new AdministratorDAO()).findByAdminstrator(user1);
            List<RegularUser> regularUserList=(new RegularUserDAO()).findByUser(user1);*/

            List<Administrator> administratorList=clientServices.findAdministrator(user1);
            List<RegularUser> regularUserList=clientServices.findRegularUser(user1);

            if(administratorList.size()==1){
                return administratorList.get(0);
            }

            if(regularUserList.size()==1){
                return regularUserList.get(0);

            }
        }
        return null;
    }

}
