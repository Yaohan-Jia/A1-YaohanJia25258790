import java.util.List;

/**
 * A nurse assigned to a ward, who carries out nursing tasks such as taking
 * blood samples.
 */
public class Nurse extends HealthProfessional {

    private String ward;

    /**
     * Creates a nurse.
     *
     * @param id          digits-only professional ID
     * @param name        professional's full name
     * @param workingDays weekdays the nurse works
     * @param ward        ward the nurse works on
     */
    public Nurse(String id, String name, List<String> workingDays, String ward) {
        super(id, name, workingDays);
        this.ward = (ward == null || ward.trim().isEmpty())
                ? "Unassigned" : ward.trim();
    }

    public String getWard() {
        return ward;
    }

    @Override
    public String getSpecialty() {
        return "Nurse";
    }

    @Override
    public String describeRole() {
        return "Provides nursing care and may take blood samples for patients.";
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialty: " + getSpecialty()
                + ", Ward: " + ward;
    }
}
