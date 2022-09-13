import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDAOImpl extends MySQL implements FileDAO{

    @Override
    public FileClass getFileById(int fileId) {
        Connect();
        FileClass file = null; //if no records returned we dont want to use mem
        try {
            String sp = "{call sp_GetFile(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,GET_BY_ID);
            cStmt.setInt(2, fileId);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()){
                file = hydrateObject(rs);
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public List<FileClass> getFileList() {
        Connect();
        List<FileClass> fileList = new ArrayList<FileClass>(); //if no records returned we dont want to use mem
        try {
            String sp = "{call sp_GetFile(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,GET_COLLECTION);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()){
                fileList.add(hydrateObject(rs));
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    @Override
    public int insertFile(FileClass file) {

        Connect();
        int id = 0;
        //
        //-- call sp_ExecDirectoryTable(10,1,"testDirecto",2.3,32,"./")
        //queryId, dirId, dirName, dirSize, numberOfFiles, dirPAth
        String sp = "{call sp_ExecFileTable(?,?,?,?,?,?,?)}";
        try {
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3,file.getFileName());
            cStmt.setString(4, file.getFileType());
            cStmt.setDouble(5, file.getFileSize());
            cStmt.setString(6,file.getFilePath());
            cStmt.setInt(7, file.getDirId());
            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean updateFile(FileClass file) {
        Connect();
        int id = 0;
        //
        //-- call sp_ExecDirectoryTable(10,1,"testDirecto",2.3,32,"./")
        //queryId, dirId, dirName, dirSize, numberOfFiles, dirPAth
        String sp = "{call sp_ExecFileTable(?,?,?,?,?,?)}";
        try {
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2, file.getFileId());
            cStmt.setString(3,file.getFileName());
            cStmt.setString(4, file.getFileType());
            cStmt.setDouble(5, file.getFileSize());
            cStmt.setString(6,file.getFilePath());
            cStmt.setInt(7, file.getDirId());
            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id > 0;
    }

    @Override
    public boolean deleteFile(int fileId) {
        Connect();
        int id = 0;
        //
        //-- call sp_ExecDirectoryTable(10,1,"testDirecto",2.3,32,"./")
        //queryId, dirId, dirName, dirSize, numberOfFiles, dirPAth
        String sp = "{call sp_ExecFileTable(?,?,?,?,?,?)}";
        try {
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, fileId);
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
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id > 0;
    }
    private static FileClass hydrateObject(ResultSet rs) throws SQLException{
        /*
        fileId - int 1
        fileName    - varchar(100)  - 2
        fileType    - varchar(50)   - 3
        fileSize    - double        - 4
        filePath    - varchar(500)  - 5
        dirId       - int           - 6
         */
        FileClass file = new FileClass();
        file.setFileId(rs.getInt(1));
        file.setFileName(rs.getString(2));
        file.setFileType((rs.getString(3)));
        file.setFileSize(rs.getDouble(4));
        file.setFilePath(rs.getString(5));
        file.setDirId(rs.getInt(6));


        return file;
    }
}
