package com.patelheggere.harshaacademy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.patelheggere.harshaacademy.fragments.MCQReviewFragment;
import com.patelheggere.harshaacademy.fragments.MCQTestFragment;
import com.patelheggere.harshaacademy.R;
import com.patelheggere.harshaacademy.model.MCQQuestionModel;
import com.patelheggere.harshaacademy.model.QuestionMainResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuestionStartActivity extends AppCompatActivity implements MCQTestFragment.updateCheckedAnswer, MCQReviewFragment.updateCheckedAnswer2 {
    private static final String TAG = "QuestionStartActivity";

    private Button buttonPrev, buttonNext, buttonClear, btnTakeTest, btnReview;
    private TextView mTextViewQuestionNumber, mTextViewTimer;
    List<MCQQuestionModel> mQuestionList = new ArrayList<>();
    private LinearLayout mLinearLayoutInfo;
    private FrameLayout mFrameLayoutContainer;
    int count = 0;
    private boolean isTimeToTakeTest = true;
    private boolean isTestCompleted = false;
    private long timeAllotedforTest;
    private boolean isReviewMode;
    private QuestionMainResponseModel questionMainResponseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_start);
        initData();
        initView();
        buttonVisible(false);
        initListener();

    }
    CountDownTimer countDownTimer;

    private void initData() {
        questionMainResponseModel = getIntent().getParcelableExtra("QDATA");
        mQuestionList = questionMainResponseModel.getMcqQuestionModelList();
        timeAllotedforTest = questionMainResponseModel.getDuration();

        countDownTimer = new CountDownTimer(timeAllotedforTest, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                mTextViewTimer.setText("Time Remaining: "+hms);
            }

            @Override
            public void onFinish() {
                submitAnswers();
            }
        };
    }

    private void initView() {
        btnTakeTest = findViewById(R.id.btntaketest);
        btnReview = findViewById(R.id.btnreview);
        buttonNext = findViewById(R.id.nextButton);
        buttonPrev = findViewById(R.id.nextprevious);
        buttonClear = findViewById(R.id.clear);
        buttonClear.setVisibility(View.INVISIBLE);
        mTextViewQuestionNumber = findViewById(R.id.questionNumber);
        mLinearLayoutInfo = findViewById(R.id.linearlayout_info);
        mFrameLayoutContainer = findViewById(R.id.detailscontainer);
        mTextViewTimer = findViewById(R.id.timer);
    }

    private void initListener() {
        btnTakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(isTimeToTakeTest && !isTestCompleted)  {
                  count = 0;
                  mLinearLayoutInfo.setVisibility(View.GONE);
                  mFrameLayoutContainer.setVisibility(View.VISIBLE);
                  buttonVisible(true);
                  isReviewMode = false;
                  handlingFragment(0);
                  countDownTimer.start();
              }
            }
        });
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isReviewMode = true;
                count = 0;
                mLinearLayoutInfo.setVisibility(View.GONE);
                mFrameLayoutContainer.setVisibility(View.VISIBLE);
                mTextViewTimer.setVisibility(View.GONE);
                handlingReviewFragment(0);
                buttonVisible(true);
            }
        });
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0)
                {
                    count--;
                    if(!isReviewMode)
                    {
                        handlingFragment(count);
                    }
                    else {
                        handlingReviewFragment(count);
                    }
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonNext.getText().toString().equalsIgnoreCase("submit")){
                    if(!isReviewMode) {
                        submitAnswers();
                        buttonVisible(false);
                        countDownTimer.cancel();
                    }
                    else{
                        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.detailscontainer)).commit();
                        mLinearLayoutInfo.setVisibility(View.VISIBLE);
                        mFrameLayoutContainer.setVisibility(View.GONE);
                        buttonVisible(false);
                    }
                }
                else {
                    if (count < mQuestionList.size() - 1) {
                        count++;
                        if(!isReviewMode)
                        {
                            handlingFragment(count);
                        }
                        else {
                            handlingReviewFragment(count);
                        }
                    }
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void submitAnswers() {
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.detailscontainer)).commit();
        mLinearLayoutInfo.setVisibility(View.VISIBLE);
        mFrameLayoutContainer.setVisibility(View.GONE);
        buttonVisible(false);
        isTestCompleted = true;
    }

    private void buttonVisible(boolean val){
        if(val){
            buttonNext.setVisibility(View.VISIBLE);
            buttonPrev.setVisibility(View.VISIBLE);
            mTextViewTimer.setVisibility(View.VISIBLE);
            mTextViewQuestionNumber.setVisibility(View.VISIBLE);
        }
        else {
            buttonNext.setVisibility(View.GONE);
            buttonPrev.setVisibility(View.GONE);
            mTextViewTimer.setVisibility(View.GONE);
            mTextViewQuestionNumber.setVisibility(View.GONE);
        }
    }

    private void handlingFragment(int questionIndex)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = new MCQTestFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Data",mQuestionList.get(questionIndex));
        fragment.setArguments(bundle);
        ft.replace(R.id.detailscontainer, fragment, null);
      //  ft.addToBackStack(null);
        ft.commit();
        mTextViewQuestionNumber.setText("Question ("+ (questionIndex+1) +"/"+mQuestionList.size()+")");
        if(questionIndex==mQuestionList.size()-1){
            buttonNext.setText("Submit");
        }
        else{
            buttonNext.setText("Next");
        }
    }

    private void handlingReviewFragment(int questionIndex)
    {
        isReviewMode = true;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = new MCQReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Data",mQuestionList.get(questionIndex));
        fragment.setArguments(bundle);
        ft.replace(R.id.detailscontainer, fragment, null);
        //  ft.addToBackStack(null);
        ft.commit();
        mTextViewQuestionNumber.setText("Question ("+ (questionIndex+1) +"/"+mQuestionList.size()+")");
        if(questionIndex==mQuestionList.size()-1){
            buttonNext.setText("Submit");
        }
        else{
            buttonNext.setText("Next");
        }
    }


    @Override
    public void click(MCQQuestionModel mcqQuestionModel) {
        mQuestionList.remove(mcqQuestionModel.getIndex());
        mQuestionList.add(mcqQuestionModel.getIndex(), mcqQuestionModel);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}