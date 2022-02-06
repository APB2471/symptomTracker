package st.symptomtracker;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.*;

public class AddSymptoms {
    LocalDateTime onset;
    Symptom symptom;

    public AddSymptoms() {
    }

    AddToDB test = new AddToDB();

    public void SymptomLoop() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("What Symptom are you experiencing?: ");
            String name = sc.next();
            System.out.print("If the symptom started before now, " +
                    "enter the date (MM/DD/YYYY): ");
            String date = sc.next();
            if (date != null) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mma");
                LocalDate datePart = LocalDate.parse(date, dateFormat);
                sc.nextLine();
                System.out.print("Estimate the time the symptom started (HH:MM AM/PM): ");
                String time = sc.nextLine().strip();
                time = time.replaceAll("\\s+", "");
                LocalTime timePart = LocalTime.parse(time, timeFormat);
                onset = LocalDateTime.of(datePart, timePart);
            } else {
                onset = LocalDateTime.now();
            }

            System.out.print("Provide the severity of the symptom from 1-10: ");
            int severity = sc.nextInt();

            System.out.print("Would you like to add another symptom (yes/no): ");

            // adds data to database
            test.insert(symptom.getName(), symptom.getOnset(), symptom.getSeverity());
            String another = sc.next();

            if (another.equals("no")) {
                symptom = new Symptom(name, onset, severity);
                break;
            }
            System.out.println("\n");
        }
        System.out.print("Your symptom is: " + symptom);
    }
}
