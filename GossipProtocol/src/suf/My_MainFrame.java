package suf;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import utils.Utils;

import java.sql.*;
import java.util.Vector;

public class My_MainFrame {
	private final static javax.swing.ImageIcon icon = new javax.swing.ImageIcon("res/uestc.png");
	private JFrame frame = new javax.swing.JFrame();
	private JMenuBar menuBar = null;
	private JPanel panel;
	private JPanel Add = null;
		
	public My_MainFrame() {
		// TODO 自动生成构造函数存根
	}
	public My_MainFrame(String name){
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception exe){System.err.print(exe.getMessage());}
		java.awt.Toolkit tool = frame.getToolkit();
		Image image = tool.getImage("res/uestc.png");
		java.awt.Dimension dimn = tool.getScreenSize();
		String title = "仓库管理系统--"+name;
		frame.setTitle(title);
		frame.setIconImage(image);
		frame.setFocusable(true);
		frame.setLayout(new java.awt.BorderLayout());
		frame.setJMenuBar(createJMenuBar());
		frame.add(createHandlePane(),"Center");
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent e){}
			//因对窗口调用dispose()而将其关闭时调用
			public void windowClosed(WindowEvent e){}
			//用户试图从窗口的系统菜单中关闭窗口时调用
			public void windowClosing(WindowEvent e){
				frame.dispose();
				System.exit(0);
			}
			//当窗口不再是活动窗口时调用
			public void windowDeactivated(WindowEvent e){}
			//窗口从最小化状态变为正常状态时调用
			public void windowDeiconified(WindowEvent e){}
			//窗口从正常状态变为最小状态时调用
			public void windowIconified(WindowEvent e){}
			//窗口首次变为可见时调用
			public void windowOpened(WindowEvent e){}
		});
		frame.setPreferredSize(dimn);
		frame.pack();
		frame.validate();
		frame.setVisible(true);
		config.config_matedata();
		Thread thread = new Thread(new net());
		thread.setDaemon(true);   //设置为守护进程
		thread.start();
		
	}
	private JMenu createSystem_Manage_Menu(){
		javax.swing.JMenu systemMenu = new javax.swing.JMenu("系统管理");
		javax.swing.JMenuItem logoin = new javax.swing.JMenuItem("注      册");
		logoin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new AddUser();
			}
		});
		javax.swing.JMenuItem logoout = new javax.swing.JMenuItem("删除用户");
		logoout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//new JOptionPane();
				String jp = JOptionPane.showInputDialog(frame,"请输入要删除的用户名！","输入用户名",JOptionPane.YES_NO_CANCEL_OPTION).trim();
				if(USeDB.delUser(jp)){
					JOptionPane.showConfirmDialog(null, "用户删除成功!");
				}
				else{
					JOptionPane.showConfirmDialog(null, "用户删除失败!");
				}
			}
		});
		systemMenu.add(logoin);
		systemMenu.add(logoout);
		return systemMenu;
	}
	private JMenu createStuff_Manage_Menu()
	{
		javax.swing.JMenu stuffMenu = new javax.swing.JMenu("库存管理");
		javax.swing.JMenuItem stuff_stock = new javax.swing.JMenuItem("查询库存");
		stuff_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				try {
					tle();					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		javax.swing.JMenuItem add_stock = new javax.swing.JMenuItem("加入库存");
		add_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				//String sql = "insert into stuff_in (stuff_name,stuff_company,stuff_people) values ('a','b','c')";
				//DBUtil.update(sql);
				//JOptionPane.showMessageDialog(panel, "xi");
				//JPanel p1 = new JPanel();
				//p1.setBackground(Color.blue);
				//panel.add(p1, BorderLayout.EAST);
				Add = new JPanel();
				javax.swing.JLabel component1 = new javax.swing.JLabel("sssssssssss");
				Add.setLayout(new GridBagLayout());
				Add.setPreferredSize(new Dimension(300, 150));
				Add.setBackground(java.awt.Color.lightGray);
				Add.add(component1);
				Button btn = new Button("ok");
				btn.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub	
						panel.remove(Add);
						panel.updateUI();
					}
					
				});
				Add.add(btn);
				setupComponent(Add,3,1,10,10,false);
				//panel.remove(table);
				panel.updateUI();
				//panel.repaint();
				AddStock as = new AddStock();
				
			}
		});
		
		stuffMenu.add(stuff_stock);
		stuffMenu.add(add_stock);
		return stuffMenu;
	}
	
	private void tle() throws SQLException{
		Vector<String> colum = new Vector<String>();  
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
		Connection con = DBUtil.getConnection();
		Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stat.executeQuery("select * from product"); 
        try {  
        	ResultSetMetaData rsmd = rs.getMetaData();  
            for (int i = 1; i <= rsmd.getColumnCount(); ++i){  
                colum.addElement(rsmd.getColumnName(i));
                System.out.println(rsmd.getColumnName(i));
                }  
            while (rs.next()) {  
                Vector<String> currow = new Vector<String>();  
                for (int i = 1; i <= rsmd.getColumnCount(); ++i) {  
                    currow.addElement(rs.getString(i));  
                }  
                rows.addElement(currow);  
            } 
            MyTable x = new MyTable(rows,colum);
            TableModelListener tm = new TableModelListener(){
				@Override
				public void tableChanged(TableModelEvent e) {
					// TODO Auto-generated method stub
			        int row = e.getFirstRow();  
			        int column = e.getColumn();
					String sql = "update product set " + colum.get(column) + " = '" +
							x.getValueAt(row, column) + "' where product_ID = " + x.getValueAt(row,0);
					String record = "insert into record (record_id,node,type,tableAndID,text,date) values ("
							+ config.metadata_operate + ",'" + config.node + "','update','product_1','"
							+ Utils.CheckString(sql) + "','11-30')";
					
					DBUtil.update(sql);
					DBUtil.update(record);
					config.AddInsertCount();
					JOptionPane.showMessageDialog(panel, "修改成功");
				}
            	
            };            
            x.addTableModelListener(tm);
            JTable table = new JTable(x);
            //panel.add(table, BorderLayout.CENTER);
            setupComponent(table,0,1,1,1,false);
            //table.setVisible(true);  
            table.setRowHeight(30);  
            setupComponent(new JScrollPane(table),0,1,1,1,false);
            table.updateUI();
        } catch (SQLException e1) {  
            // TODO Auto-generated catch block  
            e1.printStackTrace();  
        }  
	}
	
	private JMenuBar createJMenuBar(){
		menuBar = new JMenuBar();
		menuBar.add(createSystem_Manage_Menu());
		menuBar.add(createStuff_Manage_Menu());
		menuBar.setBackground(Color.blue);
		return menuBar;
	}
	private JPanel createHandlePane(){
		panel = new JPanel();
		icon.setImage(icon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
		javax.swing.JLabel component = new javax.swing.JLabel(icon);
		panel.setLayout(new GridBagLayout());
		panel.setBackground(java.awt.Color.cyan);
		setupComponent(component,0,0,GridBagConstraints.ABOVE_BASELINE_LEADING,1,false);
		return panel;
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
		panel.add(component,gridBagConstrains);
	}
}
