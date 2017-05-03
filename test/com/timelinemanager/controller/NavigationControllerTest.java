/**
 * 
 */
package com.timelinemanager.controller;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

import com.timelinemanager.TimelineManager;

import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name NavigationControllerTest.java
 */
public class NavigationControllerTest extends ApplicationTest{
	
	Parent mainNode;
	
	MenuBar navigationMenu;
	MenuItem file;

	@Override
	public void start(Stage stage) throws Exception {
		new TimelineManager().start(stage);
	}
	
	@Test
	public void allFileButtonsClickable() {
		moveCheckClick("File");
		moveCheckClick("New");
		moveCheckClick("Timeline");
		
		//Close create timeline window using ALT + F4
		push(KeyCode.ALT, KeyCode.F4);
		
		moveCheckClick("File");
		moveCheckClick("New");
		moveTo("Timeline");
		moveCheckClick("Event");
		
		//Close create event window using ALT + F4
		push(KeyCode.ALT, KeyCode.F4);
		
		moveCheckClick("File");
		moveCheckClick("Open");
		
		//Close open file window using ALT + F4
		push(KeyCode.ALT, KeyCode.F4);
		
		moveCheckClick("File");
		moveCheckClick("Save");
		
		moveCheckClick("File");
		moveCheckClick("Save as");
		
		moveCheckClick("File");
		moveCheckClick("Close");
		
		moveCheckClick("File");
		
		moveTo("Exit");
		verifyThat("Exit", NodeMatchers.hasText("Exit"));
	}
	
	@Test
	public void allEditButtonsClickable() {
		moveCheckClick("Edit");
		moveCheckClick("Timeline");
		moveCheckClick("Update Timeline");
		
		moveCheckClick("Edit");
		moveCheckClick("Timeline");
		
		moveTo("Update Timeline");
		moveCheckClick("Remove");
		
		moveCheckClick("Edit");
		moveCheckClick("Event");
		moveCheckClick("Update Event");
		
		moveCheckClick("Edit");
		moveCheckClick("Event");
		
		moveTo("Update Event");
		moveCheckClick("Delete");
	}
	
	@Test
	public void allHelpButtonsClickable() {
		moveCheckClick("Help");
		moveCheckClick("About");
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
		
		moveTo("#createTimelineButton");
		clickOn("#createTimelineButton");
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
		
		moveTo("#cancelCreateTimeline");
		clickOn("#cancelCreateTimeline");
		moveTo("OK");
		clickOn("OK");
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
