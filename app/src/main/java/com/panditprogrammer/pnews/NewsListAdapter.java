package com.panditprogrammer.pnews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import static android.view.View.GONE;


public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.CustomViewHolder> {
    private Context context;
    private News news;
    private List<News> newsList;

    public NewsListAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsitem, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        news = newsList.get(position);
        holder.newsTitle.setText(news.getTitle());
        holder.newsDescription.setText(news.getDescription());

        Glide.with(context).load(news.getImageUrl()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(GONE);
                return false;
            }
        }).into(holder.newsImage);   // set the url image into imageview
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView newsTitle;
        public ImageView newsImage;
        public TextView newsDescription;
        ProgressBar progressBar;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            //onClick on itemView
            itemView.setOnClickListener(this);
            // find the views by their id's
            newsTitle = itemView.findViewById(R.id.newHeadingId);
            newsDescription = itemView.findViewById(R.id.newsDescId);
            newsImage = itemView.findViewById(R.id.newsImageId);
            progressBar = itemView.findViewById(R.id.progressId);
        }

        @Override
        public void onClick(View v) {
            // get the current position
            int adapterPosition = this.getAdapterPosition();
            News news;
            news = newsList.get(adapterPosition);

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();

            customTabsIntent.launchUrl(context, Uri.parse(news.getNewsUrl()));
            Toast.makeText(context, "Opening News ", Toast.LENGTH_SHORT).show();
        }
    }
}
