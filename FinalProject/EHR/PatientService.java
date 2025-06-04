package FinalProject.EHR;

import java.util.ArrayList;
import java.util.List;

public class PatientService {
    private List<Patient> patients = new ArrayList<>();
    private int nextId = 1000;
    private DoctorService doctorService;

    public PatientService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public void addPatient(Patient patient) {
        try {
            patients.add(patient);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error adding patient: " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }

    public Patient getPatientById(int id) {
        try {
            // Replaced lambda with traditional loop
            for (Patient patient : patients) {
                if (patient.getId() == id) {
                    return patient;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error finding patient: " + e.getMessage() + ConsoleColors.RESET);
            return null;
        }
    }

    public int getNextId() {
        return nextId++;
    }

    public void updatePatient(Patient updatedPatient) {
        try {
            for (int i = 0; i < patients.size(); i++) {
                if (patients.get(i).getId() == updatedPatient.getId()) {
                    patients.set(i, updatedPatient);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error updating patient: " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    public void deletePatient(int id) {
        try {
            // Replaced lambda with traditional loop
            List<Patient> toRemove = new ArrayList<>();
            for (Patient patient : patients) {
                if (patient.getId() == id) {
                    toRemove.add(patient);
                }
            }
            patients.removeAll(toRemove);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error deleting patient: " + e.getMessage() + ConsoleColors.RESET);
        }
    }
}