package ps.project.Models;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.util.List;

public class PdfFile implements File {


    @Override
    public void generateFile(String path, List<Car> listCar) {

        PdfDocument pdf = null;
        try {
            pdf = new PdfDocument(new PdfWriter(path+"/report.pdf"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Document document = new Document(pdf);
        String line = "Car Report Stock Items";
        document.add(new Paragraph(line).setBold());

        String carInfo="";
        for(Car car: listCar){
            carInfo=carInfo+car.toString()+"\n";
        }
        document.add(new Paragraph(carInfo));
        document.close();

        System.out.println("Awesome PDF just got created.");

    }
}
