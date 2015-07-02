package com.example.cramcards;

import java.util.UUID;

public class Card {
	private UUID mId;
	private String mFront;
	private String mBack;
	private boolean mFlipStatus;
	private boolean mMastered;
	
	public Card() {
        // Generate unique identifier
        mId = UUID.randomUUID();
        mFlipStatus = false;
        mMastered = false;
        mFront = "";
        mBack = "";
    }
	
	@Override
	public String toString() {
		if (mFront.length()>=20){
			return mFront.substring(0, 17)+"...";
		}
		return mFront;
	}

	 
	public UUID getId() {
		return mId;
	}

	public void setId(UUID mId) {
		this.mId = mId;
	}

	public String getFront() {
		return mFront;
	}

	public void setFront(String mFront) {
		this.mFront = mFront;
	}

	public String getBack() {
		return mBack;
	}

	public void setBack(String mBack) {
		this.mBack = mBack;
	}

	public boolean isFlipStatus() {
		return mFlipStatus;
	}

	public void setFlipStatus(boolean mFlipStatus) {
		this.mFlipStatus = mFlipStatus;
	}

	public boolean isMastered() {
		return mMastered;
	}

	public void setMastered(boolean mMastered) {
		this.mMastered = mMastered;
	}

}
