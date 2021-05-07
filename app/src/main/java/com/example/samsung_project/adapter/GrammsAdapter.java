package com.example.samsung_project.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samsung_project.Navigation.NavigationHost;
import com.example.samsung_project.Network.RecipeGet;
import com.example.samsung_project.R;
import com.example.samsung_project.RecipeFragment;
import com.example.samsung_project.recipes.RecipeMap;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import static java.lang.Thread.sleep;

public class GrammsAdapter extends RecyclerView.Adapter<GrammsAdapter.GrammViewHolder> {
    List<String> gramList;
    Activity activity;
    LayoutInflater inflater;
    Bitmap bm;

    public GrammsAdapter(List<String> dishes, Context context, Activity activity){
        this.activity = activity;
        this.gramList = dishes;
        this.inflater = LayoutInflater.from(context);
    }

    public List<String> getGramList() {return gramList;}

    public void setGramList(List<String> gramList){this.gramList = gramList; }


    @NonNull
    @Override
    public GrammViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recipe_gram_item, parent, false);

        ImageView imageView = view.findViewById(R.id.image);
        TextView textView = view.findViewById(R.id.name);
        GrammViewHolder recipeViewHolder = new GrammViewHolder(view);
        return new GrammViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GrammViewHolder holder, int position) {
        holder.name.setText(gramList.get(position));
    }


    @Override
    public int getItemCount() {
        return 5;
    }
    class GrammViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        public GrammViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.gramms);
        }
        public TextView getName(){
            return name;
        }
    }
    public void setActivity(Activity activity){
        this.activity = activity;
    }
    public Activity getActivity() {
        return activity;
    }
}
