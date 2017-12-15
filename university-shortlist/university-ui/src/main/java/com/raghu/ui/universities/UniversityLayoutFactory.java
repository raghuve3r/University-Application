package com.raghu.ui.universities;

import org.springframework.beans.factory.annotation.Autowired;

import com.raghu.ui.commons.UniversityUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = UniversityLayoutFactory.NAME, ui = UniversityUI.class)
public class UniversityLayoutFactory extends VerticalLayout implements View, UniversitySavedListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "operations";

	private TabSheet tabSheet;

	@Autowired
	private AddUniveristyLayoutFactory addUniveristyLayoutFactory;

	@Autowired
	private ShowUniversityLayoutFactory showUniversityLayoutFactory;
	
	@Autowired
	private StatisticsUniversityLayoutFactory statisticsUniversityLayoutFactory;

	private void addLayout() {
		setMargin(true);

		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");

		Component addUniversityTab = addUniveristyLayoutFactory.createComponent(this);
		Component showAllUniversity = showUniversityLayoutFactory.createComponent();
		Component showStatistics = statisticsUniversityLayoutFactory.createComponent();

		tabSheet.addTab(addUniversityTab, "ADD");
		tabSheet.addTab(showAllUniversity, "SHOW ALL");
		tabSheet.addTab(showStatistics, "STATS");

		addComponent(tabSheet);
	}

	public void universitySaved() {
		showUniversityLayoutFactory.refreshTable();
		statisticsUniversityLayoutFactory.refresh();
	}

	public void enter(ViewChangeEvent event) {

		removeAllComponents();

		addLayout();
	}

}
