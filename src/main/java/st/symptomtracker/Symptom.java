package st.symptomtracker;
import java.time.*;

/**
 * @author Andrew Bush (apb2471@rit.edu)
 * @author Ryan Ong (rto9185@rit.edu)
 */
public class Symptom {
    private final String name;
    private final LocalDateTime onset;
    private final int severity;
    public Symptom(String name, LocalDateTime onset, int severity) {
        this.name = name;
        this.onset = onset;
        this.severity = severity;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getOnset() {
        return this.onset;
    }

    public int getSeverity() {
        return this.severity;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "; Onset: "
                + this.onset.toString() + "; Severity: " + this.severity;
    }
}