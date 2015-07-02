package com.example.cramcards;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DeckListFragment extends ListFragment {
	
	private static final String TAG = "DeckListFragment";
	private static final String DIALOG_NAME = "name";
	private static final int REQUEST_NAME = 0;
	private boolean mDeleteMode;
	
	private ArrayList<Deck> mDecks;
	
	private Deck newDeck;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		mDeleteMode = false;
		getActivity().setTitle(R.string.decks_title);
		mDecks = DeckBox.get(getActivity()).getDecks();
		DeckAdapter adapter = new DeckAdapter(mDecks);
        setListAdapter(adapter);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode != Activity.RESULT_OK) return;
	    if (requestCode == REQUEST_NAME) {
	    	String name = (String)data
	            .getSerializableExtra("deckName");
	        newDeck.setTitle(name);
	        
	        DeckBox.get(getActivity()).addDeck(newDeck);
            //go to new decks cardlistactivity
            Intent i = new Intent(getActivity(), CardListActivity.class);
            i.putExtra("deckID", newDeck.getId());
            startActivityForResult(i, 0);
	    }
	}
	
	@Override
    public void onResume() {
        super.onResume();
        ((DeckAdapter)getListAdapter()).notifyDataSetChanged();
    }
	
	@Override
	 public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	     super.onCreateOptionsMenu(menu, inflater);
	     inflater.inflate(R.menu.fragment_deck_list, menu);
	 }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_item_new_deck:
	            newDeck = new Deck();
	            //make name dialog
	            FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
	            DeckNamePickerFragment dialog = DeckNamePickerFragment.newInstance();
	            dialog.setTargetFragment(DeckListFragment.this, REQUEST_NAME);
                dialog.show(fm, DIALOG_NAME);
                //add the new name to the new deck, add deck
	            return true;
	        case R.id.menu_item_delete_deck:
	        	mDeleteMode = !mDeleteMode;
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Deck d = ((DeckAdapter)getListAdapter()).getItem(position);
        if(mDeleteMode){
        	mDeleteMode = !mDeleteMode;
        	DeckBox.get(getActivity()).removeDeck(d.getId());
        	((DeckAdapter)getListAdapter()).notifyDataSetChanged();
        } else {
        //start CardListActivity
	        Intent i = new Intent(getActivity(), CardListActivity.class);
	        i.putExtra("deckID", d.getId());
	        startActivity(i);
        }
    }
	
	private class DeckAdapter extends ArrayAdapter<Deck> {
		public DeckAdapter(ArrayList<Deck> decks) {
			super(getActivity(),0,decks);
		}
		
		@Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        // If we weren't given a view, inflate one
	        if (convertView == null) {
	            convertView = getActivity().getLayoutInflater()
	                .inflate(R.layout.list_item_deck, null);
	        }

	        // Configure the view for this Crime
	        Deck d = getItem(position);
	        
	        TextView cardsTextView =
	            (TextView)convertView.findViewById(R.id.deck_list_item_cardsTextView);
	        cardsTextView.setText(Integer.toString(d.getCards().size())+" cards");

	        TextView titleTextView =
	            (TextView)convertView.findViewById(R.id.deck_list_item_titleTextView);
	        titleTextView.setText(d.toString());
	       
	        

	        return convertView;
	    }
	}
}
