package studentmanagementapp;
import java.util.*;

public class Student {
    private String firstName;
    private String lastName;
    private int yearLevel;
    private int expectedGradYear;
    private int studentID;
    private String[] courses = new String[5];
    private static int courseCost = 100;
    private static int id = 0;
    private int tuitionBalance = 0;
    private Calendar cal = Calendar.getInstance();

    // Constructor to ask user for student name / year.
    public Student() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Student's First Name: ");
        firstName = input.nextLine();

        System.out.print("Enter Student's Last Name: ");
        lastName = input.nextLine();

        System.out.print("1 - Freshman\n2 - Sophomore\n3 - Junior\n4 - Senior\n5 - Super Senior\n");
        System.out.print("Enter Student's Year Level: ");
        yearLevel = input.nextInt();
        while( (0 > yearLevel) && (yearLevel > 5)) {
            System.out.print("Enter a number between 1-5:");
            yearLevel = input.nextInt();
            break;
        }
        expectedGradYear = 5 - yearLevel + cal.get(Calendar.YEAR);

        setStudentID();
        System.out.println("Student entered: \n" + firstName + " " + lastName + "\nYear : " + yearLevel 
                        + "\nExpected Graduation : " + expectedGradYear + "\nStudent ID : " + studentID
                        + "\nStudent Account Balance : " + tuitionBalance);
    }

    // Generate Student 5-digit unique ID w/ first number being the year started.
    private void setStudentID() {
        id++;
        this.studentID = (yearLevel * 10000) + id;
    }
    // Enroll in courses
    public void enroll() {
        Scanner input = new Scanner(System.in);
        int i = 0;
        String course;
        while(i < 5) {
            System.out.println("Which classes do you wish to enroll in? (Q to quit)");
            System.out.println("History 95\nMath 20a\nPhysics 2a\nChemistry 6a\nComputer Science 12\n");
            course = input.nextLine();
            if(course.equals("Q")) {                        
                System.out.println("Successfully Enrolled in " + (i) + " courses!");
                break;
            }
            courses[i] = course;
            System.out.println("\nAdded - " + courses[i]);
            this.tuitionBalance += courseCost;
            System.out.println("Tuition Balance is : " + tuitionBalance + "\n");
            i++;

        }
    }

    // Drop a course
    public void drop(String course){
        while(this.courses[i] != course) {
            
        }
    }

    // View Enrolled Courses
    public void viewCourses() {
        System.out.println(this.firstName + " " + this.lastName + "'s Courses:");
        if(this.courses[0] == null) {
            System.out.println("No enrolled courses found...\n");
            return;
        }
        int i = 0;
        while(this.courses[i] != null) {
            System.out.println((i+1) + ") " + this.courses[i]);
            i++;
        }
    }

    // View balance 
    public void viewTuitionBalance(){
        System.out.println(this.firstName + " " + this.lastName + "'s Tuition Balance : $" + this.tuitionBalance);
    }

    // Pay tuition
    public void payTuition(int payment) {
        System.out.println(this.firstName + " " + this.lastName + "'s Previous Tuition Balance : $" + this.tuitionBalance);
        this.tuitionBalance = this.tuitionBalance - payment;
        System.out.println("Thank you for your payment of $" + payment);
        System.out.println(this.firstName + " " + this.lastName + "'s New Tuition Balance : $" + this.tuitionBalance);
    }

    // Show Student Account (Name, ID, year level, expected graduation, courses enrolled, and balance)
    public void viewStudentAccount(){
        System.out.println("\nStudent Account Information: ");
        System.out.println("Name : " + this.firstName + " " + this.lastName);
        System.out.println("Student ID : " + this.studentID);
        System.out.println("Year Level : " + this.yearLevel);
        System.out.println("Expected Graduation : " + this.expectedGradYear);
        System.out.println("Enrolled Courses : ");
        int i = 0;
        while(this.courses[i] != null) { 
            System.out.println((i+1) + ") " + this.courses[i]);
            i++;
        }
        System.out.println("\nTuition Balance : " + this.tuitionBalance);
    }
}
