import java.util.List;

/**
 * A specialist doctor who practises a particular medical field, such as a
 * paediatrician, radiologist or cardiologist.
 *
 * <p>Inherits all common details from {@link HealthProfessional} and adds the
 * specific field of specialisation.</p>
 */
public class Specialist extends HealthProfessional {

    /** The specific medical field, e.g. "Paediatrician", "Cardiologist". */
    private String specialtyField;

    /**
     * Constructs a specialist.
     *
     * @param id            the specialist's digit-only ID
     * @param name          the specialist's full name
     * @param workingDays   the weekdays on which the specialist works
     * @param specialtyField the medical field of specialisation
     */
    public Specialist(String id, String name, List<String> workingDays, String specialtyField) {
        super(id, name, workingDays);
        this.specialtyField = (specialtyField == null || specialtyField.trim().isEmpty())
                ? "General specialist" : specialtyField.trim();
    }

    /**
     * Returns the specialisation field.
     *
     * @return the medical field
     */
    public String getSpecialtyField() {
        return specialtyField;
    }

    @Override
    public String getSpecialty() {
        return "Specialist (" + specialtyField + ")";
    }

    @Override
    public String describeRole() {
        return "Provides expert diagnosis and treatment within the " + specialtyField + " field.";
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialty: " + getSpecialty();
    }
}
