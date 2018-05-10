package org.vilchezruben.smelllikebakin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPageFragment extends Fragment {
public static final String KEY_RECIPE_INDEX =  "recipe_index";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        int index = getArguments().getInt(KEY_RECIPE_INDEX);
        getActivity().setTitle(Recipes.names[index]);
        View view = inflater.inflate(R.layout.fragment_viewpage, container, false);
        final IngredientsFragment ingredientsFragment = new IngredientsFragment();
        final DirectionsFragment directionsFragment = new DirectionsFragment();

        ViewPager viewPager = view.findViewById(R.id.viewPager);

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return position ==0 ? ingredientsFragment : directionsFragment;
            }

            @Override
            public int getCount() {
                return 2;
            }


            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return position==0 ?"Ingredients" : "Directions";
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);
       return view;
    }


    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));

    }
}
