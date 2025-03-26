package finedustmain;

import java.awt.BorderLayout;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

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
	private JTextArea fineDustText;
	
	public Frame() {
		
		
		
		setTitle("미세먼지 정보");
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel inputPanel = new JPanel();
		
		JLabel yearTextLabel = new JLabel("연도명 : "); 
		yearText = new JTextField(20);
		
		JButton btn1 = new JButton("확인");
		
		inputPanel.add(yearTextLabel);
		inputPanel.add(yearText);
		inputPanel.add(btn1);
		
		fineDustText = new JTextArea();
		fineDustText.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(fineDustText);
		
		add(inputPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		
		btn1.addActionListener(e -> {
			System.out.println("버튼 확인");
			String year = yearText.getText();
			String result = Main.getDustDate(year);
			fineDustText.setText(result);
			
			
		});
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater(() -> {
			
			Frame frame = new Frame();
			frame.setVisible(true);
			
		});

		
		

	}

}



