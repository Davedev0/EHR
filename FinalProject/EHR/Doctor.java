package FinalProject.EHR;

public class Doctor {
    private int id;
    private String name;
    private String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }

    @Override
    public String toString() {
        return ConsoleColors.PURPLE + "ID: " + id + ConsoleColors.RESET + " | " +
               ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET + name;
       }
  }