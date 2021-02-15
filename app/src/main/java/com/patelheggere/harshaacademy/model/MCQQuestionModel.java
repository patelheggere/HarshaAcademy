package com.patelheggere.harshaacademy.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MCQQuestionModel implements Parcelable {
    private String title;
    private List<String> options;
    int rightAnswerIndex;
    int index;
    int selectedOptionIndex = -1;
    String mQuestionURL;

    public MCQQuestionModel() {
    }

    public MCQQuestionModel(int index, String title, String mQuestionURL, List<String> options, int rightAnswerIndex, int selectedOptionIndex) {
        this.title = title;
        this.options = options;
        this.rightAnswerIndex = rightAnswerIndex;
        this.selectedOptionIndex = selectedOptionIndex;
        this.index = index;
        this.mQuestionURL = mQuestionURL;
    }

    protected MCQQuestionModel(Parcel in) {
        title = in.readString();
        options = in.createStringArrayList();
        rightAnswerIndex = in.readInt();
        selectedOptionIndex = in.readInt();
        index = in.readInt();
        mQuestionURL = in.readString();
    }

    public static final Creator<MCQQuestionModel> CREATOR = new Creator<MCQQuestionModel>() {
        @Override
        public MCQQuestionModel createFromParcel(Parcel in) {
            return new MCQQuestionModel(in);
        }

        @Override
        public MCQQuestionModel[] newArray(int size) {
            return new MCQQuestionModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getRightAnswerIndex() {
        return rightAnswerIndex;
    }

    public int getSelectedOptionIndex() {
        return selectedOptionIndex;
    }

    public void setSelectedOptionIndex(int selectedOptionIndex) {
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public void setRightAnswerIndex(int rightAnswerIndex) {
        this.rightAnswerIndex = rightAnswerIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getmQuestionURL() {
        return mQuestionURL;
    }

    public void setmQuestionURL(String mQuestionURL) {
        this.mQuestionURL = mQuestionURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeStringList(options);
        dest.writeInt(rightAnswerIndex);
        dest.writeInt(selectedOptionIndex);
        dest.writeInt(index);
        dest.writeString(mQuestionURL);
    }
}
