package com.example.expandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> expendable_ListView_Header;
    HashMap<String, List<String>> expendable_ListView_Child;

    public CustomAdapter(Context context, List<String> expendable_ListView_Header, HashMap<String, List<String>> expendable_ListView_Child) {
        this.context = context;
        this.expendable_ListView_Header = expendable_ListView_Header;
        this.expendable_ListView_Child = expendable_ListView_Child;
    }

    @Override
    public int getGroupCount() {
        return expendable_ListView_Header.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expendable_ListView_Child.get(expendable_ListView_Header.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return expendable_ListView_Header.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return expendable_ListView_Child.get(expendable_ListView_Header.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerText = (String) getGroup(groupPosition);

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.header_layout, null);
        }

        TextView textView = convertView.findViewById(R.id.header_TextView_id);
        textView.setText(headerText);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_layout, null);
        }

        TextView textView = convertView.findViewById(R.id.child_TextView_id);
        textView.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
