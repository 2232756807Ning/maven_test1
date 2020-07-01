package com.nwq.entity;

/**
 * @Author: nwq
 * @Description:
 * @Date: 2020/7/1 15:48
 * @Version: 1.0
 */
public class Meeting extends BaseEntity{
    //部门名称
    private String deptName;

    private Integer id;
    private Integer deptId;
    private String title;
    private String content;
    private String publishDate;
    private String startTime;
    private String endTime;
    //会议状态 0:未开始 1:进行中 2:已结束
    private Integer status;
    private String makeUser;
    private String[] makeUsers;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMakeUser() {
        return makeUser;
    }

    public void setMakeUser(String makeUser) {
        this.makeUser = makeUser;
    }

    public String[] getMakeUsers() {
        return makeUsers;
    }

    public void setMakeUsers(String[] makeUsers) {
        this.makeUsers = makeUsers;
    }
}
