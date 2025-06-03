package FinalProject.EHR;

public class Patient {
    private int id;
    private String name;
    private int age;          
    private String dob;
    private String gender;
    private String contact;
    private String address;
    private String emergencyContact;
    private String allergies;
    private String currentMeds;
    private String medicalHistory;
    private String diagnosis;
    private String treatmentPlan;
    private int doctorId;

    public Patient(int id, String name, int age, String dob, String gender, String contact, 
                  String address, String emergencyContact, String allergies, 
                  String currentMeds, String medicalHistory, String diagnosis, 
                  String treatmentPlan, int doctorId) {
        this.id = id;
        this.name = name;
        this.age = age;      
        this.dob = dob;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.allergies = allergies;
        this.currentMeds = currentMeds;
        this.medicalHistory = medicalHistory;
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
        this.doctorId = doctorId;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getContact() { return contact; }
    public String getAddress() { return address; }
    public String getEmergencyContact() { return emergencyContact; }
    public String getAllergies() { return allergies; }
    public String getCurrentMeds() { return currentMeds; }
    public String getMedicalHistory() { return medicalHistory; }
    public String getDiagnosis() { return diagnosis; }
    public String getTreatmentPlan() { return treatmentPlan; }
    public int getDoctorId() { return doctorId; }

    @Override
    public String toString() {
        return ConsoleColors.CYAN + "ID: " + id + ConsoleColors.RESET + "\n" +
               "Name: " + name + "\n" +
               "Age: " + age +  
               "DOB: " + dob + " | Gender: " + gender + "\n" +
               "Contact: " + contact + "\n" +
               "Address: " + address + "\n" +
               "Emergency Contact: " + emergencyContact + "\n" +
               "Allergies: " + allergies + "\n" +
               "Current Meds: " + currentMeds + "\n" +
               "Medical History: " + medicalHistory + "\n" +
               "Diagnosis: " + diagnosis + "\n" +
               "Treatment: " + treatmentPlan + "\n" +
               "Doctor ID: " + doctorId;
    }
}