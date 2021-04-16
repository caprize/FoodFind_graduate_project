package com.example.samsung_project;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samsung_project.Network.RecipeGet;
import com.example.samsung_project.adapter.DishAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

public class FoodChoice extends Fragment {
    Context context;
    public List<String> dishes;
    public Bitmap bm;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.choice_dishes, container, false);
        System.out.println(dishes);
        recyclerView = view.findViewById(R.id.recyclerViewForChoice);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new DishAdapter(dishes,context,getActivity()));

        return view;
    }

    public FoodChoice(String dishes,Context context){
        this.context = context;
        this.dishes = Arrays.asList(dishes.split("\\s"));;
    }

}
