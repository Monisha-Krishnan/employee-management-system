import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static EmployeeManager manager = new EmployeeManager();

    public static void main(String[] args) {
        if (!adminLogin()) {
            System.out.println("Login failed. Exiting...");
            return;
        }

        int choice;
        do {
            printMenu();
            choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: manager.viewEmployees(); break;
                case 3: searchEmployee(); break;
                case 4: updateEmployee(); break;
                case 5: deleteEmployee(); break;
                case 6: System.out.println("Exiting... Thank you!"); break;
                default: System.out.println("Invalid choice, try again.");
            }
        } while (choice != 6);
    }

    // Simple admin login (max 3 attempts)
    static boolean adminLogin() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter Admin Username: ");
            String username = sc.nextLine();
            System.out.print("Enter Admin Password: ");
            String password = sc.nextLine();

            if (username.equals("admin") && password.equals("admin123")) {
                System.out.println("Login successful!");
                return true;
            }
            attempts++;
            System.out.println("Invalid credentials. Attempts left: " + (3 - attempts));
        }
        return false;
    }

    static void printMenu() {
        System.out.println("\n===== Employee Management System =====");
        System.out.println("1. Add Employee");
        System.out.println("2. View Employees");
        System.out.println("3. Search Employee");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Exit");
    }

    static void addEmployee() {
        int id = getIntInput("Enter Employee ID: ");
        if (manager.searchEmployee(id) != null) {
            System.out.println("Employee ID already exists!");
            return;
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Designation: ");
        String desig = sc.nextLine();
        double salary = getDoubleInput("Enter Basic Salary: ");

        Employee emp = new Employee(id, name, dept, desig, salary);
        manager.addEmployee(emp);
    }

    static void searchEmployee() {
        int id = getIntInput("Enter Employee ID to search: ");
        Employee emp = manager.searchEmployee(id);
        if (emp != null) {
            System.out.println("Employee Found:\n" + emp);
        } else {
            System.out.println("Employee not found.");
        }
    }

    static void updateEmployee() {
        int id = getIntInput("Enter Employee ID to update: ");
        boolean updated = manager.updateEmployee(id, sc);
        System.out.println(updated ? "Employee updated successfully!" : "Employee not found.");
    }

    static void deleteEmployee() {
        int id = getIntInput("Enter Employee ID to delete: ");
        boolean deleted = manager.deleteEmployee(id);
        System.out.println(deleted ? "Employee deleted successfully!" : "Employee not found.");
    }

    // Helper to safely read integer input
    static int getIntInput(String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. " + message);
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    // Helper to safely read double input
    static double getDoubleInput(String message) {
        System.out.print(message);
        while (!sc.hasNextDouble()) {
            System.out.print("Invalid input. " + message);
            sc.next();
        }
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }
}
