package com.example.basicnewsapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.basicnewsapplication.models.Articles;

import java.util.List;

public class Adapters extends RecyclerView.Adapter<Adapters.MyViewHolder> {
    private List<Articles> articles;
    private Context context;

    public Adapters(List<Articles> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.iitem, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Articles model=articles.get(position);
        Glide.with(context).load(model.getUrlToImage()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.imageView);
        holder.author.setText(model.getAuthor());
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.date.setText(model.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView author,title,description,date;
        ImageView imageView;
        ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            author=itemView.findViewById(R.id.author);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.desc);
            date=itemView.findViewById(R.id.date);
            progressBar=itemView.findViewById(R.id.loadimage);
            imageView=itemView.findViewById(R.id.img);

        }
    }
}
