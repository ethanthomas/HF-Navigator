package com.hhs.hfnavigator.teacherdirectory;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hhs.hfnavigator.R;

import java.util.ArrayList;
import java.util.List;

public class TeacherDetails extends ListActivity {

    protected TextView employeeName;
    protected TextView employeeTitle;
    protected TextView officePhone;
    protected TextView cellPhone;
    protected TextView email;
    protected int employeeId;
    protected int managerId;

    protected List<TeacherAction> actions;
    protected EmployeeActionAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_details);

        getActionBar().setDisplayHomeAsUpEnabled(true);


        employeeId = getIntent().getIntExtra("EMPLOYEE_ID", 0);
        SQLiteDatabase db = (new DatabaseHelper(this)).getWritableDatabase();
        Cursor cursor = db
                .rawQuery(
                        "SELECT emp._id, emp.firstName, emp.lastName, emp.title, emp.officePhone, emp.cellPhone, emp.email, emp.managerId, mgr.firstName managerFirstName, mgr.lastName managerLastName FROM employee emp LEFT OUTER JOIN employee mgr ON emp.managerId = mgr._id WHERE emp._id = ?",
                        new String[]{"" + employeeId});

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();

            employeeName = (TextView) findViewById(R.id.employeeName);
            employeeName.setText(cursor.getString(cursor
                    .getColumnIndex("firstName"))
                    + " "
                    + cursor.getString(cursor.getColumnIndex("lastName")));

            employeeTitle = (TextView) findViewById(R.id.title);
            employeeTitle.setText(cursor.getString(cursor
                    .getColumnIndex("title")));

            actions = new ArrayList<TeacherAction>();

            String officePhone = cursor.getString(cursor
                    .getColumnIndex("officePhone"));
            if (officePhone != null) {
                actions.add(new TeacherAction("Call office", officePhone,
                        TeacherAction.ACTION_CALL));
            }

            String cellPhone = cursor.getString(cursor
                    .getColumnIndex("cellPhone"));
            if (cellPhone != null) {
                actions.add(new TeacherAction("Call mobile", cellPhone,
                        TeacherAction.ACTION_CALL));
                actions.add(new TeacherAction("SMS", cellPhone,
                        TeacherAction.ACTION_SMS));
            }

            String email = cursor.getString(cursor.getColumnIndex("email"));
            if (email != null) {
                actions.add(new TeacherAction("Email", email,
                        TeacherAction.ACTION_EMAIL));
            }

            managerId = cursor.getInt(cursor.getColumnIndex("managerId"));
            if (managerId > 0) {
                actions.add(new TeacherAction("View manager", cursor
                        .getString(cursor.getColumnIndex("managerFirstName"))
                        + " "
                        + cursor.getString(cursor
                        .getColumnIndex("managerLastName")),
                        TeacherAction.ACTION_VIEW
                ));
            }

            adapter = new EmployeeActionAdapter();
            setListAdapter(adapter);

        }

    }

    public void onListItemClick(ListView parent, View view, int position,
                                long id) {

        TeacherAction action = actions.get(position);

        Intent intent;
        switch (action.getType()) {

            case TeacherAction.ACTION_CALL:
                Uri callUri = Uri.parse("tel:" + action.getData());
                intent = new Intent(Intent.ACTION_CALL, callUri);
                startActivity(intent);
                break;

            case TeacherAction.ACTION_EMAIL:


                Log.d("", action.getData());

                intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:" + action.getData() + "?subject=" + "&body=");
                intent.setData(data);
                startActivity(intent);
                break;

            case TeacherAction.ACTION_SMS:
                Uri smsUri = Uri.parse("sms:" + action.getData());
                intent = new Intent(Intent.ACTION_VIEW, smsUri);
                startActivity(intent);
                break;

            case TeacherAction.ACTION_VIEW:
                intent = new Intent(this, TeacherDetails.class);
                intent.putExtra("EMPLOYEE_ID", managerId);
                startActivity(intent);
                break;
        }
    }

    class EmployeeActionAdapter extends ArrayAdapter<TeacherAction> {

        EmployeeActionAdapter() {
            super(TeacherDetails.this, R.layout.action_list_item, actions);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TeacherAction action = actions.get(position);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.action_list_item, parent,
                    false);
            TextView label = (TextView) view.findViewById(R.id.label);
            label.setText(action.getLabel());
            TextView data = (TextView) view.findViewById(R.id.data);
            data.setText(action.getData());
            return view;
        }

    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }

}






