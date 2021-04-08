package com.example.samsung_project;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.samsung_project.Network.RecipeGet;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Arrays;
import java.util.List;

public class FoodChoice extends Fragment {
    final List<String> dishes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choice_dishes, container, false);

        System.out.println(dishes);

        return view;
    }

    public FoodChoice(String dishes){
        this.dishes = Arrays.asList(dishes.split("\\s"));;
    }

}
