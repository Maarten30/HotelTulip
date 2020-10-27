package com.humanCompilers.hotelTulip;

import com.humanCompilers.hotelTulip.model.Reservation;
import com.humanCompilers.hotelTulip.model.Room;
import com.humanCompilers.hotelTulip.model.RoomType;
import com.humanCompilers.hotelTulip.model.Tarifa;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HotelTulipApplicationTests {

	Tarifa tarifa;
	Tarifa tarifa1;
	Room room = new Room(UUID.randomUUID(), RoomType.DOUBLE);;
	Reservation reserva = new Reservation();
	LocalDate date_0 = LocalDate.of(2020, 7, 2);
	LocalDate date_1 = LocalDate.of(2020, 7, 5);

	LocalDate date_2 = LocalDate.of(2020, 7, 28);
	LocalDate date_3 = LocalDate.of(2020, 8, 3);

	@Test
	public void calculateTotalPriceTest() {
		assertEquals(180.00, reserva.calculateTotalPrice(date_0, date_1, room), 0.0);
		assertEquals(320.00, reserva.calculateTotalPrice(date_2, date_3, room), 0.0);
	}


}
