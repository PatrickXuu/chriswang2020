import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;

/**
 * ClassName: ExceptionalCareAdmissions Package PACKAGE_NAME Description Author zwang2654
 *
 * @Create 2/26/2023 1:10 AM
 * @Version 1.0
 */
public class ExceptionalCareAdmissions {
  public PatientRecord[] patientsList;
  public int size=0;

  public ExceptionalCareAdmissions(int capacity) throws IllegalArgumentException{
    if (capacity <= 0) {
      throw new IllegalArgumentException("it is not positive");
    }
    int cap = capacity;
    patientsList = new PatientRecord[cap];
  }

  /**
   *
   * check exception
   *
   * @param rec
   * @param index
   * @throws IllegalStateException
   * @throws IllegalArgumentException
   */
  public void addPatient(PatientRecord rec, int index) throws IllegalStateException, IllegalArgumentException {
    if (size >= patientsList.length) {
      for (int i = 0; i < patientsList.length; i++) {
        if (patientsList[i].hasBeenSeen()) {
          throw new IllegalStateException("cleanPatientsList()");
        }
      }
      throw new IllegalStateException("Cannot admit new patients");
    }

    if (index < 0 || index > size) {
      throw new IllegalArgumentException("Invalid index: " + index);
    }

    for (int i = size - 1; i >= index; i--) {
      patientsList[i + 1] = patientsList[i];
    }
    patientsList[index] = rec;
    size++;
  }

  /**
   *
   * @param rec
   * @param patientsList
   * @return
   * @throws IllegalStateException
   */
  public int getAdmissionIndex(PatientRecord rec, PatientRecord[] patientsList) throws IllegalStateException {
    int len_of_room = patientsList.length;
    int final_record = 0;
    boolean hasSeenPatients = false;
    for (int i = 0; i < patientsList.length; i++) {
      if (patientsList[i] == null) {
        // found an empty spot, so we're done searching
        break;
      } else if (patientsList[i].hasBeenSeen()) {
        hasSeenPatients = true;
      } else if (rec.getTriage() >= patientsList[i].getTriage()) {
        final_record += 1;
      }
    }
    if (this.size >= len_of_room) {
      if (hasSeenPatients) {
        throw new IllegalStateException("cleanPatientsList()");
      } else {
        throw new IllegalStateException("Cannot admit new patients");
      }
    }
    return final_record;
  }

  public void cleanPatientsList(File file) {
    // Create a PrintWriter to write to the file
    PrintWriter writer;
    try {
      writer = new PrintWriter(file);
    } catch (FileNotFoundException e) {
      // If the file cannot be written to, return from the method without modifying the patientsList
      return;
    }

    // Write the current summary of the patientsList to the file
    writer.println("Total number of patients: " + size);
    writer.println("Number of seen patients: " + getNumberSeenPatients());

    // Write the string representation of each SEEN patient to the file and remove them from the patientsList
    for (int i = size - 1; i >= 0; i--) {
      if (patientsList[i].hasBeenSeen()) {
        writer.println(patientsList[i].toString());
        // Remove the patient from the patientsList by shifting all subsequent elements to the left
        for (int j = i; j < size - 1; j++) {
          patientsList[j] = patientsList[j + 1];
        }
        size--;
      }
    }

    // Write "Total number seen: 0" to the file if there were no seen patients
    if (getNumberSeenPatients() == 0) {
      writer.println("Total number seen: 0");
    }

    // Flush the PrintWriter and close it to ensure all data is written to the file
    writer.flush();
    writer.close();
  }

  /**
   * Marks the patient with the given caseID as having been seen.
   * This method will require you to find the patient with the
   * given caseID within the patientsList before you can modify their status.
   * @param caseID
   * @throws IllegalStateException
   * @throws IllegalArgumentException
   */
  public void seePatient(int caseID)
      throws IllegalStateException, IllegalArgumentException{
    boolean patientFound = false;
    // if patientsList empty
    if (patientsList[0]==null){throw new IllegalStateException();}

    for (int i = size - 1; i >= 0; i--) {
      if (patientsList[i]==null){}
      else if (patientsList[i].CASE_NUMBER == caseID){
        patientsList[i].seePatient();
        patientFound = true;
        break;
      }
    }
    // if not found
    if (!patientFound){throw new IllegalArgumentException();}
  }

  public boolean isFull(){
    return patientsList.length ==size;
  }

  public int size(){
    return this.size;
  }

  public int getNumberSeenPatients(){
    int count = 0;
    for (int i = size - 1; i >= 0; i--) {
      if (patientsList[i].hasBeenSeen()){
        count += 1;
      }
    }
    return count;
  }



}
