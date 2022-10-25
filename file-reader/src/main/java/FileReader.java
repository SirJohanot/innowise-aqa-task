import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class FileReader {

    public FileReader() {
    }

    public String readLineFromFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        return scanner.nextLine();
    }
}
