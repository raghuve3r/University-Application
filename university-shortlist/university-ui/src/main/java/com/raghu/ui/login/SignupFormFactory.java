package com.raghu.ui.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.raghu.service.security.RegisterUserService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class SignupFormFactory {

	@Autowired
	private RegisterUserService registerUserService;
	
	private class SignupForm{
		private VerticalLayout root;
		private Panel panel;
		private TextField username;
		private PasswordField password;
		private PasswordField passwordAgain;
		private Button saveButton;
		
		public SignupForm init() {
			
			root = new VerticalLayout();
			root.setMargin(true);
			root.setHeight("100%");
			
			panel = new Panel("Signup");
			panel.setSizeUndefined();
			
			saveButton = new Button("Save");
			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			
			username = new TextField("Username");
			password = new PasswordField("Password");
			passwordAgain = new PasswordField("Re-enter password");
			
			saveButton.addClickListener(new Button.ClickListener() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void buttonClick(ClickEvent event) {
					
					if(!password.getValue().equals(passwordAgain.getValue())) {
						Notification.show("Error","Passwords do not match!", Type.ERROR_MESSAGE);
						return;
					}
					
					registerUserService.save(username.getValue(), password.getValue());
					UI.getCurrent().getPage().setLocation("/university-web-1.3.6.RELEASE/login");
				}
			});
			
			return this;
			
		}
		
		public Component layout() {
			root.addComponent(panel);
			root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
			
			FormLayout signupLayout = new FormLayout();
			signupLayout.addComponent(username);
			signupLayout.addComponent(password);
			signupLayout.addComponent(passwordAgain);
			
			signupLayout.addComponent(saveButton);
			signupLayout.setSizeUndefined();
			signupLayout.setMargin(true);
			
			panel.setContent(signupLayout);
			
			return root;
		}
	}
	
	public Component createComponent() {
		return new SignupForm().init().layout();
	}
}
