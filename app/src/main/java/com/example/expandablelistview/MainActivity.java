package com.example.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter customAdapter;
    private ExpandableListView expandableListView;
    List<String> expendable_ListView_Header;
    HashMap<String, List<String>> expandable_ListView_Child;
    private int lastExpandableListView = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareListData();

        expandableListView = findViewById(R.id.expandableListView_id);
        customAdapter = new CustomAdapter(this, expendable_ListView_Header, expandable_ListView_Child);
        expandableListView.setAdapter(customAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                String headerString = expendable_ListView_Header.get(groupPosition);
                Toast.makeText(getApplicationContext(), headerString+" is opned", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

                String headerString = expendable_ListView_Header.get(groupPosition);
                Toast.makeText(getApplicationContext(), headerString+" is collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String childString = expandable_ListView_Child.get(expendable_ListView_Header.get(groupPosition)).get(childPosition);
                Toast.makeText(getApplicationContext(), childString, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                if (lastExpandableListView != -1 && lastExpandableListView != groupPosition){
                    expandableListView.collapseGroup(lastExpandableListView);
                }
                lastExpandableListView = groupPosition;
            }
        });
    }

    private void prepareListData(){
        String[] headerString = getResources().getStringArray(R.array.department_Name_Header);
        String[] childString = getResources().getStringArray(R.array.department_Details_Child);

        expendable_ListView_Header = new ArrayList<>();
        expandable_ListView_Child = new HashMap<>();

        for (int i=0; i<headerString.length; i++){

            // Adding Header
            expendable_ListView_Header.add(headerString[i]);

            // Adding Child
            List<String> child = new ArrayList<>();
            child.add(childString[i]);

            expandable_ListView_Child.put(expendable_ListView_Header.get(i), child);
        }

        /* expendable_ListView_Header.add("1. OverView");

        List<String> overView = new ArrayList<>();
        overView.add("1.1   What is C programming");
        overView.add("1.2   History of C programming");
        overView.add("1.3   Why C programming");
        overView.add("1.4   How C programming works");
        overView.add("1.5   C programming compiler");

        expandable_ListView_Child.put(expendable_ListView_Header.get(0), overView);  */
    }
}
