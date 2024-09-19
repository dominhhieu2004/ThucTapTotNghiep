package com.example.thuctapimtech.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.thuctapimtech.Adapter.TopAdapter;
import com.example.thuctapimtech.DAO.ThongKeDao;
import com.example.thuctapimtech.Model.Top;
import com.example.thuctapimtech.R;

import java.util.ArrayList;


public class Top10Fragment extends Fragment {





    TopAdapter adapter;
    RecyclerView rcv_top;
    ThongKeDao thongKeDao;
    ArrayList<Top> lstTop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top10, container, false);
        rcv_top = v.findViewById(R.id.rcv_top10);
        thongKeDao = new ThongKeDao(getContext());

        lstTop =(ArrayList<Top>) thongKeDao.getTop();
        adapter = new TopAdapter(getContext(), lstTop);
        rcv_top.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rcv_top.setAdapter(adapter);
        return v;
    }

    public static Top10Fragment newInstance(){
        Top10Fragment fragment = new Top10Fragment();
        return fragment;
    }

}