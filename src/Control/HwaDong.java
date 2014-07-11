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

	private EventHandle eventhandle; // �ֿ��̺�Ʈ���� ������ �̺�Ʈ�ڵ鷯 ��ü
	private final Color firstColor = Color.BLACK;       //�⺻���� ���� ������ ����
	private Making Layer = new Making(firstColor);; 	// Making�� ���� �׸��� �׸� ��ü
	private Label nowColorLabel;				
	private JLabel nowStatusLabel;
	private JLabel nowXYLabel;	

	//����ư
	private JButton selectButton, lineButton, rectButton, ovalButton, triButton, starButton, resizeButton, moveButton, deleteButton, 
	fillcolorButton, undoButton, reundoButton, backButton, penButton, eraserButton, arrowButton;

	//�ȷ�Ʈ��ư
	private JButton whiteButton, blackButton, redButton, blueButton, greenButton, yellowButton; 
	
	//�޴���ư
	private JButton newButton, openButton, saveButton, helpButton; 

	//ĵ���������� �ϴ� �г�
	public CustomPanel DrawPanel;

	//��������� ����
	private String fileName = "�������.cha";
	private File saveFile;

	private boolean isSelected = true;

	//���θŽ��
	public static void main(String args[])
	{
		HwaDong application= new HwaDong();
		application.setVisible(true);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                       
		application.setTitle("HwaDong Production");
		application.setSize(950,620);
		
	}

	// HwaDong ������ ���̾ƿ��� ����
	public HwaDong()
	{
		super();
		eventhandle = new EventHandle();	
		setTitle(fileName);

		//������ ��Ÿ�Ϸ� ����� ����
		try { 
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel"); 
			SwingUtilities.updateComponentTreeUI(this); 
		} 
		catch (Exception e) { }
		
		
		//���� �г� ����
		JPanel ToolBar = new JPanel();
		ToolBar.setBorder(BorderFactory.createLoweredBevelBorder());
		
		
		//�޴� �ִ� �г� ����
		JPanel ToolBar_Menu=new JPanel();
		ToolBar_Menu.setBorder(BorderFactory.createRaisedBevelBorder());
		ToolBar_Menu.setLayout(new GridLayout(2,2));
		ToolBar_Menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		//��ư�� �����ϰ� ��ư ����
		newButton = new JButton(new ImageIcon("new.jpg"));
		newButton.setToolTipText("���θ����");
		newButton.addActionListener(this);
		ToolBar_Menu.add(newButton);


		openButton = new JButton(new ImageIcon("open.jpg"));
		openButton.setToolTipText("����");
		openButton.addActionListener(this);
		ToolBar_Menu.add(openButton);


		saveButton = new JButton(new ImageIcon("save.jpg"));
		saveButton.setToolTipText("����");
		saveButton.addActionListener(this);
		ToolBar_Menu.add(saveButton);


		helpButton = new JButton(new ImageIcon("help.jpg"));
		helpButton.setToolTipText("����Ű");
		helpButton.addActionListener(this);
		ToolBar_Menu.add(helpButton);

		
		
		//�� �ִ� �гλ���
		JPanel ToolBar_Tool = new JPanel();
		ToolBar_Tool.setBorder(BorderFactory.createRaisedBevelBorder());
		ToolBar_Tool.setLayout(new GridLayout(6,3));
		ToolBar_Tool.setCursor(new Cursor(Cursor.HAND_CURSOR));


		lineButton = new JButton(new ImageIcon("Line.jpg"));
		lineButton.setToolTipText("���׸���");
		lineButton.addActionListener(this);
		lineButton.addActionListener(eventhandle);
		lineButton.setActionCommand("11");
		lineButton.setMnemonic('l');
		ToolBar_Tool.add(lineButton); 
		
		
		penButton = new JButton(new ImageIcon("pen.jpg"));
		penButton.setToolTipText("��");
		penButton.addActionListener(this);
		penButton.addActionListener(eventhandle);
		penButton.setActionCommand("18");
		penButton.setMnemonic('p');
		ToolBar_Tool.add(penButton); 


		ovalButton = new JButton(new ImageIcon("Oval.jpg"));
		ovalButton.setToolTipText("���׸���");
		ovalButton.addActionListener(this);
		ovalButton.addActionListener(eventhandle);
		ovalButton.setActionCommand("12");
		ovalButton.setMnemonic('o');
		ToolBar_Tool.add(ovalButton); 


		rectButton = new JButton(new ImageIcon("Rect.jpg"));
		rectButton.setToolTipText("�簢���׸���");
		rectButton.addActionListener(this);
		rectButton.addActionListener(eventhandle);
		rectButton.setActionCommand("14");
		rectButton.setMnemonic('r');
		ToolBar_Tool.add(rectButton);


		triButton = new JButton(new ImageIcon("tri.jpg"));
		triButton.setToolTipText("�ﰢ���׸���");
		triButton.addActionListener(this);
		triButton.addActionListener(eventhandle);
		triButton.setActionCommand("15");
		triButton.setMnemonic('t');
		ToolBar_Tool.add(triButton);


		starButton = new JButton(new ImageIcon("star.jpg"));
		starButton.setToolTipText("���׸���");
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
		selectButton.setToolTipText("��ü ����");
		selectButton.addActionListener(this);
		selectButton.addActionListener(eventhandle);
		selectButton.setActionCommand("25");
		selectButton.setMnemonic('c');
		ToolBar_Tool.add(selectButton);


		moveButton = new JButton(new ImageIcon("Move.jpg"));
		moveButton.setToolTipText("�̵�");
		moveButton.addActionListener(this);
		moveButton.addActionListener(eventhandle);
		moveButton.setActionCommand("23");
		moveButton.setMnemonic('m');
		ToolBar_Tool.add(moveButton); 


		resizeButton = new JButton(new ImageIcon("Resize.jpg"));
		resizeButton.setToolTipText("ũ�� ����");
		resizeButton.addActionListener(this);
		resizeButton.addActionListener(eventhandle);
		resizeButton.setActionCommand("22");
		resizeButton.setMnemonic('z');
		ToolBar_Tool.add(resizeButton);


		deleteButton = new JButton(new ImageIcon("Delete.jpg"));
		deleteButton.setToolTipText("����");
		deleteButton.addActionListener(this);
		deleteButton.setMnemonic('d');
		ToolBar_Tool.add(deleteButton);


		undoButton = new JButton(new ImageIcon("Undo.jpg"));
		undoButton.setToolTipText("�������");
		undoButton.addActionListener(this);
		undoButton.setMnemonic('u');
		ToolBar_Tool.add(undoButton);
		
		
		reundoButton = new JButton(new ImageIcon("ReUndo.jpg"));
		reundoButton.setToolTipText("�ٽ� ����");
		reundoButton.addActionListener(this);
		reundoButton.setMnemonic('q');
		ToolBar_Tool.add(reundoButton);


		fillcolorButton = new JButton(new ImageIcon("FillColor.jpg"));
		fillcolorButton.setToolTipText("��ä���");
		fillcolorButton.addActionListener(this);
		fillcolorButton.addActionListener(eventhandle);
		fillcolorButton.setActionCommand("21");
		fillcolorButton.setMnemonic('f');
		ToolBar_Tool.add(fillcolorButton);


		backButton = new JButton(new ImageIcon("back.jpg"));
		backButton.setToolTipText("�ڷ� ������");
		backButton.addActionListener(this);
		backButton.setMnemonic('b');
		ToolBar_Tool.add(backButton);
		
		
		eraserButton = new JButton(new ImageIcon("Fdelete.jpg"));
		eraserButton.setToolTipText("f�����");
		eraserButton.addActionListener(this);
		eraserButton.addActionListener(eventhandle);
		eraserButton.setActionCommand("17");
		eraserButton.setMnemonic('e');
		ToolBar_Tool.add(eraserButton); 
		

		//����� �ķ�Ʈ�� �ִ� �г� ���� 
		JPanel ToolBar_Color = new JPanel();
		ToolBar_Color.setLayout(null);
		
		//�⺻���� �ִ� �г� ����
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

		
		//�ķ�Ʈ �ִ� ��ư ����
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


		//���콺��Ǹ����ʿ� ���콺��Ǿ���͸� �̿��� ���µ���ǥ���ٿ� ������� �����ش�
		nowColorLabel = new Label(); 
		nowColorLabel.setBackground(firstColor);

		//���� ���¸� �ؽ�Ʈ�� ������ִ� �г�
		nowStatusLabel = new JLabel();
		nowStatusLabel.setToolTipText("���� ���� ����");

		Label xyLabel = new Label("X,Y ��ǥ",Label.CENTER);
		
		ToolBar_State.setLayout(new GridLayout(8,1));

		Label CurrentColor = new Label("�����",Label.CENTER);
		ToolBar_State.add(CurrentColor,BorderLayout.NORTH);      	     
		
		ToolBar_State.add(nowColorLabel);
		ToolBar_State.add(line);
		Label CurrentStatus = new Label("�������",Label.CENTER);
		ToolBar_State.add(CurrentStatus,BorderLayout.CENTER); 

		nowStatusLabel = new JLabel("���þ���",nowStatusLabel.CENTER);
		ToolBar_State.add(nowStatusLabel);


		ToolBar_State.add(line2);
		ToolBar_State.add(xyLabel,BorderLayout.CENTER);
		nowXYLabel = new JLabel("0, 0",nowXYLabel.CENTER);
		ToolBar_State.add(nowXYLabel);



		//���ٿ� ����_��, ����_����, ����_�÷�, ����_�޴� �г� ��ġ
		ToolBar.setLayout(null);
		ToolBar_Tool.setBounds(0, 0, 120, 240);
		ToolBar.add(ToolBar_Tool);
		ToolBar_State.setBounds(0, 240, 120, 130);
		ToolBar.add(ToolBar_State);
		ToolBar_Color.setBounds(0, 370, 120, 170);
		ToolBar.add(ToolBar_Color);
		ToolBar_Menu.setBounds(0, 540, 120, 50);
		ToolBar.add(ToolBar_Menu);
		
		

		//�׸��� �׷����� �г�
		DrawPanel = new CustomPanel();
		DrawPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		DrawPanel.setBackground(Color.WHITE);
		
		
		DrawPanel.setBounds(120, 0, 850, 600);
		add(DrawPanel);
		ToolBar.setBounds(0, 0, 120, 600);
		add(ToolBar);

		// ũ�⺯�� �Ұ�
		setResizable(false);
	}

	//�����Լ�.
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
			JOptionPane.showMessageDialog(this, "�����̸��� �Է����� �����̽��ϴ�!",
					"�����̸��� �Է����� �����̽��ϴ�!",JOptionPane.ERROR_MESSAGE);
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
				setTitle("���� ���ϸ� : " + fileName + " , ��ġ : " + saveFile.getPath());
			} 
			catch (Exception e) { System.out.println(e); }
		}

	}


	//�����Լ�
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
			JOptionPane.showMessageDialog(this, "�����̸��� �Է����� �����̽��ϴ�!",
					"�����̸��� �Է����� �����̽��ϴ�!",JOptionPane.ERROR_MESSAGE);
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
			setTitle("���� ���ϸ� : " + fileName + " , ��ġ : " + saveFile.getPath());
		}

	}

	public void actionPerformed(ActionEvent event)
	{

		DrawPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		Object action = event.getSource();

	
		//��ư�� �������� �̺�Ʈ ó��
		if (action == newButton)
		{
			Layer.newShape();
			repaint();
			setTitle("�������.cha");
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
			nowStatusLabel.setText("���׸���");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		else if(action == penButton)
		{
			nowStatusLabel.setText("��׸���");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		else if(action == ovalButton)
		{
			nowStatusLabel.setText("���׸���");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		else if(action == rectButton)
		{
			nowStatusLabel.setText("�簢���׸���");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		else if(action == triButton)
		{
			nowStatusLabel.setText("�ﰢ���׸���");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		else if(action == starButton)
		{
			nowStatusLabel.setText("���׸���");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		else if(action == arrowButton)
		{
			nowStatusLabel.setText("ȭ��ǥ�׸���");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}

		else if(action == selectButton)
		{
			nowStatusLabel.setText("���� ����");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		else if(action == fillcolorButton)
		{
			nowStatusLabel.setText("�� ä���");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		else if(action == moveButton)
		{
			nowStatusLabel.setText("�����̵�");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}
		
		else if(action == eraserButton)
		{
			nowStatusLabel.setText("���찳");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}

		else if(action == resizeButton)
		{
			nowStatusLabel.setText("ũ�� �����ϱ�");
		}

		else if(action == undoButton)
		{
			nowStatusLabel.setText("�������");
			Layer.undo();
			repaint();
		}
		
		else if(action == reundoButton)
		{
			nowStatusLabel.setText("�ٽ� ����");
			Layer.reundo();
			repaint();
		}

		else if(action == backButton)
		{
			nowStatusLabel.setText("�ڷκ�����");
			if(isSelected == false)
				return;
			Layer.moveToBack(Layer.getShape(Layer.getEditIndex()));
			repaint();
		}

		else if (action == deleteButton)
		{
			nowStatusLabel.setText("���������");
			if(Layer.isEditing()==true){
				Layer.delete();
				repaint();
			}
		}

		else if (action == helpButton)
		{
			JOptionPane.showMessageDialog(HwaDong.this, 
					"����Ű\n"+ 
					"�� alt+l        �� alt+p      �� alt+o\n"+
					"�簢�� alt+r    �ﰢ�� alt+t  �� alt+s\n"+
					"ȭ��ǥ alt+a    ���� alt+c    �̵� alt+m\n"+
					"ũ�� alt+z      ���� alt+d    ������� alt+u\n"+
					"�ٽý��� alt+i  ��ĥ alt+f    �ڷΰ��� alt+b\n"+
					"���찳 alt+e\n\n"+
					"2007122051 �輺Ź  2007122053 ����\n"+
					"2009122084 �����  2009122039 �赿��"
					,"��ü����", JOptionPane.INFORMATION_MESSAGE);
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

	// ĵ���������� �ϴ� DrawPanel
	private class CustomPanel extends JPanel implements MouseInputListener
	{
		// ������
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

		
		// ���콺�� Ŭ������ �� ���� ��� ����
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

		// ���콺�� ������ �� ���� ��� ����
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
		// ���콺�� �巡���� �� ���� ��� ����
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
