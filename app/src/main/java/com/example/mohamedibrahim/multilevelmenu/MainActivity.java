package com.example.mohamedibrahim.multilevelmenu;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {


    private ArrayMap<String, Object> sharedData;


    private Stack<SectionListFragment> mStack;


    private List<Section> sectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        sharedData = new ArrayMap<>();
        mStack = new Stack<>();


        sectionList = Section.generateSections();


        if (sectionList != null && sectionList.size() > 0) {

            SectionListFragment fragment = new SectionListFragment();
            pushFragments(fragment, false, true, sectionList, null);


        }


    }


    public void popFragments() {

        final SectionListFragment previousFragment = mStack.get(mStack.size() - 2);
        mStack.pop();

        final FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
        ft.replace(R.id.content, previousFragment);
        ft.commit();

    }


    public void pushFragments(SectionListFragment fragment, boolean animate, boolean add, List<Section> args, Section parent) {
        if (add) {
            mStack.add(fragment);

            final FragmentManager manager = getSupportFragmentManager();
            final FragmentTransaction fragmentTransaction = manager.beginTransaction();

            fragment.setArguments(args, parent);

            if (animate)
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();


        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
