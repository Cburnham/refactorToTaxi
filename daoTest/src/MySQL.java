import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySQL {
    protected static String dbHost = "localhost";
    protected static String dbName = "finalassessment";
    protected static String dbUser = "consoleUser";
    protected static String dbPass = "password01";
    protected static String useSSL = "true";
    protected static Connection connection = null;

    final static Logger logger = Logger.getLogger(MySQL.class);
    protected static final int GET_BY_ID = 10;
    protected static final int GET_COLLECTION = 20;
    protected static final int INSERT = 10;
    protected static final int UPDATE = 20;
    protected static final int DELETE = 30;
    protected static void Connect(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        }catch(ClassNotFoundException ex){
            logger.error("MySQL.MySQL Driver not found");
            //return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        logger.info("MySQL.MySQL driver registered.");

        try {
            /*
                        connection = DriverManager.getConnection("jdbc:mysql://"+dbHost+":3306/"+
                    dbName+"?autoReconnect=true&useSSL="+useSSL+"&noAccessToProcedureBodies="
                    + procBod,dbUser,dbPass);
             */
            connection = DriverManager.getConnection("jdbc:mysql://"+dbHost+":3306/"+
                    dbName+"?autoReconnect=true&useSSL=false",dbUser,dbPass);
        } catch (
                SQLException e) {
            System.out.println("Connection Failed!!!!!");
            e.printStackTrace();
            //return null;
        }
        if(connection != null){
            logger.info("Success fully connected to MYSQL database");
            //return connection;
        }else{
            logger.info("Connection failed!!!");
            //return null;
        }

    }
}