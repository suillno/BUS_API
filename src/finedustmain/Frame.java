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
				
		// 프레임 제목 입력
		setTitle("미세먼지 정보");
		
		// 프레임 사이즈 설정
		setSize(700, 400);
		
		// 창 닫는 기능 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 레이아웃 생성
		setLayout(new BorderLayout());
		
		// 입력 관련 패널 생성
		JPanel inputPanel = new JPanel();
		
		// 프레임에 표시할 텍스트 라벨 생성
		JLabel yearTextLabel = new JLabel("연 도(2018 ~ 2025) : "); 		
		JLabel localTextLabel = new JLabel("     지역명 : "); 
		
		// 연도와 지역명 입력값을 받을 수 있는 텍스트필드 생성
		yearText = new JTextField(10);
		localText = new JTextField(15);
		
		// 버튼 생성
		JButton btn1 = new JButton("확인");
		
		// inputPanel에 왼쪽부터 나열될 순서대로 추가 해준다
		// 연도 텍스트 -> 연도 입력 영역 -> 지역명 텍스트 -> 지역명 입력 영역 -> 버튼 순서
		inputPanel.add(yearTextLabel);
		inputPanel.add(yearText);	
		inputPanel.add(localTextLabel);
		inputPanel.add(localText);
		inputPanel.add(btn1);
		
		// 출력 표시할 영역 값 초기화
		fineDustText = new JTextArea();
		// 출력값 편집할 수 없도록 설정
		fineDustText.setEditable(false);
		
		// 출력 표시할 영역을 스크롤 형태로 만들 수 있도록 생성
		JScrollPane scrollPane = new JScrollPane(fineDustText);

		// 입력 관련 패널을 프레임 위쪽으로, 출력은 중앙에 놓이도록 함
		add(inputPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
			
		// 버튼을 눌렀을 때 동작
		btn1.addActionListener(e -> {
			
			// 연도 입력 영역에 입력된 값을 year 값에 넣어준다.
			String year = yearText.getText();
			// 지역명 입력 영역에 입력된 값을 local 값에 넣어준다.
			String local = localText.getText();
				
			// 출력할 문자열을 담을 변수 생성
			String result = "";
			// 지역명 입력이 없을 경우, 
			if (local.equals("")) { 
				
				// Main 클래스의 연도 한 개의 값만 받는 getDustDate 메서드의 결과값을 받아서 result에 입력
				result = Main.getDustDate(year);
				
			// 지역명 입력이 있을 경우, 
			}else {
				
				//DistrictMain 클래스의 연도와 지역명 두 개의 값을 받는 getDustDate 메서드의 결과값을 받아서 result에 입력
				result = DistrictMain.getDustDate(year, local);
				
			}
			// result에 담긴 String 문자열을 출력
			fineDustText.setText(result);
					
		});
				
	}
		
	public static void main(String[] args) throws UnsupportedEncodingException {
			
		SwingUtilities.invokeLater(() -> {
			
			// Frame 메서드 생성
			Frame frame = new Frame();
			// 생성한 프레임이 표시되도록 설정
			frame.setVisible(true);
			
		});
	}

}



