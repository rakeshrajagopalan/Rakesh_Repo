package com.metroscs.core;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.metroscs.config.MetroSCSAppConfig;
import com.metroscs.data.Card;
import com.metroscs.data.Station;
import com.metroscs.data.Travel;
import com.metroscs.farestrategy.Context;
import com.metroscs.farestrategy.FareStrategy;
import com.metroscs.farestrategy.FareStrategyFactory;
import com.metroscs.service.MetroSCSService;

@Transactional
public class MetroSCSAppTest {

	private MetroSCSService metroSCSService;

	private AbstractApplicationContext context;

	private SessionFactory sessionFactory;

	@Before
	public void setUp() {
		context = new AnnotationConfigApplicationContext(MetroSCSAppConfig.class);
		metroSCSService = context.getBean("metroSCSService", MetroSCSService.class);
		sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
	}

	@Test
	public void testTrainTravel() {
		float cardAmount = 75.0f;
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		FareStrategy strategy = FareStrategyFactory.getFareStrategy(dayOfWeek);
		Context context = new Context(strategy);
		String name = getRandomName();
		Card card = new Card();
		card.setCardHolderName(name);
		card.setCardBalance(cardAmount);
		card.setCardHolderIdentityType("Passport");
		card.setCardHolderIdentityValue("AAA1234");
		card.setBlocked(0);
		metroSCSService.saveCard(card);
		card = metroSCSService.getCardByName(name);
		Station startStation = new Station();
		startStation.setStationId(4);
		Station endStation = new Station();
		endStation.setStationId(6);
		Travel travel = new Travel();
		travel.setCardId(card.getCardId());
		travel.setFromStation(startStation.getStationId());
		travel.setToStation(startStation.getStationId());
		travel.setStartTime(new Timestamp(System.currentTimeMillis()));
		metroSCSService.flagTravelStart(travel);
		Travel travel2 = metroSCSService.getUncompletedTravel(card);
		travel2.setEndTime(new Timestamp(System.currentTimeMillis()));
		travel2.setToStation(endStation.getStationId());
		travel2.setBaseCharge(context.executeStrategy());
		metroSCSService.flagTravelEnd(travel2);
		card.setCardBalance(card.getCardBalance() - travel2.getTravelCharges());
		metroSCSService.updateCard(card);
		// Assertion part:
		int stationCount = 2;
		float charge = strategy.getFareCharge();
		float balance = cardAmount - (charge * stationCount);
		Assert.assertTrue(balance == card.getCardBalance());
	}

	private String getRandomName() {
		String name;
		String[] chars = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };
		name = chars[((int) (Math.random() * chars.length))] + chars[((int) (Math.random() * chars.length))]
				+ chars[((int) (Math.random() * chars.length))] + chars[((int) (Math.random() * chars.length))]
				+ chars[((int) (Math.random() * chars.length))];
		return name;
	}

	@After
	public void tearDown() {
		context.close();
		sessionFactory.close();
	}

}
