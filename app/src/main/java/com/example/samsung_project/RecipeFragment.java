package com.example.samsung_project;

import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.Dictionary;
import java.util.Map;

public class RecipeFragment extends Fragment {
    Map map;
    String dish;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe,container,false);

        return view;
    }


    public RecipeFragment(Map map,String dish){
        this.map = map;
        this.dish = dish;
    }
}
