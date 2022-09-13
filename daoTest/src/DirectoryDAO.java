import java.util.List;

public interface DirectoryDAO {
    public DirectoryClass getDirectoryById(int dirId);
    public List<DirectoryClass> getDirectoryList();
    public int insertDirectory(DirectoryClass directory);
    public boolean updateDirectory(DirectoryClass directory);
    public boolean deleteDirectory(int dirId);
}
