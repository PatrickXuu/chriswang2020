public class PatientRecord {

  static final int GREEN = 2;
  static final int RED = 0;
  static final int YELLOW = 1;
  private int triage;
  private static int patientCounter;
  public int orderOfArrival = patientCounter;
  private int age;

  private char gender;
  final int CASE_NUMBER;

  private boolean hasBeenSeen = false;


  public PatientRecord(char gender, int age, int triage) {
    this.triage = triage;
    this.gender =  gender;
    this.age = age;
    this.CASE_NUMBER = generateCaseNumber(gender, age);
  }

  public static int generateCaseNumber(char gender, int age) {
    int firstDigit = 0;
    switch (gender) {
      case 'F':
        firstDigit = 1;
        break;
      case 'M':
        firstDigit = 2;
        break;
      case 'X':
        firstDigit = 3;
        break;
      default:
        firstDigit = 4;
    }
    int nextTwoDigitsOfAge = age % 100;
    int lastTwoDigitsOfCounter = ++patientCounter % 100;
    int generatedCaseNumber = firstDigit * 10000 + nextTwoDigitsOfAge * 100 + lastTwoDigitsOfCounter;
    return generatedCaseNumber;
  }

  public static void resetCounter(){
    patientCounter = 0;
  }
  public int getAge(){
    return this.age;
  }
  public char getGender(){
    return this.gender;
  }

  public int getTriage(){
    return this.triage;
  }

  public int getArrivalOrder(){
    return this.orderOfArrival;
  }

  public boolean hasBeenSeen(){
    return this.hasBeenSeen;
  }

  public void seePatient(){
    this.hasBeenSeen = true;
  }

  public String toString(){
    String triageColor;
    switch (triage) {
      case 0:
        triageColor = "YELLOW";
        break;
      case 1:
        triageColor = "RED";
        break;
      case 2:
        triageColor = "GREEN";
        break;
      default:
        triageColor = "UNKNOWN";
        break;
    }
    String output = String.valueOf(CASE_NUMBER);
    output = output+": "+ this.age + this.gender + "(" + triageColor + ")";

    return output;
  }

}
