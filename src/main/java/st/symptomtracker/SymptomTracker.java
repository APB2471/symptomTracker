package st.symptomtracker;

import java.util.Scanner;

/**
 * Symptom tracker main class which begins the loop that
 * starts when the program is initialized.
 *
 * @author Andrew Bush (apb2471@rit.edu)
 */
public class SymptomTracker {
    public static void main(String[] args) {
        SymptomTracker st = new SymptomTracker();
        st.inputLoop();
    }

    public SymptomTracker() {
    }

    public void inputLoop() {
        System.out.println("Symptom Tracker V1.0");
        Scanner sc = new Scanner(System.in);
        System.out.print("To display symptoms, enter d. \nTo add symptoms, enter a.\n-> " );
        String choice = sc.nextLine();
        if (choice.equals("a")) {
            AddSymptoms as = new AddSymptoms();

        } else if (choice.equals("d")) {
            DisplaySymptoms ds = new DisplaySymptoms();

        }
    }
}
