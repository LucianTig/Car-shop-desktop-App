package ps.project.DataAccess;

import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import ps.project.Models.RegularUser;
import ps.project.Models.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RegularUserDAO {

    public List<RegularUser> findByUser(User user){
        Session session = ConnectionFactory.getConnection().openSession();

        Example addressExample = Example.create(user);
        Criteria criteria = session.createCriteria(User.class).add(addressExample);
        List<RegularUser> userList=criteria.list();

        return userList;
    }

    public void updateUser(User user){
        Session session = ConnectionFactory.getConnection().openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();

    }

    public List<RegularUser> findAllUser(){
        Session session = ConnectionFactory.getConnection().openSession();
        List<RegularUser> regularUserList = session.createQuery("from RegularUser", RegularUser.class).list();
        return regularUserList;
    }

    public void insertRegularUser(RegularUser regularUser){
        Session session = ConnectionFactory.getConnection().openSession();
        session.beginTransaction();
        session.save(regularUser);
        session.getTransaction().commit();
    }

    public RegularUser findByColumn(String column, Object value) {
        Session session = ConnectionFactory.getConnection().openSession();
        Criteria criteria = session.createCriteria(RegularUser.class);
        RegularUser regularUser = (RegularUser) criteria.add(Restrictions.eq(column, value)).uniqueResult();
        return regularUser;
    }

    public void updateRegularUserByCondition(String col, String valCol, String setCol, String setValCol){
        Session session = ConnectionFactory.getConnection().openSession();
        session.beginTransaction();
        Query query = session.createQuery("update RegularUser set "+setCol+"="+"'"+setValCol+"'"+" where "+col+"="+"'"+valCol+"'");

        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void deleteRegularUserByCondition(String col, String val){
        Session session = ConnectionFactory.getConnection().openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from RegularUser"+" where "+col+"="+"'"+val+"'");
        query.executeUpdate();
        session.getTransaction().commit();
    }


}
