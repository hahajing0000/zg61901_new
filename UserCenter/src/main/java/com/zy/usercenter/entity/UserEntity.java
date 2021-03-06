package com.zy.usercenter.entity;

/**
 * @ProjectName: NewsZG61901
 * @Package: com.zy.usercenter.entity
 * @ClassName: UserEntity
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/17 14:37
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/17 14:37
 * @UpdateRemark:
 * @Version: 1.0
 */
public class UserEntity {

    private int id;
    private String username;
    private String pwd;
    private String sex;
    private String birthday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
