package org.vilchezruben.smelllikebakin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;


public class CheckBoxesFragment extends Fragment{

    CheckBox[] mCheckBoxes;
    private static final String KEY_CHECKED_BOXES = "key_checked_boxes";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPageFragment.KEY_RECIPE_INDEX);
        boolean isIngredients = getArguments().getBoolean(ViewPageFragment.KEY_IS_INGREDIENT);
        View view = inflater.inflate(R.layout.fragment_check_boxes, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.checkBoxesLayout);
        String[] contents;
        if(isIngredients){
            contents = Recipes.ingredients[index].split("`");
        }else{
            contents = Recipes.directions[index].split("`");
        }
        mCheckBoxes = new CheckBox[contents.length];
        boolean[] checkBoxes = new boolean[mCheckBoxes.length];
        if(savedInstanceState!= null && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null){
            checkBoxes =  savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);
        }
        setUpCheckBoxes(contents, linearLayout, checkBoxes);
        return view;

    }

    private void setUpCheckBoxes(String[] contents, ViewGroup container, boolean[] checkBoxes) {
        int i =0;
        for (String content : contents) {
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8,18,8,16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText(content);
            if(checkBoxes[i]){
                mCheckBoxes[i].toggle();
            }
            container.addView(mCheckBoxes[i]);
            i++;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckBoxes.length];
        int i = 0;
        for (CheckBox checkBox: mCheckBoxes) {
            stateOfCheckBoxes[i] = checkBox.isChecked();
            i++;
        }
        outState.putBooleanArray(KEY_CHECKED_BOXES, stateOfCheckBoxes);
        super.onSaveInstanceState(outState);
    }
}
