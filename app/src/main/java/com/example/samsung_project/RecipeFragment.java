package com.example.samsung_project;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samsung_project.adapter.DishAdapter;
import com.example.samsung_project.adapter.GrammsAdapter;
import com.example.samsung_project.adapter.StepsAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class RecipeFragment extends Fragment {
    Map<String,String> map;
    String dish;
    Bitmap bm;
    List<String> gramms = new ArrayList<>();
    List<String> steps = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe,container,false);
        ImageView imageView1 = view.findViewById(R.id.image1);
        imageView1.setImageBitmap(bm);
        RecyclerView recyclerView1 = view.findViewById(R.id.name_gramms);
        RecyclerView recyclerView2 = view.findViewById(R.id.name_steps);
        TextView textView = view.findViewById(R.id.name1);
        textView.setText(dish);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().charAt(entry.getKey().length()-1) == '.') gramms.add(entry.getValue());
            else steps.add(entry.getValue());
        }
        recyclerView1.setHasFixedSize(true);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView1.addItemDecoration(new FoodChoiceDecoration(largePadding, smallPadding));
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView1.setAdapter(new GrammsAdapter(gramms,getContext(),getActivity()));


        recyclerView2.setHasFixedSize(true);
        recyclerView2.addItemDecoration(new FoodChoiceDecoration(largePadding, smallPadding));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView2.setAdapter(new StepsAdapter(steps,getContext(),getActivity()));
        return view;
    }


    public RecipeFragment(Map map, String dish, Bitmap bm){
        this.map = map;
        this.bm = bm;
        this.dish = dish;
    }
}
