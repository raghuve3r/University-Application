package com.raghu.ui.students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.raghu.model.entity.Student;
import com.raghu.service.removestudent.RemoveStudentService;
import com.raghu.service.showallstudents.ShowAllStudentsService;
import com.raghu.ui.commons.UniversityUI;
import com.raghu.utils.NotificationMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = RemoveStudentLayoutFactory.NAME, ui = UniversityUI.class)
public class RemoveStudentLayoutFactory extends VerticalLayout implements View, Button.ClickListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "removestudent";
	private Grid removeStudentTable;
	private Button removeStudentButton;
	private List<Student> students;

	@Autowired
	private ShowAllStudentsService allStudentsService;
	
	@Autowired
	private RemoveStudentService removeStudentService;

	private void addLayout() {
		removeStudentButton = new Button(NotificationMessages.STUDENT_REMOVE_BUTTON.getString());

		setMargin(true);
		BeanItemContainer<Student> container = new BeanItemContainer<Student>(Student.class, students);
		
		removeStudentTable = new Grid(container);
		removeStudentTable.setColumnOrder("firstName","lastName","age","gender");
		removeStudentTable.removeColumn("id");
		removeStudentTable.setImmediate(true);
		removeStudentTable.setSelectionMode(SelectionMode.MULTI);
		
		removeStudentButton.addClickListener(this);
		removeStudentButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		addComponent(removeStudentTable);
		addComponent(removeStudentButton);
	}

	private void loadStudents() {
		students = allStudentsService.getAllStudents();
	}

	public void buttonClick(ClickEvent event) {
		MultiSelectionModel selectionModel = (MultiSelectionModel) removeStudentTable.getSelectionModel();
		
		for(Object selectedItem : selectionModel.getSelectedRows()) {
			Student student = (Student) selectedItem;
			removeStudentTable.getContainerDataSource().removeItem(student);
			removeStudentService.removeStudent(student);
		}
		Notification.show(NotificationMessages.STUDENT_REMOVE_SUCCESS_TITLE.getString(),
				NotificationMessages.STUDENT_REMOVE_SUCCESS_DESC.getString(), Type.WARNING_MESSAGE);
		
		removeStudentTable.getSelectionModel().reset();
	}

	public void enter(ViewChangeEvent event) {

		if (removeStudentTable != null)
			return;

		loadStudents();
		addLayout();

	}

}
