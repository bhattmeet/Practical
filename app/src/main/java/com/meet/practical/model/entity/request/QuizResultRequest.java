package com.meet.practical.model.entity.request;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class QuizResultRequest {

    int video_id;
    List<QuizResultInfo> quiz_result;
    String taken_time;

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
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
