package com.timelinemanager.Entity;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Event.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name EventTest.java
 */
public class EventTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#setTitle(java.lang.String)}.
	 */
	@Test
	public final void testSetTitle() {
		Event e = new Event();
		
		e.setTitle("myEvent");
		assertEquals("Event title was set accordingly", "myEvent", e.getTitle());
		e.setTitle("  MyT4#VEn6T");
		assertEquals("Event title was set accordingly", "  MyT4#VEn6T", e.getTitle());
		
		//TODO: Should not be allowed.
		//e.setTitle("\n\n\n\n\n\n\n\n\nTTTT!$#%");
		//assertEquals("Event title was set accordingly", "\n\n\n\n\n\n\n\n\nTTTT!$#%", e.getTitle());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#setDescription(java.lang.String)}.
	 */
	@Test
	public final void testSetDescription() {
		Event e = new Event();
		
		e.setDescription("My own description");
		assertEquals("Event description was set accordingly", "My own description", e.getDescription());
		e.setDescription("    Descriptions contains newlines and spaces ! Desscription \n Test");
		assertEquals("Event description was set accordingly", 
				"    Descriptions contains newlines and spaces ! Desscription \n Test", e.getDescription());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#setStartDate(java.time.LocalDate)}.
	 */
	@Test
	public final void testSetStartDate() {
		Event e = new Event();
		
		LocalDate currentDate = LocalDate.now();
		LocalDate tomorrow = currentDate.plusDays(1);
		
		e.setStartDate(currentDate);
		assertEquals("Event start date set", currentDate, e.getStartDate());
		e.setStartDate(tomorrow);
		assertEquals("Event start date set", tomorrow, e.getStartDate());
		
		//Start date cannot be after the end date
		LocalDate myLaterStartDate = tomorrow.plusDays(365);
		
		e.setStartDate(currentDate);
		e.setEndDate(tomorrow);
		
		//TODO: Throw error since start date cannot be later than end date.
		e.setStartDate(myLaterStartDate);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#setEndDate(java.time.LocalDate)}.
	 */
	@Test
	public final void testSetEndDate() {
		Event e = new Event();
		
		LocalDate currentDate = LocalDate.now();
		LocalDate tomorrow = currentDate.plusDays(1);
		
		e.setEndDate(currentDate);
		assertEquals("Event end date set", currentDate, e.getEndDate());
		e.setEndDate(tomorrow);
		assertEquals("Event end date set", tomorrow, e.getEndDate());
		
		//Start date cannot be after the end date
		LocalDate mySoonerEndDate = tomorrow.minusDays(365);
		
		e.setStartDate(currentDate);
		e.setEndDate(tomorrow);
		
		//TODO: Throw error since end date cannot be sooner than start date.
		e.setEndDate(mySoonerEndDate);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#setStartTime(java.time.LocalTime)}.
	 */
	@Test
	public final void testSetStartTime() {
		Event e = new Event();
		
		LocalTime currentTime = LocalTime.now();
		LocalTime nextHour = currentTime.plusHours(1);
		
		e.setStartTime(currentTime);
		assertEquals("Event start time set", currentTime, e.getStartTime());
		e.setStartTime(nextHour);
		assertEquals("Event start time set", nextHour, e.getStartTime());
		
		//Start time cannot be after the end time
		LocalTime myLaterStartTime = nextHour.plusHours(365);
		
		e.setStartTime(currentTime);
		e.setEndTime(nextHour);
		
		//TODO: Throw error since start time cannot be later than end time.
		e.setStartTime(myLaterStartTime);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#setEndTime(java.time.LocalTime)}.
	 */
	@Test
	public final void testSetEndTime() {
		Event e = new Event();
		
		LocalTime currentTime = LocalTime.now();
		LocalTime nextHour = currentTime.plusHours(1);
		
		e.setEndTime(currentTime);
		assertEquals("Event end time set", currentTime, e.getEndTime());
		e.setEndTime(nextHour);
		assertEquals("Event end time set", nextHour, e.getEndTime());
		
		//End time cannot be sooner than start time
		LocalTime mySoonerEndTime = nextHour.minusHours(365);
		
		e.setStartTime(currentTime);
		e.setEndTime(nextHour);
		
		//TODO: Throw error since end time cannot be sooner than start time.
		e.setEndTime(mySoonerEndTime);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#setPic(java.lang.String)}.
	 */
	@Test
	public final void testSetPic() {
		Event e = new Event();
		
		e.setPic("/path/to/pic");
		assertEquals("Event picture was set accordingly", "/path/to/pic", e.getPic());
		e.setPic("new/path/to/pic");
		assertEquals("Event picture was set accordingly", "new/path/to/pic", e.getPic());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#getTitle()}.
	 */
	@Test
	public final void testGetTitle() {
		Event e = new Event();
		
		e.setTitle("myEvent");
		assertEquals("Event title was set accordingly", "myEvent", e.getTitle());
		e.setTitle("  MyT4#VEn6T");
		assertEquals("Event title was set accordingly", "  MyT4#VEn6T", e.getTitle());
		
		//TODO: Should not be allowed.
		//e.setTitle("\n\n\n\n\n\n\n\n\nTTTT!$#%");
		//assertEquals("Event title was set accordingly", "\n\n\n\n\n\n\n\n\nTTTT!$#%", e.getTitle());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#getDescription()}.
	 */
	@Test
	public final void testGetDescription() {
		Event e = new Event();
		
		e.setDescription("My own description");
		assertEquals("Event description was set accordingly", "My own description", e.getDescription());
		e.setDescription("    Descriptions contains newlines and spaces ! Desscription \n Test");
		assertEquals("Event description was set accordingly", 
				"    Descriptions contains newlines and spaces ! Desscription \n Test", e.getDescription());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#getStartDate()}.
	 */
	@Test
	public final void testGetStartDate() {
		Event e = new Event();
		
		LocalDate currentDate = LocalDate.now();
		LocalDate tomorrow = currentDate.plusDays(1);
		
		e.setStartDate(currentDate);
		assertEquals("Event start date set", currentDate, e.getStartDate());
		e.setStartDate(tomorrow);
		assertEquals("Event start date set", tomorrow, e.getStartDate());
		
		//Start date cannot be after the end date
		LocalDate myLaterStartDate = tomorrow.plusDays(365);
		
		e.setStartDate(currentDate);
		e.setEndDate(tomorrow);
		
		//TODO: Throw error since start date cannot be later than end date.
		e.setStartDate(myLaterStartDate);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#getEndDate()}.
	 */
	@Test
	public final void testGetEndDate() {
		Event e = new Event();
		
		LocalDate currentDate = LocalDate.now();
		LocalDate tomorrow = currentDate.plusDays(1);
		
		e.setEndDate(currentDate);
		assertEquals("Event end date set", currentDate, e.getEndDate());
		e.setEndDate(tomorrow);
		assertEquals("Event end date set", tomorrow, e.getEndDate());
		
		//Start date cannot be after the end date
		LocalDate mySoonerEndDate = tomorrow.minusDays(365);
		
		e.setStartDate(currentDate);
		e.setEndDate(tomorrow);
		
		//TODO: Throw error since end date cannot be sooner than start date.
		e.setEndDate(mySoonerEndDate);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#getStartTime()}.
	 */
	@Test
	public final void testGetStartTime() {
		Event e = new Event();
		
		LocalTime currentTime = LocalTime.now();
		LocalTime nextHour = currentTime.plusHours(1);
		
		e.setStartTime(currentTime);
		assertEquals("Event start time set", currentTime, e.getStartTime());
		e.setStartTime(nextHour);
		assertEquals("Event start time set", nextHour, e.getStartTime());
		
		//Start time cannot be after the end time
		LocalTime myLaterStartTime = nextHour.plusHours(365);
		
		e.setStartTime(currentTime);
		e.setEndTime(nextHour);
		
		//TODO: Throw error since start time cannot be later than end time.
		e.setStartTime(myLaterStartTime);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#getEndTime()}.
	 */
	@Test
	public final void testGetEndTime() {
		Event e = new Event();
		
		LocalTime currentTime = LocalTime.now();
		LocalTime nextHour = currentTime.plusHours(1);
		
		e.setEndTime(currentTime);
		assertEquals("Event end time set", currentTime, e.getEndTime());
		e.setEndTime(nextHour);
		assertEquals("Event end time set", nextHour, e.getEndTime());
		
		//End time cannot be sooner than start time
		LocalTime mySoonerEndTime = nextHour.minusHours(365);
		
		e.setStartTime(currentTime);
		e.setEndTime(nextHour);
		
		//TODO: Throw error since end time cannot be sooner than start time.
		e.setEndTime(mySoonerEndTime);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#getPic()}.
	 */
	@Test
	public final void testGetPic() {
		Event e = new Event();
		
		e.setPic("/path/to/pic");
		assertEquals("Event picture was set accordingly", "/path/to/pic", e.getPic());
		e.setPic("new/path/to/pic");
		assertEquals("Event picture was set accordingly", "new/path/to/pic", e.getPic());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Event#toString()}.
	 */
	@Test
	public final void testToString() {
		//TODO: Timelines toString is yet to be implemented.
	}

}
