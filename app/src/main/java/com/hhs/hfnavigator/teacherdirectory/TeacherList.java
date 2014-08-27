package com.hhs.hfnavigator.teacherdirectory;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hhs.hfnavigator.R;

public class TeacherList extends ListFragment {

    protected EditText searchText;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ListAdapter adapter;

    Button search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.teacherdir, null);
        db = (new DatabaseHelper(getActivity())).getWritableDatabase();
        searchText = (EditText) root.findViewById(R.id.searchText);
        searchText.requestFocus();


        cursor = db.rawQuery("SELECT _id, firstName, lastName, title FROM employee WHERE firstName || ' ' || lastName LIKE ?",
                new String[]{"%" + searchText.getText().toString() + "%"});
        adapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.teacher_list_item,
                cursor,
                new String[]{"firstName", "title"},
                new int[]{R.id.firstName, R.id.title});
        setListAdapter(adapter);

        search = (Button) root.findViewById(R.id.searchButton);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = db.rawQuery("SELECT _id, firstName, lastName, title FROM employee WHERE firstName || ' ' || lastName LIKE ?",
                        new String[]{"%" + searchText.getText().toString() + "%"});
                adapter = new SimpleCursorAdapter(
                        getActivity(),
                        R.layout.teacher_list_item,
                        cursor,
                        new String[]{"firstName", "title"},
                        new int[]{R.id.firstName, R.id.title});
                setListAdapter(adapter);

            }
        });

        return root;
    }

    public void onListItemClick(ListView parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), TeacherDetails.class);
        Cursor cursor = (Cursor) adapter.getItem(position);
        intent.putExtra("EMPLOYEE_ID", cursor.getInt(cursor.getColumnIndex("_id")));
        startActivity(intent);
    }


}



