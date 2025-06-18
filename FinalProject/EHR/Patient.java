package FinalProject.EHR;

public class Patient {
    private int id;
    private String name;
    private int age;          
    private String dob;
    private String gender;
    private long contact;
    private String address;
    private long emergencyContact;
    private String allergies;
    private String currentMeds;
    private String medicalHistory;
    private String diagnosis;
    private String treatmentPlan;
    private int doctorId;

    public Patient(int id, String name, int age, String dob, String gender,long contact, 
                  String address, long emergencyContact, String allergies, 
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
    public long getContact() { return contact; }
    public String getAddress() { return address; }
    public long getEmergencyContact() { return emergencyContact; }
    public String getAllergies() { return allergies; }
    public String getCurrentMeds() { return currentMeds; }
    public String getMedicalHistory() { return medicalHistory; }
    public String getDiagnosis() { return diagnosis; }
    public String getTreatmentPlan() { return treatmentPlan; }
    public int getDoctorId() { return doctorId; }

    @Override
    public String toString() {
    return ConsoleColors.PURPLE + "ID: " + ConsoleColors.RESET + id + "\n" +
           ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET + name + "\n" +
           ConsoleColors.CYAN + "Age: " + ConsoleColors.RESET + age + "\n" +
           ConsoleColors.CYAN + "DOB: " + ConsoleColors.RESET + dob + "\n" +
           ConsoleColors.CYAN + "Gender: " + ConsoleColors.RESET + gender + "\n" +
           ConsoleColors.CYAN + "Contact: " + ConsoleColors.RESET + contact + "\n" +
           ConsoleColors.CYAN + "Address: " + ConsoleColors.RESET + address + "\n" +
           ConsoleColors.CYAN + "Emergency Contact: " + ConsoleColors.RESET + emergencyContact + "\n" +
           ConsoleColors.CYAN + "Allergies: " + ConsoleColors.RESET + allergies + "\n" +
           ConsoleColors.CYAN + "Current Meds: " + ConsoleColors.RESET + currentMeds + "\n" +
           ConsoleColors.CYAN + "Medical History: " + ConsoleColors.RESET + medicalHistory + "\n" +
           ConsoleColors.CYAN + "Diagnosis: " + ConsoleColors.RESET + diagnosis + "\n" +
           ConsoleColors.CYAN + "Treatment: " + ConsoleColors.RESET + treatmentPlan + "\n" +
           ConsoleColors.CYAN + "Doctor Id: " + ConsoleColors.RESET + doctorId;
}
}