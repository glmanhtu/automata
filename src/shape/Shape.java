package shape;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public interface Shape {
	
	/**
	 * Paint Shape
	 * This will design how shape look like
	 * We let each shape decide how to paint it self
	 * @param ga
	 */
	void draw(Graphics2D ga);
	
	/**
	 * This decide where shape will be paint
	 * @param point
	 */
	void setPosition(Point2D point);
	
	/**
	 * Get current position of this shape
	 * @return Point2D
	 */
	Point2D getPosition();
	
	/**
	 * Check if this shape is contain a given point or not
	 * This useful when you want to check if you click in shape or not
	 * @param point
	 * @return boolean
	 */
	boolean verifyShapeExists(Point2D point);
	
	/**
	 * Compare between each shape
	 * @param shape
	 * @return
	 */
	boolean compare(Shape shape);
	
	/**
	 * Set shape label
	 * @param label
	 */
	void setLabel(String label);
	String getLabel();
	
	/**
	 * In left panel, we can select which shape will be paint
	 * For more convenience, I add icon in each shape type
	 * So, Instead to define new Shape for icon, I reuse this shape
	 * but make it size is smaller and the color always is black
	 */
	void setAsIcon();
	
	/**
	 * This for print shape as string
	 * This return getLabel() above
	 * @return
	 */
	String toString();
	
	/**
	 * Determine this shape is drawing or not
	 * @return
	 */
	boolean isDrawing();
	
	/**
	 * Set this shape is drawing or not.
	 * Default is true
	 * @param draw
	 */
	void setDrawing(boolean draw);
}
