package shape;
abstract class JoinPointBase implements Shape {

	private Shape source;
	private Shape dest;
	protected Square delegate;
	
	public Square getDelegate() {
		return delegate;
	}

	public Shape getSource() {
		return source;
	}

	public void setSource(Shape source) {
		this.source = source;
	}

	public Shape getDest() {
		return dest;
	}

	public void setDest(Shape dest) {
		this.dest = dest;
	}
	
	public void setAsIcon()
	{
		
	}
}
