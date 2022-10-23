import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class FileReaderTest {

    private static String FILE_PATH;

    private FileReader fileReader;

    @BeforeAll
    static void setupBeforeAll(){
        FILE_PATH = "src/test/resources/file.txt";
    }

    @AfterAll
    static void cleanupAfterAll(){
        FILE_PATH = null;
    }

    @BeforeEach
    public void setupBeforeEach(){
        fileReader = new FileReader();
    }

    @AfterEach
    public void cleanupAfterEach(){
        fileReader = null;
    }


    @Test
    public void testLineFromFileShouldReturnTheFirstLineFromTheFileWhenFileExists() throws FileNotFoundException {
        //given
        String expectedLine = "Hello World!";
        //when
        String actualLine = fileReader.readLineFromFile(FILE_PATH);
        //then
        Assertions.assertEquals(expectedLine, actualLine);
    }
}
