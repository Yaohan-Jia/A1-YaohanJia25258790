import java.util.List;

/**
 * A general practitioner (GP), the family doctor who sees common health
 * problems and can write prescriptions.
 */
public class GeneralPractitioner extends HealthProfessional {

    private String clinicRoom;

    /**
     * Creates a GP.
     *
     * @param id          digits-only professional ID
     * @param name        professional's full name
     * @param workingDays weekdays the GP works
     * @param clinicRoom  room the GP consults in
     */
    public GeneralPractitioner(String id, String name, List<String> workingDays, String clinicRoom) {
        super(id, name, workingDays);
        this.clinicRoom = (clinicRoom == null || clinicRoom.trim().isEmpty())
                ? "Unassigned" : clinicRoom.trim();
    }

    /** Returns the room the GP consults in. */
    public String getClinicRoom() {
        return clinicRoom;
    }

    /** Returns the specialty label for this professional type. */
    @Override
    public String getSpecialty() {
        return "General Practitioner";
    }

    /** Describes what this role does. */
    @Override
    public String describeRole() {
        return "Sees patients for everyday health concerns and may prescribe medication.";
    }

    /** Combines the shared details with this type's own field. */
    @Override
    public String toString() {
        return super.toString() + ", Specialty: " + getSpecialty()
                + ", Clinic room: " + clinicRoom;
    }
}
