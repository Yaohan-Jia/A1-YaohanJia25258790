import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Runs the demonstration for the appointment system. There is no UI: main
 * creates objects, calls the system's methods and prints the results.
 */
public class AssignmentOne {

    public static void main(String[] args) {
        demonstrateProfessionals();
        demonstrateAppointments();
    }

    /** Part 3: create professionals of each type and print their details. */
    private static void demonstrateProfessionals() {
        System.out.println("============================================================");
        System.out.println("PART 3: HEALTH PROFESSIONALS");
        System.out.println("============================================================");

        // Hold every subtype in one list of the base type, so one loop can
        // print them all and each object uses its own toString.
        List<HealthProfessional> professionals = new ArrayList<HealthProfessional>(Arrays.asList(
                new GeneralPractitioner("101", "Dr Alice Bennett",
                        Arrays.asList("Monday", "Wednesday", "Friday"), "Room 3"),
                new GeneralPractitioner("102", "Dr Brian Carter",
                        Arrays.asList("Tuesday", "Thursday"), "Room 5"),
                new GeneralPractitioner("103", "Dr Chloe Nguyen",
                        Arrays.asList("Monday", "Tuesday", "Thursday", "Friday"), "Room 1"),
                new Specialist("201", "Dr David Okafor",
                        Arrays.asList("Wednesday", "Friday"), "Cardiologist"),
                new Specialist("202", "Dr Emma Laurent",
                        Arrays.asList("Monday", "Thursday"), "Paediatrician"),
                new Nurse("301", "Nurse Fiona Zhang",
                        Arrays.asList("Monday", "Wednesday", "Friday"), "Ward B"),
                new Nurse("302", "Nurse George Miller",
                        Arrays.asList("Tuesday", "Thursday", "Saturday"), "Ward A"),
                new Dietitian("401", "Diana Walsh",
                        Arrays.asList("Tuesday", "Friday"), "Accredited Practising Dietitian")
        ));

        for (HealthProfessional professional : professionals) {
            System.out.println("- " + professional);
            System.out.println("    Role: " + professional.describeRole());
        }

        System.out.println();
    }

    /** Part 6: add appointments, show and filter them, cancel some, and show the error cases. */
    private static void demonstrateAppointments() {
        System.out.println("============================================================");
        System.out.println("PART 6: APPOINTMENT SYSTEM");
        System.out.println("============================================================");

        AppointmentManager manager = new AppointmentManager();

        // Professionals used by the booking examples.
        HealthProfessional gp1 = new GeneralPractitioner("101", "Dr Alice Bennett",
                Arrays.asList("Monday", "Wednesday", "Friday"), "Room 3");
        HealthProfessional specialist1 = new Specialist("201", "Dr David Okafor",
                Arrays.asList("Wednesday", "Friday"), "Cardiologist");
        HealthProfessional nurse1 = new Nurse("301", "Nurse Fiona Zhang",
                Arrays.asList("Monday", "Wednesday", "Friday"), "Ward B");
        HealthProfessional dietitian1 = new Dietitian("401", "Diana Walsh",
                Arrays.asList("Tuesday", "Friday"), "Accredited Practising Dietitian");

        // Add a normal set of appointments.
        System.out.println("--- Adding appointments ---");
        System.out.println(manager.addAppointment(
                new Appointment(new Patient("Jack Brown", "0412 300 001"), gp1, "09:00", 2)));
        System.out.println(manager.addAppointment(
                new Appointment(new Patient("Mary Green", "0412 300 002"), specialist1, "10:30", 5)));
        System.out.println(manager.addAppointment(
                new Appointment(new Patient("Peter White", "0412 300 003"), nurse1, "08:30", 1)));
        System.out.println(manager.addAppointment(
                new Appointment(new Patient("Susan Black", "0412 300 004"), dietitian1, "13:30", 11)));
        System.out.println(manager.addAppointment(
                new Appointment(new Patient("Jack Brown", "0412 300 001"), gp1, "14:00", 12)));
        System.out.println();

        // Show all appointments in the order they were added.
        System.out.println("--- All appointments (current order) ---");
        printAppointments(manager.getAllAppointments());

        // Show them sorted by time.
        System.out.println("--- All appointments (sorted by time) ---");
        printAppointments(manager.getSortedByTime());

        // Filter by professional ID.
        System.out.println("--- Filter: professional ID 101 ---");
        printAppointments(manager.filterByProfessionalId("101"));

        // Filter by patient phone number.
        System.out.println("--- Filter: patient phone 0412 300 002 ---");
        printAppointments(manager.filterByPatientPhone("0412 300 002"));

        // Error case: book the same professional in the same slot again.
        System.out.println("--- Error case: double-booking Dr Alice Bennett at 09:00 ---");
        System.out.println(manager.addAppointment(
                new Appointment(new Patient("Tom Blue", "0412 300 005"), gp1, "09:00", 2)));
        System.out.println();

        // Cancel by professional ID and slot.
        System.out.println("--- Cancelling: professional ID 301 at 08:30 ---");
        System.out.println(manager.cancelByProfessionalAndSlot("301", "08:30"));
        System.out.println();

        // Cancel by patient phone number.
        System.out.println("--- Cancelling: patient phone 0412 300 004 ---");
        System.out.println(manager.cancelByPatientPhone("0412 300 004"));
        System.out.println();

        // Error case: cancel an appointment that doesn't exist.
        System.out.println("--- Error case: cancel professional ID 202 at 11:00 (not booked) ---");
        System.out.println(manager.cancelByProfessionalAndSlot("202", "11:00"));
        System.out.println();

        // Show what's left after the cancellations.
        System.out.println("--- Remaining appointments after cancellations (sorted by time) ---");
        printAppointments(manager.getSortedByTime());
    }

    /** Prints a list of appointments, or a message if it is empty. */
    private static void printAppointments(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("  (no appointments)");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println("  " + appointment);
            }
        }
        System.out.println();
    }
}
