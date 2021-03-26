package ps.project.UI.View;

import ps.project.BusinessLogic.AdministratorBll;
import ps.project.ClientServices;
import ps.project.IClientServices;
import ps.project.Models.Administrator;
import ps.project.Models.Car;
import ps.project.UI.Controller.AdminController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import javax.swing.JFileChooser;

public class AdminView extends JFrame implements IAdminView {

    private final JPanel panel=new JPanel(new BorderLayout());
    private JPanel panel2=new JPanel(new GridLayout(0,1,5,5));
    private JPanel panel3=new JPanel(new GridLayout(0,2));
    private final JPanel panel4=new JPanel(new GridLayout(2,5));
    private final JPanel panel5=new JPanel(new GridLayout(2,5));
    private JPanel panel6;


    private final JButton createButton=new JButton("Create");
    private final JButton viewButton=new JButton("View");
    private final JButton updateButton=new JButton("Update");
    private final JButton deleteButton=new JButton("Delete");
    private final JButton generateReportButton=new JButton("Generate report file");

    private final String[] listTables = { "RegularUser", "Car"};
    private final JComboBox optionTable = new JComboBox(listTables);

    private final String[] listFileFormat = { "Pdf", "Txt"};
    private final JComboBox optionFileFormat = new JComboBox(listFileFormat);

    private final JLabel administratorName=new JLabel("");

    private final JTextField usernameTextField=new JTextField();
    private final JTextField passwordTextField=new JTextField();
    private final JTextField nameTextField=new JTextField();
    private final JTextField accountCreationTextField=new JTextField();
    private final JTextField walletAmountTextField=new JTextField();

    private final JTextField typeTextField=new JTextField();
    private final JTextField brandTextField=new JTextField();
    private final JTextField modelTextField=new JTextField();
    private final JTextField priceTextField=new JTextField();
    private final JTextField stockTextField=new JTextField();
    private final JTextField  soldNumberTextField=new JTextField();

    private final JTextField  deleteValueTextField=new JTextField();
    private final JTextField  updateConditionValueTextField=new JTextField();
    private final JTextField  updateSetValueTextField=new JTextField();

    JPanel panelBox=new JPanel(new GridLayout(9,0,5,5));
    private final String[] tableList = { "RegularUser", "Car"};
    private final String[] columnTableCar = { "id", "type", "brand", "model", "price", "stock", "soldNumber","rating"};
    private final String[] columnTableRegularUser = { "id", "username", "password", "name", "accountCreationDate", "walletAmount"};


    private final JComboBox tableOption = new JComboBox(tableList);
    private final JComboBox columnTableCarOption1 = new JComboBox(columnTableCar);
    private final JComboBox columnTableRegularUserOption1 = new JComboBox(columnTableRegularUser);

    private final JComboBox columnTableCarOption2 = new JComboBox(columnTableCar);
    private final JComboBox columnTableRegularUserOption2 = new JComboBox(columnTableRegularUser);

    private final JComboBox columnTableCarOptionDelete = new JComboBox(columnTableCar);
    private final JComboBox columnTableRegularUserOptionDelete = new JComboBox(columnTableRegularUser);

    private final JPanel panelUpdate;
    private final JPanel panelDelete;

    ///pentru salvare report
    private JFileChooser chooser;
    private String choosertitle;



