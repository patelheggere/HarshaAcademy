package com.patelheggere.harshaacademy.activity.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.patelheggere.harshaacademy.R;
import com.skyhope.eventcalenderlibrary.CalenderEvent;
import com.skyhope.eventcalenderlibrary.model.Event;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private View mView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         mView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        CalenderEvent calenderEvent = mView.findViewById(R.id.calender_event);
        Event event = new Event(System.currentTimeMillis(), "Test");
// to set desire day time milli second in first parameter
//or you set color for each event
        Event event2 = new Event(System.currentTimeMillis(), "Test", Color.RED);
        calenderEvent.addEvent(event2);
        return mView;
    }
}