package com.example.mohamedibrahim.multilevelmenu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Mohamed Ibrahim on 10/5/2015.
 */
public class SectionListFragment extends Fragment implements AdapterView.OnItemClickListener {


    private List<Section> args;
    private Section parentSection;
    private MainActivity parent;

    private ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_list, container, false);

        list = (ListView) view.findViewById(R.id.list);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final SectionsAdapter adapter = new SectionsAdapter(this.args, parentSection, getActivity());
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        if (context instanceof AppCompatActivity) {
            parent = (MainActivity) context;
        }
    }

    public void setArguments(List<Section> args, Section parentSection) {
        this.args = args;
        this.parentSection = parentSection;
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

        final Section s = (Section) adapter.getItemAtPosition(position);

        if (s != null) {

            if (s.getSubSections() != null && s.getSubSections().size() > 0) {
                if (parent != null) {
                    parent.pushFragments(new SectionListFragment(), true, true, s.getSubSections(), s);
                }
            }


        }


    }
}
