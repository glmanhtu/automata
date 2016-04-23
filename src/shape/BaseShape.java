package shape;

import java.awt.Color;
import java.io.Serializable;

abstract class BaseShape implements Shape, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean isDrawing = true;

	protected Color getContrastColor(Color color) {
		int d = 0;

	    // Counting the perceptive luminance - human eye favors green color... 
	    double a = 1 - ( 0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue())/255;

	    if (a < 0.5)
	       d = 0; // bright colors - black font
	    else
	       d = 255; // dark colors - white font

	    return new Color(d, d, d);
	}
	
	public String toString() {
		return getLabel();
	}
	
	public boolean isDrawing() {
		return isDrawing;
	}
	public void setDrawing(boolean draw) {
		isDrawing = draw;
	}
}
