package com.raghu.ui.universities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.raghu.model.entity.University;
import com.raghu.service.showalluniversities.ShowAllUniversitiesService;
import com.raghu.service.universitystatistics.UniversityStatisticsService;
import com.raghu.ui.commons.UIComponentBuilder;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


@org.springframework.stereotype.Component
public class StatisticsUniversityLayoutFactory implements UIComponentBuilder{

	private List<University> universities;
	
	private StatisticsUniversityLayout statisticsUniversityLayout;
	
	@Autowired
	private UniversityStatisticsService universityStatisticsService;
	
	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;
	
	private class StatisticsUniversityLayout extends VerticalLayout{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public StatisticsUniversityLayout load() {
			universities = showAllUniversitiesService.getAllUniversity();
			
			return this;
		}
		
		public StatisticsUniversityLayout layout() {
			setMargin(true);
			
			for(University university: universities) {
				int numOfStudent = universityStatisticsService.getNumStudentsForUniversity(university.getId());
				Label label = new Label("<p><b>"+university.getUniversityName()+"</b>"+" - "+numOfStudent+" student(s)"+"</p>", ContentMode.HTML);
				addComponent(label);
			}
			
			return this;
		}
		
	}
	
	public void refresh() {
		if(statisticsUniversityLayout == null) return;
		
		statisticsUniversityLayout.removeAllComponents();
		statisticsUniversityLayout.load();
		statisticsUniversityLayout.layout();
	}
	
	public Component createComponent() {
		statisticsUniversityLayout = new StatisticsUniversityLayout();
		return statisticsUniversityLayout.load().layout();
	}

	

}
