import java.util.List;

/**
 * A dietitian who gives patients nutrition and eating advice.
 */
public class Dietitian extends HealthProfessional {

    private String qualification;

    /**
     * Creates a dietitian.
     *
     * @param qualification professional qualification level
     */
    public Dietitian(String id, String name, List<String> workingDays, String qualification) {
        super(id, name, workingDays);
        this.qualification = (qualification == null || qualification.trim().isEmpty())
                ? "Registered" : qualification.trim();
    }

    public String getQualification() {
        return qualification;
    }

    @Override
    public String getSpecialty() {
        return "Dietitian";
    }

    @Override
    public String describeRole() {
        return "Reviews patients' diets and gives nutrition advice.";
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialty: " + getSpecialty()
                + ", Qualification: " + qualification;
    }
}
