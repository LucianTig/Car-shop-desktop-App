package ps.project.DataAccess;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ps.project.Models.User;

import java.util.List;

public class UserDAO {

    public List<User> findByColumn(String column, String value){
        Session session = ConnectionFactory.getConnection().openSession();

        Criteria criteria = session.createCriteria(User.class);
        List<User> userList = criteria.add(Restrictions.eq(column, value)).list();

        return userList;
    }
}
