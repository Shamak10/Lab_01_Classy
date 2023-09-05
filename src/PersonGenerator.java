import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> persons = new ArrayList<>();

        do {
            String ID = getValidInput(scanner, "Enter your ID number (e.g., 000001):", "\\d{6}");
            String firstName = getValidInput(scanner, "Enter your First Name:", "\\w+");
            String lastName = getValidInput(scanner, "Enter your Last Name:", "\\w+");
            String title = getValidInput(scanner, "Enter your Title:", "\\w+");
            int YOB = getValidIntInput(scanner, "Enter your Year of Birth (1940-2000):", 1940, 2000);

            Person person = new Person(ID, firstName, lastName, title, YOB);
            persons.add(person);

        } while (getConfirmation(scanner, "Do you want to enter another person? (yes/no):"));

        saveToFile(persons);

        System.out.println("Data file written!");
    }

    private static String getValidInput(Scanner scanner, String prompt, String regex) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        } while (!input.matches(regex));
        return input;
    }

    private static int getValidIntInput(Scanner scanner, String prompt, int min, int max) {
        int input;
        do {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                input = -1; // Invalid input
            }
        } while (input < min || input > max);
        return input;
    }

    private static boolean getConfirmation(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim().toLowerCase();
        } while (!input.equals("yes") && !input.equals("no"));
        return input.equals("yes");
    }

    private static void saveToFile(ArrayList<Person> persons) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("persons.txt"))) {
            for (Person person : persons) {
                String dataRecord = person.toCSVDataRecord();
                System.out.println("Record: " + dataRecord);
                writer.write(dataRecord);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
