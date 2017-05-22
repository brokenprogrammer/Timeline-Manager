package com.timelinemanager.Entity;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Timeline.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name TimelineTest.java
 */
public class TimelineTest {

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
	 * Test method for {@link com.timelinemanager.Entity.Timeline#setTitle(java.lang.String)}.
	 */
	@Test
	public final void testSetTitle() {
		Timeline t = new Timeline();
		
		t.setTitle("myTimeline");
		assertEquals("Timelines title was set accordingly", "myTimeline", t.getTitle());
		t.setTitle("  MyT1!mL^ne");
		assertEquals("Timelines title was set accordingly", "  MyT1!mL^ne", t.getTitle());
		
		//TODO: Should not be allowed.
		//t.setTitle("\n\n\n\n\n\n\n\n\nTTTT!$#%");
		//assertEquals("Timelines title was set accordingly", "\n\n\n\n\n\n\n\n\nTTTT!$#%", t.getTitle());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#setDescription(java.lang.String)}.
	 */
	@Test
	public final void testSetDescription() {
		Timeline t = new Timeline();
		
		t.setDescription("My own description");
		assertEquals("Timelines description was set accordingly", "My own description", t.getDescription());
		t.setDescription("    Descriptions contains newlines and spaces ! Desscription \n Test");
		assertEquals("Timelines description was set accordingly", 
				"    Descriptions contains newlines and spaces ! Desscription \n Test", t.getDescription());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#setStartDate(java.time.LocalDate)}.
	 */
	@Test
	public final void testSetStartDate() {
		Timeline t = new Timeline();
		
		LocalDate currentDate = LocalDate.now();
		LocalDate tomorrow = currentDate.plusDays(1);
		
		t.setStartDate(currentDate);
		assertEquals("Timelines start date set", currentDate, t.getStartDate());
		t.setStartDate(tomorrow);
		assertEquals("Timelines start date set", tomorrow, t.getStartDate());
		
		//Start date cannot be after the end date
		LocalDate myLaterStartDate = tomorrow.plusDays(365);
		
		t.setStartDate(currentDate);
		t.setEndDate(tomorrow);
		
		//TODO: Throw error since start date cannot be later than end date.
		t.setStartDate(myLaterStartDate);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#setEndDate(java.time.LocalDate)}.
	 */
	@Test
	public final void testSetEndDate() {
		Timeline t = new Timeline();
		
		LocalDate currentDate = LocalDate.now();
		LocalDate tomorrow = currentDate.plusDays(1);
		
		t.setEndDate(currentDate);
		assertEquals("Timelines end date set", currentDate, t.getEndDate());
		t.setEndDate(tomorrow);
		assertEquals("Timelines end date set", tomorrow, t.getEndDate());
		
		//Start date cannot be after the end date
		LocalDate mySoonerEndDate = tomorrow.minusDays(365);
		
		t.setStartDate(currentDate);
		t.setEndDate(tomorrow);
		
		//TODO: Throw error since end date cannot be sooner than start date.
		t.setEndDate(mySoonerEndDate);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#setStartTime(java.time.LocalTime)}.
	 */
	@Test
	public final void testSetStartTime() {
		Timeline t = new Timeline();
		
		LocalTime currentTime = LocalTime.now();
		LocalTime nextHour = currentTime.plusHours(1);
		
		t.setStartTime(currentTime);
		assertEquals("Timelines start time set", currentTime, t.getStartTime());
		t.setStartTime(nextHour);
		assertEquals("Timelines start time set", nextHour, t.getStartTime());
		
		//Start time cannot be after the end time
		LocalTime myLaterStartTime = nextHour.plusHours(365);
		
		t.setStartTime(currentTime);
		t.setEndTime(nextHour);
		
		//TODO: Throw error since start time cannot be later than end time.
		t.setStartTime(myLaterStartTime);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#setEndTime(java.time.LocalTime)}.
	 */
	@Test
	public final void testSetEndTime() {
		Timeline t = new Timeline();
		
		LocalTime currentTime = LocalTime.now();
		LocalTime nextHour = currentTime.plusHours(1);
		
		t.setEndTime(currentTime);
		assertEquals("Timelines end time set", currentTime, t.getEndTime());
		t.setEndTime(nextHour);
		assertEquals("Timelines end time set", nextHour, t.getEndTime());
		
		//End time cannot be sooner than start time
		LocalTime mySoonerEndTime = nextHour.minusHours(365);
		
		t.setStartTime(currentTime);
		t.setEndTime(nextHour);
		
		//TODO: Throw error since end time cannot be sooner than start time.
		t.setEndTime(mySoonerEndTime);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#setPic(java.lang.String)}.
	 */
	@Test
	public final void testSetPic() {
		Timeline t = new Timeline();
		
		t.setPic("/path/to/pic");
		assertEquals("Timelines picture was set accordingly", "/path/to/pic", t.getPic());
		t.setPic("new/path/to/pic");
		assertEquals("Timelines picture was set accordingly", "new/path/to/pic", t.getPic());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#getTitle()}.
	 */
	@Test
	public final void testGetTitle() {
		Timeline t = new Timeline();
		
		t.setTitle("myTimeline");
		assertEquals("Timelines title was set accordingly", "myTimeline", t.getTitle());
		t.setTitle("  MyT1!mL^ne");
		assertEquals("Timelines title was set accordingly", "  MyT1!mL^ne", t.getTitle());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#getDescription()}.
	 */
	@Test
	public final void testGetDescription() {
		Timeline t = new Timeline();
		
		t.setDescription("My own description");
		assertEquals("Timelines description was set accordingly", "My own description", t.getDescription());
		t.setDescription("    Descriptions contains newlines and spaces ! Desscription \n Test");
		assertEquals("Timelines description was set accordingly", 
				"    Descriptions contains newlines and spaces ! Desscription \n Test", t.getDescription());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#getStartDate()}.
	 */
	@Test
	public final void testGetStartDate() {
		Timeline t = new Timeline();
		
		LocalDate currentDate = LocalDate.now();
		LocalDate tomorrow = currentDate.plusDays(1);
		
		t.setStartDate(currentDate);
		assertEquals("Timelines start date set", currentDate, t.getStartDate());
		t.setStartDate(tomorrow);
		assertEquals("Timelines start date set", tomorrow, t.getStartDate());
		
		//Start date cannot be after the end date
		LocalDate myLaterStartDate = tomorrow.plusDays(365);
		
		t.setStartDate(currentDate);
		t.setEndDate(tomorrow);
		
		//TODO: Throw error since start date cannot be later than end date.
		t.setStartDate(myLaterStartDate);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#getEndDate()}.
	 */
	@Test
	public final void testGetEndDate() {
		Timeline t = new Timeline();
		
		LocalDate currentDate = LocalDate.now();
		LocalDate tomorrow = currentDate.plusDays(1);
		
		t.setEndDate(currentDate);
		assertEquals("Timelines end date set", currentDate, t.getEndDate());
		t.setEndDate(tomorrow);
		assertEquals("Timelines end date set", tomorrow, t.getEndDate());
		
		//Start date cannot be after the end date
		LocalDate mySoonerEndDate = tomorrow.minusDays(365);
		
		t.setStartDate(currentDate);
		t.setEndDate(tomorrow);
		
		//TODO: Throw error since end date cannot be sooner than start date.
		t.setEndDate(mySoonerEndDate);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#getStartTime()}.
	 */
	@Test
	public final void testGetStartTime() {
		Timeline t = new Timeline();
		
		LocalTime currentTime = LocalTime.now();
		LocalTime nextHour = currentTime.plusHours(1);
		
		t.setStartTime(currentTime);
		assertEquals("Timelines start time set", currentTime, t.getStartTime());
		t.setStartTime(nextHour);
		assertEquals("Timelines start time set", nextHour, t.getStartTime());
		
		//Start date cannot be after the end date
		LocalTime myLaterStartTime = nextHour.plusHours(365);
		
		t.setStartTime(currentTime);
		t.setEndTime(nextHour);
		
		//TODO: Throw error since start time cannot be later than end time.
		t.setStartTime(myLaterStartTime);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#getEndTime()}.
	 */
	@Test
	public final void testGetEndTime() {
		Timeline t = new Timeline();
		
		LocalTime currentTime = LocalTime.now();
		LocalTime nextHour = currentTime.plusHours(1);
		
		t.setEndTime(currentTime);
		assertEquals("Timelines end time set", currentTime, t.getEndTime());
		t.setEndTime(nextHour);
		assertEquals("Timelines end time set", nextHour, t.getEndTime());
		
		//End time cannot be sooner than start time
		LocalTime mySoonerEndTime = nextHour.minusHours(365);
		
		t.setStartTime(currentTime);
		t.setEndTime(nextHour);
		
		//TODO: Throw error since end date cannot be sooner than start date.
		t.setEndTime(mySoonerEndTime);
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#getPic()}.
	 */
	@Test
	public final void testGetPic() {
		Timeline t = new Timeline();
		
		t.setPic("/path/to/pic");
		assertEquals("Timelines picture was set accordingly", "/path/to/pic", t.getPic());
		t.setPic("new/path/to/pic");
		assertEquals("Timelines picture was set accordingly", "new/path/to/pic", t.getPic());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#getDaysLength()}.
	 */
	@Test
	public final void testGetDaysLength() {
		Timeline t = new Timeline();
		t.setStartDate(LocalDate.now());
		t.setEndDate(LocalDate.now().plusDays(1));
		
		assertEquals("Timeline is counting days between", 2, t.getDaysLength());
		t.setEndDate(LocalDate.now().plusDays(400));
		assertEquals("Timeline is counting days between", 401, t.getDaysLength());
		t.setEndDate(LocalDate.now().plusDays(2500));
		assertEquals("Timeline is counting days between", 2501, t.getDaysLength());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#getMonthsLength()}.
	 */
	@Test
	public final void testGetMonthsLength() {
		Timeline t = new Timeline();
		t.setStartDate(LocalDate.now());
		t.setEndDate(LocalDate.now().plusMonths(1));
		
		assertEquals("Timeline is counting months between", 2, t.getMonthsLength());
		t.setEndDate(LocalDate.now().plusMonths(12));
		assertEquals("Timeline is counting months between", 13, t.getMonthsLength());
		t.setEndDate(LocalDate.now().plusMonths(200));
		assertEquals("Timeline is counting months between", 201, t.getMonthsLength());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#getYearsLength()}.
	 */
	@Test
	public final void testGetYearsLength() {
		Timeline t = new Timeline();
		t.setStartDate(LocalDate.now());
		t.setEndDate(LocalDate.now().plusYears(1));
		
		assertEquals("Timeline is counting years between", 2, t.getYearsLength());
		t.setEndDate(LocalDate.now().plusYears(12));
		assertEquals("Timeline is counting years between", 13, t.getYearsLength());
		t.setEndDate(LocalDate.now().plusYears(200));
		assertEquals("Timeline is counting years between", 201, t.getYearsLength());
	}

	/**
	 * Test method for {@link com.timelinemanager.Entity.Timeline#toString()}.
	 */
	@Test
	public final void testToString() {
		//TODO: Timelines toString is yet to be implemented.
	}

}
