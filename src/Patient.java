/**
 * A patient attending the health service, identified by name and mobile phone
 * number.
 *
 * <p>The details are kept private and are validated in the constructor, so a
 * patient object can never be created without the basic contact information
 * the appointment system needs.</p>
 */
public class Patient {

    /** The patient's full name. */
    private String name;

    /** The patient's mobile phone number used for contact. */
    private String mobile;

    /**
     * Constructs a patient.
     *
     * @param name   the patient's full name
     * @param mobile the patient's mobile phone number
     * @throws IllegalArgumentException if either value is blank
     */
    public Patient(String name, String mobile) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name must not be blank.");
        }
        if (mobile == null || mobile.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient mobile number must not be blank.");
        }
        this.name = name.trim();
        this.mobile = mobile.trim();
    }

    /**
     * Returns the patient's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the patient's mobile number.
     *
     * @return the mobile number
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Returns a readable summary of the patient.
     *
     * @return a string containing name and mobile number
     */
    @Override
    public String toString() {
        return "Patient: " + name + " (mobile: " + mobile + ")";
    }
}
