
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryDAOImpl extends MySQL implements DirectoryDAO {

    @Override
    public Directory getDirectoryById(int dirId) {
        Connect();
        Directory directory = null; //if no records returned we dont want to use mem
        try {
            String sp = "{call sp_GetDirectory(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,GET_BY_ID);
            cStmt.setInt(2, dirId);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()){
                directory = hydrateObject(rs);
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return directory;
    }

    @Override
    public List<Directory> getDirectoryList() {
        Connect();
        List<Directory> directoryList = new ArrayList<Directory>(); //if no records returned we dont want to use mem
        try {
            String sp = "{call sp_GetDirectory(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,GET_COLLECTION);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()){
                directoryList.add(hydrateObject(rs));
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return directoryList;
    }

    @Override
    public int insertDirectory(Directory directory) {
        Connect();
        int id = 0;
        //
        //-- call sp_ExecDirectoryTable(10,1,"testDirecto",2.3,32,"./")
        //queryId, dirId, dirName, dirSize, numberOfFiles, dirPAth
        String sp = "{call sp_ExecDirectoryTable(?,?,?,?,?,?)}";
        try {
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3,directory.getDirName());
            cStmt.setDouble(4, directory.getDirSize());
            cStmt.setInt(5, directory.getNumberOfFiles());
            cStmt.setString(6,directory.getDirPath());
            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean updateDirectory(Directory directory) {
        Connect();
        int id = 0;
        //
        //-- call sp_ExecDirectoryTable(10,1,"testDirecto",2.3,32,"./")
        //queryId, dirId, dirName, dirSize, numberOfFiles, dirPAth
        String sp = "{call sp_ExecDirectoryTable(?,?,?,?,?,?)}";
        try {
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2, directory.getDirId());
            cStmt.setString(3,directory.getDirName());
            cStmt.setDouble(4, directory.getDirSize());
            cStmt.setInt(5, directory.getNumberOfFiles());
            cStmt.setString(6,directory.getDirPath());
            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return id > 0;
    }

    @Override
    public boolean deleteDirectory(int dirId) {
        Connect();
        int id = 0;
        //
        //-- call sp_ExecDirectoryTable(10,1,"testDirecto",2.3,32,"./")
        //queryId, dirId, dirName, dirSize, numberOfFiles, dirPAth
        String sp = "{call sp_ExecDirectoryTable(?,?,?,?,?,?)}";
        try {
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, dirId);
            cStmt.setString(3,"");
            cStmt.setDouble(4, 0.0);
            cStmt.setInt(5, 0);
            cStmt.setString(6,"directory.getDirPath()");
            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return id > 0;
    }
    private static Directory hydrateObject(ResultSet rs) throws SQLException{
        /*
        dirId - int 1
        dirName - varchar(100) - 2
        dirSize - double - 3
        numberOfFiles - int 4
        dirPath - varchar(500)
         */
        Directory directory = new Directory();
        directory.setDirId(rs.getInt(1));
        directory.setDirName(rs.getString(2));
        directory.setDirSize(rs.getDouble(3));
        directory.setNumberOfFiles(rs.getInt(4));
        directory.setDirPath(rs.getString(5));


        return directory;
    }
}
