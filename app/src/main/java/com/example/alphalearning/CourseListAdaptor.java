package com.example.alphalearning;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CourseListAdaptor extends RecyclerView.Adapter<CourseListAdaptor.ViewHolder> {


    private Context context;
    private List<Course> coursesList;
    private  ViewGroup parent;
    private String userId;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public CourseListAdaptor(){}



    public CourseListAdaptor(Context context, List<Course> coursesList, String userId) {
        this.coursesList = coursesList;
        this.context = context;
        this.userId = userId;
    }

    private Bitmap getImageBitmap(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.courses_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        TextView title;
        ImageView courseImage;
        Button courseBtn;

        title = holder.itemView.findViewById(R.id.courseName);
        courseImage = holder.itemView.findViewById(R.id.courseImage);
        courseBtn = holder.itemView.findViewById(R.id.courseBtn);



        title.setText(coursesList.get(position).getName());

        if(userId.equals(coursesList.get(position).getCreatedBy())){
            courseBtn.setText("Edit Course");
        }else{
            courseBtn.setText("Go to course");
        }

        Bitmap bitmapImg = this.getImageBitmap(coursesList.get(position).getThumbnail());
        courseImage.setImageBitmap(bitmapImg);


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((ViewCourse) v.getContext()).onClickCalled(v,position);
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }




}