    public AdminView(Administrator administrator, IClientServices clientServices) {

        AdministratorBll administratorBll=new AdministratorBll();
//        administratorBll.setClientServices(clientServices);
        AdminController adminController=new AdminController(this,administrator, administratorBll, clientServices);

        tableOption.setSelectedIndex(0);
        columnTableCarOption1.setSelectedIndex(0);
        columnTableRegularUserOption1.setSelectedIndex(0);
        columnTableCarOption2.setSelectedIndex(0);
        columnTableRegularUserOption2.setSelectedIndex(0);
        columnTableCarOptionDelete.setSelectedIndex(0);
        columnTableRegularUserOptionDelete.setSelectedIndex(0);


        panel2.add(new JLabel("Name"));
        panel2.add(administratorName);

        panel3.add(new JLabel("Select Table"));
        panel3.add(tableOption);

        panel4.add(new JLabel("Username"));
        panel4.add(new JLabel("Password"));
        panel4.add(new JLabel("Name"));
        panel4.add(new JLabel("Account Creation Date"));
        panel4.add(new JLabel("Wallet Amount"));

        panel4.add(usernameTextField);
        panel4.add(passwordTextField);
        panel4.add(nameTextField);
        panel4.add(accountCreationTextField);
        panel4.add(walletAmountTextField);

        panel5.add(new JLabel("Type"));
        panel5.add(new JLabel("Brand"));
        panel5.add(new JLabel("Model"));
        panel5.add(new JLabel("Price"));
        panel5.add(new JLabel("Stock"));
        panel5.add(new JLabel("Sold number"));

        panel5.add(typeTextField);
        panel5.add(brandTextField);
        panel5.add(modelTextField);
        panel5.add(priceTextField);
        panel5.add(stockTextField);
        panel5.add(soldNumberTextField);

        panelUpdate=new JPanel(new GridLayout(2,4));
        panelUpdate.add(new JLabel("Condition Column"),0);
        panelUpdate.add(new JLabel("Value Column"),1);
        panelUpdate.add(new JLabel("Set Column"),2);
        panelUpdate.add(new JLabel("Set value Column"),3);

        panelUpdate.add(columnTableRegularUserOption1,4);
        panelUpdate.add(updateConditionValueTextField,5);
        panelUpdate.add(columnTableRegularUserOption2,6);
        panelUpdate.add(updateSetValueTextField,7);
        panelUpdate.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        panelDelete=new JPanel(new GridLayout(2,2));
        panelDelete.add(new Label("Column"),0);
        panelDelete.add(new Label("Value"),1);
        panelDelete.add(columnTableRegularUserOptionDelete,2);
        panelDelete.add(deleteValueTextField,3);
        panelDelete.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel panelAditional=new JPanel(new BorderLayout());
        JPanel panelAditional2=new JPanel(new BorderLayout());

        panel2.setBorder(BorderFactory.createTitledBorder("Administrator Information"));
        panel3.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel panelFileFormat=new JPanel(new GridLayout(1,2,10,0));
        panelFileFormat.add(new JLabel("Choose file format:"));
        panelFileFormat.add(optionFileFormat);

        panel6=panel4;
        panelBox.add(panel6,0);
        panelBox.add(createButton,1);
        panelBox.add(panelUpdate,2);
        panelBox.add(updateButton,3);
        panelBox.add(panelDelete,4);
        panelBox.add(deleteButton,5);
        panelBox.add(viewButton,6);
        panelBox.add(panelFileFormat,7);
        panelBox.add(generateReportButton,8);


        panelBox.setBorder(BorderFactory.createTitledBorder("Table Operation"));

        panelAditional.add(panel2,BorderLayout.BEFORE_FIRST_LINE);
        panelAditional.add(panel3,BorderLayout.AFTER_LAST_LINE);

        panelAditional2.add(panelAditional,BorderLayout.BEFORE_FIRST_LINE);
        panelAditional2.add(panelBox,BorderLayout.CENTER);

        panel.add(panelAditional2,BorderLayout.BEFORE_FIRST_LINE);

        tableOption.addActionListener(e -> adminController.tableOption());
        columnTableRegularUserOption1.addActionListener(e -> adminController.updateColumnOption());
        columnTableCarOption1.addActionListener(e -> adminController.updateColumnOption());

        columnTableRegularUserOption2.addActionListener(e -> adminController.updateColumnOption());
        columnTableCarOption2.addActionListener(e -> adminController.updateColumnOption());

        createButton.addActionListener(e -> adminController.createButtonListener());
        viewButton.addActionListener(e -> adminController.viewButtonListener());
        updateButton.addActionListener(e -> adminController.updateButtonListener());
        deleteButton.addActionListener(e -> adminController.deleteButtonListener());
        generateReportButton.addActionListener(e -> adminController.choosePathSave());





        this.add(panel);
        this.setMinimumSize(new Dimension(800, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Administrator View");
        pack();
        setVisible(true);
    }

    @Override
    public String getUsernameText() {
        return usernameTextField.getText();
    }

    @Override
    public String getPasswordText() {
        return passwordTextField.getText();
    }

    @Override
    public String getNameText() {
        return nameTextField.getText();
    }

    @Override
    public String getAccountCreationText() {
        return accountCreationTextField.getText();
    }

    @Override
    public String getWalletAmountText() {
        return walletAmountTextField.getText();
    }

    @Override
    public String getTypeText() {
        return typeTextField.getText();
    }

    @Override
    public String getBrandText() {
        return brandTextField.getText();
    }

    @Override
    public String getModelText() {
        return modelTextField.getText();
    }

    @Override
    public String getPriceText() {
        return priceTextField.getText();
    }

    @Override
    public String getStockText() {
        return stockTextField.getText();
    }

    @Override
    public String getSoldNumberText() {
        return soldNumberTextField.getText();
    }

    @Override
    public String getTableOption() {
        return (String)tableOption.getSelectedItem();
    }

    @Override
    public String getUpdateColumnCarOption() {
        return (String)columnTableCarOption1.getSelectedItem();
    }

    @Override
    public String getUpdateSetColumnCarOption() {
        return (String)columnTableCarOption2.getSelectedItem();
    }

    @Override
    public String getDeleteColumnCarOption() {
        return (String)columnTableCarOptionDelete.getSelectedItem();
    }

    @Override
    public String getUpdateColumnRegularUserOption() {
        return (String)columnTableRegularUserOption1.getSelectedItem();
    }

    @Override
    public String getUpdateSetColumnRegularUserOption() {
        return (String)columnTableRegularUserOption2.getSelectedItem();
    }

    @Override
    public String getDeleteColumnRegularUserOption() {
        return (String)columnTableRegularUserOptionDelete.getSelectedItem();

    }

    @Override
    public String getTextUpdateValueColumn() {
        return updateConditionValueTextField.getText();
    }

    @Override
    public String getTextUpdateSetValueColumn() {
        return updateSetValueTextField.getText();
    }

    @Override
    public String getTextDeleteColumnValue() {
        return deleteValueTextField.getText();
    }


    @Override
    public String getFileFormat() {
        return optionFileFormat.getSelectedItem().toString();
    }

    @Override
    public String getChooseSavePath() {
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().toString();
        }
        else {
            return "Error";
        }
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void setCarViewCreate() {
        panel6=panel5;
        panelBox.remove(0);
        panelBox.add(panel6,0);

        panelUpdate.remove(4);
        panelUpdate.add(columnTableCarOption1,4);

        panelUpdate.remove(6);
        panelUpdate.add(columnTableCarOption2,6);

        panelDelete.remove(2);
        panelDelete.add(columnTableCarOptionDelete,2);

        panel.revalidate();
        panel.repaint();

    }

    @Override
    public void setRegularUserViewCreate() {
        panel6=panel4;
        panelBox.remove(0);
        panelBox.add(panel6,0);

        panelUpdate.remove(4);
        panelUpdate.add(columnTableRegularUserOption1,4);

        panelUpdate.remove(6);
        panelUpdate.add(columnTableRegularUserOption2,6);

        panelDelete.remove(2);
        panelDelete.add(columnTableRegularUserOptionDelete,2);

        panel.revalidate();
        panel.repaint();
    }

    @Override
    public void showCarTable(DefaultTableModel model) {

        JTable j=new JTable(model);
        JScrollPane sp = new JScrollPane(j);

        JDialog d = new JDialog(this, "Car");
        d.add(sp);
        d.setSize(800,600);
        d.setVisible(true);

    }

    @Override
    public void showCarList(List<Car> carList) {
        for(Car car: carList){
            System.out.println(car.getBrand());
        }
    }

    @Override
    public void showRegularUserTable(DefaultTableModel model) {
        JTable j=new JTable(model);
        JScrollPane sp = new JScrollPane(j);

        JDialog d = new JDialog(this, "Regular User");
        d.add(sp);
        d.setSize(800,600);
        d.setVisible(true);
    }

    @Override
    public void showChooseFileDialog() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

    }

    @Override
    public void accountInformation(String name) {
        administratorName.setText(name);
    }


    public static void main(String[] args){
        AdminView ad=new AdminView(null,null);
    }
}
