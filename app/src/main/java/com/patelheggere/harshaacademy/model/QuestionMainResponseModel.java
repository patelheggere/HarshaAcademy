package com.patelheggere.harshaacademy.model;

import java.util.List;

public class QuestionMainResponseModel {
    String testID;
    long startDateTime, endDateTime;
    long duration;
    List<MCQQuestionModel> mcqQuestionModelList;

    public QuestionMainResponseModel() {
    }

    public QuestionMainResponseModel(String testID, long startDateTime, long endDateTime, long duration, List<MCQQuestionModel> mcqQuestionModelList) {
        this.testID = testID;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.duration = duration;
        this.mcqQuestionModelList = mcqQuestionModelList;
    }

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public long getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(long startDateTime) {
        this.startDateTime = startDateTime;
    }

    public long getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(long endDateTime) {
        this.endDateTime = endDateTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<MCQQuestionModel> getMcqQuestionModelList() {
        return mcqQuestionModelList;
    }

    public void setMcqQuestionModelList(List<MCQQuestionModel> mcqQuestionModelList) {
        this.mcqQuestionModelList = mcqQuestionModelList;
    }
}
