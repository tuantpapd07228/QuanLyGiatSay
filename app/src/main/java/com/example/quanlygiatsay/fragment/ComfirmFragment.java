package com.example.quanlygiatsay.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanlygiatsay.R;
import com.example.quanlygiatsay.adapter.ComfirmAdapter;
import com.example.quanlygiatsay.dao.OrderDAO;
import com.example.quanlygiatsay.model.OrderOuter;

import java.util.ArrayList;


public class ComfirmFragment extends Fragment {
    private ComfirmAdapter comfirmAdapter;
    private OrderDAO orderDAO;
    private RecyclerView rcv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_comfirm, container, false);
        rcv= v.findViewById(R.id.comfirm_rcv);
        orderDAO = new OrderDAO(getContext());
        setData();





        return v;
    }

    private void setData(){
        orderDAO.getOrDerWaitComfirm(new OrderDAO.OrderDAOITF() {
            @Override
            public void xuli(Object obj) {
                LinearLayoutManager l =new LinearLayoutManager(getContext());
                l.setOrientation(LinearLayoutManager.VERTICAL);
                rcv.setLayoutManager(l);
                comfirmAdapter = new ComfirmAdapter((ArrayList<OrderOuter>) obj, getContext());
                rcv.setAdapter(comfirmAdapter);
            }
        });
    }
}