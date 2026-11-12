import java.util.ArrayList;
import java.util.List;

/**
 * A health professional at the clinic. Holds the details shared by every
 * professional type: ID, name and working days. Specific professional types
 * extend this class.
 */
public abstract class HealthProfessional {

    private String id;
    private String name;
    private List<String> workingDays;

    /**
     * Creates a health professional and checks the supplied details.
     *
     * @param id          digits-only professional ID
     * @param name        professional's full name
     * @param workingDays weekdays the professional works
     * @throws IllegalArgumentException if the ID is blank or not all digits,
     *                                  or the name is blank
     */
    protected HealthProfessional(String id, String name, List<String> workingDays) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Professional ID must not be blank.");
        }
        if (!id.trim().matches("\\d+")) {
            throw new IllegalArgumentException("Professional ID must contain digits only: " + id);
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Professional name must not be blank.");
        }

        this.id = id.trim();
        this.name = name.trim();

        // Keep our own copy so changes to the list passed in don't affect this object.
        this.workingDays = new ArrayList<String>();
        if (workingDays != null) {
            for (String day : workingDays) {
                if (day != null && !day.trim().isEmpty()) {
                    this.workingDays.add(day.trim());
                }
            }
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the working days, so the internal list can't be
     * changed from outside.
     */
    public List<String> getWorkingDays() {
        return new ArrayList<String>(workingDays);
    }

    /** Short label for this professional's specialty. */
    public abstract String getSpecialty();

    /** What this role does. */
    public abstract String describeRole();

    /**
     * Two professionals count as the same person when their IDs match.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional other = (HealthProfessional) o;
        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Working days: " + workingDays;
    }
}
