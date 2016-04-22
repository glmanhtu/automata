package draw;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import file.FileUtils;
import shape.Shape;

public class GraphMenu extends JMenuBar{
	
	private FileUtils fileUtils;
	private Graph decorateComponent;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GraphMenu(JFrame mainFrame, Graph decorateComponent)
	{
		this.decorateComponent = decorateComponent;
		fileUtils = new FileUtils<Vector<Shape>>();
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem(new AbstractAction("Create new window") {
		    /**
			 * Action perform wen click create new windows
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
		       GraphFrame.createNewWindows();
		    }
		});
	
		JMenuItem menuItem2 = new JMenuItem(new AbstractAction("Close this window") {

			/**
			 * 
			 */	
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				mainFrame.dispose();
		    }
		});
		JMenuItem menuItem3 = new JMenuItem(new AbstractAction("Quit the program") {

			/**
			 * 
			 */	
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
		    }
		});
		
		JMenuItem menuItem4 = new JMenuItem(new AbstractAction("Export to xml") {

			/**
			 * 
			 */	
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent ae) {
				fileUtils.generateExport(object)
		    }
		});
		
		menu.add(menuItem);
		menu.add(menuItem2);
		menu.addSeparator();
		menu.add(menuItem4);
		menu.addSeparator();
		menu.add(menuItem3);
		this.add(menu);
	}
}
