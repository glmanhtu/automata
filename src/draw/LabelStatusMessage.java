package draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

import javax.swing.JLabel;

public class LabelStatusMessage extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private boolean status;
	private String error;
	private static LabelStatusMessage INSTANCE;
	private static final String START_MESSAGE = "Automaton Editor is started, waiting for states";
	private static final String SUCCESS_MESSAGE = "Automaton is corrected";
	private static final String FAILED_MESSAGE = "Automaton is incorrect, Error: ";
	private static final String SUCCESS_RECOGNIZED = "Automaton has success to recognize word: ";
	private static final String FAILED_RECOGNIZED = "Automaton has unable to recognize word: ";
	
	public static LabelStatusMessage getInstance()
	{
		return (INSTANCE == null) ? new LabelStatusMessage() : INSTANCE;
	}
	
	private LabelStatusMessage() {
		setMessage(START_MESSAGE);
		this.status = true;
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		this.setText(message);
		Graphics2D ga = (Graphics2D) g;
		ga.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(ga);
		Point2D p = this.getLocation();
		Color current = ga.getColor();
		if (status) {
			ga.setColor(Color.green);
		} else {
			ga.setColor(Color.red);
		}
			
		ga.fillOval((int)p.getX()+2, (int)p.getY(), 10, 10);
		
		ga.setColor(current);
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = "          "+ message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
		if (status) {
			setMessage(SUCCESS_MESSAGE);
		} else {
			setMessage(FAILED_MESSAGE + error);
		}
		this.revalidate();
		this.repaint();
	}
	
	public void setRecognize(boolean status, String word) {
		this.status = status;
		if (status) {
			setMessage(SUCCESS_RECOGNIZED + word);
		} else {
			setMessage(FAILED_RECOGNIZED + word);
		}
	}
	
	public void setError(String error) {
		this.error = error;
	}
	

}
