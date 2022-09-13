import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FinalMenu extends MySQL{

    public static void finalMenu(){
        Connect();
        System.out.println("Please choose an option from the list: ");
        System.out.println("1 - Display the directory with the most files");
        System.out.println("2 - Display the directory largest in size");
        System.out.println("3 - Display the 5 largest files");
        System.out.println("4 - Display files of a certain type");
        System.out.println("5 - Clear the DB and start over");
        System.out.println("6 - Exit");
        System.out.println("Please enter a number 1 - 6");
        String sp = "";
        //CallableStatement cStmt;
        Scanner reader = new Scanner(System.in);
        int option = reader.nextInt();
        switch (option){
            case 1:
                System.out.println("You selected directory with the most files:");
                 sp = "{call sp_GetDirectory(?,?)}";
                try {
                    //11,1
                    CallableStatement cStmt = connection.prepareCall(sp);
                    cStmt.setInt(1,11);
                    cStmt.setInt(2, 1);
                    ResultSet rs = cStmt.executeQuery();
                    if(rs.next()){
                        System.out.println("Directory name: " +rs.getString(1)
                        + " Number of files: " + rs.getString(2));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }break;
            case 2:
                System.out.println("You selected directory largest in size:");
                 sp = "{call sp_GetDirectory(12,1)}";
                try {
                    CallableStatement cStmt = connection.prepareCall(sp);
                    ResultSet rs = cStmt.executeQuery();
                    if(rs.next()){
                        System.out.println("Directory name: " + rs.getString(1)
                        + " Directory size: " + rs.getString(2));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }break;
            case 3:
                System.out.println("You selected display the 5 largest files:");
                sp = "{call sp_GetFile(?,?,?)}";
                try {
                    //11,1,''
                    CallableStatement cStmt = connection.prepareCall(sp);
                    cStmt.setInt(1, 11);
                    cStmt.setInt(2, 1);
                    cStmt.setString(3, "");
                    ResultSet rs = cStmt.executeQuery();
                    while(rs.next()){
                        System.out.println("Filename: " + rs.getString(1) +
                                " FileSize: " + rs.getString(2));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }break;
            case 4:
                System.out.println("You selected display all files by a certain type.");
                System.out.println("Please enter the type, for example for .exe enter exe");
                String fileType = reader.next();

               // call sp_GetFile(12,1,fileType";
                sp = "{call sp_GetFile(?,?,?)}";
                try {
                    //12,1,''
                    CallableStatement cStmt = connection.prepareCall(sp);
                    cStmt.setInt(1, 12);
                    cStmt.setInt(2, 1);
                    cStmt.setString(3, fileType);
                    ResultSet rs = cStmt.executeQuery();
                    while(rs.next()){
                        System.out.println("Filename: " + rs.getString(1)
                        + " FileType: " + rs.getString(2));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }break;
            case 5:
                System.out.println("You selected clear the DB and start over");

                sp = "{call sp_TruncateAll()}";
                try {
                    CallableStatement cStmt = connection.prepareCall(sp);
                    ResultSet rs = cStmt.executeQuery();
                    System.out.println("tables cleared");
                    Main.theMenu();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                System.out.println("You selected exit. Goodbye!");
                System.exit(1);
            default:
                System.out.println("INvalid input, goodbye");
                System.exit(1);
        }

    }
}
