package ps.project.UI.View;

import ps.project.BusinessLogic.LoginBll;
import ps.project.Client;
import ps.project.ClientServices;
import ps.project.IClientServices;
import ps.project.Models.Administrator;
import ps.project.Models.RegularUser;
import ps.project.UI.Controller.LoginController;


import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame implements ILoginView {

    private final JTextField usernameTextField=new JTextField();
    private final JPasswordField passwordTextField= new JPasswordField();

    public LoginView(Client.Connection connection) {

        JPanel panel2 = new JPanel(new GridLayout(3, 1));
        JLabel userlabel = new JLabel("Username: ");
        panel2.add(userlabel);
        panel2.add(usernameTextField);
        JLabel passwordLabel = new JLabel("Password :");
        panel2.add(passwordLabel);
        panel2.add(passwordTextField);

        JLabel messageLabel = new JLabel();
        panel2.add(messageLabel);
        JButton submitButton = new JButton("SUBMIT");
        panel2.add(submitButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(panel2, BorderLayout.CENTER);
        this.add(panel);

        setMinimumSize(new Dimension(800,400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");
        pack();
        setVisible(true);


        ClientServices clientServices=new ClientServices();
        clientServices.setConnection(connection);
        LoginBll loginBll=new LoginBll();
        LoginController loginController = new LoginController(this, loginBll,clientServices);
        submitButton.addActionListener(e -> loginController.login());
    }

    @Override
    public void setInvisible() {
        this.setVisible(false);
    }

    @Override
    public void showAdminView(Administrator administrator, IClientServices clientServices) {
        new AdminView(administrator, clientServices).setVisible(true);
    }

    @Override
    public void showRegularView(RegularUser regularUser, IClientServices clientServices) {
        new RegularView(regularUser, clientServices).setVisible(true);
    }

    @Override
    public String getUsername() {
        return usernameTextField.getText();
    }

    @Override
    public String getPassword() {
        return passwordTextField.getText();
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


}
