package com.metroscs.command;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metroscs.data.Card;
import com.metroscs.data.Station;
import com.metroscs.data.Travel;
import com.metroscs.exception.MetroSCSException;
import com.metroscs.farestrategy.Context;
import com.metroscs.farestrategy.FareStrategy;
import com.metroscs.farestrategy.FareStrategyFactory;
import com.metroscs.service.MetroSCSService;

@Component
public class SwipeInCommand implements Command {
	
	@Autowired
	private MetroSCSService metroSCSService;
	
	private Card card;
	
	private Station station;
	
	public void execute() throws MetroSCSException {
		Travel travel = new Travel();
		travel.setCardId(card.getCardId());
		travel.setFromStation(station.getStationId());
		travel.setToStation(station.getStationId());
		Calendar calendar = Calendar.getInstance();
		FareStrategy fareStrategy = FareStrategyFactory.getFareStrategy(calendar.get(Calendar.DAY_OF_WEEK));
		Context context = new Context(fareStrategy);
		travel.setBaseCharge(context.executeStrategy());
		travel.setStartTime(new Timestamp(calendar.getTimeInMillis()));
		if(card.getCardBalance() > 5.5f && card.getBlocked() != 1) {
			metroSCSService.flagTravelStart(travel);
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
