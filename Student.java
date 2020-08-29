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

    //private static int[] classSize = new int[]{30,0,0,0,0,0};
    //private static final int classLimit = 30;
    private static final int courseCost = 100;
    private static final Calendar cal = Calendar.getInstance();
    private static int[] studentYearCount = new int[]{0,0,0,0,0};

    // Constructor to ask user for student name / year.
    public Student(Scanner input) {
        //Scanner input = new Scanner(System.in);
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
        
        studentYearCount[yearLevel-1]++;
        expectedGradYear = 5 - yearLevel + cal.get(Calendar.YEAR);
        setStudentID();
    }

    // Generate Student 5-digit unique ID w/ first number being the year started.
    private void setStudentID() {
        studentID = (yearLevel * 10000) + studentYearCount[yearLevel - 1];
    }
    
    // Enroll in courses
    public void enroll(Scanner input) {
        int i = 0;
        String course = input.nextLine();

        /*  FOR FUTURE, maybe use a string list to add courses instead of switch case with a catch exception for anything 
            other than a value.
        */
        System.out.println("Available Courses to Enroll:\n1 - History 101\n2 - Math 20A\n3 - Physics 2A\n4 - Computer Science 12\n5 - Humanities 5\n6 - Chemistry 6A");
        while(i < studentsCourses.length) {        
            System.out.println("Enter Y to view courses again, else type in course number, or Q to Quit.");
            course = input.nextLine();   

            //use switch case
            switch(course) {
                case "Y":         
                    System.out.println("Available Courses to Enroll:\n1 - History 101\n2 - Math 20A\n3 - Physics 2A\n4 - Computer Science 12\n5 - Humanities 5\n6 - Chemistry 6A");
                    break;
                case "Q":
                    System.out.println("Successfully Enrolled in " + (i) + " courses!");
                    i = studentsCourses.length;
                    break;
                case "1":
                    addCourse(i, "History 101");
                    i++;
                    break;
                case "2":
                    addCourse(i, "Math20A");
                    i++;
                    break;
                case "3": 
                    addCourse(i, "Physics2A");
                    i++;
                    break;
                case "4": 
                    addCourse(i, "Computer Science 12");
                    i++;
                    break;
                case "5":
                    addCourse(i, "Humanities 5"); 
                    i++;
                    break; 
                case "6": 
                    addCourse(i, "Chemistry 6A");
                    i++;
                    break;
            } 
        }
        System.out.println("Tuition Balance is : " + tuitionBalance + "\n");
    }

    private void addCourse(int studentClassNum, String course) { 
        studentsCourses[studentClassNum] = course;
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
        while(studentsCourses[i] != null) {
            System.out.println((i+1) + ") " + studentsCourses[i]);
            i++;
        }
    }


    // View balance 
    public void viewTuitionBalance(){
        System.out.println(firstName + " " + lastName + "'s Tuition Balance : $" + tuitionBalance);
    }


    // Pay tuition
    public void payTuition(int payment) {
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
        while(studentsCourses[i] != null) { 
            System.out.println("   " + (i+1) + ") " + studentsCourses[i]);
            i++;
        }
        System.out.println("\nTuition Balance : " + tuitionBalance);
    }
}
