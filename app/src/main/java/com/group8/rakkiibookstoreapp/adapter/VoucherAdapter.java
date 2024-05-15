package com.group8.rakkiibookstoreapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group8.rakkiibookstoreapp.R;
import com.group8.rakkiibookstoreapp.model.Voucher;

import java.util.ArrayList;

public class VoucherAdapter extends BaseAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Voucher> vouchers;

    public VoucherAdapter(Context context, int layoutResourceId, ArrayList<Voucher> vouchers) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.vouchers = vouchers;
    }

    @Override
    public int getCount() {
        return vouchers.size();
    }

    @Override
    public Object getItem(int position) {
        return vouchers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        ImageView VoucherPhoto = convertView.findViewById(R.id.imvVoucher);
        VoucherPhoto.setImageResource(vouchers.get(position).getVoucherPhoto());
        TextView VoucherCategory = convertView.findViewById(R.id.txtCategoryVoucher);
        VoucherCategory.setText(vouchers.get(position).getVoucherCategory());
        TextView VoucherTitle = convertView.findViewById(R.id.txtTitleVoucher);
        VoucherTitle.setText(vouchers.get(position).getVoucherTitle());
        TextView VoucherCondition = convertView.findViewById(R.id.txtConditionVoucher);
        VoucherCondition.setText(vouchers.get(position).getVoucherCondition());
        TextView VoucherTime = convertView.findViewById(R.id.txtTimeVoucher);
        VoucherTime.setText(vouchers.get(position).getVoucherTime());

        return convertView;
    }
}
