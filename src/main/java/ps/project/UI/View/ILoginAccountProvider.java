package ps.project.UI.View;

import ps.project.IClientServices;

public interface ILoginAccountProvider {

    Object login(String username, String password) throws Exception;

    void setClientClientService(IClientServices clientServices);

}
