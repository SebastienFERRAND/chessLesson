package com.example.lessonchess;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VideoActivity extends Activity {


	private ListView menuVideo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);

		overridePendingTransition(R.anim.push_to_right,R.anim.push_to_left);

		menuVideo = (ListView) this.findViewById(R.id.listViewVideo);

		String[] values = new String[] { "Bouger les pièces", "Echecs et Echecs et mat !", "Quelques mats connus", "Les mats éclairs !" };

		ArrayAdapter adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, values);

		menuVideo.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video, menu);
		return true;
	}

}
