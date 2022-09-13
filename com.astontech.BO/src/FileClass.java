public class FileClass {
    private int     fileId;
    private String  fileName;
    private String  fileType;
    private double  fileSize;
    private String filePath;
    private int     dirId;

    //region CONSTRUCTORS
    //default constructor
    public FileClass(){

    }
    //all attributes constructor
    public FileClass(String fileName, String fileType, double fileSize, String filePath, int dirId){
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.dirId = dirId;
    }
    //endregion

    //region GETTERS AND SETTERS

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getDirId() {
        return dirId;
    }

    public void setDirId(int dirId) {
        this.dirId = dirId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //endregion

    //region OTHER METHODS

    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                ", dirId=" + dirId +
                '}';
    }


    //endregion
}
