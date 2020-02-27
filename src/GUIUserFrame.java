import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.*; 
import javax.swing.border.*; 
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.Color;

public class GUIUserFrame extends JFrame{
	
	private ActionController actionController;
	private StudentDetails selectedStudentDetails;
	private int updatedIndex;

	public GUIUserFrame() {  
		final JFrame jFrame = new JFrame("Queue Status"); 
		final JList<StudentDetails> studentList = new JList<>();
		final List<StudentDetails> studentDetailList = new ArrayList<>();

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel p1 = new JPanel();
		final JPanel p2 = new JPanel();

		final JLabel empty_indicatorJLabel = new JLabel("Queue is empty!");
		final JLabel header = new JLabel("NAME");
		final JLabel indicator = new JLabel("Red == Paused");

		final JButton addEntry = new JButton("AddEntry");
		final JButton pauseEntry = new JButton("PauseEntry");
		pauseEntry.setEnabled(false);
		pauseEntry.setFocusable(false);
		final JButton unpauseEntry = new JButton("UnpauseEntry");
		unpauseEntry.setEnabled(false);
		unpauseEntry.setFocusable(false);
		final JButton removeEntry = new JButton("RemoveEntry");
		removeEntry.setEnabled(false);
		removeEntry.setFocusable(false);

		p1.add(addEntry);
		p1.add(pauseEntry);
		p1.add(unpauseEntry);
		p1.add(removeEntry);
		p1.add(indicator);
		GroupLayout groupLayout = new GroupLayout(p1);
		groupLayout.setAutoCreateGaps(true);  
        groupLayout.setAutoCreateContainerGaps(true);  
        p1.setLayout(groupLayout);  
		
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
        		.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(addEntry)
            			.addComponent(pauseEntry)
            			.addComponent(unpauseEntry)
            			.addComponent(removeEntry))
        		.addComponent(indicator)
        		);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
        		.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(addEntry)
        				.addComponent(indicator))
        		.addComponent(pauseEntry).addComponent(unpauseEntry)
        		.addComponent(removeEntry));
      
		final ListSelectionListener listSelectionListener = new ListSelectionListener() {

			@Override
			public void valueChanged(final ListSelectionEvent e) {
				updatedIndex = studentList.getSelectedIndex();
				actionController.setUpdatedIndex(updatedIndex);
				removeEntry.setEnabled(false);
				pauseEntry.setEnabled(false);
				unpauseEntry.setEnabled(false);
				unpauseEntry.setFocusable(false);
				pauseEntry.setFocusable(false);
				if (studentDetailList.size() != updatedIndex) {
					selectedStudentDetails = studentDetailList.get(studentList.getSelectedIndex());
					removeEntry.setEnabled(true);
					removeEntry.setFocusable(true);
					if (selectedStudentDetails.isPaused()) {
						unpauseEntry.setEnabled(true);
						unpauseEntry.setFocusable(true);
					} else {	
						pauseEntry.setEnabled(true);
						pauseEntry.setFocusable(true);
					}
				}
			}
		};

		studentList.addListSelectionListener(listSelectionListener);
		
		actionController = new ActionController(studentDetailList);
		studentList.setCellRenderer(new PausedCellRenderer(actionController));
		final ActionListener actionListener = new ButtonActionListeners(actionController);
		addEntry.addActionListener(actionListener);
		removeEntry.addActionListener(actionListener);
		pauseEntry.addActionListener(actionListener);
		unpauseEntry.addActionListener(actionListener);


		jFrame.setSize(600, 300);
		jFrame.setVisible(true);

		studentList.setModel(actionController);
		actionController.setQueueListListenerCallBack(listSelectionListener);

		actionController.addEntry(new StudentDetails("Avinash", "s", "ss", false));
		actionController.addEntry(new StudentDetails("Sai Kiran", "s", "ss", true));
		actionController.addEntry(new StudentDetails("Moe", "s", "ss", false));
		actionController.addEntry(new StudentDetails("Mahesh", "s", "ss", false));
		actionController.addEntry(new StudentDetails("Swathi", "s", "ss", false));

		// studentList.setPreferredSize(new Dimension(400, 360));
		studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentList.setLayoutOrientation(JList.VERTICAL);
		JScrollPane tablePane = new JScrollPane(studentList);
		tablePane.setPreferredSize(new Dimension(300, 150));

		p2.add(tablePane);
		
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 10);
		
		header.setFont(f);
		header.setFont (header.getFont ().deriveFont (20.0f));
		header.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	    tablePane.setViewportView(studentList);
	    tablePane.setColumnHeaderView(header);


	    jFrame.add(p1);
	    jFrame.setLocationRelativeTo(null);
	    jFrame.add(p2, BorderLayout.CENTER);
	    jFrame.add(p1, BorderLayout.NORTH);
	    jFrame.add(empty_indicatorJLabel, BorderLayout.SOUTH);
	    jFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(final WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(final WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(final WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(final WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(final WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(final WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(final WindowEvent e) {
				// TODO Auto-generated method stub

				if (studentList.getModel().getSize() == 0) {
					empty_indicatorJLabel.setVisible(true);
				} else {
					empty_indicatorJLabel.setVisible(false);
				}

			}
		});

		
	    jFrame.setSize(500,300);  
	    jFrame.setVisible(true);
	    jFrame.setLocationRelativeTo(null);

}
}