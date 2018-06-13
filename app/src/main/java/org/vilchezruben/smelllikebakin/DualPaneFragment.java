package org.vilchezruben.smelllikebakin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DualPaneFragment extends Fragment {
    private static final String INGREDIENTS_FRAGMENT = "ingredients_fragment";
    private static final String DIRECTIONS_FRAGMENT = "directions_fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPageFragment.KEY_RECIPE_INDEX);
        getActivity().setTitle(Recipes.names[index]);
        View view = inflater.inflate(R.layout.fragment_dualpane, container, false);

        FragmentManager fragmentManager = getChildFragmentManager();

        IngredientsFragment saveIngredientsFragment = (IngredientsFragment) fragmentManager.findFragmentByTag(INGREDIENTS_FRAGMENT);

        if(saveIngredientsFragment == null){
            IngredientsFragment ingredientsFragment = new IngredientsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ViewPageFragment.KEY_RECIPE_INDEX, index);
            bundle.putBoolean(ViewPageFragment.KEY_IS_INGREDIENT, true);
            ingredientsFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.leftPlaceholder, ingredientsFragment, INGREDIENTS_FRAGMENT).commit();
        }

        DirectionsFragment saveDirectionFragment = (DirectionsFragment) fragmentManager.findFragmentByTag(DIRECTIONS_FRAGMENT);

        if(saveDirectionFragment == null){
            final DirectionsFragment directionsFragment = new DirectionsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ViewPageFragment.KEY_RECIPE_INDEX, index);
            bundle.putBoolean(ViewPageFragment.KEY_IS_INGREDIENT, false);
            directionsFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.rightPlaceholder, directionsFragment, DIRECTIONS_FRAGMENT).commit();
        }


    return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));

    }
}
