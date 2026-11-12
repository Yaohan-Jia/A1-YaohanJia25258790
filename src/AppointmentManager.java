import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the collection of appointments for the current day.
 *
 * <p>The manager supports adding appointments (with a double-booking check),
 * filtering by professional ID or patient phone number, sorting by time, and
 * cancelling appointments either by professional ID and slot or by patient
 * phone number. Every operation returns a clear success or warning message.</p>
 *
 * <p>The fixed daily schedule is held as a constant array: 08:00 to 16:00 in
 * 30-minute intervals. Changing this constant later only affects the schedule
 * itself; the rest of the booking logic works with it unchanged.</p>
 */
public class AppointmentManager {

    /** Fixed daily schedule: 08:00-16:00 in 30-minute intervals. */
    private static final String[] TIME_SLOTS = {
            "08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
            "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
            "14:00", "14:30", "15:00", "15:30", "16:00"
    };

    /** All appointments currently booked for the day. */
    private List<Appointment> appointments;

    /**
     * Constructs an empty appointment manager.
     */
    public AppointmentManager() {
        appointments = new ArrayList<Appointment>();
    }

    /**
     * Returns a copy of the fixed daily schedule.
     *
     * @return an independent copy of the time-slot array
     */
    public static String[] getTimeSlots() {
        String[] copy = new String[TIME_SLOTS.length];
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            copy[i] = TIME_SLOTS[i];
        }
        return copy;
    }

    /**
     * Finds the schedule index of a time label, for validation and ordering.
     *
     * @param timeSlot the time label to look up
     * @return the slot index, or -1 if the label is not in the schedule
     */
    private int indexOfSlot(String timeSlot) {
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            if (TIME_SLOTS[i].equals(timeSlot)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds an appointment.
     *
     * <p>The booking is rejected if any required detail is missing, if the
     * time slot is not part of the daily schedule, or if the same professional
     * is already booked into that slot. Professionals are matched by their ID
     * through {@link HealthProfessional#equals(Object)}.</p>
     *
     * @param appointment the appointment to add
     * @return a success message, or a warning explaining why it was rejected
     */
    public String addAppointment(Appointment appointment) {
        if (appointment == null) {
            return "WARNING: Appointment could not be added because no appointment was provided.";
        }
        if (appointment.getPatient() == null || appointment.getProfessional() == null
                || appointment.getTimeSlot() == null || appointment.getTimeSlot().trim().isEmpty()) {
            return "WARNING: Appointment could not be added because required details are missing.";
        }
        if (indexOfSlot(appointment.getTimeSlot()) == -1) {
            return "WARNING: Appointment could not be added because " + appointment.getTimeSlot()
                    + " is not a valid time slot in the daily schedule.";
        }

        for (Appointment existing : appointments) {
            if (existing.getProfessional().equals(appointment.getProfessional())
                    && existing.getTimeSlot().equals(appointment.getTimeSlot())) {
                return "WARNING: " + appointment.getProfessional().getName()
                        + " is already booked at " + appointment.getTimeSlot()
                        + "; this double-booking was rejected.";
            }
        }

        appointments.add(appointment);
        return "SUCCESS: Appointment booked for " + appointment.getPatient().getName()
                + " with " + appointment.getProfessional().getName()
                + " at " + appointment.getTimeSlot() + ".";
    }

    /**
     * Returns the appointments for the professional with the given ID.
     *
     * @param professionalId the ID to match
     * @return a list of matching appointments (empty if none match)
     */
    public List<Appointment> filterByProfessionalId(String professionalId) {
        List<Appointment> result = new ArrayList<Appointment>();
        if (professionalId == null) {
            return result;
        }
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)) {
                result.add(appointment);
            }
        }
        return result;
    }

    /**
     * Returns the appointments for the patient with the given mobile number.
     *
     * @param mobile the phone number to match
     * @return a list of matching appointments (empty if none match)
     */
    public List<Appointment> filterByPatientPhone(String mobile) {
        List<Appointment> result = new ArrayList<Appointment>();
        if (mobile == null) {
            return result;
        }
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobile().equals(mobile)) {
                result.add(appointment);
            }
        }
        return result;
    }

    /**
     * Returns all appointments in their current order.
     *
     * @return a list of all appointments
     */
    public List<Appointment> getAllAppointments() {
        return new ArrayList<Appointment>(appointments);
    }

    /**
     * Returns all appointments sorted from earliest to latest by time.
     *
     * <p>The internal collection is left unchanged; a sorted copy is returned.</p>
     *
     * @return a time-ordered list of all appointments
     */
    public List<Appointment> getSortedByTime() {
        List<Appointment> sorted = new ArrayList<Appointment>(appointments);
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * Cancels the appointment for the given professional at the given slot.
     *
     * @param professionalId the professional's ID
     * @param timeSlot       the time slot to cancel
     * @return a success message, or a warning if no matching appointment exists
     */
    public String cancelByProfessionalAndSlot(String professionalId, String timeSlot) {
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)
                    && appointment.getTimeSlot().equals(timeSlot)) {
                appointments.remove(appointment);
                return "SUCCESS: Appointment cancelled for " + appointment.getProfessional().getName()
                        + " at " + timeSlot + ".";
            }
        }
        return "WARNING: No appointment found for professional ID " + professionalId
                + " at " + timeSlot + "; nothing was cancelled.";
    }

    /**
     * Cancels all appointments for the patient with the given mobile number.
     *
     * @param mobile the patient's phone number
     * @return a success message, or a warning if no matching appointment exists
     */
    public String cancelByPatientPhone(String mobile) {
        List<Appointment> toRemove = new ArrayList<Appointment>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobile().equals(mobile)) {
                toRemove.add(appointment);
            }
        }

        if (toRemove.isEmpty()) {
            return "WARNING: No appointment found for patient phone " + mobile
                    + "; nothing was cancelled.";
        }

        appointments.removeAll(toRemove);
        return "SUCCESS: " + toRemove.size() + " appointment(s) cancelled for patient phone "
                + mobile + ".";
    }
}
