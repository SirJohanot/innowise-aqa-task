import java.io.InputStream;
import java.util.Scanner;

public class FileReader {

    public FileReader() {
    }

    public String readLineFromFile(String filePath){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(filePath);
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextLine();
    }
}
