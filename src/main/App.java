package main;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import models.Address;
import models.Brush;
import models.Employee;
import models.Render;
import models.Software;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;    

public class App implements ActionListener{ 
	DefaultTableModel employeesTableModel, softwareTableModel;
	JTable jtEmployees, jtSoftware;
	JTabbedPane tp;
	int selectedRowE, selectedRowT;
	ArrayList<Employee> employeesData = new ArrayList<>(); 
	ArrayList<Software> softwareData = new ArrayList<>(); 
	JFrame f;
	JButton addToolbarBtn, editToolbarBtn, deleteToolbarBtn;
	JMenuItem newMenuItem, openMenuItem, exitMenuItem, editMenuItem, deleteMenuItem, aboutMenuItem, employeeMenuItem, softwareMenuItem;    
	public App(){    
		f = new JFrame();    
		newMenuItem = new JMenu("New");    
		openMenuItem = new JMenuItem("Open");    
		exitMenuItem = new JMenuItem("Exit");    
		editMenuItem = new JMenuItem("Edit");    
		deleteMenuItem = new JMenuItem("Delete");    
		aboutMenuItem = new JMenuItem("About");
		employeeMenuItem = new JMenuItem("Employee");
		softwareMenuItem = new JMenuItem("Software");
		  

		employeeMenuItem.addActionListener(this);  
		softwareMenuItem.addActionListener(this);
		deleteMenuItem.addActionListener(this);
		editMenuItem.addActionListener(this);

		JMenuBar mb = new JMenuBar();    
		JMenu file = new JMenu("File");    
		JMenu edit = new JMenu("Edit");    
		JMenu help = new JMenu("Help"); 
		newMenuItem.add(employeeMenuItem);newMenuItem.add(softwareMenuItem);
		file.add(newMenuItem);file.add(openMenuItem);file.add(exitMenuItem);
		edit.add(editMenuItem);edit.add(deleteMenuItem); 
		help.add(aboutMenuItem);
		mb.add(file);mb.add(edit);mb.add(help);    
		
		JToolBar tb = new JToolBar();  
        tb.setRollover(true);  
        addToolbarBtn = new JButton("Add");
        editToolbarBtn = new JButton("Edit");
        deleteToolbarBtn = new JButton("Delete");
        tb.add(addToolbarBtn);  
        tb.add(editToolbarBtn);
        tb.add(deleteToolbarBtn);
        addToolbarBtn.addActionListener(this);
        editToolbarBtn.addActionListener(this);
        deleteToolbarBtn.addActionListener(this);
        
		
		String softwareColumn[] = {"Name", "Brushes", "File Format", "Animation Tools", "Render"};         
		softwareTableModel = new DefaultTableModel(softwareColumn, 0);
		jtSoftware = new JTable(softwareTableModel);            
		JScrollPane spSoftware = new JScrollPane(jtSoftware);    
		
		jtSoftware.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            selectedRowT = jtSoftware.getSelectedRow();
	        }
	    });
		
		String employeesColumn[]={"First Name","Last Name","JMBG", "Date of Birth", "Email", "Address", "Role", "Software"};  
		employeesTableModel = new DefaultTableModel(employeesColumn, 0);
		jtEmployees = new JTable(employeesTableModel);            
		JScrollPane spEmployees = new JScrollPane(jtEmployees);    
		
		jtEmployees.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            selectedRowE = jtEmployees.getSelectedRow();
	        }
	    });
		
		JPanel p1 = new JPanel();  
        p1.add(spEmployees);
        JPanel p2 = new JPanel();  
        p2.add(spSoftware);
        tp = new JTabbedPane();  
        tp.setBounds(50,50,200,200);  
        tp.add("Employees",p1);  
        tp.add("Software",p2);   
        
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        f.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(400, 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.LINE_AXIS));
        statusPanel.add(Box.createHorizontalGlue());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        JLabel statusLabel = new JLabel(dtf.format(now).toString(), SwingConstants.RIGHT);
        statusPanel.add(statusLabel);


		f.add(mb);    
		f.setJMenuBar(mb);  
		f.add(tp);
		f.add(tb, BorderLayout.NORTH);
		Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
		f.setSize((int)(size.getWidth() * 0.75),(int)(size.getHeight() * 0.75));    
		f.setLocationRelativeTo(null);
		f.setVisible(true);    
	}     
	public void RefreshSoftwareTable() {
		for (int i = jtSoftware.getRowCount() - 1; i >= 0; i--) {
			softwareTableModel.removeRow(i);
		}
		
		softwareData.forEach((software) -> {


			softwareTableModel.addRow(new Object[]{
					software.getName(),
					software.getBrushes().toString(),
					software.getFileFormat(),
					software.getAnimationTools(),
					software.getRender().toString()
              });
		});
	}
	public void RefreshEmployeesTable() {
		for (int i = jtEmployees.getRowCount() - 1; i >= 0; i--) {
			employeesTableModel.removeRow(i);
		}
		
		employeesData.forEach((employee) -> {
			String address = "";
			Address empolyeeAddress = employee.getAddress();
			
			address = empolyeeAddress.getCity() + " " + empolyeeAddress.getStreet() + " " + empolyeeAddress.getNumber();
			
			employeesTableModel.addRow(new Object[]{
					employee.getFirstName(),
					employee.getLastName(),
					employee.getJmbg(),
					employee.getDob(),
					employee.getEmail(),
					address,
					employee.getRole(),
					employee.getSoftware().toString()
              });
		});
	}


	public void actionPerformed(ActionEvent e) {    
		int selectedIndex = tp.getSelectedIndex();
		String tabTitle = tp.getTitleAt(selectedIndex);

		if(e.getSource()==softwareMenuItem || (e.getSource()==addToolbarBtn && tabTitle == "Software")) {

			ArrayList<Brush> brushes = new ArrayList<>(); 
			Brush b1 = new Brush("standard", "Create", "black");
			Brush b2 = new Brush("smooth", "Turbosmooth", "red");
			Brush b3 = new Brush("remove", "Delete", "blue");
			Brush b4 = new Brush("deform", "Reshape", "white");
			brushes.add(b1);
			brushes.add(b2);
			brushes.add(b3);
			brushes.add(b4);
			DefaultListModel<String> brushesNames = new DefaultListModel<>();
			brushes.forEach((brush) -> {
				brushesNames.addElement(brush.getName());
			});
			
			ArrayList<Render> renders = new ArrayList<>(); 
			Render r1 = new Render("render 1", "Translucency", "Physical", "Plane");
			Render r2 = new Render("render 2", "Bump", "Target", "Box");
			Render r3 = new Render("render 3", "Displacement", "Standard", "Sphere");
			Render r4 = new Render("render 4", "Opacity", "Dolly", "Teapot");
			renders.add(r1);
			renders.add(r2);
			renders.add(r3);
			renders.add(r4);
			DefaultListModel<String> renderNames = new DefaultListModel<>();
			renders.forEach((render) -> {
				renderNames.addElement(render.getName());
			});
		
		
			JList<String> brushesJList = new JList<>(brushesNames);
			JTextField nameField = new JTextField();
	        JTextField fileFormatFied = new JTextField();
	        JTextField animationToolsField = new JTextField();
	        JList<String> renderJList = new JList<>(renderNames);
	        JPanel panel = new JPanel(new GridLayout(0, 1));
	        panel.add(new JLabel("Name"));
	        panel.add(nameField);
	        panel.add(new JLabel("File Format"));
	        panel.add(fileFormatFied);
	        panel.add(new JLabel("Animation Tools"));
	        panel.add(animationToolsField);
	        panel.add(new JLabel("Brushes"));
	        panel.add(brushesJList);
	        panel.add(new JLabel("Renders"));
	        panel.add(renderJList);
       
	        int result = JOptionPane.showConfirmDialog(null, panel, "Create Software Form",
	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if (result == JOptionPane.OK_OPTION) {
	        	String name = nameField.getText();
		        String fileFormat = fileFormatFied.getText();
		        String animationTools = animationToolsField.getText();
		        ArrayList<String> brushesList = (ArrayList<String>) brushesJList.getSelectedValuesList();
		        int[] brushesSelectedIndices = brushesJList.getSelectedIndices();
		        ArrayList<String> rendersList = (ArrayList<String>) renderJList.getSelectedValuesList();
		        int[] rendersSelectedIndices = renderJList.getSelectedIndices();
		        Software newSoftware = new Software(name, brushesList, brushesSelectedIndices, fileFormat, animationTools, rendersList, rendersSelectedIndices);
		        softwareData.add(newSoftware);
		        
		        RefreshSoftwareTable();
	        } else {
	            System.out.println("Cancelled");
	        }
            
	        panel.setVisible(true);
		}
		if(e.getSource()==employeeMenuItem || (e.getSource()==addToolbarBtn && tabTitle == "Employees") ) {

			JTextField firstNameField = new JTextField();
	        JTextField lastNameField = new JTextField();
	        JTextField jmbgField = new JTextField();
	        JTextField dobField = new JTextField();
	        JTextField emailField = new JTextField();
	        JTextField cityField = new JTextField();
	        JTextField streetField = new JTextField();
	        JTextField numberField = new JTextField();
	        String roles[] = { "modelator", "riger", "animator", "ilustrator" };
			JComboBox<String> rolesComboBox = new JComboBox<String>(roles);
			DefaultListModel<String> softwareList = new DefaultListModel<>();
			softwareData.forEach((software) -> {
				softwareList.addElement(software.getName());
			});
		
			 JList<String> softwareJList = new JList<>(softwareList);
	        JPanel panel = new JPanel(new GridLayout(0, 1));
	        panel.add(new JLabel("First Name"));
	        panel.add(firstNameField);
	        panel.add(new JLabel("Last Name"));
	        panel.add(lastNameField);
	        panel.add(new JLabel("JMBG"));
	        panel.add(jmbgField);
	        panel.add(new JLabel("City"));
	        panel.add(cityField);
	        panel.add(new JLabel("Street"));
	        panel.add(streetField);
	        panel.add(new JLabel("Number"));
	        panel.add(numberField);
	        panel.add(new JLabel("Date of birth"));
	        panel.add(dobField);
	        panel.add(new JLabel("Email"));
	        panel.add(emailField);
	        panel.add(new JLabel("Role"));
	        panel.add(rolesComboBox);
	        panel.add(new JLabel("Software"));
	        panel.add(softwareJList);
	        
	        int result = JOptionPane.showConfirmDialog(null, panel, "Create Employee Form",
	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if (result == JOptionPane.OK_OPTION) {
	        	String firstName = firstNameField.getText();
		        String lastName = lastNameField.getText();
		        String jmbg = jmbgField.getText();
		        String dob = dobField.getText();
		        String email = emailField.getText();
		        String city = cityField.getText();
		        String street = streetField.getText();
		        String number = numberField.getText();
		        String role = rolesComboBox.getSelectedItem().toString();
		        Address address = new Address(city, street, number);
		        ArrayList<String> software = (ArrayList<String>) softwareJList.getSelectedValuesList();
		        int[] softwareSelectedIndices = softwareJList.getSelectedIndices();
		        Employee newEmployee = new Employee(firstName, lastName, jmbg, dob, email, address, role, software, softwareSelectedIndices);
		        employeesData.add(newEmployee);
		        
		        RefreshEmployeesTable();
	        } else {
	            System.out.println("Cancelled");
	        }
            
	        panel.setVisible(true);
		}
		if(e.getSource()==editMenuItem || e.getSource()==editToolbarBtn) {

			if (tabTitle == "Employees") {
				Employee currentEmployee = employeesData.get(selectedRowE);
				JTextField firstNameField = new JTextField(currentEmployee.getFirstName());
		        JTextField lastNameField = new JTextField(currentEmployee.getLastName());
		        JTextField jmbgField = new JTextField(currentEmployee.getJmbg());
		        JTextField dobField = new JTextField(currentEmployee.getDob());
		        JTextField emailField = new JTextField(currentEmployee.getEmail());
		        Address currentEmpolyeeAddress = currentEmployee.getAddress();
		        JTextField cityField = new JTextField(currentEmpolyeeAddress.getCity());
		        JTextField streetField = new JTextField(currentEmpolyeeAddress.getStreet());
		        JTextField numberField = new JTextField(currentEmpolyeeAddress.getNumber());
		        String roles[] = { "modelator", "riger", "animator", "ilustrator" };
		        int getIndex = Arrays.asList(roles).indexOf(currentEmployee.getRole());
				JComboBox<String> rolesComboBox = new JComboBox<String>(roles);
				rolesComboBox.setSelectedIndex(getIndex);
				DefaultListModel<String> softwareList = new DefaultListModel<>();
				softwareData.forEach((software) -> {
					softwareList.addElement(software.getName());
				});
			
				 JList<String> softwareJList = new JList<>(softwareList);
				 softwareJList.setSelectedIndices(currentEmployee.getSoftwareSelectedIndices());
	
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.add(new JLabel("First Name"));
		        panel.add(firstNameField);
		        panel.add(new JLabel("Last Name"));
		        panel.add(lastNameField);
		        panel.add(new JLabel("JMBG"));
		        panel.add(jmbgField);
		        panel.add(new JLabel("City"));
		        panel.add(cityField);
		        panel.add(new JLabel("Street"));
		        panel.add(streetField);
		        panel.add(new JLabel("Number"));
		        panel.add(numberField);
		        panel.add(new JLabel("Date of birth"));
		        panel.add(dobField);
		        panel.add(new JLabel("Email"));
		        panel.add(emailField);
		        panel.add(new JLabel("Role"));
		        panel.add(rolesComboBox);
		        panel.add(new JLabel("Software"));
		        panel.add(softwareJList);
		        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Employee Form",
		            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		        if (result == JOptionPane.OK_OPTION) {
		        	String firstName = firstNameField.getText();
			        String lastName = lastNameField.getText();
			        String jmbg = jmbgField.getText();
			        String dob = dobField.getText();
			        String email = emailField.getText();
			        String city = cityField.getText();
			        String street = streetField.getText();
			        String number = numberField.getText();
			        String role = rolesComboBox.getSelectedItem().toString();
			        Address address = new Address(city, street, number);
			        ArrayList<String> software = (ArrayList<String>) softwareJList.getSelectedValuesList();
			        int[] softwareSelectedIndices = softwareJList.getSelectedIndices();
			        Employee newEmployee = new Employee(firstName, lastName, jmbg, dob, email, address, role, software, softwareSelectedIndices);
			        employeesData.set(selectedRowE, newEmployee);
			        RefreshEmployeesTable();
		        }
	            
		        panel.setVisible(true);
			} else {
				Software currentSoftware = softwareData.get(selectedRowT);
				
				ArrayList<String> currentBrushes = currentSoftware.getBrushes();
				ArrayList<Brush> brushes = new ArrayList<>(); 
				Brush b1 = new Brush("standard", "asd", "black");
				Brush b2 = new Brush("smooth", "asd", "red");
				Brush b3 = new Brush("remove", "asd", "blue");
				Brush b4 = new Brush("deform", "asd", "white");
				brushes.add(b1);
				brushes.add(b2);
				brushes.add(b3);
				brushes.add(b4);
				
				DefaultListModel<String> brushesNames = new DefaultListModel<>();
				brushes.forEach((brush) -> {
					brushesNames.addElement(brush.getName());
				});
				currentBrushes.forEach((brush) -> {
					
				});
				
				ArrayList<Render> renders = new ArrayList<>(); 
				Render r1 = new Render("render 1", "asd", "asd", "asd");
				Render r2 = new Render("render 2", "asd", "asd", "asd");
				Render r3 = new Render("render 3", "asd", "asd", "asd");
				Render r4 = new Render("render 4", "asd", "asd", "asd");
				renders.add(r1);
				renders.add(r2);
				renders.add(r3);
				renders.add(r4);
				DefaultListModel<String> renderNames = new DefaultListModel<>();
				renders.forEach((render) -> {
					renderNames.addElement(render.getName());
				});
				
			
				JList<String> brushesJList = new JList<>(brushesNames);
				brushesJList.setSelectedIndices(currentSoftware.getBrushesSelectedIndices());
				JTextField nameField = new JTextField(currentSoftware.getName());
		        JTextField fileFormatFied = new JTextField(currentSoftware.getFileFormat());
		        JTextField animationToolsField = new JTextField(currentSoftware.getAnimationTools());
				JList<String> renderJList = new JList<>(renderNames);
				renderJList.setSelectedIndices(currentSoftware.getRendersSelectedIndices());
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.add(new JLabel("Name"));
		        panel.add(nameField);
		        panel.add(new JLabel("File Format"));
		        panel.add(fileFormatFied);
		        panel.add(new JLabel("Animation Tools"));
		        panel.add(animationToolsField);
		        panel.add(new JLabel("Brushes"));
		        panel.add(brushesJList);
		        panel.add(new JLabel("Renders"));
		        panel.add(renderJList);
	       
		        int result = JOptionPane.showConfirmDialog(null, panel, "Create Software Form",
		            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		        if (result == JOptionPane.OK_OPTION) {
		        	String name = nameField.getText();
			        String fileFormat = fileFormatFied.getText();
			        String animationTools = animationToolsField.getText();
			        ArrayList<String> brushesList = (ArrayList<String>) brushesJList.getSelectedValuesList();
			        int[] brushesSelectedIndices = brushesJList.getSelectedIndices();
			        ArrayList<String> rendersList = (ArrayList<String>) renderJList.getSelectedValuesList();
			        int[] rendersSelectedIndices = renderJList.getSelectedIndices();
			        Software newSoftware = new Software(name, brushesList, brushesSelectedIndices, fileFormat, animationTools, rendersList, rendersSelectedIndices);
			        softwareData.set(selectedRowT, newSoftware);

			        RefreshSoftwareTable();
		        } else {
		            System.out.println("Cancelled");
		        }
	            
		        panel.setVisible(true);
			}

		}
		if(e.getSource()==deleteMenuItem || e.getSource()==deleteToolbarBtn) {

			JPanel panel = new JPanel(new GridLayout(0, 1));
			panel.add(new JLabel("Are your sure ?"));
			if (tabTitle == "Employees") {
				int result = JOptionPane.showConfirmDialog(null, panel, "Remove Employee",
			            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				 if (result == JOptionPane.OK_OPTION) {
					 employeesData.remove(selectedRowE);
					 RefreshEmployeesTable();
				 } 
			} else {
				int result = JOptionPane.showConfirmDialog(null, panel, "Remove Software",
			            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				 if (result == JOptionPane.OK_OPTION) {
					softwareData.remove(selectedRowT);
					RefreshSoftwareTable();
				 } 
			}
			panel.setVisible(true);
			
		}

	}     
	public static void main(String[] args) {    
	    new App();    
	}    
}    