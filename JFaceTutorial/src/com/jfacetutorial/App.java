package com.jfacetutorial;


import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.jfacetutorial.controller.Controller;
import com.jfacetutorial.view.View;

import org.eclipse.jface.action.MenuManager;


public class App extends ApplicationWindow {

	private Controller controller;
	private View view;
	private TopMenu topMenu;

	
	public App() {
		super(null);
		addMenuBar();
	}
	

	@Override
	protected MenuManager createMenuManager() {
		Display display = new Display();
		Shell shell = new Shell(display);
		topMenu = new TopMenu(shell);
		return topMenu.createMenuManager();
	}
	
	@Override
	protected Control createContents(Composite parent) {
		view = new View();
		
		Composite composite = view.createWidgets(parent);
		controller = new Controller(view);
		createButtonsListeners();
	    return composite;
	}

	private void createButtonsListeners() {
		view.addAllUser(controller::getAllUsers);
		view.addSaveAction(controller::save);
		view.addDeleteAction(controller::delete);
		topMenu.addAllUser(controller::getAllUsers);
		topMenu.setAllUser(controller::setAllUsers);
		topMenu.addTableViewer(view::getTableViewer);
	}
	
	public void run() {
		
		setBlockOnOpen(true);
		open();
		Display.getCurrent().dispose();
	}	
}
