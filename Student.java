import java.util.*;

public class Student {
    private String firstName;
    private String lastName;
    private int yearLevel;
    private int expectedGradYear;
    private int studentID;
    private int tuitionBalance = 0;
    private HashMap<String, Integer> studentsCourses = new HashMap<>();


    private static final String[] availableCourses = new String[]{"History 101","Math 20A","Physics 2A", "Computer Science 12","Humanities 5", "Chemistry 6A"};
    private static int[] classSize = new int[]{0,0,0,0,0,0};
    private static final int CLASS_LIMIT = 1;    
    private static final int COURSE_COST = 100;
    private static int[] studentYearCount = new int[]{0,0,0,0,0};

    // Constructor to ask user for student name / year.
    public Student(Scanner input) {
        Calendar cal = Calendar.getInstance();
        input.nextLine();
        System.out.print("Enter Student's First Name: ");
        firstName = input.nextLine();

        System.out.print("Enter Student's Last Name: ");
        lastName = input.nextLine();

        System.out.print("1 - Freshman\n2 - Sophomore\n3 - Junior\n4 - Senior\n5 - Super Senior\n");
        System.out.print("Enter Student's Year Level: ");
        int exit = 0;
        while(exit == 0) {
            if(input.hasNextInt()) {
                yearLevel = input.nextInt();
                if(1 > yearLevel || yearLevel > 6) {
                    System.out.print("Enter a number between 1-5:");
                } else {
                    exit = 1;
                }
            } else {
                System.out.print("Enter a number between 1-5:");
                input.next();
            }
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
        boolean exit = false;

        System.out.println("Available Courses to Enroll:\n1 - History 101\n2 - Math 20A\n3 - Physics 2A\n4 - Computer Science 12\n5 - Humanities 5\n6 - Chemistry 6A");

        while(!exit) {     
            System.out.println("Enter in course number, or 0(Zero) to Quit.");   
            if(input.hasNextInt()){
                course = input.nextInt(); 
                if(course < 0 || course > 6) {
                    System.out.println("Please input correct course number.");
                }
                else if(course == 0) {
                    System.out.println("Successfully Enrolled in " + (i) + " courses!");
                    exit = true;
                } else if(classSize[course-1] >= CLASS_LIMIT) { 
                    System.out.println(availableCourses[course-1] + " is full, please select another!");
                } else {
                    addCourse(course-1);
                    updateClassNum(course-1,true);
                    i++;
                }
            } else {
                System.out.println("Please input correct course number.");
                input.next();
            }
        }
        System.out.println("Tuition Balance is : $" + tuitionBalance + "\n");
    }

    //setter method to increase / decrease a classes student count.
    public static void updateClassNum (int courseId, boolean inc) {
        if(inc) {
            classSize[courseId]++;
        } else {
            classSize[courseId]--;
        }
    }

    //getter method to see classSize
    public static void checkClassSize(){
        System.out.println("Current Class Sizes are: ");
        for(int i = 0; i < classSize.length; i++) {
            System.out.println(availableCourses[i] + " : " + classSize[i] + "/" + CLASS_LIMIT);
        }
    }

    private void addCourse(int courseNum) { 
        studentsCourses.put(availableCourses[courseNum], courseNum);
        System.out.println("Added - " + availableCourses[courseNum]);
        tuitionBalance += COURSE_COST;
    }

    // Drop a course
    /*public void drop(Scanner input){
        System.out.println("Enter Course to Drop.");
        int i = 0;
        while(i < studentsCourses.size() && !studentsCourses.isEmpty()) {
            if(studentsCourses.get(course) != null) {
                updateClassNum(studentsCourses.get(course),false);
                studentsCourses.remove(course);
            }
            i++;
        }
    }*/
    
    // View Students' Enrolled Courses
    public void viewCourses() {
        System.out.println(firstName + " " + lastName + "'s Courses:");
        // if courses hashMap is empty.
        if(studentsCourses.isEmpty()) {
            System.out.println("No enrolled courses.\n");
            //return;
        }
        int i = 0;
        // loop through courses hashmap printing each course.
        for(String course : studentsCourses.keySet()) {
            System.out.println((i+1) + ") " + course);
            i++;
        }

    }

    // View balance 
    public void viewTuitionBalance(){
        System.out.println(firstName + " " + lastName + "'s Tuition Balance : $" + tuitionBalance);
    }

    // Pay tuition
    public void payTuition(Scanner input) {
        if(tuitionBalance == 0) {
            return;
        }
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
        // Loop through studentCourses HashMap and print each course.
        viewCourses();
        System.out.println("\nTuition Balance : $" + tuitionBalance);
    }
}