package ps.project.Models;


import java.io.PrintWriter;
import java.util.List;

public class TxtFile implements File {


    @Override
    public void generateFile(String path, List<Car> listCar) {
        path=path+"\\Report.txt";
        try {
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            writer.println("                                  Report Car Stock                             \n\n");

            for(Car car:listCar) {
                writer.println(car.toString());
            }
            writer.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
