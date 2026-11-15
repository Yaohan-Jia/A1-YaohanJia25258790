/**
 * One appointment: a patient sees a chosen professional at a time slot today.
 * The professional is stored as the general HealthProfessional type, so any
 * professional type can be booked.
 */
public class Appointment implements Comparable<Appointment> {

    private Patient patient;
    private HealthProfessional professional;
    private String timeSlot;
    private int slotIndex;

    /**
     * Creates an appointment.
     *
     * @param patient      patient attending
     * @param professional professional to see
     * @param timeSlot     time label, e.g. "09:30"
     * @param slotIndex    position of the slot in the schedule, used for sorting
     * @throws IllegalArgumentException if the patient, professional or slot is missing
     */
    public Appointment(Patient patient, HealthProfessional professional, String timeSlot, int slotIndex) {
        if (patient == null) {
            throw new IllegalArgumentException("An appointment must have a patient.");
        }
        if (professional == null) {
            throw new IllegalArgumentException("An appointment must have a health professional.");
        }
        if (timeSlot == null || timeSlot.trim().isEmpty()) {
            throw new IllegalArgumentException("An appointment must have a time slot.");
        }
        this.patient = patient;
        this.professional = professional;
        this.timeSlot = timeSlot.trim();
        this.slotIndex = slotIndex;
    }

    /** Returns the patient attending. */
    public Patient getPatient() {
        return patient;
    }

    /** Returns the booked professional. */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /** Returns the time slot label, e.g. "09:30". */
    public String getTimeSlot() {
        return timeSlot;
    }

    /** Returns the slot's position in the daily schedule. */
    public int getSlotIndex() {
        return slotIndex;
    }

    /**
     * Orders appointments by slot index, earliest to latest.
     */
    @Override
    public int compareTo(Appointment other) {
        return Integer.compare(this.slotIndex, other.slotIndex);
    }

    /** Builds the readable details shown when the appointment is printed. */
    @Override
    public String toString() {
        return timeSlot + " | " + patient + " | " + professional;
    }
}
