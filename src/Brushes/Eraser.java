package Brushes;

import java.awt.*;
import java.awt.geom.*;
public class Eraser implements RainbowPaint 
{
	public void paintComponent(Graphics g, int x, int y) 
    {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(Color.BLACK);
		Ellipse2D b = new Ellipse2D.Double(x, y, 40, 40);
		g2.fill(b);
	}
}