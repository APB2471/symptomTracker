package st.symptomtracker;

import java.util.Scanner;

/**
 * Symptom tracker main class which begins the loop that
 * starts when the program is initialized.
 *
 * @author Andrew Bush (apb2471@rit.edu)
 * @author Ryan Ong (rto9185@rit.edu)
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
        while (true) {
            System.out.print("To display symptoms, enter 1. \nTo add symptoms, enter 2.\n-> ");
            String choice = sc.nextLine();
            if (choice.equals("2")) {
                AddSymptoms as = new AddSymptoms();
                as.SymptomLoop();
                break;

            } else if (choice.equals("1")) {
                DisplaySymptoms ds = new DisplaySymptoms();
                ds.setVisible(true);
                break;

            } else {
                System.out.println("Incorrect Input, please enter 1 or 2 to select.");
            }
        }
        RecieveFromDB display = new RecieveFromDB();
        display.displayCollection();
    }
}
