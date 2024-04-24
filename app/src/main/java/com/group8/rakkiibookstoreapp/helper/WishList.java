package com.group8.rakkiibookstoreapp.helper;

import android.content.Context;
import com.group8.rakkiibookstoreapp.model.BookList;
import java.util.ArrayList;

public class WishList {
    private Context context;
    private TinyDB tinyDB;

    public WishList(Context context) {
        this.context = context;
        this.tinyDB=new TinyDB(context);
    }

    public void addtoWishlist(BookList item) {
        ArrayList<BookList> listpop = getWishList();
        listpop.add(item);
        tinyDB.putListObject("WishList", listpop);
    }
    public void removefromWishlist(BookList item) {
        ArrayList<BookList> listpop = getWishList();
        listpop.remove(item);
        tinyDB.putListObject("WishList", listpop);
    }
    public ArrayList<BookList> getWishList() {
        return tinyDB.getListObject("WishList");
    }
}
