package ps.project.UI.Controller;

import ps.project.BusinessLogic.RegularUserBll;
import ps.project.ClientServices;
import ps.project.IClientServices;
import ps.project.Models.Car;
import ps.project.Models.Comment;
import ps.project.Models.RegularUser;
import ps.project.UI.View.IRegularView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RegularController {

    private final IRegularView regularView;
    private RegularUser regularUser;
    private final RegularUserBll regularUserBll;
    private final IClientServices clientServices;

    public RegularController(IRegularView regularView, RegularUser regularUser, RegularUserBll regularUserBll, IClientServices clientServices) {

        this.clientServices = clientServices;
        this.regularUserBll = regularUserBll;
        this.regularView = regularView;
        this.regularUser = regularUser;


        try {
            regularView.showCarTable(this.retrieveProperties(clientServices.findAllCar()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            regularView.showErrorMessage("Eroare conectare la server");
        }
        regularView.accountInformation(regularUser.getName(), regularUser.getWalletAmount());
        regularUserBll.setClientServices(clientServices);
    }

    public <T> DefaultTableModel retrieveProperties(java.util.List<T> lista) {

        int i = 0;
        int ok = 1;

        DefaultTableModel model = new DefaultTableModel()
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };

        for (Object object : lista) {
            Object data[] = new Object[100];
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(object);
                    if (ok == 1)
                        if(object instanceof Comment){
                            if(!field.getName().equals("id_count"))
                                model.addColumn(field.getName());
                        }else
                        if (!field.getName().equals("userRated") && !field.getName().equals("comment")) {
                            model.addColumn(field.getName());
                        }

                    if(field.getName().equals("image")){
                        if(value!=null) {
                            ImageIcon image = new ImageIcon(new ImageIcon((byte[]) value).getImage()
                                    .getScaledInstance(150, 120, Image.SCALE_SMOOTH));
                            data[i] = image;
                            i++;
                        }else{
                             File file = new File("CarImage/no_image.png");
                            byte[] bFile = new byte[(int) file.length()];
                            FileInputStream fileInputStream = new FileInputStream(file);
                            fileInputStream.read(bFile);
                            fileInputStream.close();

                            ImageIcon image = new ImageIcon(new ImageIcon(bFile).getImage()
                                    .getScaledInstance(150, 120, Image.SCALE_SMOOTH));
                            data[i] = image;
                            i++;
                        }

                    }

                    if (!field.getName().equals("userRated") && !field.getName().equals("image") && !field.getName().equals("id_count")) {
                        data[i] = value;
                        i++;
                    }




                } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(data);
            i = 0;
            ok = 0;
        }
        return model;
    }

    public void buyCar() {
        ArrayList<String> configurationList=regularView.getConfigureOprionList();
        for(String str:configurationList)
            System.out.println(str);
        String selectedRowId = regularView.getSelectedRow();
        try {
            regularUser = regularUserBll.buyCar(selectedRowId, regularUser);
            regularView.accountInformation(regularUser.getName(), regularUser.getWalletAmount());
            this.orderOption();
            regularView.showErrorMessage("Purchase successfully");
        } catch (Exception e) {
            regularView.showErrorMessage(e.getMessage());
            //e.printStackTrace();
        }
    }

    public void orderOption() {
        //0="None", 1="Most Buyed", 2="Price-Low to High", 3="Price-High to Low"
        int typeOption = regularView.getTypeOption();
        //0="None", 1="Sedan", 2="Hatchback", 3="Wagon", 4="SUV"
        int priceOption = regularView.getPriceOption();
        List<Car> carList = null;

        try {
            switch (priceOption) {
                case 0:

                    carList = regularUserBll.findAllCar();
                    regularView.showCarTable(this.retrieveProperties(carList));
                    break;
                case 1:
                    carList = regularUserBll.findAllCarMostBuyed();
                    regularView.showCarTable(this.retrieveProperties(carList));
                    break;
                case 2:
                    carList = regularUserBll.findAllCarPriceAsc();
                    regularView.showCarTable(this.retrieveProperties(carList));
                    break;
                case 3:
                    carList = regularUserBll.findAllCarPriceDesc();
                    regularView.showCarTable(this.retrieveProperties(carList));
                    break;
                case 4:
                    carList = regularUserBll.findAllCarOrderRating();
                    regularView.showCarTable(this.retrieveProperties(carList));
                    break;
                default:
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            regularView.showErrorMessage("Eroare conectare la server");
        }

        switch (typeOption) {
            case 0:
                break;
            case 1:
                regularView.showCarTable(this.retrieveProperties(this.sortByType(carList, "Sedan")));
                break;
            case 2:
                regularView.showCarTable(this.retrieveProperties(this.sortByType(carList, "Hatchback")));
                break;
            case 3:
                regularView.showCarTable(this.retrieveProperties(this.sortByType(carList, "Wagon")));
                break;
            case 4:
                regularView.showCarTable(this.retrieveProperties(this.sortByType(carList, "SUV")));
                break;
            default:
        }
    }

    public List<Car> sortByType(List<Car> carList, String type) {
        List<Car> tCarList = new ArrayList<>();
        for (Car car : carList) {
            if (car.getType().equals(type))
                tCarList.add(car);
        }
        return tCarList;
    }

    public void viewFavoriteCarList() {
        List<Car> carList = regularUser.getFavoritCars();
        ArrayList<Car> carListUpdated=null;
        try {
            carListUpdated= regularUserBll.updateFavoriteCarList(carList);
            /*ArrayList<Car> newCarList=new ArrayList<>();
            for(Car car:carListUpdated)
                newCarList.add(car);

            regularUser.setFavoritCars(newCarList);
            clientServices.updateUser(regularUser);*/

        } catch (Exception e) {
            e.printStackTrace();
        }

        regularView.showFavoriteCarList(retrieveProperties(carListUpdated));

    }

    public void removeFromFavoriteCarList() {
        String idStr = regularView.getSelectedRowFavoriteCarList();
        Long id = Long.parseLong(idStr);

        regularUser.removeFavoritCar(id);
        try {
            clientServices.updateUser(regularUser);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addToFavotiteCarList() {
        String selectedRowId = regularView.getSelectedRow();
        try {
            regularUser = regularUserBll.addToFavoriteCarList(selectedRowId, regularUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewRate() {
        String rateString = regularView.getSelectedRowRateOptionList();
        int rate = Integer.parseInt(rateString);
        String idCarString = regularView.getSelectedRow();
        if (idCarString != null) {
            try {
                regularUserBll.rateCar(idCarString, rate, regularUser);
                this.orderOption();
            } catch (Exception e) {
                regularView.showErrorMessage(e.getMessage());
            }
        } else {
            regularView.showErrorMessage("Please, select a car to rating!");
        }
    }

    public void newCarInStock(Object object)  {
        regularUserBll.verifyNewCarInStock(regularUser, object);

    }


    public void viewComments(){
        String idCarString = regularView.getSelectedRow();
        ArrayList<Comment> comments=null;
        if (idCarString != null) {
            try {
                comments=regularUserBll.viewComments(idCarString);
                regularView.showComments(retrieveProperties(comments));

            } catch (Exception e) {
                regularView.showErrorMessage(e.getMessage());
            }
        } else {
            regularView.showErrorMessage("Please, select a car to view comments!");
        }

    }

    public void likeComment(){
        String idCommentString = regularView.getSelectedRowComment();
        String idCarString = regularView.getSelectedRow();
        try {
            regularUserBll.likeComment(idCommentString,idCarString);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dislikeComment(){
        String idCommentString = regularView.getSelectedRowComment();
        String idCarString = regularView.getSelectedRow();
        try {
            regularUserBll.dislikeComment(idCommentString,idCarString);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addComment(){
        String idCarString = regularView.getSelectedRow();
        String writeComment= regularView.getWriteComment();
        if (idCarString != null) {
            try {
                regularUserBll.addComment(idCarString, writeComment, regularUser);

            } catch (Exception e) {
                regularView.showErrorMessage(e.getMessage());
            }
        } else {
            regularView.showErrorMessage("Please, select a car to view comments!");
        }
    }

    public void addCart(){
        String idCarString = regularView.getSelectedRow();
        if(idCarString==null)
            regularView.showErrorMessage("Please, select a cat for add to cart");
        else {
            List<Car> carList;
            Car car = null;
            try {
                carList = clientServices.findByColumn("id", idCarString);
                car = carList.get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            regularView.showCart(car.toString());
        }
    }


}
