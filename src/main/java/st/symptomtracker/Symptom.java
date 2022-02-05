package st.symptomtracker;
import java.time.*;

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
        return "Symptom: " + this.name + "; Onset: "
                + this.onset.toString() + "; Severity: " + this.severity;
    }
}