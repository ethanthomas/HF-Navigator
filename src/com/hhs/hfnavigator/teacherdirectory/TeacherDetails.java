/*License

THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS 
OF THIS CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW. 
ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE 
OR COPYRIGHT LAW IS PROHIBITED.

Creative Commons License

This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License; 
you may not use this work except in compliance with the License.

You may obtain a copy of the License in the LICENSE file, 
or at http://creativecommons.org/licenses/by-nc-nd/3.0/deed.en_US

BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, 
YOU ACCEPT AND AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. 
TO THE EXTENT THIS LICENSE MAY BE CONSIDERED TO BE A CONTRACT, 
THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED HERE IN CONSIDERATION 
OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 */

package com.hhs.hfnavigator.teacherdirectory;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;
import com.hhs.hfnavigator.R;

public class TeacherDetails extends SherlockListActivity {

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
		setContentView(R.layout.employee_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		
		
		employeeId = getIntent().getIntExtra("EMPLOYEE_ID", 0);
		SQLiteDatabase db = (new DatabaseHelper(this)).getWritableDatabase();
		Cursor cursor = db
				.rawQuery(
						"SELECT emp._id, emp.firstName, emp.lastName, emp.title, emp.officePhone, emp.cellPhone, emp.email, emp.managerId, mgr.firstName managerFirstName, mgr.lastName managerLastName FROM employee emp LEFT OUTER JOIN employee mgr ON emp.managerId = mgr._id WHERE emp._id = ?",
						new String[] { "" + employeeId });

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
						TeacherAction.ACTION_VIEW));
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
			intent = new Intent(Intent.ACTION_SEND);
			intent.setType("plain/text");
			intent.putExtra(Intent.EXTRA_EMAIL,
					new String[] { action.getData() });
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
			Intent upIntent = new Intent(this, TeacherList.class);
			if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
				// This activity is not part of the application's task, so
				// create a new task
				// with a synthesized back stack.
				TaskStackBuilder.from(this)
						.addNextIntent(new Intent(this, TeacherList.class))
						.addNextIntent(upIntent).startActivities();
				finish();
			} else {
				// This activity is part of the application's task, so simply
				// navigate up to the hierarchical parent activity.
				NavUtils.navigateUpTo(this, upIntent);
			}
			return true;
		}
		return true;
	}

}






