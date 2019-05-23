package com.lambdaschool.health.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@Table(name = "userinfo")
public class UserInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;
    private String username;
    private String fname;
    private String lname;

    private double weight;
    private String gender;
    private int age;
    private int height;
    private int weeklyworkoutdays;
    private String egoal;
    private int mealsaday;



    public UserInfo() {
    }

    public UserInfo(String username, String fname, String lname, double weight, String gender, int age, int height, int weeklyworkoutdays, String egoal, int mealsaday) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.weight = weight;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weeklyworkoutdays = weeklyworkoutdays;
        this.egoal = egoal;
        this.mealsaday = mealsaday;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeeklyworkoutdays() {
        return weeklyworkoutdays;
    }

    public void setWeeklyworkoutdays(int weeklyworkoutdays) {
        this.weeklyworkoutdays = weeklyworkoutdays;
    }

    public String getEgoal() {
        return egoal;
    }

    public void setEgoal(String egoal) {
        this.egoal = egoal;
    }

    public int getMealsaday() {
        return mealsaday;
    }

    public void setMealsaday(int mealsaday) {
        this.mealsaday = mealsaday;
    }
}
