package com.patelheggere.harshaacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.patelheggere.harshaacademy.model.MCQQuestionModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionStartActivity extends AppCompatActivity implements MCQFragment.updateCheckedAnswer{
    private static final String TAG = "QuestionStartActivity";

    private Button buttonPrev, buttonNext, buttonClear;
    private TextView mTextViewQuestionNumber;
    List<MCQQuestionModel> mQuestionList = new ArrayList<>();
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
        handlingFragment(0);
    }

    private void initData() {
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
    }

    private void initView() {
        buttonNext = findViewById(R.id.nextButton);
        buttonPrev = findViewById(R.id.nextprevious);
        buttonClear = findViewById(R.id.clear);
        mTextViewQuestionNumber = findViewById(R.id.questionNumber);
    }

    private void initListener() {
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0)
                {
                    count--;
                    handlingFragment(count);
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<mQuestionList.size()-1){
                    count++;
                    handlingFragment(count);
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void handlingFragment(int questionIndex)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = new MCQFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Data",mQuestionList.get(questionIndex));
        fragment.setArguments(bundle);
        ft.replace(R.id.detailscontainer, fragment, null);
      //  ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void click(MCQQuestionModel mcqQuestionModel) {
        mQuestionList.remove(mcqQuestionModel.getIndex());
        mQuestionList.add(mcqQuestionModel.getIndex(), mcqQuestionModel);
    }
}