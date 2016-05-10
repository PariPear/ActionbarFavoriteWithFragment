package com.ahmad.actionBar;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;

@SuppressLint("NewApi")
public class ActionBarMain extends Activity implements TabListener {
	RelativeLayout rl;


	private ViewPager viewPager;

	private String[] FilePathStrings;
	private String[] FileNameStrings;
	private File[] listFile;
	GridView grid;
	GridViewAdapter adapter;
	File file;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_bar_main);

		// Check for SD Card
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "Error! No SDCARD Found!", Toast.LENGTH_LONG)
					.show();
		} else {
			// Locate the image folder in your SD Card
			file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + "Download");
			// Create a new folder if no folder named SDImageTutorial exist
			file.mkdirs();
		}

		if (file.isDirectory()) {
			listFile = file.listFiles();
			// Create a String array for FilePathStrings
			FilePathStrings = new String[listFile.length];
			// Create a String array for FileNameStrings
			FileNameStrings = new String[listFile.length];

			for (int i = 0; i < listFile.length; i++) {
				// Get the path of the image file
				FilePathStrings[i] = listFile[i].getAbsolutePath();
				// Get the name image file
				FileNameStrings[i] = listFile[i].getName();
			}
		}

		// Locate the GridView in gridview_main.xml
		grid = (GridView) findViewById(R.id.mainGrid);
		// Pass String arrays to LazyAdapter Class
		adapter = new GridViewAdapter(this, FilePathStrings, FileNameStrings);
		// Set the LazyAdapter to the GridView
		grid.setAdapter(adapter);
		beginActionMode(); // this might be used by a long press or by button tam in action bar...


		try {
			rl = (RelativeLayout) findViewById(R.id.mainLayout);
			fragMentTra = getFragmentManager().beginTransaction();
			ActionBar bar = getActionBar();
			//bar.addTab(bar.newTab().setText("Listing").setTabListener(this));
			bar.addTab(bar.newTab().setText("All").setTabListener(this));
			bar.addTab(bar.newTab().setText("Favorite").setTabListener(this));

			bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
					| ActionBar.DISPLAY_USE_LOGO);
			bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			bar.setDisplayShowHomeEnabled(true);
			bar.setDisplayShowTitleEnabled(false);
			bar.show();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void beginActionMode(){
		grid.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}
		/**
		 * Hiding Action Bar
		 */


	FragMent1 fram1;
	FragmentTransaction fragMentTra = null;
	FragMent2 fram2;
	FragMent3 fram3;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_action_bar_main, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {

		if (tab.getText().equals("Listing")) {
			try {
				rl.removeAllViews();
			} catch (Exception e) {
			}
			fram1 = new FragMent1();
			fragMentTra.addToBackStack(null);
			fragMentTra = getFragmentManager().beginTransaction();
			fragMentTra.add(rl.getId(), fram1);
			fragMentTra.commit();
		} else if (tab.getText().equals("All")) {
			try {
				rl.removeAllViews();
			} catch (Exception e) {
			}
			fram2 = new FragMent2();
			fragMentTra.addToBackStack(null);
			fragMentTra = getFragmentManager().beginTransaction();
			fragMentTra.add(rl.getId(), fram2);
			fragMentTra.commit();
		} else if (tab.getText().equals("Favorite")) {
			try {
				rl.removeAllViews();
			} catch (Exception e) {
			}
			fram3 = new FragMent3();
			fragMentTra.addToBackStack(null);
			fragMentTra = getFragmentManager().beginTransaction();
			fragMentTra.add(rl.getId(), fram3);
			fragMentTra.commit();
		}

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

}
