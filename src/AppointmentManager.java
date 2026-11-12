import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Keeps the day's appointments and handles adding, filtering, sorting and
 * cancelling them. Each operation returns a success or warning message.
 */
public class AppointmentManager {

    // Clinic schedule: 08:00 to 16:00, one slot every 30 minutes.
    private static final String[] TIME_SLOTS = {
            "08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
            "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
            "14:00", "14:30", "15:00", "15:30", "16:00"
    };

    private List<Appointment> appointments;

    public AppointmentManager() {
        appointments = new ArrayList<Appointment>();
    }

    /** Returns a copy of the fixed daily schedule. */
    public static String[] getTimeSlots() {
        String[] copy = new String[TIME_SLOTS.length];
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            copy[i] = TIME_SLOTS[i];
        }
        return copy;
    }

    /** Finds the index of a time label in the schedule, or -1 if it isn't there. */
    private int indexOfSlot(String timeSlot) {
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            if (TIME_SLOTS[i].equals(timeSlot)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds an appointment. Rejects it if details are missing, the slot is not
     * in the schedule, or the same professional is already booked then.
     *
     * @return a success message, or a warning saying why it was rejected
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

        // Reject double-booking: same professional and same slot already exists.
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

    /** Returns appointments for the professional with the given ID. */
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

    /** Returns appointments for the patient with the given mobile number. */
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

    public List<Appointment> getAllAppointments() {
        return new ArrayList<Appointment>(appointments);
    }

    /** Returns a copy of all appointments sorted earliest to latest. */
    public List<Appointment> getSortedByTime() {
        List<Appointment> sorted = new ArrayList<Appointment>(appointments);
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * Cancels the appointment for the given professional and slot.
     *
     * @return a success message, or a warning if nothing matched
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
     * @return a success message, or a warning if nothing matched
     */
    public String cancelByPatientPhone(String mobile) {
        // Collect matches first, then remove them, to avoid changing the list
        // while looping over it.
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
