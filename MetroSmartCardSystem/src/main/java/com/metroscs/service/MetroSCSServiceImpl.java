package com.metroscs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metroscs.dao.CardDAO;
import com.metroscs.dao.StationDAO;
import com.metroscs.dao.TravelDAO;
import com.metroscs.data.Card;
import com.metroscs.data.Station;
import com.metroscs.data.Travel;

@Service("metroSCSService")
@Transactional
public class MetroSCSServiceImpl implements MetroSCSService {

	@Autowired
	private CardDAO cardDAO;

	@Autowired
	private StationDAO stationDAO;

	@Autowired
	private TravelDAO travelDAO;

	public void flagTravelStart(Travel travel) {
		travelDAO.flagTravelStart(travel);
	}

	public void flagTravelEnd(Travel travel) {
		travelDAO.flagTravelEnd(travel);
	}

	public void saveStation(Station station) {
		stationDAO.saveStation(station);
	}

	public void saveCard(Card card) {
		cardDAO.saveCard(card);
	}

	public Travel getUncompletedTravel(Card card) {
		return travelDAO.getUncompletedTravel(card);
	}
	
	public void updateCard(Card card) {
		cardDAO.updateCard(card);
	}

	public Card getCardByNumber(int cardId) {
		return cardDAO.getCardByNumber(cardId);
	}

	public Card getCardByName(String cardHolderName) {
		return cardDAO.getCardByName(cardHolderName);
	}

	public void topupCard(Card card) {
		cardDAO.topupCard(card);
	}

	public int totalFootfallPerStation(Station station) {
		return 0;
	}
	
	public List getCompletedTravelByCard(Card card) {
		return travelDAO.getCompletedTravelByCard(card);
	}

	public String percardReport(Card card) {
		return null;
	}

}
