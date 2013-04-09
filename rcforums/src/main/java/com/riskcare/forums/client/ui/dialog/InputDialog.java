package com.riskcare.forums.client.ui.dialog;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class InputDialog extends Window {
	
	private static final long serialVersionUID = 1L;
	
	private final GridLayout gl = new GridLayout(2,2);
	
	private Label lblNodeName = new Label("Enter categoryName: ");
	private TextField txtNodeName = new TextField();
	private Button btnOK = new Button("OK");
	private Button btnCancel = new Button("Cancel");
	
	public InputDialog() {
		gl.addComponent(lblNodeName);
		gl.addComponent(txtNodeName);
		gl.addComponent(btnOK);
		gl.addComponent(btnCancel);
	}
	
	public void show() {
		show();
		setModal(true);
	}
}
