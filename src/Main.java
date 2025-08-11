import br.com.dio.persistence.FilePersistence;
import br.com.dio.persistence.IOFIlePersistence;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new IOFIlePersistence("user.csv");
        persistence.write("Alex;Alex@lucas.com;22/04/19983");

        }
    }
