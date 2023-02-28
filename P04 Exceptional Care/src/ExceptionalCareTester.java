// File header goes here

// Javadoc style class header goes here
public class ExceptionalCareTester {
  
  /**
   * This test method is provided for you in its entirety, to give you a model for testing
   * an instantiable class. This method verifies the correctness of your PatientRecord class.
   * 
   * In this test, we create two PatientRecords with different information and use the accessor
   * methods to verify that both contain the correct information and have the correct String
   * representation.
   * 
   * @author hobbes
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testPatientRecord() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // (1) create two PatientRecords with different, valid input
    // no exceptions should be thrown, so let's be safe:
    PatientRecord test1 = null, test2 = null;
    try {
      test1 = new PatientRecord('M', 17, PatientRecord.YELLOW);
      test2 = new PatientRecord('X', 21, PatientRecord.GREEN);
    } catch (Exception e) { return false; }

    // (2) verify their data fields:
    {
      // CASE_NUMBER
      int expected1 = 21701;
      int expected2 = 32102;
      if (test1.CASE_NUMBER != expected1 || test2.CASE_NUMBER != expected2) return false;

    }
    {
      // triage
      int expected1 = PatientRecord.YELLOW;
      int expected2 = PatientRecord.GREEN;
      if (test1.getTriage() != expected1 || test2.getTriage() != expected2) return false;

    }
    {
      // gender
      char expected1 = 'M';
      char expected2 = 'X';
      if (test1.getGender() != expected1 || test2.getGender() != expected2) return false;
    }
    {
      // age
      int expected1 = 17;
      int expected2 = 21;
      if (test1.getAge() != expected1 || test2.getAge() != expected2) return false;

    }
    {
      // orderOfArrival
      int expected1 = 1;
      int expected2 = 2;
      if (test1.getArrivalOrder() != expected1 ||
          test2.getArrivalOrder() != expected2) return false;
    }
    {
      // hasBeenSeen - try the mutator too
      if (test1.hasBeenSeen() || test2.hasBeenSeen()) return false;
      test1.seePatient();
      if (!test1.hasBeenSeen() || test2.hasBeenSeen()) return false;
    }

    // (3) verify their string representations
    {
      String expected1 = "21701: 17M (YELLOW)";
      String expected2 = "32102: 21X (GREEN)";
      if (!test1.toString().equals(expected1) || !test2.toString().equals(expected2)) return false;
    }

    // (4) finally, verify that the constructor throws an exception for an invalid triage value
    try {
      new PatientRecord('F', 4, -17);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalArgumentException e) {
      // correct exception type, but it should have a message:
      if (e.getMessage() == null || e.getMessage().isBlank()) return false;
    } catch (Exception e) {
      // incorrect exception type
      return false;
    }

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  public static boolean testAdmissionsConstructorValid() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // (1) verify that a normal, valid-input constructor call does NOT throw an exception
    ExceptionalCareAdmissions admissions = null;
    try {
      admissions = new ExceptionalCareAdmissions(5);
    }catch(Exception e){
      return false;
    }
    // (2) verify that a just-created object has size 0, is not full, has no seen patients, and
    if(admissions.size() != 0) {
      return false;
    }
    if(admissions.isFull()) {
      return false;
    }
    if(admissions.getNumberSeenPatients() != 0) {
      return false;
    }
    // its toString() is an empty string
    if(!(admissions.toString().isBlank())) {
      return false;
    }
    //otherwise, the constructor build successfully, return true.
    return true;
  }
//
public static boolean testAdmissionsConstructorError() {
  // FIRST: reset the patient counter so this tester method can be run independently
  PatientRecord.resetCounter();
  // (1) verify that calling the constructor with capacity <= 0 causes an IllegalArgumentException
  try {
    ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(0);
    //if no exceptions been thrown, return false here
    return false;
  } catch (IllegalArgumentException e){
    // correct exception type, but it should have a message:
    if (e.getMessage() == null || e.getMessage().isBlank()) {
      return false;
    }
    //incorrect exception type
  } catch(Exception e) {
    return false;
  }
  //if the constructor throws a IllegalArgumentException successfully when we pass a invalid capacity into that method
  //return true
  return true;
}

  /**
   * This test method is provided for you in its entirety, to give you a model for verifying a
   * method which throws exceptions. This method tests addPatient() with two different, full
   * patientsList arrays; one contains seen patients and one does not.
   *
   * We assume for the purposes of this method that the ExceptionalCareAdmissions constructor
   * and PatientRecord constructor are working properly.
   *
   * This method must NOT allow ANY exceptions to be thrown from the tested method.
   *
   * @author hobbes
   * @return true if and only if all scenarios pass, false otherwise
   */

