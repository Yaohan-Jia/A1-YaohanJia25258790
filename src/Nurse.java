import java.util.List;

/**
 * A nurse who works on a particular ward and carries out nursing tasks such
 * as taking blood samples and monitoring patients.
 *
 * <p>Inherits all common details from {@link HealthProfessional} and adds the
 * ward the nurse is assigned to.</p>
 */
public class Nurse extends HealthProfessional {

    /** The ward the nurse is assigned to, e.g. "Ward B". */
    private String ward;

    /**
     * Constructs a nurse.
     *
     * @param id          the nurse's digit-only ID
     * @param name        the nurse's full name
     * @param workingDays the weekdays on which the nurse works
     * @param ward        the assigned ward
     */
    public Nurse(String id, String name, List<String> workingDays, String ward) {
        super(id, name, workingDays);
        this.ward = (ward == null || ward.trim().isEmpty())
                ? "Unassigned" : ward.trim();
    }

    /**
     * Returns the assigned ward.
     *
     * @return the ward name
     */
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
