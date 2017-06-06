package Brushes;

import java.awt.*;
import java.awt.geom.*;
public class ColorCircle implements RainbowPaint 
{
	public void paintComponent(Graphics g, int x, int y) 
    {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(colors());
		Ellipse2D b = new Ellipse2D.Double(x, y, 40, 40);
		g2.fill(b);
	}
	public Color colors() 
    {
		int a = (int)(Math.random() * 255);
		int b = (int)(Math.random() * 255);
		int c = (int)(Math.random() * 255);
		Color d = new Color(a, b, c, 80);
		return d;
	}
}