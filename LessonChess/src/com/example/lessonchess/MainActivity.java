package com.example.lessonchess;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView menu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		menu = (ListView) this.findViewById(R.id.listView1);
		
		String[] values = new String[] { "Apprendre", "Exercices", "Questions" };
		
		ArrayAdapter adapter = new ArrayAdapter<String>(this, 
		        android.R.layout.simple_list_item_1, values);
		
		menu.setAdapter(adapter);

		menu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int pos,
					long id) {
				if(pos == 0){
					Intent intent = new Intent(MainActivity.this, VideoActivity.class);
					startActivity(intent);
				}else if(pos == 1){
					Intent intent = new Intent(MainActivity.this, ExerciceActivity.class);
					startActivity(intent);
				}
				
			}
		});
	    
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onResume() {
	    super.onResume();
		overridePendingTransition(R.anim.back_to_left,R.anim.back_to_right);
	}

}
