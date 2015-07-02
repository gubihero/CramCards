package com.example.cramcards;

import android.support.v4.app.Fragment;

public class DeckListActivity extends SingleFragmentActivity {
	private static final String TAG = "DeckListActivity";
	
	@Override
	protected Fragment createFragment() {
		return new DeckListFragment();
	}

}
