import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic health professional working at the health service.
 *
 * <p>This abstract base class captures the information and behaviour that are
 * common to every kind of health professional, regardless of their specialty:
 * an ID made of digits only, a name, and the fixed set of weekdays on which
 * they work. More specific professional types extend this class and add their
 * own fields and responsibilities.</p>
 *
 * <p>The class is abstract because a "plain" health professional with no
 * specialty has no meaning in this system; concrete subclasses must provide a
 * specialty label and a description of their role.</p>
 *
 * <p>Encapsulation: all fields are private and are validated in the
 * constructor. Callers can read the information through getters, but cannot
 * put an object into an invalid state. The working-days list is defensively
 * copied on both entry and exit so the internal collection cannot be altered
 * from outside.</p>
 */
public abstract class HealthProfessional {

    /** The professional's identifier; digits only. */
    private String id;

    /** The professional's full name. */
    private String name;

    /** The fixed weekdays on which this professional works, e.g. "Monday". */
    private List<String> workingDays;

    /**
     * Constructs a health professional with the common details.
     *
     * @param id          the professional's identifier, digits only
     * @param name        the professional's full name
     * @param workingDays the weekdays on which the professional works
     * @throws IllegalArgumentException if the id is blank or contains any
     *         non-digit character, or if the name is blank
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

        // Defensive copy: store our own copy of the supplied list so later
        // changes to the caller's list cannot change this object's state.
        this.workingDays = new ArrayList<String>();
        if (workingDays != null) {
            for (String day : workingDays) {
                if (day != null && !day.trim().isEmpty()) {
                    this.workingDays.add(day.trim());
                }
            }
        }
    }

    /**
     * Returns the professional's ID.
     *
     * @return the digit-only ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the professional's name.
     *
     * @return the full name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the professional's working days.
     *
     * <p>A new list is returned so that modifying the result cannot affect
     * the professional's internal collection.</p>
     *
     * @return an independent copy of the working-day list
     */
    public List<String> getWorkingDays() {
        return new ArrayList<String>(workingDays);
    }

    /**
     * Returns a short label describing this professional's specialty.
     *
     * @return the specialty label
     */
    public abstract String getSpecialty();

    /**
     * Describes the main responsibility or action associated with this role.
     *
     * @return a description of the role
     */
    public abstract String describeRole();

    /**
     * Two health professionals are considered the same person when their IDs
     * match.
     *
     * @param o the object to compare against
     * @return {@code true} if the other object is a professional with the
     *         same ID
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

    /**
     * Returns a hash code consistent with {@link #equals(Object)}, based on
     * the ID.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Returns a readable summary of the common details.
     *
     * @return a string containing ID, name and working days
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Working days: " + workingDays;
    }
}
