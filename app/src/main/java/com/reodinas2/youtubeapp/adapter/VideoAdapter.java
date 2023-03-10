package com.reodinas2.youtubeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.reodinas2.youtubeapp.MainActivity;
import com.reodinas2.youtubeapp.PhotoActivity;
import com.reodinas2.youtubeapp.R;
import com.reodinas2.youtubeapp.model.Video;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    Context context;
    ArrayList<Video> videoList;


    public VideoAdapter(Context context, ArrayList<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
    }


    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // xml 파일을 연결하는 작업
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_row, parent, false);
        return new VideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        // 뷰에 데이터를 셋팅한다.
        Video video = videoList.get(position);

        holder.txtTitle.setText(video.title);
        holder.txtDescription.setText(video.description);
        Glide.with(context).load(video.mediumUrl)
                .placeholder(R.drawable.baseline_ondemand_video_24)
                .into(holder.imgThumb);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView txtTitle;
        TextView txtDescription;
        ImageView imgThumb;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            imgThumb = itemView.findViewById(R.id.imgThumb);

            // 카드뷰를 클릭하면 웹을 실행시켜서 비디오를 보여준다.
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    Video video = videoList.get(index);

                    String url = "https://www.youtube.com/watch?v=" + video.videoId;

                    ((MainActivity)context).openWebPage(url);
                }
            });

            // 썸네일 이미지를 클릭하면, 새로운 액티비티에 큰 이미지로 보여준다.
            imgThumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    Video video = videoList.get(index);

                    Intent intent = new Intent(context, PhotoActivity.class);
                    intent.putExtra("highUrl", video.highUrl);
                    context.startActivity(intent);
                }
            });
        }
    }


}
