import java.util.List;

/**
 * A dietitian who advises patients on nutrition and healthy eating plans.
 *
 * <p>Inherits all common details from {@link HealthProfessional} and adds the
 * dietitian's professional qualification level.</p>
 */
public class Dietitian extends HealthProfessional {

    /** The qualification level, e.g. "Accredited Practising Dietitian". */
    private String qualification;

    /**
     * Constructs a dietitian.
     *
     * @param id            the dietitian's digit-only ID
     * @param name          the dietitian's full name
     * @param workingDays   the weekdays on which the dietitian works
     * @param qualification the professional qualification level
     */
    public Dietitian(String id, String name, List<String> workingDays, String qualification) {
        super(id, name, workingDays);
        this.qualification = (qualification == null || qualification.trim().isEmpty())
                ? "Registered" : qualification.trim();
    }

    /**
     * Returns the qualification level.
     *
     * @return the qualification
     */
    public String getQualification() {
        return qualification;
    }

    @Override
    public String getSpecialty() {
        return "Dietitian";
    }

    @Override
    public String describeRole() {
        return "Assesses patients' diets and gives tailored nutrition advice.";
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialty: " + getSpecialty()
                + ", Qualification: " + qualification;
    }
}
