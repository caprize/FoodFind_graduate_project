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
import java.util.Map;

import static java.lang.Thread.sleep;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    List<String> dishesList;
    Activity activity;
    LayoutInflater inflater;
    Bitmap bm;

    public RecipeAdapter(List<String> dishes, Context context, Activity activity){
        this.activity = activity;
        this.dishesList = dishes;
        this.inflater = LayoutInflater.from(context);
    }

    public List<String> getDishesList() {return dishesList;}

    public void setDishesList(List<String> dishesList){this.dishesList = dishesList; }

    public Bitmap getImage(int position){
        String req = dishesList.get(position);
        byte[][] response = {new byte[0]};
        String method = "post_image";
        RecipeGet imageGet = new RecipeGet();
        String url = "10.0.2.2";
        Runnable task = () -> {
            try {
                response[0] = imageGet.post_GetImage(url,req);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (response[0]!=null) {
            imageGet.setToBitmap(response[0]);
            bm = imageGet.prep_image();
            return bm;
        }
        else return null;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.dishes_item, parent, false);

        ImageView imageView = view.findViewById(R.id.image);
        TextView textView = view.findViewById(R.id.name);
        RecipeViewHolder recipeViewHolder = new RecipeViewHolder(view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String req = textView.getText().toString();
                RecipeGet imageGet = new RecipeGet();
                String url = "10.0.2.2";

                String[] response = null;
                RecipeMap[] recipeMap = {null};
                Runnable task = () -> {
                    try {
                        recipeMap[0] =imageGet.postRecipe(url, req);
                        ((NavigationHost) activity).navigateTo(new RecipeFragment(recipeMap[0].files,req), false); // Navigate to the next Fragment
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                };
                Thread thread = new Thread(task);
                thread.start();
//                for (Map.Entry<String, RecipeFile> entry : recipeMap[0].files.entrySet()) {
//                    System.out.println(entry.getKey());
//                    System.out.println(entry.getValue().content);
//                }

            }
        });
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        bm = getImage(position);
        holder.imageView.setImageBitmap(bm);
        holder.name.setText(dishesList.get(position));
    }


    @Override
    public int getItemCount() {
        return 5;
    }
    class RecipeViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
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
