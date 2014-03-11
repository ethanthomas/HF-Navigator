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

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;
import com.hhs.hfnavigator.R;
import com.hhs.hfnavigator.core.Main;

public class TeacherList extends SherlockListActivity {
	
	protected EditText searchText;
	protected SQLiteDatabase db;
	protected Cursor cursor;
	protected ListAdapter adapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherdir);
        db = (new DatabaseHelper(this)).getWritableDatabase();
        searchText = (EditText) findViewById (R.id.searchText);
        searchText.clearFocus();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    
    @SuppressWarnings("deprecation")
	public void search(View view) {
		cursor = db.rawQuery("SELECT _id, firstName, lastName, title FROM employee WHERE firstName || ' ' || lastName LIKE ?", 
						new String[]{"%" + searchText.getText().toString() + "%"});
		adapter = new SimpleCursorAdapter(
				this, 
				R.layout.employee_list_item, 
				cursor, 
				new String[] {"firstName", "lastName", "title"}, 
				new int[] {R.id.firstName, R.id.lastName, R.id.title});
		setListAdapter(adapter);
    }
    
    public void onListItemClick(ListView parent, View view, int position, long id) {
    	Intent intent = new Intent(this, TeacherDetails.class);
    	Cursor cursor = (Cursor) adapter.getItem(position);
    	intent.putExtra("EMPLOYEE_ID", cursor.getInt(cursor.getColumnIndex("_id")));
    	startActivity(intent);
    }
 
    
    @SuppressWarnings("deprecation")
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            Intent upIntent = new Intent(this, Main.class);
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                // This activity is not part of the application's task, so
                // create a new task
                // with a synthesized back stack.
                TaskStackBuilder
                        .from(this)
                        .addNextIntent(new Intent(this, Main.class))
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



