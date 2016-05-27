package com.metroscs.command;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metroscs.data.Card;
import com.metroscs.data.Station;
import com.metroscs.data.Travel;
import com.metroscs.exception.MetroSCSException;
import com.metroscs.service.MetroSCSService;

@Component
public class SwipeOutCommand implements Command {

	@Autowired
	private MetroSCSService metroSCSService;

	private Card card;

	private Station station;

	public void execute() throws MetroSCSException {
		Travel travel = metroSCSService.getUncompletedTravel(card);
		travel.setToStation(station.getStationId());
		travel.setEndTime(new Timestamp(System.currentTimeMillis()));
		if (card.getBlocked() != 1) {
			metroSCSService.flagTravelEnd(travel);
			card.setCardBalance(card.getCardBalance() - travel.getTravelCharges());
			if (card.getCardBalance() < 0) {
				card.setBlocked(1);
			}
			metroSCSService.updateCard(card);
		} else {
			throw new MetroSCSException("Your card " + card.getCardId() + " has been blocked. Please contact admin.");
		}
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

}
