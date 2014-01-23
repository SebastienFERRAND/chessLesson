package com.example.lessonchess;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ExerciceActivity extends Activity {
	
	private ListView menuExercice;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercice);
		
		overridePendingTransition(R.anim.push_to_right,R.anim.push_to_left);

		menuExercice = (ListView) this.findViewById(R.id.listViewExercices);

		String[] values = new String[] { "Test niveau pion", "Test niveau cavalier",
				"Test niveau fou", "Test niveau tour", "Test niveau dame", "Test niveau roi" };

		ArrayAdapter adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, values);

		menuExercice.setAdapter(adapter);
		
		menuExercice.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int pos,
					long id) {
				if(pos == 0){
					Intent intent = new Intent(ExerciceActivity.this, ChessActivity.class);
					startActivity(intent);
				}
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exercice, menu);
		return true;
	}

}
