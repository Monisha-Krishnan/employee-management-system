public class Employee {
    private int id;
    private String name;
    private String department;
    private String designation;
    private double basicSalary;

    // Constructor
    public Employee(int id, String name, String department, String designation, double basicSalary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.basicSalary = basicSalary;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

    // Salary Calculation Logic: Basic + HRA(20%) + DA(10%) - PF(12%)
    public double calculateNetSalary() {
        double hra = basicSalary * 0.20;
        double da = basicSalary * 0.10;
        double pf = basicSalary * 0.12;
        return basicSalary + hra + da - pf;
    }

    // Convert employee object -> line of text (for saving to file)
    public String toFileString() {
        return id + "|" + name + "|" + department + "|" + designation + "|" + basicSalary;
    }

    // Convert line of text from file -> employee object
    public static Employee fromFileString(String line) {
        String[] parts = line.split("\\|");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String department = parts[2];
        String designation = parts[3];
        double basicSalary = Double.parseDouble(parts[4]);
        return new Employee(id, name, department, designation, basicSalary);
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d Name: %-15s Dept: %-12s Designation: %-14s Basic: %-10.2f Net Salary: %.2f",
                id, name, department, designation, basicSalary, calculateNetSalary());
    }
}
