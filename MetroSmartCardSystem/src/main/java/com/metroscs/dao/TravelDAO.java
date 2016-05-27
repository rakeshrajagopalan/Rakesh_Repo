package com.metroscs.dao;

import java.util.List;

import com.metroscs.data.Card;
import com.metroscs.data.Station;
import com.metroscs.data.Travel;

public interface TravelDAO {

	public void flagTravelStart(Travel travel);

	public void flagTravelEnd(Travel travel);

	public Travel getUncompletedTravel(Card card);
	
	public int totalFootfallPerStation(Station station);
	
	public List getCompletedTravelByCard(Card card);

}
