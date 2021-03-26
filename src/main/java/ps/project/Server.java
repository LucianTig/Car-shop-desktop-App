package ps.project;

import ps.project.DataAccess.AdministratorDAO;
import ps.project.DataAccess.CarDAO;
import ps.project.DataAccess.RegularUserDAO;
import ps.project.DataAccess.UserDAO;
import ps.project.Models.Administrator;
import ps.project.Models.Car;
import ps.project.Models.RegularUser;
import ps.project.Models.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Server {
    static class Connection extends Thread implements Observer {
        private final Socket clientSocket;
        public static ArrayList<Socket> socketArrayList = new ArrayList<>();
        private final CarDAO carDAO = new CarDAO();

        public Connection(Socket clientSocket) {
            this.clientSocket = clientSocket;
            socketArrayList.add(clientSocket);
            carDAO.addObserver(this);

        }

        @Override
        public void run() {
            try(ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream())) {
                while (clientSocket.isConnected()) {
                    /*if (System.currentTimeMillis() - lastSentMessageMillis > 10000)
                    {
                        System.out.println(Instant.now() + " Sending the notification to client");
                        output.writeObject("Notification");
                        lastSentMessageMillis = System.currentTimeMillis();
                    }*/

                    // Seems that input.available() is not reliable?
                    boolean clientHasData = clientSocket.getInputStream().available() > 0;
                    if (clientHasData) {

                        String msg = (String) input.readObject();
                        System.out.println(msg);
                        if (msg.equals("findByColumnMessage")) {
                            String column = (String) input.readObject();
                            String username = (String) input.readObject();

                            System.out.println(column);
                            System.out.println(username);


                            List<User> userList = (new UserDAO()).findByColumn(column, username);
                            output.writeObject(userList);
                        }

                        if (msg.equals("findAdministratorByUser")) {

                            User user = (User) input.readObject();
                            List<Administrator> administratorList = (new AdministratorDAO()).findByAdminstrator(user);
                            output.writeObject(administratorList);

                        }

                        if (msg.equals("findRegularUserByUser")) {

                            User user = (User) input.readObject();
                            List<RegularUser> regularUserList = (new RegularUserDAO()).findByUser(user);
                            output.writeObject(regularUserList);

                        }

                        if (msg.equals("findAllCar")) {
                            List<Car> carList = carDAO.findAllCar();
                            output.writeObject(carList);
                        }

                        if (msg.equals("findAllCarMostBuyed")) {
                            List<Car> carList = carDAO.findAllCarMostBuyed();
                            output.writeObject(carList);
                        }

                        if (msg.equals("findAllCarPriceAsc")) {
                            List<Car> carList = carDAO.findAllCarPriceAsc();
                            output.writeObject(carList);
                        }

                        if (msg.equals("findAllCarPriceDesc")) {
                            List<Car> carList = carDAO.findAllCarPriceDesc();
                            output.writeObject(carList);
                        }

                        if (msg.equals("findAllCarOrderRating")) {
                            List<Car> carList = carDAO.findAllCarOrderRating();
                            output.writeObject(carList);
                        }

                        if (msg.equals("findByColumnCar")) {
                            String column = (String) input.readObject();
                            String value = (String) input.readObject();

                            List<Car> carList = carDAO.findByColumn(column, value);
                            output.writeObject(carList);

                        }

                        if (msg.equals("updateUser")) {
                            RegularUser regularUser = (RegularUser) input.readObject();
                            (new RegularUserDAO()).updateUser(regularUser);

                        }

                        if (msg.equals("updateCar")) {
                            Car car = (Car) input.readObject();
                            carDAO.updateCar(car);
                        }

                        if (msg.equals("findAllUsers")) {
                            List<RegularUser> regularUserList = (new RegularUserDAO()).findAllUser();
                            output.writeObject(regularUserList);
                        }

                        if (msg.equals("insertRegularUser")) {
                            System.out.println("AICI SE INTRA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            RegularUser regularUser = (RegularUser) input.readObject();
                            (new RegularUserDAO()).insertRegularUser(regularUser);
                        }

                        if (msg.equals("findByColumnRegularUser")) {
                            String column = (String) input.readObject();
                            String value = (String) input.readObject();
                            RegularUser regularUser = (new RegularUserDAO()).findByColumn(column, value);
                            output.writeObject(regularUser);
                        }

                        if (msg.equals("insertCar")) {
                            System.out.println("Aici server se ajunge 5");
                            Car car = (Car) input.readObject();
                            carDAO.insertCar(car);
                        }

                        if (msg.equals("updateCarByCondition")) {
                            String column = (String) input.readObject();
                            String value = (String) input.readObject();
                            String setColumn = (String) input.readObject();
                            String setValue = (String) input.readObject();
                            carDAO.updateCarByCondition(column, value, setColumn, setValue);
                        }

                        if (msg.equals("updateRegularUserByCondition")) {
                            String column = (String) input.readObject();
                            String value = (String) input.readObject();
                            String setColumn = (String) input.readObject();
                            String setValue = (String) input.readObject();
                            (new RegularUserDAO()).updateRegularUserByCondition(column, value, setColumn, setValue);
                        }

                        if (msg.equals("deleteCarByCondition")) {
                            String column = (String) input.readObject();
                            String value = (String) input.readObject();
                            carDAO.deleteCarByCondition(column, value);
                        }

                        if (msg.equals("deleteRegularUserByCondition")) {
                            String column = (String) input.readObject();
                            String value = (String) input.readObject();
                            (new RegularUserDAO()).deleteRegularUserByCondition(column, value);
                        }


                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Instant.now() + " Client disconnected?");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            // cleanup
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void update(Observable o, Object arg) {

            /*try {
                System.out.println("AICI SE INTRA!!!!!!!!!!!!!!!!!");
                for (Socket socket : socketArrayList) {

                    if(socket!=clientSocket) {
                        System.out.println("SE INTRA IN FOR!!!!!!!!!!!!!!!!!!!");
                        System.out.println("NEW SOCKET "+socket);
                        ObjectOutputStream output1 = new ObjectOutputStream(socket.getOutputStream());

                        output1.writeObject(arg);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }


    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        try (ServerSocket socket = new ServerSocket(3000)) {
            while (true) {
                System.out.println(Instant.now() + " Waiting for client...");
                Socket clientSocket = socket.accept();
                new Server.Connection(clientSocket).start();
            }
        }
    }

}
