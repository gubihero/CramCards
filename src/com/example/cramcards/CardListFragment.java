package com.example.cramcards;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class CardListFragment extends ListFragment {
private static final String TAG = "CardListFragment";
private ArrayList<Card> mCards;
private Deck mDeck;
private boolean mDeleteMode;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		mDeleteMode = false;
		UUID deckId = (UUID)getArguments().getSerializable("deckID");
		mDeck = DeckBox.get(getActivity()).getDeck(deckId);
		getActivity().setTitle(mDeck.getTitle());
		mCards = mDeck.getCards();
		CardAdapter adapter = new CardAdapter(mCards);
	    setListAdapter(adapter);
	}
	
	@Override
    public void onResume() {
        super.onResume();
        ((CardAdapter)getListAdapter()).notifyDataSetChanged();
    }
	
	public static CardListFragment newInstance(UUID deckId) {
		Bundle args = new Bundle();
		args.putSerializable("deckID", deckId);
		
		CardListFragment fragment = new CardListFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Card c = ((CardAdapter)getListAdapter()).getItem(position);
        
        if(mDeleteMode){
        	mDeleteMode = !mDeleteMode;
        	DeckBox.get(getActivity()).removeDeckCard(mDeck.getId(), c.getId());
        	((CardAdapter)getListAdapter()).notifyDataSetChanged();
        } else {
        	 //start CardActivity
            Intent i = new Intent(getActivity(), CardPagerActivity.class);
            i.putExtra("cardID", c.getId());
            i.putExtra("deckID", mDeck.getId());
            startActivity(i);
        }
    }
	
	@Override
	 public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	     super.onCreateOptionsMenu(menu, inflater);
	     inflater.inflate(R.menu.fragment_card_list, menu);
	 }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_item_new_card:
	            Card card = new Card();
	            DeckBox.get(getActivity()).addDeckCard(mDeck.getId(),card);
	            //probably should have a dialogue that asks for deck name
	            Intent i = new Intent(getActivity(), CardEditActivity.class);
	            i.putExtra("deckID", mDeck.getId());
	            i.putExtra("cardID", card.getId());
	            startActivityForResult(i, 0);
	            return true;
	        case R.id.menu_item_delete_card:
	        	mDeleteMode = !mDeleteMode;
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private class CardAdapter extends ArrayAdapter<Card> {
		public CardAdapter(ArrayList<Card> cards) {
			super(getActivity(),0,cards);
		}
		
		 @Override
		    public View getView(int position, View convertView, ViewGroup parent) {
		        // If we weren't given a view, inflate one
		        if (convertView == null) {
		            convertView = getActivity().getLayoutInflater()
		                .inflate(R.layout.list_item_card, null);
		        }

		        // Configure the view for this Crime
		        Card c = getItem(position);

		        TextView titleTextView =
		            (TextView)convertView.findViewById(R.id.card_list_item_frontTextView);
		        titleTextView.setText(c.toString());
		        CheckBox masteredCheckBox =
		            (CheckBox)convertView.findViewById(R.id.card_list_item_masteredCheckBox);
		        masteredCheckBox.setChecked(c.isMastered());

		        return convertView;
		    }
		 
	}

}
