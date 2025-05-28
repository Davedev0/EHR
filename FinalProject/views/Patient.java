package FinalProject.views;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    // encapsulation 
    private int age; 
    private String medicalId;           
    private String fullName;           
    private String dateOfBirth;
    private String gender;              
    private String contactNumber;       
    private String homeAddress;         
    private String emergencyContactName; 
    private String emergencyContactNumber; 
    private String emergencyContactRelationship; 
    private String allergies;           
    private String currentMedications;  
    private String medicalHistory;      
    private String familyMedicalHistory; 
    private String diagnosis;           
    private String prescribedMedications; 
    private String doctorsName;         
    private final List<String> history;  

    // Protected constructor - hindi pwedeng direktang gumawa ng Patient object
    // Dapat gamitin ang createNewPatient() factory method
    protected Patient() {
        this.history = new ArrayList<>();  // Ininitialize ang history list
    }

    // Factory method para gumawa ng bagong pasyente
    public static Patient createNewPatient() {
        return new Patient();  // Bumabalik ng bagong Patient instance
    }

    // MGA GETTERS (PUBLIC) - para makuha ang mga values ng fields

    public int getAge() { 
        return age; 
    }
    protected void setAge(int age) { 
        this.age = age; 
    }

    public String getMedicalId() { 
        return medicalId;  // Ibinabalik ang medical ID
    }

    // MGA SETTERS (PROTECTED) - pwedeng gamitin lang sa loob ng package o subclass
    protected void setMedicalId(String medicalId) { 
        this.medicalId = medicalId;  // Sinaset ang medical ID
    }

    // Iba pang getters at setters na pareho ang structure
    public String getFullName() { 
        return fullName; 
    }
    protected void setFullName(String fullName) {
        this.fullName = fullName; 
    }

    public String getDateOfBirth() { 
        return dateOfBirth; 
    }
    protected void setDateOfBirth(String dateOfBirth) { 
        this.dateOfBirth = dateOfBirth; 
    }
    
    public String getGender() { 
        return gender; 
    }
    protected void setGender(String gender) { 
        this.gender = gender; 
    }

    public String getContactNumber() { 
        return contactNumber; 
    }
    protected void setContactNumber(String contactNumber) { 
        this.contactNumber = contactNumber; 
    }

    public String getHomeAddress() { 
        return homeAddress; 
    }
    protected void setHomeAddress(String homeAddress) { 
        this.homeAddress = homeAddress; 
    }

    public String getEmergencyContactName() { 
        return emergencyContactName; 
    }
    protected void setEmergencyContactName(String emergencyContactName) { 
        this.emergencyContactName = emergencyContactName; 
    }

    public String getEmergencyContactNumber() { 
        return emergencyContactNumber; 
    }
    protected void setEmergencyContactNumber(String emergencyContactNumber) { 
        this.emergencyContactNumber = emergencyContactNumber; 
    }

    public String getEmergencyContactRelationship() { 
        return emergencyContactRelationship; 
    }
    protected void setEmergencyContactRelationship(String emergencyContactRelationship) { 
        this.emergencyContactRelationship = emergencyContactRelationship; 
    }

    public String getAllergies() { 
        return allergies; 
        
    }
    protected void setAllergies(String allergies) { 
        this.allergies = allergies; 
    }

    public String getCurrentMedications() { 
        return currentMedications; 
    }
    protected void setCurrentMedications(String currentMedications) { 
        this.currentMedications = currentMedications; 
    }

    public String getMedicalHistory() { 
        return medicalHistory; 
    }
    protected void setMedicalHistory(String medicalHistory) { 
        this.medicalHistory = medicalHistory; 
    }

    public String getFamilyMedicalHistory() { 
        return familyMedicalHistory; 
    }
    protected void setFamilyMedicalHistory(String familyMedicalHistory) { 
        this.familyMedicalHistory = familyMedicalHistory; 
    }

    public String getDiagnosis() { 
        return diagnosis; 
    }
    protected void setDiagnosis(String diagnosis) { 
        this.diagnosis = diagnosis; 
    }

    public String getPrescribedMedications() { 
        return prescribedMedications; 
    }
    protected void setPrescribedMedications(String prescribedMedications) { 
        this.prescribedMedications = prescribedMedications; 
    }

    public String getDoctorsName() { 
        return doctorsName;
    }
    protected void setDoctorsName(String doctorsName) { 
        this.doctorsName = doctorsName;
    }

    // Para makuha ang history - bumabalik ng COPY ng list para hindi ma-modify ang original
    public List<String> getHistory() { 
        return new ArrayList<>(history);  // Defensive copying
    }

    // Para magdagdag ng entry sa history kasama ang timestamp
    protected void addHistory(String entry) { 
        this.history.add(LocalDateTime.now() + " - " + entry); 
    }

    // Para ipakita ang lahat ng details ng pasyente bilang String
    // Optional parin kung ganito yung layout na gagawin
    @Override
    public String toString() {
        return "Medical ID: " + medicalId + "\n" +
               "Full Name: " + fullName + "\n" +
               "Date of Birth: " + dateOfBirth + "\n" +
               "Age: " + age + "\n" +
               "Gender: " + gender + "\n" +
               "Contact Number: " + contactNumber + "\n" +
               "Home Address: " + homeAddress + "\n" +
               "Emergency Contact: " + emergencyContactName + " (" + emergencyContactRelationship + ")\n" +
               "Emergency Contact Number: " + emergencyContactNumber + "\n" +
               "Allergies: " + allergies + "\n" +
               "Current Medications: " + currentMedications + "\n" +
               "Medical History: " + medicalHistory + "\n" +
               "Family Medical History: " + familyMedicalHistory + "\n" +
               "Diagnosis: " + diagnosis + "\n" +
               "Prescribed Medications/Treatment: " + prescribedMedications + "\n" +
               "Assigned Doctor: " + doctorsName + "\n";
    }
}