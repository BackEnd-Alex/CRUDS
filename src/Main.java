import br.com.dio.persistence.FilePersistence;
import br.com.dio.persistence.IOFIlePersistence;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new IOFIlePersistence("user.csv");
        persistence.write("Alex;alex@lucas.com;22/04/1983");
        System.out.println("========================================");
        persistence.write("Rejane;rejane@rejane.com;25/07/1985");
        System.out.println("========================================");
        persistence.write("Isabella;isa@bella.com;02/08/2011");
        System.out.println("========================================");
        System.out.println(persistence.findAll());
        System.out.println("========================================");
        System.out.println(persistence.remove("/25/19"));
        System.out.println(persistence.remove("/08/011"));
        System.out.println(persistence.remove("/25/19"));
        System.out.println(persistence.findBy("Alex;"));
        System.out.println(persistence.findBy(";Isabella@"));
        System.out.println(persistence.findBy("85;"));
        System.out.println("========================================");


        }
    }