  public static boolean testAddPatientValid() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions empty = new ExceptionalCareAdmissions(3);
    // (1) add a new patient to an empty list - since you cannot use Arrays.deepEquals() here
    // anymore, verify the contents of the patientsList using ExceptionalCareAdmissions.toString()
    PatientRecord patient1 = new PatientRecord('M', 18, PatientRecord.YELLOW);
    try {
      empty.addPatient(patient1, 0);
      if(!empty.toString().equals("21801: 18M (YELLOW)")) {
        return false;
      }
    } catch(Exception e) {
      return false;
    }
    // (2) add a new patient to the end of the list
    PatientRecord patient2 = new PatientRecord('F', 19, PatientRecord.GREEN);
    try {
      empty.addPatient(patient2, 1);
      if(!empty.toString().equals("21801: 18M (YELLOW)\n11902: 19F (GREEN)")) {
        return false;
      }
    } catch(Exception e) {
      return false;
    }
    // (3) add a new patient to the beginning of the list
    PatientRecord patient3 = new PatientRecord('M', 20, PatientRecord.RED);
    try {
      empty.addPatient(patient3, 0);
      if(!empty.toString().equals("22003: 20M (RED)\n21801: 18M (YELLOW)\n11902: 19F (GREEN)")) {
        return false;
      }
    } catch(Exception e) {
      return false;
    }
    return true;
  }
  public static boolean testAddPatientError() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    ////// (1) a full Admissions object that contains no seen patients

    // create a small admissions object and fill it with patients. i'm filling the list
    // in decreasing order of triage, so the addPatient() method has to do the least
    // amount of work.
    ExceptionalCareAdmissions full = new ExceptionalCareAdmissions(3);
    full.addPatient(new PatientRecord('M', 18, PatientRecord.RED), 0);
    full.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);

    // saving one patient in a local variable so we can mark them "seen" later
    PatientRecord seenPatient = new PatientRecord('F', 20, PatientRecord.GREEN);
    full.addPatient(seenPatient, 2);

    try {
      full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception, but for this method we expect a specific
      // error message so we have one more step to verify:
      String message = e.getMessage();
      String expected = "Cannot admit new patients";
      if (!message.equals(expected)) return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and we can just fail the test now
      return false;
    }

    ////// (2) a full Admissions object that contains at least one seen patient

    // since we have a reference to the patient at index 2, we'll just mark them seen here
    seenPatient.seePatient();

    try {
      full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception again, but we expect a different error
      // message this time:
      String message = e.getMessage();
      String expected = "cleanPatientsList()";
      if (!message.equals(expected)) return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and the test fails here
      return false;
    }

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  public static boolean testGetIndexValid() {
    PatientRecord patient1 = new PatientRecord('M', 18, PatientRecord.YELLOW);
    PatientRecord patient2 = new PatientRecord('F', 19, PatientRecord.GREEN);
    PatientRecord patient3 = new PatientRecord('M', 20, PatientRecord.RED);
    PatientRecord.resetCounter();

    ExceptionalCareAdmissions empty = new ExceptionalCareAdmissions(50);
    // create an Admissions object and add a few Records to it, leaving some space
    empty.addPatient(patient1, 0);
    empty.addPatient(patient2, 1);
    empty.addPatient(patient3, 2);
    // (1) get the index of a PatientRecord that should go at the END
    if(!(empty.getAdmissionIndex(patient3)==2)) {return false;}
    // (2) get the index of a PatientRecord that should go at the BEGINNING
    if(!(empty.getAdmissionIndex(patient1)==0)) {return false;}
    // (3) get the index of a PatientRecord that should go in the MIDDLE
    if(!(empty.getAdmissionIndex(patient2)==1)) {return false;}
    return true;
  }
