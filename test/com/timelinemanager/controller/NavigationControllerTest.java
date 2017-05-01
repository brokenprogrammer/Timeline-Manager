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
}
