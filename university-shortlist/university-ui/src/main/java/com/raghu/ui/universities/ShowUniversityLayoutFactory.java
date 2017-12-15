package com.raghu.ui.universities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.raghu.model.entity.University;
import com.raghu.service.showalluniversities.ShowAllUniversitiesService;
import com.raghu.ui.commons.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class ShowUniversityLayoutFactory implements UIComponentBuilder {

	private List<University> universities;
	private BeanItemContainer<University> container;
	
	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;

	private class ShowUniversityLayout extends VerticalLayout {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Grid universityTable;
		
		public ShowUniversityLayout init() {

			setMargin(true);
			
			container = new BeanItemContainer<University>(University.class, universities);
			
			universityTable = new Grid(container);
			universityTable.setColumnOrder("universityName","universityCity","universityCountry");
			universityTable.removeColumn("id");
			universityTable.setImmediate(true);
			
			return this;
		}

		public ShowUniversityLayout load() {
			universities = showAllUniversitiesService.getAllUniversity(); 
			return this;
		}

		public ShowUniversityLayout layout() {
			
			addComponent(universityTable);
			return this;

		}
	}
	
	public void refreshTable() {
		universities = showAllUniversitiesService.getAllUniversity();
		container.removeAllItems();
		container.addAll(universities);
	}

	public Component createComponent() {
		return new ShowUniversityLayout().load().init().layout();
	}

}
