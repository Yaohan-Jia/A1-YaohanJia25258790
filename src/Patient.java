/**
 * A patient, with name and mobile number for contact.
 */
public class Patient {

    private String name;
    private String mobile;

    /**
     * Creates a patient and checks the contact details.
     *
     * @param name   patient's full name
     * @param mobile patient's mobile number
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

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return "Patient: " + name + " (mobile: " + mobile + ")";
    }
}
