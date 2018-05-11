package suf;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import utils.Utils;
import utils.keyListener;

public class AddStock {
	public final String log = "Ìí¼Ó¿â´æ";
	private JFrame add_Frame = new JFrame(log);
	private JPanel jp = new JPanel(){
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
	
	public AddStock(){
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception exe){System.err.print(exe.getMessage());}
		JFrame.setDefaultLookAndFeelDecorated(true);
		Toolkit tools = add_Frame.getToolkit();
		Image logo = tools.getImage("res/uestc.png");
		add_Frame.setIconImage(logo);
		
		jp.setLayout(new GridBagLayout());
		ImageIcon pic = new ImageIcon("res/uestc.png");
		pic.setImage(pic.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		JLabel picture = new JLabel(pic);
		Util.setupComponent(jp, picture, 0, 0, GridBagConstraints.ABOVE_BASELINE_LEADING, 1, false);
		JLabel name = new JLabel("name:");
		JLabel producer = new JLabel("producer:");
		JLabel number = new JLabel("number:");
		JLabel date = new JLabel("date:");
		JLabel p_text = new JLabel("p_text:");
		JTextField nameText = new JTextField(20);
		JTextField producerText = new JTextField(20);
		JTextField numberText = new JTextField(20);
		JTextField dateText = new JTextField(20);
		JTextField p_textText = new JTextField(20);
		JButton confirm = new JButton("confirm");
		JButton cancel = new JButton("cancel");
		
		Util.setupComponent(jp, name, 0, 1,1, 1, false);
		nameText.requestFocus();
		nameText.addKeyListener(new keyListener(nameText, producerText));
		Util.setupComponent(jp, nameText, 1, 1,1, 1, false);
		
		Util.setupComponent(jp, producer, 0, 2,1, 1, false);
		producerText.addKeyListener(new keyListener(producerText, numberText));
		Util.setupComponent(jp, producerText, 1, 2,1, 1, false);
		
		Util.setupComponent(jp, number, 0, 3,1, 1, false);
		numberText.addKeyListener(new keyListener(numberText,dateText));
		Util.setupComponent(jp, numberText, 1, 3,1, 1, false);
		
		Util.setupComponent(jp, date, 0, 4,1, 1, false);
		dateText.addKeyListener(new keyListener(dateText,p_textText));
		Util.setupComponent(jp, dateText, 1, 4,1, 1, false);
		
		Util.setupComponent(jp, p_text, 0, 5,1, 1, false);
		p_textText.addKeyListener(new keyListener(p_textText,confirm));
		Util.setupComponent(jp, p_textText, 1, 5,1, 1, false);
		
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String insert = "insert into product (product_name,producer,number,date,p_text) values ('"
				+nameText.getText()+"','"+producerText.getText()+"',"+numberText.getText()+",'"+
						dateText.getText()+"','"+p_textText.getText()+"')";
				String record = "insert into record (record_id,node,type,tableAndID,text,date) values ("
						+ config.metadata_operate + ",'" + config.node + "','insert','product_1','"
						+ Utils.CheckString(insert) + "','11-30')";
				DBUtil.update(insert);
				DBUtil.update(record);
				config.AddInsertCount();
				JOptionPane.showMessageDialog(jp, Utils.CheckString(insert));
				add_Frame.dispose();
			}
		});
		Util.setupComponent(jp, confirm, 1, 6,1, 1, false);
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add_Frame.dispose();
			}
		});
		Util.setupComponent(jp, cancel, 2, 6,1, 1, false);
		
		Dimension d = tools.getScreenSize();
		int width = (int)(d.getWidth()-350) /2;
		int height = (int)(d.getHeight()-240)/2;
		add_Frame.add(jp);
		add_Frame.setLocation(width, height);
		add_Frame.setSize(350, 240);
		add_Frame.pack();
		add_Frame.validate();
		add_Frame.setVisible(true);
	}
	
	public static void main(String[] args){
		new AddStock();
	}

}
