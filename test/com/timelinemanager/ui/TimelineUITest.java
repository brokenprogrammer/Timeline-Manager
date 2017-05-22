package com.timelinemanager.ui;

import static org.testfx.api.FxAssert.verifyThat;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import com.timelinemanager.TimelineManager;

import javafx.stage.Stage;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name TimelineUITest.java
 */
public class TimelineUITest extends ApplicationTest {
	
	@Override
	public void start(Stage stage) throws Exception {
		new TimelineManager().start(stage);
	}
	
	@Test
	public void createTimeline() {
		moveCheckClick("File");
		moveCheckClick("New");
		moveCheckClick("Timeline");
		
		clickWriteCheckText("#timelineTitle", "My Automated Title");
		clickWriteCheckText("#timelineDescription", "My Automated Description");
		
		checkNotNullClickWrite("#datePicker_startDate", "2017-05-01");
		checkNotNullClickWrite("#datePicker_endDate", "2017-05-31");
		
		moveTo("#newTimelineCreateButton");
		clickOn("#newTimelineCreateButton");
	}
	
	@Test
	public void cancelCreateTimeline() {
		moveCheckClick("File");
		moveCheckClick("New");
		moveCheckClick("Timeline");
		
		clickWriteCheckText("#timelineTitle", "My Automated Title");
		clickWriteCheckText("#timelineDescription", "My Automated Description");
		
		checkNotNullClickWrite("#datePicker_startDate", "2017-04-01");
		checkNotNullClickWrite("#datePicker_endDate", "2017-04-26");
		
		moveTo("#newTimelineCancelButton");
		clickOn("#newTimelineCancelButton");
		moveTo("OK");
		clickOn("OK");
	}
	
	@Test
	public void createTimelineWithoutTitle() {
		moveCheckClick("File");
		moveCheckClick("New");
		moveCheckClick("Timeline");
		
		clickWriteCheckText("#timelineDescription", "Automated Description");
		
		checkNotNullClickWrite("#datePicker_startDate", "2017-05-01");
		checkNotNullClickWrite("#datePicker_endDate", "2017-05-31");
		
		moveTo("#newTimelineCreateButton");
		clickOn("#newTimelineCreateButton");
		moveTo("OK");
		clickOn("OK");
	}
	
	@Test
	public void createTimelineStartDateAfterEndDate() {
		moveCheckClick("File");
		moveCheckClick("New");
		moveCheckClick("Timeline");
		
		clickWriteCheckText("#timelineTitle", "My Automated Title");
		clickWriteCheckText("#timelineDescription", "My Automated Description");
		
		checkNotNullClickWrite("#datePicker_startDate", "2017-05-01");
		checkNotNullClickWrite("#datePicker_endDate", "2017-04-26");
		
		moveTo("#newTimelineCreateButton");
		clickOn("#newTimelineCreateButton");
		
		moveTo("OK");
		clickOn("OK");
	}
	
	@Test
	public void createTimelineEndDateBeforeStartDate() {
		moveCheckClick("File");
		moveCheckClick("New");
		moveCheckClick("Timeline");
		
		clickWriteCheckText("#timelineTitle", "Timeline with end date before start date");
		clickWriteCheckText("#timelineDescription", "Automated Description");
		
		checkNotNullClickWrite("#datePicker_startDate", "2017-04-01");
		checkNotNullClickWrite("#datePicker_endDate", "2017-03-26");
		
		moveTo("#newTimelineCreateButton");
		clickOn("#newTimelineCreateButton");
		
		moveTo("OK");
		clickOn("OK");
	}
	
	@Test
	public void createThenCloseTimeline() {
		moveCheckClick("File");
		moveCheckClick("New");
		moveCheckClick("Timeline");
		
		clickWriteCheckText("#timelineTitle", "My Automated Title");
		clickWriteCheckText("#timelineDescription", "My Automated Description");
		
		checkNotNullClickWrite("#datePicker_startDate", "2017-05-01");
		checkNotNullClickWrite("#datePicker_endDate", "2017-05-31");
		
		moveTo("#newTimelineCreateButton");
		clickOn("#newTimelineCreateButton");
		
		moveCheckClick("File");
		moveCheckClick("Close");
	}
	
