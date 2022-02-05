package st.symptomtracker;
import java.util.Scanner;
import java.time.*;

public class AddSymptoms {
    LocalDateTime onset;

    public AddSymptoms() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("What Symptom are you experiencing?: ");
            String name = sc.nextLine();
            System.out.print("If the symptom started before now, enter the date: ");
            String date = sc.next();
            if (date != null) {
                LocalDate datePart = LocalDate.parse(date);
                System.out.print("Estimate the time the symptom started: ");
                String time = sc.next();
                LocalTime timePart = LocalTime.parse(time);
                onset = LocalDateTime.of(datePart, timePart);
            } else {
                onset = LocalDateTime.now();
            }

            System.out.print("Provide the severity of the symptom from 1-10: ");
            int severity = sc.nextInt();

            System.out.print("Would you like to add another symptom (true/false): ");
            boolean next = sc.nextBoolean();
            if (!next) {
                break;
            }
            Symptom symptom = new Symptom(name, onset, severity);
            System.out.print("Your symptom is: " + symptom);
        }

    }
}
