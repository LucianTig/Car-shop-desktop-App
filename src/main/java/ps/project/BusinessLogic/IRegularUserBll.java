package ps.project.BusinessLogic;

import ps.project.Models.Car;
import ps.project.Models.Comment;
import ps.project.Models.RegularUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IRegularUserBll {



    List<Car> findAllCarMostBuyed() throws IOException, ClassNotFoundException;
    List<Car> findAllCarPriceAsc() throws IOException, ClassNotFoundException;
    List<Car> findAllCarPriceDesc() throws IOException, ClassNotFoundException;

    List<Car> findAllCarOrderRating() throws IOException, ClassNotFoundException;

    List<Car> findByColumn(String column, String value) throws IOException, ClassNotFoundException;

    RegularUser buyCar(String id, RegularUser regularUser) throws Exception;

    RegularUser addToFavoriteCarList(String id, RegularUser regularUser) throws Exception;

    void rateCar(String idCarSelectedRow, int rate, RegularUser regularUser) throws Exception;

    boolean verifyNewCarInStock(RegularUser regularUser, Object object);

    ArrayList<Car> updateFavoriteCarList(List<Car> cars) throws IOException, ClassNotFoundException;

    ArrayList<Comment> viewComments(String idCarSelectedRow) throws IOException, ClassNotFoundException;

    void addComment(String idCarString, String writeComment, RegularUser regularUser) throws IOException, ClassNotFoundException;

    void likeComment(String idCommentString, String idCarString) throws IOException, ClassNotFoundException;

    void dislikeComment(String idCommentString, String idCarString) throws IOException, ClassNotFoundException;

}
