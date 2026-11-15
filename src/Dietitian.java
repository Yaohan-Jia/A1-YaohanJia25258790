import java.util.List;

/**
 * A dietitian who gives patients nutrition and eating advice.
 */
public class Dietitian extends HealthProfessional {

    private String qualification;

    /**
     * Creates a dietitian.
     *
     * @param id           digits-only professional ID
     * @param name         professional's full name
     * @param workingDays  weekdays the dietitian works
     * @param qualification professional qualification level
     */
    public Dietitian(String id, String name, List<String> workingDays, String qualification) {
        super(id, name, workingDays);
        this.qualification = (qualification == null || qualification.trim().isEmpty())
                ? "Registered" : qualification.trim();
    }

    /** Returns the dietitian's professional qualification. */
    public String getQualification() {
        return qualification;
    }

    /** Returns the specialty label for this professional type. */
    @Override
    public String getSpecialty() {
        return "Dietitian";
    }

    /** Describes what this role does. */
    @Override
    public String describeRole() {
        return "Reviews patients' diets and gives nutrition advice.";
    }

    /** Combines the shared details with this type's own field. */
    @Override
    public String toString() {
        return super.toString() + ", Specialty: " + getSpecialty()
                + ", Qualification: " + qualification;
    }
}
