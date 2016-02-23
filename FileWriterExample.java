import java.io.*;
public class FileWriterExample {
    public static void main(String[] args) {
        String text = "Hello world";
        try {
          File file = new File("example.txt");
          BufferedWriter output = new BufferedWriter(new FileWriter(file));
          output.write(text);
          output.close();
        } catch ( IOException e ) {
           e.printStackTrace();
        }
    }
}
