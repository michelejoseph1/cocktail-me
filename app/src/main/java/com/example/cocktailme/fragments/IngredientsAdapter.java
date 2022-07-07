package com.example.cocktailme.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cocktailme.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends BaseAdapter {
    private List<List<String>> mData = new ArrayList();

    public IngredientsAdapter(ArrayList<String> ingedients, ArrayList<String> measurements) {
        mData.clear();
        for (int i = 0; i < ingedients.size(); i++) {
            try {
                List<String> item = new ArrayList<>();
                item.add(ingedients.get(i));
                item.add(measurements.get(i));
                mData.add(item);
            } catch (Exception e) {
                List<String> item = new ArrayList<>();
                item.add(ingedients.get(i));
                item.add(" ");
                mData.add(item);
            }
        }
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public List<String> getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result;
        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_item, parent, false);
        }else {
            result = convertView;
        }
        List<String> current = getItem(position);
        TextView mIngredient = result.findViewById(R.id.ingredientName);
        TextView mMeasurement = result.findViewById(R.id.measurementValue);
        mIngredient.setText(current.get(0));
        mMeasurement.setText((current.get(1)));

        return result;
    }


}