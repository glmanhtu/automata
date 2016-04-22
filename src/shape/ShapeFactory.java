package shape;

public class ShapeFactory {
	public static final int CIRCLE = 0;
	public static final int SQUARE = 1;
	public static final int SMALL_SQUARE = 2;
	public static final int BIG_CIRCLE = 3;
	public static final int EDGE = 4;
	public static final int JOIN_POINT = 5;
	public static final int EDGE_LABEL = 6;
	public static final int CHECK = 7;
	public static final int INIT_STATE = 8;
	public static final int TERMINAL_STATE = 9;
	private int ShapeNumber = 0;
	public Shape getShape(int shapeType)
	{
		Shape shape;
		switch (shapeType) {
			case CIRCLE:
				shape = new Circle();
				shape.setLabel( Integer.toString(ShapeNumber++));
				return shape;
			case SQUARE:
				shape = new Square();
				shape.setLabel( Integer.toString(ShapeNumber++));
				return shape;
			case SMALL_SQUARE:
				shape = new SmallSquare();
				shape.setLabel( Integer.toString(ShapeNumber++));
				return shape;
			case BIG_CIRCLE:
				shape = new BigCircle();
				shape.setLabel( Integer.toString(ShapeNumber++));
				return shape;
			case INIT_STATE:
				shape = new InitState();
				shape.setLabel(Integer.toString(ShapeNumber++));
				return shape;
			case TERMINAL_STATE:
				shape = new TerminalState();
				shape.setLabel(Integer.toString(ShapeNumber++));
				return shape;
			case EDGE:
				return new Edge();
			case JOIN_POINT:
				return new JoinPoint();
			case EDGE_LABEL:
				return new EdgeLabel();
			case CHECK:
				return new Check();
		}
		return null;
		
	}
}
