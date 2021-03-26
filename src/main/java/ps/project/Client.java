package ps.project;

import ps.project.UI.Controller.RegularController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Instant;

public class Client {

    public static class Connection extends Thread {
        private final Socket socket;
        private final ObjectOutputStream output;
        private final ObjectInputStream input;
        private RegularController regularController;

        public Connection(Socket socket) throws IOException {
            this.socket = socket;
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

        }

        @Override
        public void run() {
            try {
                while (socket.isConnected()) {
                    // Seems that input.available() is not reliable?
                    boolean serverHasData = socket.getInputStream().available() > 0;
                    if (serverHasData) {
                        System.out.println("Socketul: "+socket);
                        Object object=input.readObject();

                        System.out.println("AICI SE INTRA "+object);
                        System.out.println(regularController);
                        if(regularController!=null)
                            regularController.newCarInStock(object);

                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Instant.now() + " Server disconnected");
            } catch (Exception  e) {
                e.printStackTrace();
            }
        }

        public void sendMessageToServer(Object obj) throws IOException {
            output.writeObject(obj);
        }

        public Object getMessage() throws IOException, ClassNotFoundException {
            return input.readObject();
        }

        public void setRegularController(RegularController regularController){
            this.regularController=regularController;
        }
    }
}
