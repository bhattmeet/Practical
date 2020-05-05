package com.meet.practical.model.entity.request;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class CourseQuizResultRequest {

    int course_id;
    List<QuizResultInfo> quiz_result;
    String taken_time;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public List<QuizResultInfo> getQuiz_result() {
        return quiz_result;
    }

    public void setQuiz_result(List<QuizResultInfo> quiz_result) {
        this.quiz_result = quiz_result;
    }

    public String getTaken_time() {
        return taken_time;
    }

    public void setTaken_time(String taken_time) {
        this.taken_time = taken_time;
    }
}
