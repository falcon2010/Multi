package com.example.mohamedibrahim.multilevelmenu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

    private final boolean hasParent;


    public SectionsAdapter(List<Section> subSection, Section parentSection, Context context) {
        this.context = context;
        this.subSection = subSection;
        this.parentSection = parentSection;

        hasParent = this.parentSection != null;


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
    public Section getItem(int position) {

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
        final int viewType = getItemViewType(position);
        final TextView text = new TextView(context);
        switch (viewType) {
            case NORMAL_SECTION:

                if (subSection != null && subSection.size() > 0)
                    if (hasParent)
                        position = position - 1;

                text.setText(subSection.get(position).getName());

                break;
            case PARENT_SECTION:
                if (parentSection != null) text.setText(parentSection.getName());
                break;


        }

        return text;
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
