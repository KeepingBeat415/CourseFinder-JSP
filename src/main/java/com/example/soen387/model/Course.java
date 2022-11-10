package com.example.soen387.model;

public class Course {
    private int id;
    private String code;
    private String title;
    private String semester;
    private String days;
    private String time;
    private String instructor;
    private String room;
    private String start_date;
    private String end_date;

    // Search Student's Enrolled Course Info
    public Course(String code, String title, String semester, String days, String time, String instructor, String room){
        this.code = code;
        this.title = title;
        this.semester = semester;
        this.days = days;
        this.time = time;
        this.instructor = instructor;
        this.room = room;

        String year = semester.substring(semester.length() - 4);
        if (semester.contains("Fall")) {
            setStart_date(year + "-09-05");
            setEnd_date(year + "-12-22");
        } else if (semester.contains("Winter")) {
            setStart_date(year + "-01-08");
            setEnd_date(year + "-04-30");
        }
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getSemester() {
        return semester;
    }

    public String getDays() {
        return days;
    }

    public String getTime() {
        return time;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getRoom() {
        return room;
    }

    public String getTitle() {
        return title;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
