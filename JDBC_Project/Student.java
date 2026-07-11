import java.sql.*;
import java.util.Scanner;

public class Student {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college",
                    "javauser",
                    "java123");

            Statement stmt = con.createStatement();

            int choice;

            do {

                System.out.println("\n========== STUDENT MANAGEMENT SYSTEM ==========");
                System.out.println("1. Create Table");
                System.out.println("2. Insert Student");
                System.out.println("3. Display Students");
                System.out.println("4. Update Student");
                System.out.println("5. Delete Student");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {

                    case 1:

                        String createTable = "CREATE TABLE IF NOT EXISTS student1("
                                + "id INT PRIMARY KEY,"
                                + "name VARCHAR(50),"
                                + "branch VARCHAR(20))";

                        stmt.executeUpdate(createTable);

                        System.out.println("Table Created Successfully!");

                        break;

                    case 2:

                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Branch: ");
                        String branch = sc.nextLine();

                        String insert = "INSERT INTO student1 VALUES("
                                + id + ",'"
                                + name + "','"
                                + branch + "')";

                        stmt.executeUpdate(insert);

                        System.out.println("Record Inserted Successfully!");

                        break;

                    case 3:

                        ResultSet rs = stmt.executeQuery("SELECT * FROM student1");

                        System.out.println("\nID\tNAME\tBRANCH");
                        System.out.println("---------------------------");

                        while (rs.next()) {

                            System.out.println(
                                    rs.getInt("id") + "\t"
                                            + rs.getString("name") + "\t"
                                            + rs.getString("branch"));

                        }

                        break;

                    case 4:

                        System.out.print("Enter Student ID to Update: ");
                        int uid = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter New Branch: ");
                        String newBranch = sc.nextLine();

                        String update = "UPDATE student1 SET branch='"
                                + newBranch
                                + "' WHERE id="
                                + uid;

                        int rows = stmt.executeUpdate(update);

                        if (rows > 0)
                            System.out.println("Record Updated Successfully!");
                        else
                            System.out.println("Student Not Found!");

                        break;

                    case 5:

                        System.out.print("Enter Student ID to Delete: ");
                        int did = sc.nextInt();

                        String delete = "DELETE FROM student1 WHERE id=" + did;

                        int d = stmt.executeUpdate(delete);

                        if (d > 0)
                            System.out.println("Record Deleted Successfully!");
                        else
                            System.out.println("Student Not Found!");

                        break;

                    case 6:

                        System.out.println("Thank You!");

                        break;

                    default:

                        System.out.println("Invalid Choice!");

                }

            } while (choice != 6);

            con.close();

            sc.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
