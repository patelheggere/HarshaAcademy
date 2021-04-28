package com.patelheggere.harshaacademy.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

public class MCQQuestionModel implements Parcelable {
    private String title;
    private List<String> optionsList;
    int rightAnswerIndex;
    int index;
    int sl_no;
    String options;
    long timeTaken;
    int selectedOptionIndex = -1;
    String mQuestionURL;
    String TestID, UserID;

    public MCQQuestionModel() {
    }


    protected MCQQuestionModel(Parcel in) {
        title = in.readString();
        optionsList = in.createStringArrayList();
        rightAnswerIndex = in.readInt();
        index = in.readInt();
        options = in.readString();
        timeTaken = in.readLong();
        selectedOptionIndex = in.readInt();
        mQuestionURL = in.readString();
        TestID = in.readString();
        UserID = in.readString();
        sl_no = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeStringList(optionsList);
        dest.writeInt(rightAnswerIndex);
        dest.writeInt(index);
        dest.writeString(options);
        dest.writeLong(timeTaken);
        dest.writeInt(selectedOptionIndex);
        dest.writeString(mQuestionURL);
        dest.writeString(TestID);
        dest.writeString(UserID);
        dest.writeInt(sl_no);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public List<String> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<String> optionsList) {
        this.optionsList = optionsList;
    }

    public int getRightAnswerIndex() {
        return rightAnswerIndex;
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public int getSelectedOptionIndex() {
        return selectedOptionIndex;
    }

    public void setSelectedOptionIndex(int selectedOptionIndex) {
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public String getmQuestionURL() {
        return mQuestionURL;
    }

    public void setmQuestionURL(String mQuestionURL) {
        this.mQuestionURL = mQuestionURL;
    }

    public String getTestID() {
        return TestID;
    }

    public void setTestID(String testID) {
        TestID = testID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public int getSl_no() {
        return sl_no;
    }

    public void setSl_no(int sl_no) {
        this.sl_no = sl_no;
    }

    public static Creator<MCQQuestionModel> getCREATOR() {
        return CREATOR;
    }
}
