public class DirectoryClass {
    private int dirId;
    private String dirName;
    private double dirSize;
    private int numberOfFiles;
    private String dirPath;

    //region CONSTRUCTORS
    //default constructor
    public DirectoryClass(){

    }
    public DirectoryClass(String dirName, double dirSize, int numberOfFiles, String dirPath){
        this.dirName = dirName;
        this.dirSize = dirSize;
        this.numberOfFiles = numberOfFiles;
        this.dirPath = dirPath;
    }
    //endregion

    //region GETTERS AND SETTERS

    public int getDirId() {
        return dirId;
    }

    public void setDirId(int dirId) {
        this.dirId = dirId;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public double getDirSize() {
        return dirSize;
    }

    public void setDirSize(double dirSize) {
        this.dirSize = dirSize;
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    //endregion

    //region OTHER METHODS

    @Override
    public String toString() {
        return "Directory{" +
                "dirName='" + dirName + '\'' +
                ", dirSize=" + dirSize +
                ", numberOfFiles=" + numberOfFiles +
                ", path='" + dirPath + '\'' +
                '}';
    }

    //endregion

}