//
  public static boolean testGetIndexError() {

    PatientRecord patient1 = new PatientRecord('M', 18, PatientRecord.YELLOW);
    PatientRecord patient2 = new PatientRecord('F', 19, PatientRecord.GREEN);
    PatientRecord patient3 = new PatientRecord('M', 20, PatientRecord.RED);
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions full = new ExceptionalCareAdmissions(3);
    // create an Admissions object and add Records to it until it is full, as in testAddPatientError
    full.addPatient(patient1, 0);
    full.addPatient(patient2, 1);
    full.addPatient(patient3, 2);

    // create an Admissions object and add Records to it until it is full, as in testAddPatientError

    // (1) verify the exception when there are no patients who have been seen in the list
    try{
      full.getAdmissionIndex(patient1);
    } catch (IllegalStateException e) {
      // this is the correct type of exception again, but we expect a different error
      // message this time:
      String message = e.getMessage();
      String expected = "Cannot admit new patients";
      if (!message.equals(expected)) return false;
    }
    // (2) verify the exception when there is at least one patient who has been seen
    full.patientsList[0].seePatient();
    try{
      full.getAdmissionIndex(patient1);
    } catch (IllegalStateException e) {
      // this is the correct type of exception again, but we expect a different error
      // message this time:
      String message = e.getMessage();
      String expected = "cleanPatientsList()";
      if (!message.equals(expected)) return false;
    }
    return true;
  }

  public static boolean testAdmissionsBasicAccessors() {
    PatientRecord patient1 = new PatientRecord('M', 18, PatientRecord.YELLOW);
    PatientRecord patient2 = new PatientRecord('F', 19, PatientRecord.GREEN);
    PatientRecord patient3 = new PatientRecord('M', 20, PatientRecord.RED);
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions full = new ExceptionalCareAdmissions(3);
    ExceptionalCareAdmissions empty = new ExceptionalCareAdmissions(50);
    if (empty.size() != 0) {return false;}
    empty.addPatient(patient1, 0);
    empty.addPatient(patient2, 1);
    empty.addPatient(patient3, 2);
    full.addPatient(patient1, 0);
    full.addPatient(patient2, 1);
    full.addPatient(patient3, 2);
    // create an Admissions object and add Records to it until it is full, as in testAddPatientError
    // (1) verify isFull() on a non-full and a full Admissions object
    if (empty.isFull() == true) {return false;}
    if (full.isFull() == false) {return false;}
    // (2) verify size() before and after adding a PatientRecord
    if (empty.size() != 3) {return false;}
    // (3) verify getNumberSeenPatients() before and after seeing a patient
    if (empty.getNumberSeenPatients() != 0) {return false;}
    full.patientsList[0].seePatient();
    if (empty.getNumberSeenPatients() != 1) {return false;}
    // (see testAddPatientError for a model of how to do this while bypassing seePatient())
    return true;
  }

  public static boolean testSeePatientValid() {
    // create an Admissions object and add a few Records to it, saving a shallow copy of
    // at least one of the PatientRecord references
    PatientRecord.resetCounter();
    PatientRecord patient1 = new PatientRecord('M', 18, PatientRecord.YELLOW);
    PatientRecord patient2 = new PatientRecord('F', 19, PatientRecord.GREEN);
    PatientRecord patient3 = new PatientRecord('M', 20, PatientRecord.RED);
    ExceptionalCareAdmissions full1 = new ExceptionalCareAdmissions(3);
    ExceptionalCareAdmissions full2 = new ExceptionalCareAdmissions(50);
    full1.addPatient(patient1, 0);
    full1.addPatient(patient2, 1);
    full1.addPatient(patient3, 2);
    full2.addPatient(patient1, 0);
    full2.addPatient(patient2, 1);
    full2.addPatient(patient3, 2);
    System.out.println(full1.toString());
    // (1) call seePatient() on the caseID of your saved reference and verify that its
    // hasBeenSeen() accessor return value changes
    if (full1.getNumberSeenPatients() != 0) {return false;}
    full1.seePatient(21801);
    if (full1.patientsList[0].hasBeenSeen()!= true) return false;
    // (2) verify getNumberSeenPatients() before and after seeing a different patient
    if (full1.getNumberSeenPatients() != 1) {return false;}
    return true;
  }

  public static boolean testSeePatientError() {
    PatientRecord.resetCounter();
    PatientRecord patient1 = new PatientRecord('M', 18, PatientRecord.YELLOW);
    PatientRecord patient2 = new PatientRecord('F', 19, PatientRecord.GREEN);
    PatientRecord patient3 = new PatientRecord('M', 20, PatientRecord.RED);
    ExceptionalCareAdmissions full1 = new ExceptionalCareAdmissions(3);
    full1.addPatient(patient1, 0);
    full1.addPatient(patient2, 1);
    full1.addPatient(patient3, 2);
    // (1) verify that seeing a caseID for a patient not in the list causes an IllegalArgumentException
    try{
      full1.seePatient(66666);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {return false;}
    return true;
  }

  public static boolean testGetSummary() {
    // (1) choose one getSummary() test from P01; this method has not changed much
    return false;
  }

  public static boolean testCleanList() {
    // create an Admissions object and add a few Records to it

    // (1) using ExceptionalCareAdmissions.toString(), verify that a patientsList with NO seen
    // patients does not change after calling cleanPatientsList()

    // (2) call seePatient() for at least two of the records in your patientsList, and use toString()
    // to verify that they have been removed after calling cleanPatientsList()

    // NOTE: you do NOT need to verify file contents in this test method; please do so manually
    return false;
  }

  /**
   * Runs each of the tester methods and displays the result. Methods with two testers have
   * their output grouped for convenience; a failed test is displayed as "X" and a passed test
   * is displayed as "pass"
   * 
   * @param args unused
   * @author hobbes
   */
  public static void main(String[] args) {

    System.out.println("PatientRecord: " + (testPatientRecord() ? "pass" : "X"));
    System.out.println("Admissions Constructor: " +
        (testAdmissionsConstructorValid() ? "pass" : "X") + ", " +
        (testAdmissionsConstructorError() ? "pass" : "X"));
    System.out.println("Add Patient: " + (testAddPatientValid() ? "pass" : "X") + ", " +
        (testAddPatientError() ? "pass" : "X"));
    System.out.println("Get Admission Index: " + (testGetIndexValid() ? "pass" : "X") + ", " +
        (testGetIndexError() ? "pass" : "X"));
    System.out.println("Basic Accessors: " + (testAdmissionsBasicAccessors() ? "pass" : "X"));
    System.out.println("See Patient: " + (testSeePatientValid() ? "pass" : "X") + ", " +
         (testSeePatientError() ? "pass" : "X"));
//    System.out.println("Get Summary: " + (testGetSummary() ? "pass" : "X"));
//    System.out.println("Clean List: " + (testCleanList() ? "pass" : "X"));
  }

}
