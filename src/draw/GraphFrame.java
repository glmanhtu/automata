package draw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import automata.Automata;

public class GraphFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graph graphComponent;
	public GraphFrame()
	{
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiTeX");
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(new BorderLayout());
		Automata automata = new Automata();
		graphComponent = new GraphWithEditor();
		graphComponent.setAutomata(automata);
		graphComponent = new GraphWithScroll(graphComponent);
		getContentPane().add(graphComponent.getComponent(), BorderLayout.CENTER);
		graphComponent = new GraphWithPanel(graphComponent);
		getContentPane().add(graphComponent.getComponent(), BorderLayout.WEST);
		graphComponent = new GraphWithColorPanel(graphComponent);
		getContentPane().add(graphComponent.getComponent(), BorderLayout.NORTH);
		graphComponent = new GraphWithVerifyStatusPanel(graphComponent);
		graphComponent.setAutomata(automata);
		getContentPane().add(graphComponent.getComponent(), BorderLayout.SOUTH);
		this.setJMenuBar(new GraphMenu(this, graphComponent));
		this.setPreferredSize(new Dimension(1100, 700));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static List<JFrame> instances = null;
	
	public static JFrame createNewWindows()
	{
		if (instances == null) {
			instances = new ArrayList<>();
		}
		GraphFrame graph = new GraphFrame();
		graph.setVisible(true);
		graph.pack();
		instances.add(graph);
		return graph;
	}
}
