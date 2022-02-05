package st.symptomtracker;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;
import java.time.*;

public class AddSymptoms {
    LocalDateTime onset;
    Symptom symptom;

    public AddSymptoms() {
    }

    public void SymptomLoop() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("What Symptom are you experiencing?: ");
            String name = sc.nextLine();
            System.out.print("If the symptom started before now, " +
                    "enter the date (MM/dd/yyyy): ");
            String date = sc.next();
            if (date != null) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDate datePart = LocalDate.parse(date, dateFormat);
                System.out.print("Estimate the time the symptom started (hh:mm:ss): ");
                String time = sc.next();
                LocalTime timePart = LocalTime.parse(time, timeFormat);
                onset = LocalDateTime.of(datePart, timePart);
            } else {
                onset = LocalDateTime.now();
            }

            System.out.print("Provide the severity of the symptom from 1-10: ");
            int severity = sc.nextInt();

            System.out.println("Would you like to add another symptom (yes/no): ");
            String another = sc.next();
            if (another.equals("no")) {
                symptom = new Symptom(name, onset, severity);
                break;
            }
        }
        System.out.print("Your symptom is: " + symptom);

        // test
        AddToDB test = new AddToDB();
        test.insert(symptom.getName(), symptom.getOnset(), symptom.getSeverity());
    }
}
