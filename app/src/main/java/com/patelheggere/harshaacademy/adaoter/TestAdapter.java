package com.patelheggere.harshaacademy.adaoter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.patelheggere.harshaacademy.R;
import com.patelheggere.harshaacademy.activity.QuestionStartActivity;
import com.patelheggere.harshaacademy.model.MCQQuestionModel;
import com.patelheggere.harshaacademy.model.QuestionMainResponseModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder>{
    private List<QuestionMainResponseModel> listdata;
    private Context mContext;

    // RecyclerView recyclerView;
    public TestAdapter(Context mContext ,List<QuestionMainResponseModel> listdata) {
        this.listdata = listdata;
        this.mContext = mContext;
        for (int i = 0; i < listdata.size(); i++)
        {
            List<MCQQuestionModel> lis = this.listdata.get(i).getMcqQuestionModelList();
            for(int j=0;j<lis.size();j++){
                String[] strarray = lis.get(j).getOptions().split(",");
                this.listdata.get(i).getMcqQuestionModelList().get(j).setOptionsList(Arrays.asList(strarray));
                this.listdata.get(i).getMcqQuestionModelList().get(j).setIndex(this.listdata.get(i).getMcqQuestionModelList().get(j).getSl_no()-1);

            }
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final QuestionMainResponseModel myListData = listdata.get(position);
        holder.textView.setText(myListData.getTestTitle());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, QuestionStartActivity.class);
                intent.putExtra("QDATA", myListData);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
