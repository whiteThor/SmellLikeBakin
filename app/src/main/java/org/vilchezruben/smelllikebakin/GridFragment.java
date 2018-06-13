package org.vilchezruben.smelllikebakin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GridFragment extends Fragment{


public interface OnRecipeSelectedInterface{
    void onGridRecipeSelected(int index);
}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycleview, container,false);
        GridFragment.OnRecipeSelectedInterface listener = (OnRecipeSelectedInterface) getActivity();
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        GridAdapter gridAdapter = new GridAdapter(listener);
        recyclerView.setAdapter(gridAdapter);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float dpWidth = metrics.widthPixels / metrics.density;
        int numColumn = (int) (dpWidth / 200);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),numColumn);

        recyclerView.setLayoutManager(layoutManager);

        return view;

    }
}
