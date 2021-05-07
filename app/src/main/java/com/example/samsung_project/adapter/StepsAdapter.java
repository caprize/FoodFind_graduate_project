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

import com.example.samsung_project.R;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {
    List<String> stepsList;
    Activity activity;
    LayoutInflater inflater;
    Bitmap bm;

    public StepsAdapter(List<String> dishes, Context context, Activity activity){
        this.activity = activity;
        this.stepsList = dishes;
        this.inflater = LayoutInflater.from(context);
    }

    public List<String> getStepsList() {return stepsList;}

    public void setStepsList(List<String> stepsList){this.stepsList = stepsList; }


    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recipe_steps_item, parent, false);

        TextView textView = view.findViewById(R.id.name);
        StepsViewHolder stepsViewHolder = new StepsViewHolder(view);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        holder.name.setText(stepsList.get(position));
    }


    @Override
    public int getItemCount() {
        return 5;
    }
    class StepsViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.steps);
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
