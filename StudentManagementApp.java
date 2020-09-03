import java.util.*;


public class StudentManagementApp {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int studentCount = 0;

        // Ask how many students are enrolling.
        while(studentCount <=0){
            System.out.print("Number of students enrolling: ");
            if(input.hasNextInt()) {
                studentCount = input.nextInt(); 
            }
            else {
                System.out.println("Please input a number.");
                input.next();
            }

        }
        Student[] students = new Student[studentCount];

        
        //Prompt user for name and year of each student
        for(int i = 0; i < studentCount; i++) { 
            System.out.println("\nAdding New Student");
            students[i] = new Student(input);
            System.out.print("\n\n");

            // Enroll student in courses
            students[i].enroll(input);
            System.out.print("\n\n");
    
            // View Student's Enrolled Courses
            students[i].viewCourses();

            // View Student's Tuition Balance
            students[i].viewTuitionBalance();

            // Pay Student's Tuition Balance
            students[i].payTuition(input);

            // View Student's Account
            students[i].viewStudentAccount();
        }

        input.close();
    }
}