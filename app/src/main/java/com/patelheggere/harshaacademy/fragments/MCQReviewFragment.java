package com.patelheggere.harshaacademy.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.patelheggere.harshaacademy.R;
import com.patelheggere.harshaacademy.model.MCQQuestionModel;

public class MCQReviewFragment extends Fragment {
    private static final String TAG = "MCQFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View mView;
    private RadioGroup rg;
    private MCQQuestionModel data;
    private TextView mTextViewQuestion;
    private updateCheckedAnswer2 mListener;
    private ImageView questionImageView;
    public MCQReviewFragment() {
        // Required empty public constructor
    }

    public static MCQReviewFragment newInstance(String param1, String param2) {
        MCQReviewFragment fragment = new MCQReviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Bundle bundle = getArguments();
            data = bundle.getParcelable("Data");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_m_c_q, container, false);
        mListener = ((updateCheckedAnswer2)getActivity());
        initView();
        initListener();
        initData();
        return mView;
    }

    private void initData() {
        if(data.getSelectedOptionIndex()!=-1)
        {
            rg.check(100+data.getSelectedOptionIndex());
        }
        if(data.getmQuestionURL()==null)
        {
            questionImageView.setVisibility(View.GONE);
        }
    }

    private void initView() {
        rg = mView.findViewById(R.id.rg);
        questionImageView = mView.findViewById(R.id.imageView);
        mTextViewQuestion = mView.findViewById(R.id.questiontitle);
    }

    public void clear(){
        rg.clearCheck();
    }
    private void initListener() {
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for(int i=0; i<data.getOptions().size(); i++){
            RadioButton rb  = new RadioButton(getContext());
            rb.setText(data.getOptions().get(i));
            rb.setId(i + 100);
            rg.addView(rb);
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(TAG, "onCheckedChanged: "+checkedId);
                if(data.getRightAnswerIndex()==checkedId-100)
                {
                    Log.d(TAG, "onCheckedChanged: "+true);
                }
                else {
                    Log.d(TAG, "onCheckedChanged: "+false);
                }
                data.setSelectedOptionIndex(checkedId-100);
                if(mListener!=null)
                {
                  //  mListener.click(data);
                }
            }
        });
        rg.setClickable(false);
        mTextViewQuestion.setText(data.getTitle());
    }

    public interface updateCheckedAnswer2 {
        void click(MCQQuestionModel mcqQuestionModel);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}