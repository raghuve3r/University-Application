package com.raghu.ui.students;

import com.raghu.ui.commons.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.raghu.model.entity.Student;
import com.raghu.service.showallstudents.ShowAllStudentsService;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class ShowAllStudentsLayoutFactory implements UIComponentBuilder {

	private List<Student> students;
	private BeanItemContainer<Student> container;

	private class ShowAllStudentLayout extends VerticalLayout {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Grid studentsTable;

		public ShowAllStudentLayout init() {

			setMargin(true);

			container = new BeanItemContainer<Student>(Student.class, students);

			studentsTable = new Grid(container);
			studentsTable.setColumnOrder("firstName", "lastName", "age", "gender");
			studentsTable.removeColumn("id");
			studentsTable.removeColumn("university");
			studentsTable.setImmediate(true);

			return this;
		}

		public ShowAllStudentLayout load() {

			students = showAllStudentsService.getAllStudents();
			
			return this;
		}

		public ShowAllStudentLayout layout() {

			addComponent(studentsTable);
			
			return this;
		}

	}
	
	public void refreshTable() {
		students = showAllStudentsService.getAllStudents();
		container.removeAllItems();
		container.addAll(students);
	}
	
	@Autowired
	private ShowAllStudentsService showAllStudentsService;

	public Component createComponent() {
		return new ShowAllStudentLayout().load().init().layout();
	}

}
