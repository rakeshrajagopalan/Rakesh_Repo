package com.metroscs.service;

import java.util.List;

import com.metroscs.data.Card;
import com.metroscs.data.Station;
import com.metroscs.data.Travel;

public interface MetroSCSService {

	public void flagTravelStart(Travel travel);
	
	public void flagTravelEnd(Travel travel);
	
	public void saveStation(Station station);
	
	public void saveCard(Card card);
	
	public Travel getUncompletedTravel(Card card);
	
	public List getCompletedTravelByCard(Card card);
	
	public void updateCard(Card card);
	
	public Card getCardByNumber(int cardId);
	
	public Card getCardByName(String cardHolderName);
	
	public void topupCard(Card card);
	
	public int totalFootfallPerStation(Station station);
	
	public String percardReport(Card card);
	
}
