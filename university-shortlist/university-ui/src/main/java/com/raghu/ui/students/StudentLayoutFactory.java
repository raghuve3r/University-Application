package com.raghu.ui.students;

import org.springframework.beans.factory.annotation.Autowired;

import com.raghu.ui.commons.UniversityUI;
import com.raghu.utils.StudentStringUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SpringView(name=StudentLayoutFactory.NAME, ui=UniversityUI.class)
public class StudentLayoutFactory extends VerticalLayout implements View, StudentSaveListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "addstudent";
	
	@Autowired
	private AddStudentNameLayoutFactory mainLayoutFactory;
	
	@Autowired
	private ShowAllStudentsLayoutFactory showAllStudentsLayoutFactory;

	private TabSheet tabSheet;
	
	private void addLayout() {
		setMargin(true);
		
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component addStudentMainTab = mainLayoutFactory.createComponent(this);
		Component showStudentTab = showAllStudentsLayoutFactory.createComponent();
		
		tabSheet.addTab(addStudentMainTab,StudentStringUtils.MAIN_MENU.getString());
		tabSheet.addTab(showStudentTab,StudentStringUtils.SHOW_ALL_STUDENTS.getString());
	
		addComponent(tabSheet);
	}
	
	public void studentSaved() {
		showAllStudentsLayoutFactory.refreshTable();
	}
	
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
	}

}
