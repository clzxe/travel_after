package com.travel.travel.entity;

public class S_file {
    private Integer fileId;

    private String filePath;

    private Integer fileScenicId;

    private Integer isDelete;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getFileScenicId() {
        return fileScenicId;
    }

    public void setFileScenicId(Integer fileScenicId) {
        this.fileScenicId = fileScenicId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}