	@Test
	public void createThenCloseThenOpenTimeline() {
		moveCheckClick("File");
		moveCheckClick("New");
		moveCheckClick("Timeline");
		
		clickWriteCheckText("#timelineTitle", "My Automated Title");
		clickWriteCheckText("#timelineDescription", "My Automated Description");
		
		checkNotNullClickWrite("#datePicker_startDate", "2017-05-01");
		checkNotNullClickWrite("#datePicker_endDate", "2017-05-31");
		
		moveTo("#newTimelineCreateButton");
		clickOn("#newTimelineCreateButton");
		
		moveCheckClick("File");
		moveCheckClick("Close");
		
		
		moveTo("#timelineManagerTimelineListingList");
		moveTo("My Automated Title");
		doubleClickOn("My Automated Title");
	}
	
	@Test
	public void createMultipleTimelines() {
		moveCheckClick("File");
		moveCheckClick("New");
		moveCheckClick("Timeline");
		
		clickWriteCheckText("#timelineTitle", "My Automated Title");
		clickWriteCheckText("#timelineDescription", "My Automated Description");
		
		checkNotNullClickWrite("#datePicker_startDate", "2017-05-01");
		checkNotNullClickWrite("#datePicker_endDate", "2017-05-31");
		
		moveTo("#newTimelineCreateButton");
		clickOn("#newTimelineCreateButton");
		
		moveCheckClick("File");
		moveCheckClick("New");
		moveCheckClick("Timeline");
		
		clickWriteCheckText("#timelineTitle", "My Second Automated Title");
		clickWriteCheckText("#timelineDescription", "My Second Automated Description");
		
		checkNotNullClickWrite("#datePicker_startDate", "2017-05-10");
		checkNotNullClickWrite("#datePicker_endDate", "2020-01-01");
		
		moveTo("#newTimelineCreateButton");
		clickOn("#newTimelineCreateButton");
	}
	
	@Test
	public void createMultipleTimelinesAndSwitchBetweenThem() {
		moveCheckClick("File");
		moveCheckClick("New");
		moveCheckClick("Timeline");
		
		clickWriteCheckText("#timelineTitle", "My Automated Title");
		clickWriteCheckText("#timelineDescription", "My Automated Description");
		
		checkNotNullClickWrite("#datePicker_startDate", "2017-05-01");
		checkNotNullClickWrite("#datePicker_endDate", "2017-05-31");
		
		moveTo("#newTimelineCreateButton");
		clickOn("#newTimelineCreateButton");
		
		moveCheckClick("File");
		moveCheckClick("New");
		moveCheckClick("Timeline");
		
		clickWriteCheckText("#timelineTitle", "My Second Automated Title");
		clickWriteCheckText("#timelineDescription", "My Second Automated Description");
		
		checkNotNullClickWrite("#datePicker_startDate", "2017-05-10");
		checkNotNullClickWrite("#datePicker_endDate", "2020-01-01");
		
		moveTo("#newTimelineCreateButton");
		clickOn("#newTimelineCreateButton");
		
		moveTo("#timelineManagerTimelineListingList");
		moveTo("My Automated Title");
		doubleClickOn("My Automated Title");
		
		moveTo("#timelineManagerTimelineListingList");
		moveTo("My Second Automated Title");
		doubleClickOn("My Second Automated Title");
	}
	
	/**
	 * Perform the actions of move the mouse to specified target
	 * then verifying that the target is the target we are expecting and 
	 * lastly click on the target.
	 * 
	 * @param target - Target to move, verify then click.
	 */
	private void moveCheckClick(String target) {
		moveTo(target);
		verifyThat(target, NodeMatchers.hasText(target));
		clickOn(target);
	}
	
	/**
	 * Performs the actions of clicking a specified target
	 * then writing a specified text then verifying that the text
	 * has been added to the target. 
	 * 
	 * @param target - Target to click and write to.
	 * @param text - Text to write to the target.
	 */
	private void clickWriteCheckText(String target, String text) {
		clickOn(target);
		write(text);
		verifyThat(target, NodeMatchers.hasText(text));
	}
	
	/**
	 * Performs the actions of checking if the target UI element 
	 * exists and not null then proceeds with clicking the target 
	 * and writing the specified text to it.
	 * 
	 * @param target - To click and write to.
	 * @param text - Text to write to the target.
	 */
	private void checkNotNullClickWrite(String target, String text) {
		verifyThat(target, NodeMatchers.isNotNull());
		clickOn(target);
		write(text);
	}
}
