package com.example.quanlygiatsay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiatsay.R;
import com.example.quanlygiatsay.dao.OrderDAO;
import com.example.quanlygiatsay.model.OrderInner;
import com.example.quanlygiatsay.model.OrderOuter;

import java.util.ArrayList;

public class ComfirmAdapter extends RecyclerView.Adapter<ComfirmAdapter.ViewHolder> {
    private ArrayList<OrderOuter> orderOuters;
    private Context context;
    private OrderDAO orderDAO;

    public ComfirmAdapter(ArrayList<OrderOuter> orderOuters, Context context) {
        this.orderOuters = orderOuters;
        this.context = context;
        orderDAO = new OrderDAO(context);
    }

    @NonNull
    @Override
    public ComfirmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comfirm_outer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComfirmAdapter.ViewHolder holder, int position) {
        OrderOuter orderOuter = orderOuters.get(position);
        holder.id.setText(orderOuter.getIddonhang());
        holder.date.setText(orderOuter.getDate());
        holder.totalprice.setText(orderOuter.getTotalprice());
        setData(orderOuter.getIddonhang() , holder.rcv);
        holder.btncomfirm.setOnClickListener(v -> {
            orderDAO.changComfirm(orderOuter.getIddonhang(), new OrderDAO.OrderDAOITF() {
                @Override
                public void xuli(Object obj) {
                    holder.status.setVisibility(View.VISIBLE);
                    holder.status.setText("Đã xác nhận");
                    holder.lnlo.setVisibility(View.GONE);
                }
            });

        });
        holder.btncancel.setOnClickListener(v -> {
            orderDAO.changCancel(orderOuter.getIddonhang(), new OrderDAO.OrderDAOITF() {
                @Override
                public void xuli(Object obj) {
                    holder.lnlo.setVisibility(View.GONE);

                    holder.status.setVisibility(View.VISIBLE);
                    holder.status.setText("Đã hủy");
                }
            });

        });
    }

    @Override
    public int getItemCount() {
        return orderOuters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout lnlo;
        private TextView id, date, totalprice, status;
        private Button btncancel, btncomfirm;
        private RecyclerView rcv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lnlo = itemView.findViewById(R.id.item_comfirm_outer_lnlo);
            id = itemView.findViewById(R.id.item_comfirm_outer_id);
            date = itemView.findViewById(R.id.item_comfirm_outer_date);
            totalprice = itemView.findViewById(R.id.item_comfirm_outer_totalprice);
            status = itemView.findViewById(R.id.item_comfirm_outer_txtstatus);
            btncancel = itemView.findViewById(R.id.item_comfirm_outer_cancel);
            btncomfirm = itemView.findViewById(R.id.item_comfirm_outer_comfirm);
            rcv = itemView.findViewById(R.id.item_comfirm_outer_rcv);
        }
    }

    private void setData(String iddonhang, RecyclerView rcv){
        orderDAO.getDonHangInner(iddonhang, new OrderDAO.OrderDAOITF() {
            @Override
            public void xuli(Object obj) {
                LinearLayoutManager l = new LinearLayoutManager(context);
                l.setOrientation(LinearLayoutManager.VERTICAL);
                rcv.setLayoutManager(l);
                rcv.setAdapter(new OrderInnerAdapter((ArrayList<OrderInner>) obj, context));
            }
        });
    }
}
