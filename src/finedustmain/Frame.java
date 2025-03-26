package finedustmain;

import java.awt.BorderLayout;
import java.io.UnsupportedEncodingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Frame extends JFrame{

	private JTextField yearText;
	private JTextField localText;
	private JTextArea fineDustText;
	
	public Frame() {
				
		setTitle("미세먼지 정보");
		
		setSize(700, 400);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel inputPanel = new JPanel();
		
		JLabel yearTextLabel = new JLabel("연 도(2018 ~ 2025) : "); 		
		JLabel localTexttLabel = new JLabel("     지역명 : "); 
		
		yearText = new JTextField(10);
		localText = new JTextField(15);
		
		//JButton btn1 = new JButton("확인");
		JButton btn2 = new JButton("확인");
		
		inputPanel.add(yearTextLabel);
		inputPanel.add(yearText);	
		//inputPanel.add(btn1);
		
		inputPanel.add(localTexttLabel);
		inputPanel.add(localText);
		inputPanel.add(btn2);
		
		fineDustText = new JTextArea();
		fineDustText.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(fineDustText);

		add(inputPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
			
		btn2.addActionListener(e -> {
			
			String year = yearText.getText();
			String local = localText.getText();
			//String result = Main.getDustDate(year);
			String result =  DistrictMain.getDustDate(year, local);
			fineDustText.setText(result);
					
		});
				
	}
		
	public static void main(String[] args) throws UnsupportedEncodingException {
			
		SwingUtilities.invokeLater(() -> {
			
			Frame frame = new Frame();
			frame.setVisible(true);
			
		});
	}

}



