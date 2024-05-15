package com.group8.rakkiibookstoreapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.group8.rakkiibookstoreapp.databinding.ViewholderCartBinding;
import com.group8.rakkiibookstoreapp.helper.ChangeNumberItemsListener;
import com.group8.rakkiibookstoreapp.helper.ManagmentCart;
import com.group8.rakkiibookstoreapp.model.BookList;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {
    ArrayList<BookList> items;
    Context context;
    ViewholderCartBinding binding;
    ChangeNumberItemsListener changeNumberItemsListener;
    ManagmentCart managmentCart;
    ArrayList<BookList> selectedItems;

    public CartAdapter(ArrayList<BookList> items, ChangeNumberItemsListener changeNumberItemsListener) {
        this.items = items;
        this.changeNumberItemsListener = changeNumberItemsListener;
//        this.selectedItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        managmentCart = new ManagmentCart(context);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
        DecimalFormat decimalFormat = (DecimalFormat)nf;
        String displayPrice = decimalFormat.format(items.get(position).getPrice()) + "đ";
        String displayTotalPrice = decimalFormat.format(Math.round(items.get(position).getNumberInCart() * items.get(position).getPrice())) + "đ";


        binding.txtTitle.setText(items.get(position).getTitle());
        binding.txtFeeEachItem.setText(displayPrice);
        binding.txtTotalEachItem.setText(displayTotalPrice);
        binding.txtNumberItem.setText(String.valueOf(items.get(position).getNumberInCart()));

        int drawableResourced = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(binding.imvPhoto);

//        // Kiểm tra xem sản phẩm đã được chọn hay chưa
//        if (selectedItems.contains(items.get(position))) {
//            binding.checkboxBook.setChecked(true);
//        } else {
//            binding.checkboxBook.setChecked(false);
//        }
//
//        binding.checkboxBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (binding.checkboxBook.isChecked()) {
//                    selectedItems.add(items.get(position));
//                } else {
//                    selectedItems.remove(items.get(position));
//                }
//            }
//        });

        binding.txtPlusCart.setOnClickListener(v -> managmentCart.plusNumberItem(items, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));

        binding.txtMinusCart.setOnClickListener(v -> managmentCart.minusNumberItem(items, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public ArrayList<BookList> getSelectedItems() {
        return selectedItems;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(ViewholderCartBinding binding) { super(binding.getRoot());
        }
    }

}
