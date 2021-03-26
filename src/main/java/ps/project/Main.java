package ps.project;

/*import org.hibernate.Session;
import ps.project.DataAccess.ConnectionFactory;
import ps.project.Models.Administrator;
import ps.project.Models.Car;
import ps.project.Models.RegularUser;
import ps.project.Models.User;*/
import ps.project.UI.View.LoginView;

/*import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;*/
import java.io.IOException;
import java.net.Socket;
/*import java.sql.Blob;
import java.sql.SQLException;*/

public class Main{
    public static void main(String[] args) {

        Client.Connection connection=null;
        try {
            connection=new Client.Connection(new Socket("localhost", 3000));
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.start();

        LoginView loginView = new LoginView(connection);
        loginView.setVisible(true);

        /*SessionFactory sessionFactory;
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
            return;
        }*/

        /*try(Session session = ConnectionFactory.getConnection().openSession()) {
            //TODO: Don't forget beginTransaction/commit when doing *changes* on the data
            session.beginTransaction();

            BufferedImage myPicture1 = ImageIO.read(new File("CarImage/citroen-c4.png"));
            BufferedImage myPicture2 = ImageIO.read(new File("CarImage/bmwm5.png"));
            BufferedImage myPicture3 = ImageIO.read(new File("CarImage/passatCC.png"));


            File file = new File("CarImage/citroen-c4.png");
            byte[] bFile = new byte[(int) file.length()];
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();

            File file2 = new File("CarImage/bmwm5.png");
            byte[] bFile2 = new byte[(int) file2.length()];
            FileInputStream fileInputStream2 = new FileInputStream(file2);
            fileInputStream2.read(bFile2);
            fileInputStream2.close();

            File file3 = new File("CarImage/passatCC.png");
            byte[] bFile3 = new byte[(int) file3.length()];
            FileInputStream fileInputStream3 = new FileInputStream(file3);
            fileInputStream3.read(bFile3);
            fileInputStream3.close();



            //session.save(new User("Daniel","daniel"));
            //session.save(new User("John", "john"));
            session.save(new Administrator("John", "john","Lucian Tigarean", "Observatorului","21.04.2019"));
            session.save(new RegularUser("Daniel", "daniel", "Robert Oprea", "21.04.2020",3000));
            session.save(new Car("Wagon","BMW","320D",3500,10,1,null));
            session.save(new Car("Hatchback","BMW","120D",4000,5,3,null));
            session.save(new Car("Coupe","BMW","420D",13500,4,0,null));
            session.save(new Car("Hatchback","Volkswagen","Golf 7",23500,10,1,null));
            session.save(new Car("Sedan","Volkswagen","Passat CC",11500,3,3,bFile3));
            session.save(new Car("Sedan","Audi","A4 B8",6500,5,5,null));
            session.save(new Car("Hatchback","Volksagen","Golf 4",1500,4,10,null));
            session.save(new Car("Wagon","Audi","A4 B6",2500,10,1,null));
            session.save(new Car("Hatchback","Citroen","C4",4500,10,5,bFile));
            session.save(new Car("Sedan","BMW","M5",25000,1,0,bFile2));
            session.save(new Car("Wagon","Audi","RS6",50000,2,0,null));
            session.save(new Car("Sedan","Audi","A8",7500,2,1,null));

            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*
        // Search using HQL
        try(Session session = sessionFactory.openSession()) {
            List<User> usersHql = session.createQuery("from User", User.class).list();
            for (User user : usersHql) {
                System.out.println(">> User: " + user.getName());
            }
        }

        // Search using Criteria
        try(Session session = sessionFactory.openSession()) {
            List<User> usersCriteria = session.createCriteria(User.class).list();
            for (User user : usersCriteria) {
                System.out.println(">> User: " + user.getName());
            }
        }

        // deleting John
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> johns = session.createQuery("from User where name='John'", User.class).list();
            session.delete(johns.get(0));
            session.getTransaction().commit();
        }

        // updating Daniel
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<User> users = session.createQuery("from User", User.class).list();
            User John = users.get(0);
            John.setName("Another Daniel");
            session.update(John);
            session.getTransaction().commit();
        }

        try(Session session = sessionFactory.openSession()) {
            List<User> usersAfterDeleteAndUpdate = session.createCriteria(User.class).list();
            for (User user : usersAfterDeleteAndUpdate) {
                System.out.println(">> User: " + user.getName());
            }
        }*/
    }
}
