package ps.project.DataAccess;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConnectionFactory {

    private static final ConnectionFactory singleInstance= new ConnectionFactory();

    private SessionFactory createConnection(){
        SessionFactory sessionFactory;
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
            System.err.println("Error in hibernate: ");
            e.printStackTrace();
            return null;
        }
        return sessionFactory;
    }

    public static SessionFactory getConnection(){
        return singleInstance.createConnection();
    }


}
