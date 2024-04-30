package com.group8.rakkiibookstoreapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.view.menu.MenuView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.group8.rakkiibookstoreapp.DetailActivity;
import com.group8.rakkiibookstoreapp.R;
import com.group8.rakkiibookstoreapp.model.Blog;
import com.group8.rakkiibookstoreapp.model.BookList;

import java.util.ArrayList;

public class BlogAdapter extends BaseAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Blog> blogs;

    public BlogAdapter(Context context, int layoutResourceId, ArrayList<Blog> blogs) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.blogs = blogs;
    }

    @Override
    public int getCount() {
        return blogs.size();
    }

    @Override
    public Object getItem(int position) {
        return blogs.get(position);
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

        ImageView BlogPhoto = convertView.findViewById(R.id.imvBlog);
        BlogPhoto.setImageResource(blogs.get(position).getBlogPhoto());
        TextView BlogTitle = convertView.findViewById(R.id.txtBlogTitle);
        BlogTitle.setText(blogs.get(position).getBlogTitle());
        TextView Description = convertView.findViewById(R.id.txtDescription);
        Description.setText(blogs.get(position).getBlogDescription());
        TextView View = convertView.findViewById(R.id.txtView);
        View.setText(String.valueOf(blogs.get(position).getBlogView()) + " Lượt xem");
        TextView Time = convertView.findViewById(R.id.txtTime);
        Time.setText(blogs.get(position).getBlogTime());

        return convertView;
    }
}

