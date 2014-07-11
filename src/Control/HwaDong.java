package Control;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

import MakeShape.Making;


public class HwaDong extends JFrame implements ActionListener
{

	private EventHandle eventhandle; // 주요이벤트들을 관리할 이벤트핸들러 객체
	private final Color firstColor = Color.BLACK;       //기본색을 검은 색으로 설정
	private Making Layer = new Making(firstColor);; 	// Making에 따라 그림을 그릴 객체
	private Label nowColorLabel;				
	private JLabel nowStatusLabel;
	private JLabel nowXYLabel;	

	//툴버튼
	private JButton selectButton, lineButton, rectButton, ovalButton, triButton, starButton, resizeButton, moveButton, deleteButton, 
	fillcolorButton, undoButton, reundoButton, backButton, penButton, eraserButton, arrowButton;

	//팔레트버튼
	private JButton whiteButton, blackButton, redButton, blueButton, greenButton, yellowButton; 
	
	//메뉴버튼
	private JButton newButton, openButton, saveButton, helpButton; 

	//캔버스역할을 하는 패널
	public CustomPanel DrawPanel;

	//파일입출력 관련
	private String fileName = "제목없음.cha";
	private File saveFile;

	private boolean isSelected = true;

	//메인매쏘드
	public static void main(String args[])
	{
		HwaDong application= new HwaDong();
		application.setVisible(true);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                       
		application.setTitle("HwaDong Production");
		application.setSize(950,620);
		
	}

