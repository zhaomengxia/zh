package com.zh.dto.user;
/**
*@Author: Zhao MengXia
*@Date:  2018/1/26
*/
public class GetSpecialistNameDTO {
    private String ID;
    private String QX_NAME;

    public String getID() {

        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getQX_NAME() {
        return QX_NAME;
    }

    public void setQX_NAME(String QX_NAME) {
        this.QX_NAME = QX_NAME;
    }

    @Override
    public String toString() {
        return "GetSpecialistNameDTO{" +
                "ID='" + ID + '\'' +
                ", QX_NAME='" + QX_NAME + '\'' +
                '}';
    }
}
