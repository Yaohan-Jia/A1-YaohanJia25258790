/**
 * A single appointment linking a patient to a chosen health professional at a
 * specific time slot on the current day.
 *
 * <p>The professional is held through the general {@link HealthProfessional}
 * type, so an appointment can be made with any professional type defined in
 * the system. The time is stored both as a displayable label ({@code timeSlot},
 * e.g. "09:30") and as a numeric {@code slotIndex}, which makes time ordering
 * correct and independent of string comparison.</p>
 *
 * <p>Appointments implement {@link Comparable} so a whole collection can be
 * sorted from earliest to latest.</p>
 */
public class Appointment implements Comparable<Appointment> {

    /** The patient who booked the appointment. */
    private Patient patient;

    /** The health professional the patient wants to see. */
    private HealthProfessional professional;

    /** The displayable time label, e.g. "09:30". */
    private String timeSlot;

    /** The position of the slot in the daily schedule, used for ordering. */
    private int slotIndex;

    /**
     * Constructs an appointment.
     *
     * @param patient      the patient attending
     * @param professional the chosen health professional
     * @param timeSlot     the displayable time label, e.g. "09:30"
     * @param slotIndex    the schedule position of the slot, for sorting
     * @throws IllegalArgumentException if the patient, professional or time
     *         slot is missing
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

    /**
     * Returns the patient.
     *
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the chosen professional.
     *
     * @return the professional
     */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /**
     * Returns the time slot label.
     *
     * @return the time label, e.g. "09:30"
     */
    public String getTimeSlot() {
        return timeSlot;
    }

    /**
     * Returns the schedule position of the slot.
     *
     * @return the slot index
     */
    public int getSlotIndex() {
        return slotIndex;
    }

    /**
     * Orders appointments by their slot index, earliest to latest. Appointments
     * at the same time may appear in any relative order.
     *
     * @param other the appointment to compare against
     * @return a negative value, zero or a positive value as this appointment
     *         is earlier than, equal to, or later than the other
     */
    @Override
    public int compareTo(Appointment other) {
        return Integer.compare(this.slotIndex, other.slotIndex);
    }

    /**
     * Returns a readable summary of the appointment, including the patient,
     * the professional's details and the time slot.
     *
     * @return a string describing the appointment
     */
    @Override
    public String toString() {
        return timeSlot + " | " + patient + " | " + professional;
    }
}
