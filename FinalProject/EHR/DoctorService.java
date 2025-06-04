package FinalProject.EHR;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    public static List<Doctor> doctors = new ArrayList<>();
    public static int nextId = 1;

    public DoctorService() {
        initializeDoctors();
    }

    private void initializeDoctors() {
        addDoctor(new Doctor(nextId++, "Dr. Anggit", "Cardiology"));
        addDoctor(new Doctor(nextId++, "Dr. Nigga", "Neurology"));
        addDoctor(new Doctor(nextId++, "Dr. John Quidan", "Pediatrics"));
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }

    public Doctor getDoctorById(int id) {
        try {
            for (Doctor doctor : doctors) {
                if (doctor.getId() == id) {
                    return doctor;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error finding doctor: " + e.getMessage() + ConsoleColors.RESET);
            return null;
        }
    }
}