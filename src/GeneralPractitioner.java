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
     * @param clinicRoom room the GP consults in
     */
    public GeneralPractitioner(String id, String name, List<String> workingDays, String clinicRoom) {
        super(id, name, workingDays);
        this.clinicRoom = (clinicRoom == null || clinicRoom.trim().isEmpty())
                ? "Unassigned" : clinicRoom.trim();
    }

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
