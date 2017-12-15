package com.raghu.ui.commons;

import org.springframework.security.core.context.SecurityContextHolder;

import com.raghu.navigator.UniversityNavigator;
import com.raghu.utils.StringUtils;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;

@org.springframework.stereotype.Component
public class UniversityMenuFactory implements UIComponentBuilder{

	 private class UniversityMenu extends VerticalLayout implements Property.ValueChangeListener{
		 
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Tree mainMenu;
				
		public UniversityMenu init() {
			mainMenu = new Tree();
			mainMenu.addValueChangeListener(this);
			return this;
		}
		
		public UniversityMenu layout() {
			setWidth("100%");
			setHeightUndefined();
			
			mainMenu.addItem(StringUtils.MENU_STUDENTS.getString());
			mainMenu.addItem(StringUtils.MENU_UNIVERSITY.getString());
			mainMenu.addItem("LOGOUT");
			
			mainMenu.expandItem(StringUtils.MENU_STUDENTS.getString());
			mainMenu.expandItem(StringUtils.MENU_UNIVERSITY.getString());
			mainMenu.expandItem("LOGOUT");
			
			mainMenu.addItem(StringUtils.MENU_ADD_STUDENT.getString());
			mainMenu.addItem(StringUtils.MENU_REMOVE_STUDENT.getString());
			mainMenu.setChildrenAllowed(StringUtils.MENU_ADD_STUDENT.getString(), false);
			mainMenu.setChildrenAllowed(StringUtils.MENU_REMOVE_STUDENT.getString(), false);
			
			mainMenu.addItem("Logout");
			mainMenu.setChildrenAllowed("Logout", false);
			mainMenu.setParent("Logout","LOGOUT");
			
			mainMenu.setParent(StringUtils.MENU_ADD_STUDENT.getString(),StringUtils.MENU_STUDENTS.getString());
			mainMenu.setParent(StringUtils.MENU_REMOVE_STUDENT.getString(),StringUtils.MENU_STUDENTS.getString());
			
			mainMenu.addItem(StringUtils.MENU_OPERATIONS.getString());
			mainMenu.setChildrenAllowed(StringUtils.MENU_OPERATIONS.getString(), false);
			mainMenu.setParent(StringUtils.MENU_OPERATIONS.getString(),StringUtils.MENU_UNIVERSITY.getString());
			
			addComponent(mainMenu);
			
			return this;
		}

		public void valueChange(ValueChangeEvent event) {
			
			String selectedItemPath = (String) event.getProperty().getValue();
			
			if(selectedItemPath == null) return;
			
			if(selectedItemPath.equals("Logout")) {
				SecurityContextHolder.clearContext();
				UI.getCurrent().getPage().setLocation("/university-web-1.3.6.RELEASE/login");
			}
			
			String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
			UniversityNavigator.navigate(path);
		}
	}
	
	public Component createComponent() {
		return new UniversityMenu().init().layout();
	}
	
}
