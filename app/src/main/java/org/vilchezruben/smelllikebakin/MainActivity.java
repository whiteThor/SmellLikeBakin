package org.vilchezruben.smelllikebakin;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  implements  ListFragment.OnRecipeSelectedInterface{
public static final String LIST_FRAGMENT="fragment_list";

public static final String VIEWPAGER_FRAGMENT= "fragment_viewpage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isTable = getResources().getBoolean(R.bool.is_tablet);
        Toast.makeText(this,"is Tablet: " + isTable, Toast.LENGTH_SHORT).show();
        if(!isTable){
            ListFragment saveFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
            if(saveFragment == null) {
                ListFragment listFragment = new ListFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.placeHolder, listFragment, LIST_FRAGMENT);
                fragmentTransaction.commit();
            }
        }else{
            GridFragment saveFragment = (GridFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
            if(saveFragment == null) {
                GridFragment listFragment = new GridFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.placeHolder, listFragment, LIST_FRAGMENT);
                fragmentTransaction.commit();
            }
        }

    }

    @Override
    public void onListRecipeSelected(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPageFragment.KEY_RECIPE_INDEX, index);
        ViewPageFragment listFragment = new ViewPageFragment();
        listFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, listFragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
