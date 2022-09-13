package com.astontech.dao;
import java.util.List;

public interface DirectoryDAO {
    public Directory getDirectoryById(int dirId);
    public List<Directory> getDirectoryList();
    public int insertDirectory(Directory directory);
    public boolean updateDirectory(Directory directory);
    public boolean deleteDirectory(int dirId);
}
