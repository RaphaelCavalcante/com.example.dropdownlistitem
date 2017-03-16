package com.home.rhounsell.example.dropdownlistitem.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.home.rhounsell.example.dropdownlistitem.R;
import com.home.rhounsell.example.dropdownlistitem.model.VaccinationYear;
import com.home.rhounsell.example.dropdownlistitem.model.Vaccine;

import java.util.HashMap;
import java.util.List;

/**
 * Created by raphael on 14/03/17.
 */

public class ExpandableListAdapter  extends BaseExpandableListAdapter {
    private Context context;
    private List<VaccinationYear> listDataHeader;
    private HashMap<Long, List<Vaccine>> listDataChild;

    public ExpandableListAdapter(Context context, List<VaccinationYear> listDataHeader,
                                  HashMap<Long, List<Vaccine>>listChild){
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChild;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return listDataChild.get(listDataHeader.get(groupPosition).getYear()).get(childPosition);
    }
    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition).getYear()).size();
    }

    @Override
    public Object getGroup(int grouPosition) {
        return listDataHeader.get(grouPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        String headerTitle= String.valueOf(((VaccinationYear) getGroup(groupPosition)).getYear());
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group,null);
        }
        TextView lblListHeader = (TextView) view.findViewById(R.id.header);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View view, ViewGroup parent) {
        final String childText=((Vaccine) getChild(groupPosition,childPosition)).getVaccineName();
        final String childDate=((Vaccine) getChild(groupPosition, childPosition)).getVaccineDate();
        final String childApplied=((Vaccine) getChild(groupPosition, childPosition)).getVaccineApplied();
        if(view == null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }
        TextView txtListChild= (TextView) view.findViewById(R.id.lbl_list_item);
        TextView txtChildDate = (TextView) view.findViewById(R.id.lbl_date);
        TextView txtChildApplied= (TextView) view.findViewById(R.id.lbl_applied);

        txtListChild.setText(childText);
        txtChildDate.setText(childDate);
        txtChildApplied.setText(childApplied);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