	// HwaDong 생성자 레이아웃을 만듦
	public HwaDong()
	{
		super();
		eventhandle = new EventHandle();	
		setTitle(fileName);

		//윈도우 스타일로 룩앤필 설정
		try { 
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel"); 
			SwingUtilities.updateComponentTreeUI(this); 
		} 
		catch (Exception e) { }
		
		
		//툴바 패널 생성
		JPanel ToolBar = new JPanel();
		ToolBar.setBorder(BorderFactory.createLoweredBevelBorder());
		
		
		//메뉴 넣는 패널 생성
		JPanel ToolBar_Menu=new JPanel();
		ToolBar_Menu.setBorder(BorderFactory.createRaisedBevelBorder());
		ToolBar_Menu.setLayout(new GridLayout(2,2));
		ToolBar_Menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		//버튼을 생성하고 버튼 설정
		newButton = new JButton(new ImageIcon("new.jpg"));
		newButton.setToolTipText("새로만들기");
		newButton.addActionListener(this);
		ToolBar_Menu.add(newButton);


		openButton = new JButton(new ImageIcon("open.jpg"));
		openButton.setToolTipText("열기");
		openButton.addActionListener(this);
		ToolBar_Menu.add(openButton);


		saveButton = new JButton(new ImageIcon("save.jpg"));
		saveButton.setToolTipText("저장");
		saveButton.addActionListener(this);
		ToolBar_Menu.add(saveButton);


		helpButton = new JButton(new ImageIcon("help.jpg"));
		helpButton.setToolTipText("단축키");
		helpButton.addActionListener(this);
		ToolBar_Menu.add(helpButton);

		
		
		//툴 넣는 패널생성
		JPanel ToolBar_Tool = new JPanel();
		ToolBar_Tool.setBorder(BorderFactory.createRaisedBevelBorder());
		ToolBar_Tool.setLayout(new GridLayout(6,3));
		ToolBar_Tool.setCursor(new Cursor(Cursor.HAND_CURSOR));


		lineButton = new JButton(new ImageIcon("Line.jpg"));
		lineButton.setToolTipText("선그리기");
		lineButton.addActionListener(this);
		lineButton.addActionListener(eventhandle);
		lineButton.setActionCommand("11");
		lineButton.setMnemonic('l');
		ToolBar_Tool.add(lineButton); 
		
		
		penButton = new JButton(new ImageIcon("pen.jpg"));
		penButton.setToolTipText("펜");
		penButton.addActionListener(this);
		penButton.addActionListener(eventhandle);
		penButton.setActionCommand("18");
		penButton.setMnemonic('p');
		ToolBar_Tool.add(penButton); 


		ovalButton = new JButton(new ImageIcon("Oval.jpg"));
		ovalButton.setToolTipText("원그리기");
		ovalButton.addActionListener(this);
		ovalButton.addActionListener(eventhandle);
		ovalButton.setActionCommand("12");
		ovalButton.setMnemonic('o');
		ToolBar_Tool.add(ovalButton); 


		rectButton = new JButton(new ImageIcon("Rect.jpg"));
		rectButton.setToolTipText("사각형그리기");
		rectButton.addActionListener(this);
		rectButton.addActionListener(eventhandle);
		rectButton.setActionCommand("14");
		rectButton.setMnemonic('r');
		ToolBar_Tool.add(rectButton);


		triButton = new JButton(new ImageIcon("tri.jpg"));
		triButton.setToolTipText("삼각형그리기");
		triButton.addActionListener(this);
		triButton.addActionListener(eventhandle);
		triButton.setActionCommand("15");
		triButton.setMnemonic('t');
		ToolBar_Tool.add(triButton);


		starButton = new JButton(new ImageIcon("star.jpg"));
		starButton.setToolTipText("별그리기");
		starButton.addActionListener(this);
		starButton.addActionListener(eventhandle);
		starButton.setActionCommand("16");
		starButton.setMnemonic('s');
		ToolBar_Tool.add(starButton);
		
		
		arrowButton = new JButton(new ImageIcon("arrow.jpg"));
		arrowButton.setToolTipText("arrow");
		arrowButton.addActionListener(this);
		arrowButton.addActionListener(eventhandle);
		arrowButton.setActionCommand("19");
		arrowButton.setMnemonic('a');
		ToolBar_Tool.add(arrowButton);


		selectButton = new JButton(new ImageIcon("Select.jpg"));
		selectButton.setToolTipText("객체 선택");
		selectButton.addActionListener(this);
		selectButton.addActionListener(eventhandle);
		selectButton.setActionCommand("25");
		selectButton.setMnemonic('c');
		ToolBar_Tool.add(selectButton);


		moveButton = new JButton(new ImageIcon("Move.jpg"));
		moveButton.setToolTipText("이동");
		moveButton.addActionListener(this);
		moveButton.addActionListener(eventhandle);
		moveButton.setActionCommand("23");
		moveButton.setMnemonic('m');
		ToolBar_Tool.add(moveButton); 


		resizeButton = new JButton(new ImageIcon("Resize.jpg"));
		resizeButton.setToolTipText("크기 변경");
		resizeButton.addActionListener(this);
		resizeButton.addActionListener(eventhandle);
		resizeButton.setActionCommand("22");
		resizeButton.setMnemonic('z');
		ToolBar_Tool.add(resizeButton);


		deleteButton = new JButton(new ImageIcon("Delete.jpg"));
		deleteButton.setToolTipText("삭제");
		deleteButton.addActionListener(this);
		deleteButton.setMnemonic('d');
		ToolBar_Tool.add(deleteButton);


		undoButton = new JButton(new ImageIcon("Undo.jpg"));
		undoButton.setToolTipText("실행취소");
		undoButton.addActionListener(this);
		undoButton.setMnemonic('u');
		ToolBar_Tool.add(undoButton);
		
		
		reundoButton = new JButton(new ImageIcon("ReUndo.jpg"));
		reundoButton.setToolTipText("다시 실행");
		reundoButton.addActionListener(this);
		reundoButton.setMnemonic('q');
		ToolBar_Tool.add(reundoButton);


		fillcolorButton = new JButton(new ImageIcon("FillColor.jpg"));
		fillcolorButton.setToolTipText("색채우기");
		fillcolorButton.addActionListener(this);
		fillcolorButton.addActionListener(eventhandle);
		fillcolorButton.setActionCommand("21");
		fillcolorButton.setMnemonic('f');
		ToolBar_Tool.add(fillcolorButton);


		backButton = new JButton(new ImageIcon("back.jpg"));
		backButton.setToolTipText("뒤로 보내기");
		backButton.addActionListener(this);
		backButton.setMnemonic('b');
		ToolBar_Tool.add(backButton);
		
		
		eraserButton = new JButton(new ImageIcon("Fdelete.jpg"));
		eraserButton.setToolTipText("f지우기");
		eraserButton.addActionListener(this);
		eraserButton.addActionListener(eventhandle);
		eraserButton.setActionCommand("17");
		eraserButton.setMnemonic('e');
		ToolBar_Tool.add(eraserButton); 
		

		//색깔과 파레트를 넣는 패널 생성 
		JPanel ToolBar_Color = new JPanel();
		ToolBar_Color.setLayout(null);
		
		//기본색깔 넣는 패널 생성
		JPanel ToolBar_basicColor = new JPanel();
		ToolBar_basicColor.setBorder(BorderFactory.createRaisedBevelBorder());
		ToolBar_basicColor.setLayout(new GridLayout(3,2));
		ToolBar_basicColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ToolBar_basicColor.setBounds(0, 0, 120, 70);
		ToolBar_Color.add(ToolBar_basicColor);
		
		
		blackButton = new JButton(new ImageIcon("black.jpg"));
		ToolBar_basicColor.add(blackButton);
		blackButton.addActionListener(this);

		whiteButton = new JButton(new ImageIcon("white.jpg"));
		ToolBar_basicColor.add(whiteButton);
		whiteButton.addActionListener(this);

		blueButton = new JButton(new ImageIcon("blue.jpg"));
		ToolBar_basicColor.add(blueButton);
		blueButton.addActionListener(this);
		
		redButton = new JButton(new ImageIcon("red.jpg"));
		ToolBar_basicColor.add(redButton);
		redButton.addActionListener(this);

		yellowButton = new JButton(new ImageIcon("yellow.jpg"));
		ToolBar_basicColor.add(yellowButton);
		yellowButton.addActionListener(this);

		greenButton = new JButton(new ImageIcon("green.jpg"));
		ToolBar_basicColor.add(greenButton);
		greenButton.addActionListener(this);

		
		//파레트 넣는 버튼 생성
		JButton variousColor = new JButton(new ImageIcon("choseColor.jpg"));
		variousColor.addMouseListener            
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent event) 
					{
						Color color = JColorChooser.showDialog(HwaDong.this, "Color Chooser", firstColor);

						if(color == null)
							color = firstColor;
						nowColorLabel.setBackground(color);	
						Layer.setColor(color);	
					}
				}           
		);
		
		
		ToolBar_Color.add(variousColor);
		variousColor.setBounds(0, 70, 120, 100);



		Label line = new Label(" ");
		Label line2 = new Label(" ");
		JPanel ToolBar_State = new JPanel();
		ToolBar_State.setBorder(BorderFactory.createRaisedBevelBorder());


		//마우스모션리스너와 마우스모션어댑터를 이용해 상태도움표시줄에 도움글을 보여준다
		nowColorLabel = new Label(); 
		nowColorLabel.setBackground(firstColor);

		//현재 상태를 텍스트로 출력해주는 패널
		nowStatusLabel = new JLabel();
		nowStatusLabel.setToolTipText("현재 상태 선택");

		Label xyLabel = new Label("X,Y 좌표",Label.CENTER);
		
		ToolBar_State.setLayout(new GridLayout(8,1));

		Label CurrentColor = new Label("현재색",Label.CENTER);
		ToolBar_State.add(CurrentColor,BorderLayout.NORTH);      	     
		
		ToolBar_State.add(nowColorLabel);
		ToolBar_State.add(line);
		Label CurrentStatus = new Label("현재상태",Label.CENTER);
		ToolBar_State.add(CurrentStatus,BorderLayout.CENTER); 

		nowStatusLabel = new JLabel("선택안함",nowStatusLabel.CENTER);
		ToolBar_State.add(nowStatusLabel);


		ToolBar_State.add(line2);
		ToolBar_State.add(xyLabel,BorderLayout.CENTER);
		nowXYLabel = new JLabel("0, 0",nowXYLabel.CENTER);
		ToolBar_State.add(nowXYLabel);



		//툴바에 툴바_툴, 툴바_상태, 툴바_컬러, 툴바_메뉴 패널 배치
		ToolBar.setLayout(null);
		ToolBar_Tool.setBounds(0, 0, 120, 240);
		ToolBar.add(ToolBar_Tool);
		ToolBar_State.setBounds(0, 240, 120, 130);
		ToolBar.add(ToolBar_State);
		ToolBar_Color.setBounds(0, 370, 120, 170);
		ToolBar.add(ToolBar_Color);
		ToolBar_Menu.setBounds(0, 540, 120, 50);
		ToolBar.add(ToolBar_Menu);
		
		

		//그림이 그려지는 패널
		DrawPanel = new CustomPanel();
		DrawPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		DrawPanel.setBackground(Color.WHITE);
		
		
		DrawPanel.setBounds(120, 0, 850, 600);
		add(DrawPanel);
		ToolBar.setBounds(0, 0, 120, 600);
		add(ToolBar);

		// 크기변경 불가
		setResizable(false);
	}

	//열기함수.
	private void Open()
	{
	
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		int result = fileChooser.showOpenDialog(this);

		if(result == JFileChooser.CANCEL_OPTION)
			return;

		File file = fileChooser.getSelectedFile();

		
		if((file == null) || (file.getName().equals("")))
		{
			JOptionPane.showMessageDialog(this, "파일이름을 입력하지 않으셨습니다!",
					"파일이름을 입력하지 않으셨습니다!",JOptionPane.ERROR_MESSAGE);
		}

		else
		{

			try
			{
				
				ObjectInputStream open = new ObjectInputStream(new FileInputStream(file));
				Vector newobject = (Vector)open.readObject();
				Layer.setDrawing(newobject);
				open.close();
				repaint();
				saveFile = file;
				fileName = saveFile.getName();
				setTitle("현재 파일명 : " + fileName + " , 위치 : " + saveFile.getPath());
			} 
			catch (Exception e) { System.out.println(e); }
		}

	}


	//저장함수
	private void Save()
	{
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		int result = fileChooser.showSaveDialog(this);

		if(result == JFileChooser.CANCEL_OPTION)
			return;

		File file = fileChooser.getSelectedFile();

		
		if((file == null) || (file.getName().equals("")))
		{
			JOptionPane.showMessageDialog(this, "파일이름을 입력하지 않으셨습니다!",
					"파일이름을 입력하지 않으셨습니다!",JOptionPane.ERROR_MESSAGE);
		}

		try
		{
			ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(file));
			save.writeObject(Layer.getDrawing());
			save.close();
		}
		catch (Exception e) { System.out.println(e); }			

		if (file != saveFile)
		{
			saveFile = file;
			fileName = saveFile.getName();
			setTitle("현재 파일명 : " + fileName + " , 위치 : " + saveFile.getPath());
		}

	}

	public void actionPerformed(ActionEvent event)
	{

		DrawPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		Object action = event.getSource();

	
		//버튼이 눌렸을때 이벤트 처리
		if (action == newButton)
		{
			Layer.newShape();
			repaint();
			setTitle("제목없음.cha");
			saveFile = null;
		}

		else if (action == openButton)
		{			
			Open();
		}

		else if (action == saveButton)
		{
			Save(); 		
		}

		else if(action == lineButton)
		{
			nowStatusLabel.setText("선그리기");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		else if(action == penButton)
		{
			nowStatusLabel.setText("펜그리기");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		else if(action == ovalButton)
		{
			nowStatusLabel.setText("원그리기");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		else if(action == rectButton)
		{
			nowStatusLabel.setText("사각형그리기");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		else if(action == triButton)
		{
			nowStatusLabel.setText("삼각형그리기");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		else if(action == starButton)
		{
			nowStatusLabel.setText("별그리기");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		else if(action == arrowButton)
		{
			nowStatusLabel.setText("화살표그리기");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}

		else if(action == selectButton)
		{
			nowStatusLabel.setText("도형 선택");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		else if(action == fillcolorButton)
		{
			nowStatusLabel.setText("색 채우기");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		else if(action == moveButton)
		{
			nowStatusLabel.setText("도형이동");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}
		
		else if(action == eraserButton)
		{
			nowStatusLabel.setText("지우개");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}

		else if(action == resizeButton)
		{
			nowStatusLabel.setText("크기 수정하기");
		}

		else if(action == undoButton)
		{
			nowStatusLabel.setText("실행취소");
			Layer.undo();
			repaint();
		}
		
		else if(action == reundoButton)
		{
			nowStatusLabel.setText("다시 실행");
			Layer.reundo();
			repaint();
		}

		else if(action == backButton)
		{
			nowStatusLabel.setText("뒤로보내기");
			if(isSelected == false)
				return;
			Layer.moveToBack(Layer.getShape(Layer.getEditIndex()));
			repaint();
		}

		else if (action == deleteButton)
		{
			nowStatusLabel.setText("도형지우기");
			if(Layer.isEditing()==true){
				Layer.delete();
				repaint();
			}
		}

		else if (action == helpButton)
		{
			JOptionPane.showMessageDialog(HwaDong.this, 
					"단축키\n"+ 
					"선 alt+l        펜 alt+p      원 alt+o\n"+
					"사각형 alt+r    삼각형 alt+t  별 alt+s\n"+
					"화살표 alt+a    선택 alt+c    이동 alt+m\n"+
					"크기 alt+z      삭제 alt+d    실행취소 alt+u\n"+
					"다시실행 alt+i  색칠 alt+f    뒤로가기 alt+b\n"+
					"지우개 alt+e\n\n"+
					"2007122051 김성탁  2007122053 김용두\n"+
					"2009122084 김재욱  2009122039 김동근"
					,"객체지향", JOptionPane.INFORMATION_MESSAGE);
		}

		else if(action == blackButton)
		{
			nowColorLabel.setBackground(Color.BLACK);	
			Layer.setColor(Color.BLACK);
		}			

		else if(action == whiteButton)
		{
			nowColorLabel.setBackground(Color.WHITE);
			Layer.setColor(Color.WHITE);
		}

		else if(action == redButton)
		{
			nowColorLabel.setBackground(new Color(255,0,0));	
			Layer.setColor(new Color(255,0,0));
		}		

		else if(action == yellowButton)
		{
			nowColorLabel.setBackground(new Color(255,255,0));	
			Layer.setColor(new Color(255,255,0));
		}

		else if(action == greenButton)
		{
			nowColorLabel.setBackground(new Color(0,255,0));	
			Layer.setColor(new Color(0,255,0));
		}

		else if(action == blueButton)
		{
			nowColorLabel.setBackground(new Color(0,0,255));	
			Layer.setColor(new Color(0,0,255));
		}
	}

	// 캔버스역할을 하는 DrawPanel
	private class CustomPanel extends JPanel implements MouseInputListener
	{
		// 생성자
		public CustomPanel()
		{
			addMouseListener(this);
			addMouseMotionListener(this);
		}

		public void paint(Graphics g)
		{
			super.paint(g);
			Layer.draw(g);

		}

		
		// 마우스를 클릭했을 때 현재 명령 실행
		public void mouseClicked(MouseEvent event)
		{
			try {
				
				if (!(event.isMetaDown()) && !(event.isAltDown()))
				{
					
					eventhandle.ordering.mouseClickExe(event.getPoint(), Layer); 
					repaint();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}


		}

		// 마우스를 눌렀을 때 현재 명령 실행
		public void mousePressed(MouseEvent event)
		{
			try {

				if (!(event.isMetaDown()) && !(event.isAltDown()))
				{
					eventhandle.ordering.mousePressExe(event.getPoint(), Layer);
					repaint();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		} 
		// 마우스를 드래그할 때 현재 명령 실행
		public void mouseDragged(MouseEvent event)
		{
			try {

				if (!(event.isMetaDown()) && !(event.isAltDown()) && event.isShiftDown())
				{
					eventhandle.ordering.mouseDragShiftExe(event.getPoint(), Layer); 
					repaint();
				}
				else if (!(event.isMetaDown()) && !(event.isAltDown()))
				{
					eventhandle.ordering.mouseDragExe(event.getPoint(), Layer);
					repaint();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}   

		public void mouseReleased(MouseEvent event) 
		{ 
			try {
				eventhandle.ordering.mouseRelExe(event.getPoint(), Layer);
				repaint();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		public void mouseEntered(MouseEvent event) 
		{

		}
		public void mouseExited(MouseEvent event)
		{

		} 
		public void mouseMoved(MouseEvent event) 
		{
			try {

				if (!(event.isMetaDown()) && !(event.isAltDown()))
				{
					nowXYLabel.setText(String.format("%d, %d", event.getX(), event.getY()));
					
					repaint();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} 
	}
}
