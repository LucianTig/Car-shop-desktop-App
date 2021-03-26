package ps.project.BusinessLogic;

import ps.project.ClientServices;
import ps.project.IClientServices;
import ps.project.Models.Car;
import ps.project.Models.Comment;
import ps.project.Models.RegularUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegularUserBll implements IRegularUserBll {

    private IClientServices clientServices=null;


    public void setClientServices(IClientServices clientServices){
        this.clientServices=clientServices;
    }

    public RegularUserBll(){

    }

    public List<Car> findAllCar() throws IOException, ClassNotFoundException {
        return clientServices.findAllCar();
    }

    @Override
    public List<Car> findAllCarMostBuyed() throws IOException, ClassNotFoundException {
        return clientServices.findAllCarMostBuyed();
    }
    @Override
    public List<Car> findAllCarPriceAsc() throws IOException, ClassNotFoundException {
        return clientServices.findAllCarPriceAsc();
    }
    @Override
    public List<Car> findAllCarPriceDesc() throws IOException, ClassNotFoundException {
        return clientServices.findAllCarPriceDesc();
    }

    @Override
    public List<Car> findAllCarOrderRating() throws IOException, ClassNotFoundException {
        return clientServices.findAllCarOrderRating();
    }

    @Override
    public List<Car> findByColumn(String column, String value) throws IOException, ClassNotFoundException {
        List<Car> carList=clientServices.findByColumn(column, value);
        return carList;
    }

    @Override
    public RegularUser buyCar(String id, RegularUser regularUser) throws Exception{
        List<Car> carList=clientServices.findByColumn("id", id);

        Car car;
        if(carList.size()!=0){
            car=carList.get(0);
            if(car.getPrice()>regularUser.getWalletAmount() || car.getStock()<=0){
                throw new Exception("Insufficient money/stock insufficient");
            }
            regularUser.setWalletAmount(regularUser.getWalletAmount()-car.getPrice());

            //(new RegularUserDAO()).updateUser(regularUser);
            clientServices.updateUser(regularUser);
            car.setStock(car.getStock()-1);
            car.setSoldNumber(car.getSoldNumber()+1);

            //(new CarDAO()).updateCar(car);
            clientServices.updateCar(car);
            return regularUser;
        }
        throw new Exception("Purchase error");

    }

    @Override
    public RegularUser addToFavoriteCarList(String id, RegularUser regularUser) throws Exception {
        List<Car> carList=clientServices.findByColumn("id", id);
        Car car;
        if(carList.size()!=0){
            car=carList.get(0);
        }else{
            throw new Exception("Car not found");
        }
        regularUser.addFavoritCar(car);
        clientServices.updateUser(regularUser);
        return regularUser;
    }

    @Override
    public void rateCar(String idCarSelectedRow, int rate, RegularUser regularUser) throws Exception {

        List<Car> carList=clientServices.findByColumn("id", idCarSelectedRow);
        Car car=carList.get(0);
        if(car.getUserRated().containsKey(regularUser.getUsername())){
            throw new Exception("Car allready rated");
        }

        if(car.getNoEvaluation()==0) {
            car.setRating(rate);
            car.setNoEvaluation(1);
        }else{
            car.setRating((car.getRating() * car.getNoEvaluation()+rate) / (car.getNoEvaluation() + 1));
            car.setNoEvaluation(car.getNoEvaluation()+1);
        }
        car.addUserRated(regularUser.getUsername(),rate);
        clientServices.updateCar(car);
    }

    @Override
    public boolean verifyNewCarInStock(RegularUser regularUser, Object object) {
        ArrayList<String> arrayList=null;
        Car car=null;
        if(object instanceof ArrayList){
            arrayList=(ArrayList)object;
        }
        if(object instanceof Car){
            car=(Car)object;
        }

        if(arrayList!=null){
            if(arrayList.get(2).equals("stock") && Integer.parseInt(arrayList.get(3))!=0){

            }
        }
        List<Car> favoriteCarList=regularUser.getFavoritCars();
        boolean isNewCar=false;

        return true;

    }

    @Override
    public ArrayList<Car> updateFavoriteCarList(List<Car> cars) throws IOException, ClassNotFoundException {
        ArrayList<Car> carListUpdated=new ArrayList<>();
        for(Car car:cars){
            List<Car> carList=clientServices.findByColumn("id",car.getId().toString());
            if(carList.size()!=0) {
                carListUpdated.add(carList.get(0));
            }
        }
        return carListUpdated;

    }

    @Override
    public ArrayList<Comment> viewComments(String idCarSelectedRow) throws IOException, ClassNotFoundException {
        List<Car> carList=clientServices.findByColumn("id", idCarSelectedRow);
        return carList.get(0).getComment();
    }

    @Override
    public void addComment(String idCarString, String writeComment, RegularUser regularUser) throws IOException, ClassNotFoundException {
        List<Car> carList=clientServices.findByColumn("id", idCarString);
        Car car=carList.get(0);

        Comment comment=new Comment(regularUser.getUsername(),writeComment);
        car.addComment(comment);
        clientServices.updateCar(car);
    }

    @Override
    public void likeComment(String idCommentString, String idCarString) throws IOException, ClassNotFoundException {
        List<Car> carList=clientServices.findByColumn("id", idCarString);
        Car car=carList.get(0);

        ArrayList<Comment> comments=car.getComment();
        for(Comment comment: comments){
            if(comment.getId() == Integer.parseInt(idCommentString)){
                comment.setLike(comment.getLike()+1);
            }
        }
        clientServices.updateCar(car);

    }

    @Override
    public void dislikeComment(String idCommentString, String idCarString) throws IOException, ClassNotFoundException {
        List<Car> carList=clientServices.findByColumn("id", idCarString);
        Car car=carList.get(0);

        ArrayList<Comment> comments=car.getComment();
        for(Comment comment: comments){
            if(comment.getId() == Integer.parseInt(idCommentString)){
                comment.setDislike(comment.getDislike()+1);
            }
        }
        clientServices.updateCar(car);


    }

}
