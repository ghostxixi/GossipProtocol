package utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class keyListener implements KeyListener{
	private JTextComponent J1;
	private JComponent J2;
	
	public keyListener(JTextComponent J1,JComponent J2){
		this.J1 = J1;
		this.J2 = J2;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if((e.getKeyChar() == KeyEvent.VK_ENTER )&&(!J1.getText().trim().equals(""))){
			J2.requestFocus();
		}
		else{
			J1.requestFocus();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
