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
import androidx.recyclerview.widget.GridLayoutManager;
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
    public List<String> dishes;
    public Bitmap bm;
    RecyclerView recyclerView;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.choice_dishes, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewForChoice);
        recyclerView.setHasFixedSize(true);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new FoodChoiceDecoration(largePadding, smallPadding));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new DishAdapter(dishes,getContext(),getActivity()));

        return view;
    }

    public FoodChoice(String dishes){
        this.dishes = Arrays.asList(dishes.split("\\s"));;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
        }
    }

}
