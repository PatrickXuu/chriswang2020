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
   * Adds the provided PatientRecord at the provided position in the oversize
   * patientsList array. This method must maintain the ordering of the patientsList as before, and rather than returning the new size,
   * maintains the size field in this ExceptionalCareAdmissions object appropriately.
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
   * As before, this helper method will find the correct index to
   * insert the new patient record into patientsList,maintaining triage priority order.
   * This method's logic is the same as that from P01,
   * but uses objects and exceptions rather than primitive values.
   * @param rec
   * @return
   * @throws IllegalStateException
   */
  public int getAdmissionIndex(PatientRecord rec) throws IllegalStateException {
    int len_of_room = patientsList.length;
    int final_record = 0;
    boolean hasSeenPatients = false;
    for (int i = 0; i < patientsList.length; i++) {
      if (patientsList[i] == null) {
        // found an empty spot, so we're done searching
        break;
      } else if (patientsList[i].hasBeenSeen()) {
        hasSeenPatients = true;
      } else if (rec.CASE_NUMBER==patientsList[i].CASE_NUMBER) {
        break;
      } ++final_record;
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

  /**
   * As before, this helper method will find the correct index to
   * insert the new patient record into patientsList, maintaining
   * triage priority order. This method's logic is the same as that from P01,
   * but uses objects and exceptions rather than primitive values.
   * @param file
   */

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
    writer.println("Total number seen: " + getNumberSeenPatients());

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

  /**
   * For testing purposes: this method creates and returns a string representation of the
   * patientsList, as the in-order string representation of each patient in the list on a
   * separate line. If patientsList is empty, returns an empty string.
   * *
   @return a string representation of the contents of patientsList
   */
  public String toString() {
    String returnValue = "";
    for (PatientRecord r : patientsList) {
      returnValue += (r != null) ? r.toString()+"\n" : "";
    }
    return returnValue.trim();
  }



}
