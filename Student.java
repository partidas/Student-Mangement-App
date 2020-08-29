package studentmanagementapp;
import java.util.*;

public class Student {
    private String firstName;
    private String lastName;
    private int yearLevel;
    private int expectedGradYear;
    private int studentID;
    private int tuitionBalance = 0;
    private String[] studentsCourses = new String[6];


    private static final String[] availableCourses = new String[]{"History 101","Math 20A","Physics 2A", "Computer Science 12","Humanities 5", "Chemistry 6A"};
    private static int[] classSize = new int[]{0,0,0,0,0,0};
    private static final int classLimit = 1;    
    private static final int courseCost = 100;
    private static final Calendar cal = Calendar.getInstance();
    private static int[] studentYearCount = new int[]{0,0,0,0,0};

    // Constructor to ask user for student name / year.
    public Student(Scanner input) {
        System.out.print("Enter Student's First Name: ");
        firstName = input.next();

        System.out.print("Enter Student's Last Name: ");
        lastName = input.next();

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
        studentYearCount[yearLevel-1]++;

    }

    // Generate Student 5-digit unique ID w/ first number being the year started.
    private void setStudentID() {
        studentID = (yearLevel * 10000) + studentYearCount[yearLevel - 1];
    }
    
    // Enroll in courses
    public void enroll(Scanner input) {
        int i = 0;
        int course;

        System.out.println("Available Courses to Enroll:\n1 - History 101\n2 - Math 20A\n3 - Physics 2A\n4 - Computer Science 12\n5 - Humanities 5\n6 - Chemistry 6A");
        while(i < studentsCourses.length) {        
            System.out.println("Enter in course number, or 0(Zero) to Quit.");

            if(input.hasNextInt()){
                course = input.nextInt(); 
                if(course < 0 || course > 6) {
                    System.out.println("Please input correct course number.");
                }
                else if(course == 0) {
                    System.out.println("Successfully Enrolled in " + (i) + " courses!");
                    i = studentsCourses.length;
                    break;
                } else if(classSize[course-1] >= classLimit) { 
                    System.out.println(availableCourses[course-1] + " is full, please select another!");
                } else {
                    addCourse(i, course-1);
                    classSize[course-1]++;
                    i++;
                }
            } else {
                System.out.println("Please input correct course number.");
                input.next();
            }
        }
        System.out.println("Tuition Balance is : " + tuitionBalance + "\n");
    }

    private void addCourse(int studentClassNum, int courseNum) { 
        studentsCourses[studentClassNum] = availableCourses[courseNum];
        System.out.println("Added - " + studentsCourses[studentClassNum]);
        tuitionBalance += courseCost;
    }

    /* Still need to implement this.
    // Drop a course
    public void drop(String course){
        int i = 0;
        while(studentsCourses[i] != course) {
        }
    }*/
    
    // View Enrolled Courses
    public void viewCourses() {
        System.out.println(firstName + " " + lastName + "'s Courses:");
        // if courses array is empty.
        if(studentsCourses[0] == null) {
            System.out.println("No enrolled courses found...\n");
            return;
        }
        int i = 0;
        // loop through printing each course.
        while(i < studentsCourses.length && studentsCourses[i] != null ) {
            System.out.println((i+1) + ") " + studentsCourses[i]);
            i++;
        }
    }

    // View balance 
    public void viewTuitionBalance(){
        System.out.println(firstName + " " + lastName + "'s Tuition Balance : $" + tuitionBalance);
    }

    // Pay tuition
    public void payTuition(Scanner input) {
        int payment;
        System.out.print("Please enter how much of your tuition you wish to pay : $");
        while(!input.hasNextInt()) {
            System.out.println("Please input a number.");
            input.next();
            System.out.print("Please enter how much of your tuition you wish to pay : $");

        }
        payment = input.nextInt();
        tuitionBalance = tuitionBalance - payment;
        System.out.print("Thank you for your payment of $" + payment + "\n");
        System.out.println(firstName + " " + lastName + "'s New Tuition Balance : $" + tuitionBalance);

    }

    // Show Student Account (Name, ID, year level, expected graduation, courses enrolled, and balance)
    public void viewStudentAccount(){
        System.out.println("\nStudent Account Information: \nName : " + firstName + " " + lastName +
                        "\nStudent ID : " + studentID + "\nYear Level : " + yearLevel + 
                        "\nExpected Graduation : " + expectedGradYear + "\nEnrolled Courses : ");  
        // Loop through Courses array and print each course.
        int i = 0;
        while(i < studentsCourses.length && studentsCourses[i] != null) { 
            System.out.println("   " + (i+1) + ") " + studentsCourses[i]);
            i++;
        }
        System.out.println("\nTuition Balance : " + tuitionBalance);
    }
}