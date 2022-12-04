package com.example.soen387.model;

import com.example.soen387.dao.CourseDao;
import com.example.soen387.dao.PersonDao;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

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

    private static final ReentrantLock create_course_lock = new ReentrantLock();
    private static final CourseDao courseDao = new CourseDao();


    public Course(){
        this.id = -1;
        this.code = null;
        this.title = null;
        this.semester = null;
        this.days = null;
        this.time = null;
        this.instructor = null;
        this.room = null;
        this.start_date = null;
        this.end_date = null;
    }

    public Course(String code, String title, String semester, String days, String time, String instructor, String room, String start_date, String end_date) {

        this.code = code;
        this.title = title;
        this.semester = semester;
        this.days = days;
        this.time = time;
        this.instructor = instructor;
        this.room = room;
        this.start_date = start_date;
        this.end_date = end_date;
    }


    //For add and delete purpose
    public Course(int id, String code, String title, String semester, String days, String time, String instructor, String room) {
        this.id = id;
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

    public boolean createCourse(){
        create_course_lock.lock();
        try {
            // Insert Course data into the database
            return courseDao.createCourse(this);
        }
        finally {
            create_course_lock.unlock();
        }
    }

    public boolean updateCourse() {
        create_course_lock.lock();
        try {
            // Update Course data
            return courseDao.updateCourse(this);
        } finally {
            create_course_lock.unlock();
        }
    }

    public ArrayList<Course> searchCourseByCode(String code){
        return courseDao.searchCourseByCode(code);
    }

    public boolean isCourseExisted(String code){
        return courseDao.isCourseExisted(code);
    }
}
