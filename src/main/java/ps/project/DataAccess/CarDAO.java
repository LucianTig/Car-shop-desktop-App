package ps.project.DataAccess;

import javafx.beans.InvalidationListener;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import ps.project.Models.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class CarDAO extends Observable {

    public List<Car> findAllCar(){
        Session session = ConnectionFactory.getConnection().openSession();
        List<Car> carList = session.createQuery("from Car", Car.class).list();
        return carList;
    }

    public List<Car> findAllCarMostBuyed(){
        Session session = ConnectionFactory.getConnection().openSession();
        List result = session.createCriteria(Car.class).addOrder(Order.desc("soldNumber")).list();
        return result;
    }

    public List<Car> findAllCarPriceAsc(){
        Session session = ConnectionFactory.getConnection().openSession();
        List result = session.createCriteria(Car.class).addOrder(Order.asc("price")).list();
        return result;
    }

    public List<Car> findAllCarPriceDesc(){
        Session session = ConnectionFactory.getConnection().openSession();
        List result = session.createCriteria(Car.class).addOrder(Order.desc("price")).list();
        return result;
    }

    public List<Car> findAllCarOrderRating(){
        Session session = ConnectionFactory.getConnection().openSession();
        List result = session.createCriteria(Car.class).addOrder(Order.desc("rating")).list();
        return result;
    }

    public List<Car> findByColumn(String column, String value){
        Session session = ConnectionFactory.getConnection().openSession();

        Criteria criteria = session.createCriteria(Car.class);
        List<Car> carList = criteria.add(Restrictions.eq(column, Long.parseLong(value))).list();

        return carList;
    }

    public void updateCar(Car car){
        Session session = ConnectionFactory.getConnection().openSession();
        session.beginTransaction();
        session.update(car);
        session.getTransaction().commit();
        setChanged();
        notifyObservers(car);
    }

    public void insertCar(Car car){
        Session session = ConnectionFactory.getConnection().openSession();
        session.beginTransaction();
        session.save(car);
        session.getTransaction().commit();
    }

    public void updateCarByCondition(String col, String valCol, String setCol, String setValCol){
        Session session = ConnectionFactory.getConnection().openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Car set "+setCol+" = "+"'"+setValCol+"'"+" where "+col+"="+"'"+valCol+"'");
        query.executeUpdate();
        session.getTransaction().commit();

        ArrayList<String> modifiedCar=new ArrayList<>();
        modifiedCar.add(col);
        modifiedCar.add(valCol);
        modifiedCar.add(setCol);
        modifiedCar.add(setValCol);
        setChanged();
        notifyObservers(modifiedCar);
    }

    public void deleteCarByCondition(String col, String val){
        Session session = ConnectionFactory.getConnection().openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Car"+" where "+col+"="+"'"+val+"'");
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
