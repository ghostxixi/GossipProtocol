package suf;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Face {
	public static final String logoon = "登录";
	public static final String helpt = "帮助";
	public static final String logooff ="退出";
	public static final String user = "用    户:";
	public static final String password = "密    码:";
	private javax.swing.JTextField userText;
	private javax.swing.JPasswordField userpwd;
	private javax.swing.JPanel jp = new javax.swing.JPanel(){

		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			if(!isOpaque()){return;}
			Graphics2D g2d = (Graphics2D) g;
			AlphaComposite opaque = AlphaComposite.SrcOver;
			int width = getWidth();
			int height = getHeight();
			GradientPaint gradientPaint = new GradientPaint(0,0,java.awt.Color.green,width/2,height/2,java.awt.Color.yellow,false);
			g2d.setComposite(opaque);
			g2d.setPaint(gradientPaint);
			g2d.fillRect(0, 0, width,height);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
		}
	};
	private javax.swing.JButton logoin = null;
	private JFrame logo_Frame = new JFrame(logoon);

	public Face(){
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception exe){System.err.print(exe.getMessage());}
		JFrame.setDefaultLookAndFeelDecorated(true);
		Toolkit tools = logo_Frame.getToolkit();
		Image logo = tools.getImage("res/uestc.png");
		logo_Frame.setIconImage(logo);
		
		
		jp.setLayout(new GridBagLayout());
		ImageIcon pic = new ImageIcon("res/uestc.png");
		pic.setImage(pic.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
		JLabel picture = new JLabel(pic);
		setupComponent(picture,0,0,GridBagConstraints.ABOVE_BASELINE_LEADING,1,false);
		JLabel users = new JLabel(user);
		setupComponent(users,0,1,1,1,false);
		JLabel pas = new JLabel(password);
		setupComponent(pas,0,2,1,1,false);
		userText = new javax.swing.JTextField(12);
		userText.requestFocus();
		userText.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e){}
			public void keyPressed(KeyEvent e){
				if((e.getKeyChar() == KeyEvent.VK_ENTER )&&(userText.getText().trim() != "")){
					userpwd.requestFocus();
				}
			}
		});
		setupComponent(userText,1,1,1,1,false);
		userpwd = new javax.swing.JPasswordField(12);
		userpwd.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e){}
			public void keyPressed(KeyEvent e){
				if((e.getKeyChar() == KeyEvent.VK_ENTER) && ((new String(userpwd.getPassword())).trim() != "")){
					logoin.requestFocus();
					System.out.println(new String(userpwd.getPassword()));
				}
				else{
					userpwd.requestFocus();
				}
			}
			public void keyReleased(KeyEvent e){
				
			}
		});
		setupComponent(userpwd,1,2,1,1,false);
		
		logoin = new javax.swing.JButton(logoon);
		logoin.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e){}
			public void keyPressed(KeyEvent e){
				if((e.getKeyChar()==KeyEvent.VK_ENTER )){
					if((userText.getText().trim() == "") ){
						javax.swing.JOptionPane.showMessageDialog(null, "必须输入用户名！","输入用户名",JOptionPane.ERROR_MESSAGE);
						userText.requestFocus();}
					if(new String(userpwd.getPassword()).trim() ==""){
						javax.swing.JOptionPane.showMessageDialog(null, "必须输入密码！","输入密码",JOptionPane.ERROR_MESSAGE);
						userpwd.requestFocus();
						}
					USeDB.logoon(userText.getText().trim(), new String(userpwd.getPassword()).trim());
				}
			}
		});
		logoin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if((userText.getText().trim() == "")){
				userText.requestFocus();
				}
				if((new String(userpwd.getPassword()).trim() =="")){
					userpwd.requestFocus();
				}
				if(userText.getText().trim()!=""&& new String(userpwd.getPassword()).trim()!=""){
				boolean b =USeDB.logoon(userText.getText().trim(), new String(userpwd.getPassword()).trim());
				if(b==true){
					logo_Frame.dispose();
					new My_MainFrame(userText.getText().trim());
					}else{
				userText.setText("");
				userpwd.setText("");
				userText.requestFocus();}
				}
			}
		});
		setupComponent(logoin,0,3,1,1,true);
		
		javax.swing.JButton help = new javax.swing.JButton(helpt);
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
			JOptionPane.showMessageDialog(null,"你需要一个注册的用户名和正确的密码！","帮助",JOptionPane.ERROR_MESSAGE);
			userText.setText("");
			userpwd.setText("");
			userText.setFocusable(true);
			}
		});
		setupComponent(help,2,3,1,1,true);
		
		javax.swing.JButton exit = new javax.swing.JButton(logooff);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e2){
				logo_Frame.dispose();
				System.exit(0);
			}
		});
		setupComponent(exit,1,3,1,1,true);
		Dimension d = tools.getScreenSize();
		int width = (int)(d.getWidth()-350) /2;
		int height = (int)(d.getHeight()-240)/2;
		logo_Frame.add(jp);
		logo_Frame.setAlwaysOnTop(true);
		logo_Frame.setLocation(width, height);
		logo_Frame.setSize(350, 240);
		logo_Frame.pack();
		logo_Frame.validate();
		logo_Frame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO 自动生成方法存根
		new Face();
	}
	public void setupComponent(JComponent component,int gridx,int gridy,int gridwidth,int ipadx,boolean fill){
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new java.awt.Insets(5,3,3,3);
		if(gridwidth>1)
			gridBagConstrains.gridwidth = gridwidth;
		if(ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		if(fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		jp.add(component,gridBagConstrains);
	}
}

