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
        boolean existAlready = false;
        for (BookList book : listpop) {
            if (book.getTitle().equals(item.getTitle())) {
                existAlready = true;
                break;
            }
        }
        if (!existAlready) {
            listpop.add(item);
            tinyDB.putListObject("WishList", listpop);
        }
    }
    public void removefromWishlist(BookList item) {
        ArrayList<BookList> listpop = getWishList();
        boolean existAlready = false;
        int indexToRemove = -1;
        for (int i = 0; i < listpop.size(); i++) {
            if (listpop.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                indexToRemove = i;
                break;
            }
        }
        if (existAlready) {
            listpop.remove(indexToRemove);
            tinyDB.putListObject("WishList", listpop);
        }
    }
    public ArrayList<BookList> getWishList() {
        return tinyDB.getListObject("WishList");
    }
}
