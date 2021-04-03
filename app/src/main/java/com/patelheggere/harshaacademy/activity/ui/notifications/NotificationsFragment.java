package com.patelheggere.harshaacademy.activity.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.patelheggere.harshaacademy.R;
import com.patelheggere.harshaacademy.activity.QuestionStartActivity;
import com.patelheggere.harshaacademy.adaoter.TestAdapter;
import com.patelheggere.harshaacademy.model.MCQQuestionModel;
import com.patelheggere.harshaacademy.model.QuestionMainResponseModel;
import com.patelheggere.harshaacademy.network.ApiInterface;
import com.patelheggere.harshaacademy.network.RetrofitInstance;
import com.patelheggere.harshaacademy.utils.Constants;
import com.patelheggere.harshaacademy.utils.SharedPrefsHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {
    private static final String TAG = "NotificationsFragment";
    private View mView;
    private RecyclerView mTestRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         mView = inflater.inflate(R.layout.fragment_notifications, container, false);
         setUpNetwork();
         initViews();
         initData();
         initListener();
         getTestDataFromServer();
        return mView;
    }

    private void getTestDataFromServer() {
        Call<List<QuestionMainResponseModel>> questionMainResponseModel = apiInterface.GetQuestionData(SharedPrefsHelper.getInstance().get(Constants.USER_NAME));
        questionMainResponseModel.enqueue(new Callback<List<QuestionMainResponseModel>>() {
            @Override
            public void onResponse(Call<List<QuestionMainResponseModel>> call, Response<List<QuestionMainResponseModel>> response) {
                if(response.isSuccessful() && response.body().size()>0)
                {
                    TestAdapter testAdapter = new TestAdapter(getContext(), response.body());
                    mTestRecyclerView.setAdapter(testAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<QuestionMainResponseModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });

        /*List<MCQQuestionModel> mQuestionList = new ArrayList<>();


        MCQQuestionModel mcqQuestionModel = new MCQQuestionModel();
        mcqQuestionModel.setTitle("First");
        List<String> options = new ArrayList<>();
        options.add("Option1");
        mcqQuestionModel.setOptions(options);
        mcqQuestionModel.setIndex(0);
        mcqQuestionModel.setRightAnswerIndex(0);
        mQuestionList.add(mcqQuestionModel);

        MCQQuestionModel mcqQuestionModel2 = new MCQQuestionModel();
        mcqQuestionModel2.setTitle("Two");
        List<String> options2 = new ArrayList<>();
        options2.add("Option1");
        options2.add("Option2");

        mcqQuestionModel2.setOptions(options2);
        mcqQuestionModel2.setIndex(1);
        mcqQuestionModel2.setRightAnswerIndex(1);
        mQuestionList.add(mcqQuestionModel2);

        MCQQuestionModel mcqQuestionModel3 = new MCQQuestionModel();
        mcqQuestionModel3.setTitle("Three");
        List<String> options3 = new ArrayList<>();
        options3.add("Option1");
        options3.add("Option2");
        options3.add("Option3");

        mcqQuestionModel3.setOptions(options3);
        mcqQuestionModel3.setIndex(2);
        mcqQuestionModel3.setRightAnswerIndex(2);
        mQuestionList.add(mcqQuestionModel3);

        MCQQuestionModel mcqQuestionModel4 = new MCQQuestionModel();
        mcqQuestionModel4.setTitle("Four");
        List<String> options4 = new ArrayList<>();
        options4.add("Option1");
        options4.add("Option2");
        options4.add("Option3");
        options4.add("Option4");
        mcqQuestionModel4.setIndex(3);
        mcqQuestionModel4.setOptions(options4);
        mcqQuestionModel4.setRightAnswerIndex(3);
        mQuestionList.add(mcqQuestionModel4);


        QuestionMainResponseModel questionMainResponseModel1 = new QuestionMainResponseModel();
        questionMainResponseModel1.setTestTitle("Test 1");
        questionMainResponseModel1.setDuration(60000);
        questionMainResponseModel1.setEndDateTime(11110000);
        questionMainResponseModel1.setMcqQuestionModelList(mQuestionList);

        QuestionMainResponseModel questionMainResponseModel2 = new QuestionMainResponseModel();
        questionMainResponseModel2.setTestTitle("Test 2");
        questionMainResponseModel2.setDuration(120000);
        questionMainResponseModel2.setEndDateTime(11110000);
        questionMainResponseModel2.setMcqQuestionModelList(mQuestionList);

        List<QuestionMainResponseModel> questionMainResponseModelList = new ArrayList<>();
        questionMainResponseModelList.add(questionMainResponseModel1);
        questionMainResponseModelList.add(questionMainResponseModel2);

        TestAdapter testAdapter = new TestAdapter(getContext(), questionMainResponseModelList);
        mTestRecyclerView.setAdapter(testAdapter);*/
    }

    private void initViews() {
        mTestRecyclerView = mView.findViewById(R.id.testsrecyclerview);
        mTestRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private ApiInterface apiInterface;

    private void setUpNetwork() {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
        apiInterface = retrofitInstance.getClient().create(ApiInterface.class);
    }

    private void initData() {

    }

    private void initListener() {

    }
}