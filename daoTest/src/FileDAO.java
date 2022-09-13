

import java.util.List;
public interface FileDAO {
    public FileClass getFileById(int fileId);
    public List<FileClass> getFileList();
    public int insertFile(FileClass file);
    public boolean updateFile(FileClass file);
    public boolean deleteFile(int fileId);

}
