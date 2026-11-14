import java.util.List;

/**
 * A specialist doctor working in one medical field, such as a cardiologist
 * or paediatrician.
 */
public class Specialist extends HealthProfessional {

    private String specialtyField;

    /**
     * Creates a specialist.
     *
     * @param id             digits-only professional ID
     * @param name           professional's full name
     * @param workingDays    weekdays the specialist works
     * @param specialtyField the medical field, e.g. Cardiologist
     */
    public Specialist(String id, String name, List<String> workingDays, String specialtyField) {
        super(id, name, workingDays);
        this.specialtyField = (specialtyField == null || specialtyField.trim().isEmpty())
                ? "General specialist" : specialtyField.trim();
    }

    public String getSpecialtyField() {
        return specialtyField;
    }

    @Override
    public String getSpecialty() {
        return "Specialist (" + specialtyField + ")";
    }

    @Override
    public String describeRole() {
        return "Provides specialist diagnosis and treatment as a " + specialtyField + ".";
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialty: " + getSpecialty();
    }
}
