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

    /** Returns the ward the nurse works on. */
    public String getWard() {
        return ward;
    }

    /** Returns the specialty label for this professional type. */
    @Override
    public String getSpecialty() {
        return "Nurse";
    }

    /** Describes what this role does. */
    @Override
    public String describeRole() {
        return "Provides nursing care and may take blood samples for patients.";
    }

    /** Combines the shared details with this type's own field. */
    @Override
    public String toString() {
        return super.toString() + ", Specialty: " + getSpecialty()
                + ", Ward: " + ward;
    }
}
