package com.example.mohamedibrahim.multilevelmenu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Mohamed Ibrahim on 10/5/2015.
 */
public class SectionsAdapter extends BaseAdapter {


    private static int TYPE_COUNT = 2;

    private static final int NORMAL_SECTION = 0;
    private static final int PARENT_SECTION = 1;


    private List<Section> subSection;
    private Section parentSection;
    private Context context;


    public SectionsAdapter(List<Section> subSection, Section parentSection, Context context) {
        this.context = context;
        this.subSection = subSection;
        this.parentSection = parentSection;

    }

    @Override
    public int getCount() {

        int count = 0;
        if (subSection != null) {
            count += subSection.size();
        }

        if (parentSection != null)
            count += 1;

        return count;
    }


    @Override
    public Object getItem(int position) {

        final int type = getItemViewType(position);

        switch (type) {
            case NORMAL_SECTION:

                return subSection.get(position);
            case PARENT_SECTION:
                return parentSection;
            default:
                return new Section(0, "", null);
        }

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (parentSection != null && position == 0)
            return PARENT_SECTION;
        else
            return NORMAL_SECTION;
    }
}
