package com.group8.rakkiibookstoreapp.helper;

import android.content.Context;
import android.widget.Toast;

import com.group8.rakkiibookstoreapp.model.BookList;
import java.util.ArrayList;

public class WishList {
    private Context context;
    private TinyDB tinyDB;

    public WishList(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void addtoWishlist(BookList item) {
        ArrayList<BookList> listpop = getWishList();
        boolean existAlready = false;
        for (int i = 0; i < listpop.size(); i++) {
            if (listpop.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                break;
            }
        }
        if(existAlready){
            Toast.makeText(context, "Sản phẩm đã được thêm vào Yêu thích trước đó", Toast.LENGTH_LONG).show();
        }else{
            listpop.add(item);
        }
        tinyDB.putListObject("WishList",listpop);
        Toast.makeText(context, "Đã thêm vào Yêu thích", Toast.LENGTH_SHORT).show();
    }
    public void removefromWishlist(ArrayList<BookList> item, int position) {
        item.remove(position);
        tinyDB.putListObject("WishList",item);
        Toast.makeText(context, "Đã bỏ khỏi Yêu thích", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<BookList> getWishList() {
        return tinyDB.getListObject("WishList");
    }
}
