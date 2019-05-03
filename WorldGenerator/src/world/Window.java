package world;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JPanel {
	JFrame frame = new JFrame();
	JPanel controlPanel = new JPanel();
	
	static int viewmode = 0;
	
	String[] viewmodes = new String[] {"Height","Temperature","TempBio","HeightBio"};
	
	JButton mode;
	JLabel modeLabel;
	
	void setup() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		setPreferredSize(new Dimension(1280,640));
		
		frame.add(this, BorderLayout.CENTER);
		
		mode = new JButton("Viewmode");
		mode.addActionListener(new ViewModeHandler());
		modeLabel = new JLabel("Current Viewmode: Height");
		
		controlPanel.add(mode);
		controlPanel.add(modeLabel);
		
		frame.add(controlPanel, BorderLayout.EAST);
		
		frame.pack();
		
		frame.setVisible(true);
		repaint();
	}

	public static void main(String[] args) {
		new Window().setup();
	}
	
	public static Random rng = new Random();
	
	World w = new World();
	
	@Override
	public void paintComponent(Graphics g) {
		w.draw(g);
	}
	
	class ViewModeHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(viewmode == viewmodes.length-1)
				viewmode = 0;
			else viewmode++;
			
			modeLabel.setText("Current Viewmode: "+viewmodes[viewmode]);
			frame.pack();
			repaint();
		}
		
	}
}
