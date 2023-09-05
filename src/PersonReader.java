import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        List<Person> recs = new ArrayList<>();

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path filePath = Paths.get(selectedFile.getPath());

                try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                    int line = 0;
                    String lineData;
                    while ((lineData = reader.readLine()) != null) {
                        String[] rec = lineData.split(", ");
                        if (rec.length == 5) {
                            Person p = new Person(rec[0], rec[1], rec[2], rec[3], Integer.parseInt(rec[4]));
                            recs.add(p);
                            line++;
                            System.out.printf("\nLine %4d: %-60s", line, p);
                        } else {
                            System.out.printf("\nLine %4d: Invalid data format", line);
                        }
                    }
                    System.out.println("\n\nData file read!");
                } catch (IOException e) {
                    System.err.println("Error reading the file.");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
