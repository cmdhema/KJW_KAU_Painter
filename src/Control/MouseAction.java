package Control;

import java.awt.*;

import MakeShape.Making;

// 모든 명령은 Order 클래스로부터 상속받는다.
public class MouseAction 
{
	public void mouseMoveExe(Point p, Making Layer) { }
  	public void mouseClickExe(Point p, Making Layer) { }
  	public void mousePressExe(Point p, Making Layer) { }
  	public void mouseDragExe(Point p, Making Layer) { }
  	public void mouseDragShiftExe(Point p, Making Layer) { }
	public void mouseRelExe(Point p, Making Layer) { }

}
