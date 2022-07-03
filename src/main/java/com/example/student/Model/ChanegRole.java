package com.example.student.Model;

public class ChanegRole {

    private long app_user_id;

    private String app_user_name;

    private String role_name;

    public long getApp_user_id() {
        return app_user_id;
    }

    public void setApp_user_id(long app_user_id) {
        this.app_user_id = app_user_id;
    }

    public String getApp_user_name() {
        return app_user_name;
    }

    public void setApp_user_name(String app_user_name) {
        this.app_user_name = app_user_name;
    }

    public ChanegRole(long app_user_id, String app_user_name, String role_name) {
        this.app_user_id = app_user_id;
        this.app_user_name = app_user_name;
        this.role_name = role_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "ChanegRole{" +
                "app_user_id=" + app_user_id +
                ", app_user_name='" + app_user_name + '\'' +
                ", role_name='" + role_name + '\'' +
                '}';
    }
}
