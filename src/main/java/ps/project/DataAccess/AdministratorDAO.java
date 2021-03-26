package ps.project.DataAccess;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import ps.project.Models.Administrator;
import ps.project.Models.RegularUser;
import ps.project.Models.User;

import java.util.List;

public class AdministratorDAO {
    public List<Administrator> findByAdminstrator(User user){
        Session session = ConnectionFactory.getConnection().openSession();

        Example addressExample = Example.create(user);
        Criteria criteria = session.createCriteria(User.class).add(addressExample);
        return (List<Administrator>) criteria.list();
    }
}
