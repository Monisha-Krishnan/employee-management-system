import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManager {
    private ArrayList<Employee> employeeList;
    private final String FILE_NAME = "employees.txt";

    // Constructor - loads existing data from file when program starts
    public EmployeeManager() {
        employeeList = new ArrayList<>();
        loadFromFile();
    }

    // Add new employee
    public void addEmployee(Employee emp) {
        employeeList.add(emp);
        saveToFile();
        System.out.println("Employee added successfully!");
    }

    // View all employees
    public void viewEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }
        System.out.println("\n----- Employee List -----");
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
    }

    // Search employee by ID
    public Employee searchEmployee(int id) {
        for (Employee emp : employeeList) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    // Update employee details
    public boolean updateEmployee(int id, Scanner sc) {
        Employee emp = searchEmployee(id);
        if (emp == null) return false;

        System.out.print("Enter new name (" + emp.getName() + "), or press Enter to keep same: ");
        String name = sc.nextLine();
        if (!name.trim().isEmpty()) emp.setName(name);

        System.out.print("Enter new department (" + emp.getDepartment() + "): ");
        String dept = sc.nextLine();
        if (!dept.trim().isEmpty()) emp.setDepartment(dept);

        System.out.print("Enter new designation (" + emp.getDesignation() + "): ");
        String desig = sc.nextLine();
        if (!desig.trim().isEmpty()) emp.setDesignation(desig);

        System.out.print("Enter new basic salary (" + emp.getBasicSalary() + "): ");
        String salaryInput = sc.nextLine();
        if (!salaryInput.trim().isEmpty()) emp.setBasicSalary(Double.parseDouble(salaryInput));

        saveToFile();
        return true;
    }

    // Delete employee
    public boolean deleteEmployee(int id) {
        Employee emp = searchEmployee(id);
        if (emp != null) {
            employeeList.remove(emp);
            saveToFile();
            return true;
        }
        return false;
    }

    // Save all employees to file (overwrites file with current list)
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee emp : employeeList) {
                bw.write(emp.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load employees from file into ArrayList
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    employeeList.add(Employee.fromFileString(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
