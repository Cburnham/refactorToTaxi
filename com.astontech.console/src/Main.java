
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    final static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        // write your code here
        LessonDBConnection();
        theMenu();
    }
    private static Connection LessonDBConnection(){
        String dbHost = "localhost";
        String dbName = "finalassessment";
        String dbUser = "consoleUser";
        String dbPass = "putpassordhere";
        String useSSL = "true";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        }catch(ClassNotFoundException ex){
            logger.info("com.astontech.dao.MySQL.MySQL.com.astontech.dao.MySQL.MySQL Driver not found");
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        logger.info("com.astontech.dao.MySQL.MySQL.com.astontech.dao.MySQL.MySQL driver registered.");
        Connection connection = null;
        try {
            /*
                        connection = DriverManager.getConnection("jdbc:mysql://"+dbHost+":3306/"+
                    dbName+"?autoReconnect=true&useSSL="+useSSL+"&noAccessToProcedureBodies="
                    + procBod,dbUser,dbPass);
             */
            connection = DriverManager.getConnection("jdbc:mysql://"+dbHost+":3306/"+
                    dbName+"?autoReconnect=true&useSSL=false",dbUser,dbPass);
        } catch (SQLException e) {
            System.out.println("Connection Failed!!!!!");
            e.printStackTrace();
            return null;
        }
        if(connection != null){
            logger.info("Success fully connected to MYSQL database");
            return connection;
        }else{
            logger.info("Connection failed!!!");
            return null;
        }
    }
    public static void theMenu(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter the root target directory");
        String targetDir = reader.nextLine();
        File dir = new File(targetDir);
        LessonRecursionComplex(dir);
        FinalMenu lastMenu = new FinalMenu();
        lastMenu.finalMenu();
    }
    public static void LessonRecursionComplex(File dir){
        String mostFilesDir;
        String largestSizeDir;

        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                int dirId = 0;
                if (file.isDirectory()) {
                    //temp variables

                    String tempDirName = file.getName();
                    double tempDirSize = file.length();
                    int tempCount = file.list().length;
                    String tempDirPath = file.getPath();
                    DirectoryClass tempDir = new DirectoryClass(tempDirName, tempDirSize, tempCount, tempDirPath);
                    DirectoryDAO theDirDAO = new DirectoryDAOImpl();
                    logger.info("Adding directory to database: " +file.getCanonicalPath());
                    theDirDAO.insertDirectory(tempDir);

                    //recursive calling
                    LessonRecursionComplex(file);
                    dirId++;
                } else {
                    FileDAO theFileDAO = new FileDAOImpl();
                    String tempFileName = file.getName();
                    String tempFileType= FilenameUtils.getExtension(String.valueOf(file));
                    double tempFileSize = file.length();
                    int fileDirId = dirId;
                    String tempPath = file.getPath();
                    FileClass tempFile = new FileClass(tempFileName, tempFileType, tempFileSize, tempPath, dirId);
                    theFileDAO.insertFile(tempFile);
                    logger.info("Adding File to database: " + file.getCanonicalPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
