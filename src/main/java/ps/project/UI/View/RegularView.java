package ps.project.UI.View;

import com.itextpdf.layout.renderer.ImageRenderer;
import javafx.scene.layout.BorderRepeat;
import ps.project.BusinessLogic.RegularUserBll;
import ps.project.ClientServices;
import ps.project.IClientServices;
import ps.project.Models.RegularUser;
import ps.project.UI.Controller.LoginController;
import ps.project.UI.Controller.RegularController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class RegularView extends JFrame implements IRegularView{

    private final JPanel panel=new JPanel(new BorderLayout());
    private final JPanel panel4=new JPanel(new GridLayout(0,1));


    private final JLabel usernameLabel=new JLabel("");
    private final JLabel walletAmountLabel1= new JLabel("");
    private final JButton buyButton=new JButton("Buy");


    JScrollPane scrollPaneComments;
    JTable jTableComments;
    JTextField jTextFieldComment=new JTextField("");

    private JCheckBox[] checkBox=new JCheckBox[9];
    private final String[] configurationCarString={"bluetooth","AC","Lane assist","Automatic Trunk","Massaging Seats","Carplay/Android Auto","Self-Parking System","Xenon headlights","Sport Seats"};


    private final String[] orderOptionBuy = { "None", "Most Buyed", "Price-Low to High", "Price-High to Low","Rating"};
    private final String[] orderOptionType = { "None", "Sedan", "Hatchback", "Wagon", "SUV"};
    private final JComboBox orderPriceList = new JComboBox(orderOptionBuy);
    private final JComboBox orderTypeList = new JComboBox(orderOptionType);
    private final JTable j=new JTable();

    private final String[] rateOptionList = { "1", "2", "3", "4", "5"};
    private final JComboBox rateOptions = new JComboBox(rateOptionList);

    JScrollPane spFavoriteCarList;
    JTable jFavoriteCarList;


    public RegularView(RegularUser regularUser, IClientServices clientServices) {

        RegularUserBll regularUserBll=new RegularUserBll();
        RegularController regularController = new RegularController(this, regularUser, regularUserBll, clientServices);
        clientServices.getConnection().setRegularController(regularController);

        for(int i=0;i<9;i++){
            checkBox[i]=new JCheckBox(configurationCarString[i]);
        }

        rateOptions.setSelectedIndex(4);

        JPanel panel2 = new JPanel(new GridLayout(2, 2, 5, 5));
        panel2.add(new JLabel("Name: "));
        panel2.add(usernameLabel);
        panel2.add(new JLabel("Wallet amount:"));
        panel2.add(walletAmountLabel1);

        JPanel panel3 = new JPanel(new GridLayout(0, 4));
        panel3.add(new JLabel("Order by price:"));
        panel3.add(orderPriceList);

        panel3.add(new JLabel("Order by type:"));
        panel3.add(orderTypeList);

        JPanel panel5 = new JPanel(new GridLayout(2, 3));
        panel5.setBorder(BorderFactory.createTitledBorder("Favorite Cars "));
        JButton viewFavoritListButton = new JButton("View favorite List");
        panel5.add(viewFavoritListButton);
        JButton addFavoritListButton = new JButton("Add to favorite list");
        panel5.add(addFavoritListButton);
        JButton deleteFromFovoritListButton = new JButton("Remove from favorit list");
        panel5.add(deleteFromFovoritListButton);

        panel5.add(new JLabel(""));
        JButton rateButton = new JButton("Rate car");
        panel5.add(rateButton);
        panel5.add(rateOptions);

        /*petList.setSelectedIndex(4);
        petList.addActionListener(this);*/

        JPanel panelAditional=new JPanel(new BorderLayout());
        JPanel panelAditional2=new JPanel(new BorderLayout());


        panelAditional.add(panel2, BorderLayout.BEFORE_FIRST_LINE);
        panel2.setBorder(BorderFactory.createTitledBorder("User Information"));
        panel3.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelAditional.add(panel5);

        panelAditional2.add(panelAditional, BorderLayout.BEFORE_FIRST_LINE);
        panelAditional2.add(panel3, BorderLayout.EAST);

        JScrollPane sp = new JScrollPane(j);

        JPanel auxPanel=new JPanel(new GridLayout(1,3));
        JPanel auxPanel2=new JPanel(new GridLayout(2,1));

        JButton commentButton = new JButton("Comments");
        auxPanel.add(commentButton);
        JButton likeCommentButton = new JButton("Like");
        auxPanel.add(likeCommentButton);
        JButton dislikeCommentButton = new JButton("Dislike");
        auxPanel.add(dislikeCommentButton);
        JPanel panelButtonComment = new JPanel(new GridLayout(3, 2));
        panelButtonComment.add(jTextFieldComment);
        JButton addCommentButton = new JButton("Add Comment");
        panelButtonComment.add(addCommentButton);
        JButton addToCartButton = new JButton("Add to Cart");
        panelButtonComment.add(addToCartButton);

        auxPanel2.add(auxPanel);
        auxPanel2.add(panelButtonComment);




        panel.add(panelAditional2, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(sp, BorderLayout.CENTER);
        panel.add(auxPanel2, BorderLayout.AFTER_LAST_LINE);

        buyButton.addActionListener(e -> regularController.buyCar());
        addToCartButton.addActionListener(e -> regularController.addCart());
        orderPriceList.addActionListener(e -> regularController.orderOption());
        orderTypeList.addActionListener(e -> regularController.orderOption());
        commentButton.addActionListener(e -> regularController.viewComments());
        addCommentButton.addActionListener(e -> regularController.addComment());
        likeCommentButton.addActionListener(e -> regularController.likeComment());
        dislikeCommentButton.addActionListener(e -> regularController.dislikeComment());

        viewFavoritListButton.addActionListener((e->regularController.viewFavoriteCarList()));
        deleteFromFovoritListButton.addActionListener((e->regularController.removeFromFavoriteCarList()));
        addFavoritListButton.addActionListener((e->regularController.addToFavotiteCarList()));
        rateButton.addActionListener((e->regularController.addNewRate()));

        this.add(panel);
        setMinimumSize(new Dimension(1200,800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("RegularView");
        pack();
        setVisible(true);

        /*if(source == buttonAView){

            List<Client> lista=(new EmployeeBll()).viewAccount();
            this.retrieveProperties(lista);
        }


         */


    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getSelectedRow() {
        if(j.getSelectedRow()!=-1) {
            return j.getModel().getValueAt(j.getSelectedRow(), 0).toString();
        }
        return null;
    }

    @Override
    public int getPriceOption() {
        return orderPriceList.getSelectedIndex();
    }

    @Override
    public int getTypeOption() {
        return orderTypeList.getSelectedIndex();

    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void showCarTable(DefaultTableModel model) {
        j.setModel(model);

//        jTableComments.setModel(model);
        /*j.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());*/
        j.setRowHeight(120);
        j.getColumnModel().getColumn(7).setPreferredWidth(150);
        panel.revalidate();
        panel.repaint();
    }


    @Override
    public void accountInformation(String name, int walletAmount) {
        usernameLabel.setText(name);
        walletAmountLabel1.setText(walletAmount+"");
        panel.revalidate();
        panel.repaint();

    }

    @Override
    public void showFavoriteCarList(DefaultTableModel model){
        jFavoriteCarList=new JTable(model);
        spFavoriteCarList = new JScrollPane(jFavoriteCarList);

        JDialog d = new JDialog(this, "Favorite Car List");
        d.add(spFavoriteCarList);
        d.setSize(800,600);
        d.setVisible(true);
    }

    @Override
    public void showComments(DefaultTableModel model) {
        jTableComments=new JTable(model);
        scrollPaneComments=new JScrollPane(jTableComments);

        JDialog d = new JDialog(this, "Comments");
        d.add(scrollPaneComments);
        d.setSize(800,600);
        d.setVisible(true);

    }

    @Override
    public String getSelectedRowComment() {
        return jTableComments.getModel().getValueAt(jTableComments.getSelectedRow(),0).toString();
    }

    @Override
    public String getWriteComment() {
        return jTextFieldComment.getText();
    }

    @Override
    public String getSelectedRowFavoriteCarList() {
        return jFavoriteCarList.getModel().getValueAt(jFavoriteCarList.getSelectedRow(),0).toString();
    }

    @Override
    public String getSelectedRowRateOptionList() {
        return rateOptions.getSelectedItem().toString();
    }

    @Override
    public ArrayList<String> getConfigureOprionList() {
        ArrayList<String> configuration=new ArrayList<>();
        for(int i=0; i<9; i++){
            if(checkBox[i].isSelected()){
                configuration.add(configurationCarString[i]);
            }
        }
        return configuration;
    }

    @Override
    public void showCart(String carInfo) {

        JLabel textField=new JLabel(carInfo);
        JPanel panelCart=new JPanel(new GridLayout(3,3));

        for(int i=0;i<9;i++){
            panelCart.add(checkBox[i]);
        }

        JPanel panelCart2=new JPanel(new BorderLayout());
        panelCart2.add(textField,BorderLayout.BEFORE_FIRST_LINE);
        panelCart2.add(panelCart,BorderLayout.CENTER);
        panelCart2.add(buyButton,BorderLayout.AFTER_LAST_LINE);



        JDialog d = new JDialog(this, "Cart");
        d.add(panelCart2);
        d.setSize(800,600);
        d.setVisible(true);
    }
}
