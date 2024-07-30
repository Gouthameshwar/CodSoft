import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    String name;
    int totalMarks;
    double averagePercentage;
    char grade;
    int highestMark;
    int lowestMark;
    double standardDeviation;

    public Student(String name, int[] marks) {
        this.name = name;
        calculate(marks);
    }

    private void calculate(int[] marks) {
        totalMarks = 0;
        highestMark = Integer.MIN_VALUE;
        lowestMark = Integer.MAX_VALUE;
        for (int mark : marks) {
            totalMarks += mark;
            if (mark > highestMark) {
                highestMark = mark;
            }
            if (mark < lowestMark) {
                lowestMark = mark;
            }
        }
        averagePercentage = (double) totalMarks / marks.length;
        grade = getGrade(averagePercentage);
        standardDeviation = getStandardDeviation(marks, averagePercentage);
    }

    private char getGrade(double averagePercentage) {
        if (averagePercentage >= 90) return 'A';
        if (averagePercentage >= 80) return 'B';
        if (averagePercentage >= 70) return 'C';
        if (averagePercentage >= 60) return 'D';
        return 'F';
    }

    private double getStandardDeviation(int[] marks, double average) {
        double sumSquaredDifferences = 0;
        for (int mark : marks) {
            sumSquaredDifferences += Math.pow(mark - average, 2);
        }
        return Math.sqrt(sumSquaredDifferences / marks.length);
    }

    @Override
    public String toString() {
        return "Student Name: " + name +
                "\nTotal Marks: " + totalMarks +
                "\nAverage Percentage: " + averagePercentage + "%" +
                "\nGrade: " + grade +
                "\nHighest Mark: " + highestMark +
                "\nLowest Mark: " + lowestMark +
                "\nStandard Deviation: " + standardDeviation +
                "\n";
    }
}

public class StudentGrades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        while (true) {
            System.out.print("Enter student name: ");
            String name = scanner.next();

            System.out.print("Enter the number of subjects: ");
            int numberOfSubjects = scanner.nextInt();

            int[] marks = new int[numberOfSubjects];
            for (int i = 0; i < numberOfSubjects; i++) {
                while (true) {
                    System.out.print("Enter marks for subject " + (i + 1) + " (0-100): ");
                    int mark = scanner.nextInt();
                    if (mark >= 0 && mark <= 100) {
                        marks[i] = mark;
                        break;
                    } else {
                        System.out.println("Invalid mark. Please enter a value between 0 and 100.");
                    }
                }
            }

            Student student = new Student(name, marks);
            students.add(student);

            System.out.println("\nResults for " + name + ":");
            System.out.println(student);

            System.out.print("\nDo you want to enter details for another student? (yes/no): ");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("\nSummary for all students:");
        for (Student student : students) {
            System.out.println(student);
        }

        scanner.close();
    }
}