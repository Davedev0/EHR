package FinalProject.EHR;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    private List<Doctor> doctors = new ArrayList<>();
    private int nextId = 1;  

    public DoctorService() {
        initializeDoctors();
    }

    private void initializeDoctors() {
        addDoctor(new Doctor(nextId++, "John Smith", "Cardiology"));
        addDoctor(new Doctor(nextId++, "Emily Chen", "Neurology"));
        addDoctor(new Doctor(nextId++, "William Brown", "Pediatrics"));
        addDoctor(new Doctor(nextId++, "Robert Davis", "Dermatologist"));
        addDoctor(new Doctor(nextId++, "Jennifer Hall", "Physician"));
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        if (doctor.getId() >= nextId) {
            nextId = doctor.getId() + 1;
        }
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

    public int getNextId() {
        return nextId++;
    }
}