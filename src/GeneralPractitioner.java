import java.util.List;

/**
 * A general practitioner (GP), the family doctor who sees patients for common
 * health concerns and may issue prescriptions.
 *
 * <p>Inherits all common details from {@link HealthProfessional} and adds the
 * GP's clinic room number.</p>
 */
public class GeneralPractitioner extends HealthProfessional {

    /** The clinic room where this GP consults, e.g. "Room 3". */
    private String clinicRoom;

    /**
     * Constructs a general practitioner.
     *
     * @param id          the GP's digit-only ID
     * @param name        the GP's full name
     * @param workingDays the weekdays on which the GP works
     * @param clinicRoom  the clinic room number
     */
    public GeneralPractitioner(String id, String name, List<String> workingDays, String clinicRoom) {
        super(id, name, workingDays);
        this.clinicRoom = (clinicRoom == null || clinicRoom.trim().isEmpty())
                ? "Unassigned" : clinicRoom.trim();
    }

    /**
     * Returns the clinic room.
     *
     * @return the clinic room number
     */
    public String getClinicRoom() {
        return clinicRoom;
    }

    @Override
    public String getSpecialty() {
        return "General Practitioner";
    }

    @Override
    public String describeRole() {
        return "Sees patients for everyday health concerns and may prescribe medication.";
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialty: " + getSpecialty()
                + ", Clinic room: " + clinicRoom;
    }
}
