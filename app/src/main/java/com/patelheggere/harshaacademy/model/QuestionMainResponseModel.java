package com.patelheggere.harshaacademy.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class QuestionMainResponseModel implements Parcelable {
    private String testID;
    long startDateTime, endDateTime;
    long duration;
    String testTitle;
    List<MCQQuestionModel> mcqQuestionModelList;

    public QuestionMainResponseModel() {
    }

    public QuestionMainResponseModel(String testTitle, String testID, long startDateTime, long endDateTime, long duration, List<MCQQuestionModel> mcqQuestionModelList) {
        this.testID = testID;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.duration = duration;
        this.mcqQuestionModelList = mcqQuestionModelList;
        this.testTitle = testTitle;
    }

    protected QuestionMainResponseModel(Parcel in) {
        testID = in.readString();
        startDateTime = in.readLong();
        endDateTime = in.readLong();
        duration = in.readLong();
        testTitle = in.readString();
        mcqQuestionModelList = in.createTypedArrayList(MCQQuestionModel.CREATOR);
    }

    public static final Creator<QuestionMainResponseModel> CREATOR = new Creator<QuestionMainResponseModel>() {
        @Override
        public QuestionMainResponseModel createFromParcel(Parcel in) {
            return new QuestionMainResponseModel(in);
        }

        @Override
        public QuestionMainResponseModel[] newArray(int size) {
            return new QuestionMainResponseModel[size];
        }
    };

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

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(testID);
        dest.writeLong(startDateTime);
        dest.writeLong(endDateTime);
        dest.writeLong(duration);
        dest.writeString(testTitle);
        dest.writeTypedList(mcqQuestionModelList);
    }
}
