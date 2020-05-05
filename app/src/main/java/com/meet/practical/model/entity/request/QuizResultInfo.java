package com.meet.practical.model.entity.request;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class QuizResultInfo {
    int question_id;
    List<Integer> answers;

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
