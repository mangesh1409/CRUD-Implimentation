import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.FileWriter; 
import java.io.PrintWriter;
import java.io.BufferedWriter;

class AdminPage extends  Template implements ActionListener
{
	public JButton update,display, prev,delete;
	public JLabel TopLabel;
	public ResultSet rs=null;
	public Statement stmt=null;
	public Connection con=null;
	public PreparedStatement stmt1=null;
	public String value;
	
	public AdminPage(String str)
	{
		value=str;
		TopLabel = new JLabel();
		TopLabel.setHorizontalAlignment(SwingConstants.CENTER);			
		TopLabel.setText("Welcome "+str+"....");
		TopLabel.setForeground(Color.BLUE);

		Dimension topsize = TopLabel.getPreferredSize();
		TopLabel.setBounds(50,40, topsize.width, topsize.height);
		header.add(TopLabel);

		delete=new JButton("Delete");
		delete.setHorizontalAlignment(SwingConstants.CENTER);
		Dimension ssize = delete.getPreferredSize();
		delete.setBounds(80,120,ssize.width,ssize.height );
		
		update=new JButton("Update");
		update.setHorizontalAlignment(SwingConstants.CENTER);
		ssize = update.getPreferredSize();
		update.setBounds(200,120,ssize.width,ssize.height );
		
		display=new JButton("Download Data");
		display.setHorizontalAlignment(SwingConstants.CENTER);
		ssize = display.getPreferredSize();
		display.setBounds(320,120,ssize.width,ssize.height );
		
		prev=new JButton("BACK");
		prev.setHorizontalAlignment(SwingConstants.CENTER);
		ssize = prev.getPreferredSize();
		prev.setBounds(200,220,ssize.width,ssize.height );

		content.add(delete);
		content.add(update);
		content.add(display);
		content.add(prev);
		
		ClockHome();
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		update.addActionListener(this);
		display.addActionListener(this);
		prev.addActionListener(this);
		delete.addActionListener(this);
	}

	public int JDBCconnect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/School","root","");
			stmt=con.createStatement();
			return 0;
		}
		catch(Exception e)
		{
			return -1;
		}	
	}
	
	public void DisplayData()
	{
		try
		{
			rs=stmt.executeQuery("select * from Student");		
			String path="/home/mangesh/Desktop/AWT JDBC/Student.csv";
			FileWriter fw= new FileWriter(path);
    			BufferedWriter bw=new BufferedWriter(fw);
    			PrintWriter pw = new PrintWriter(bw);
			
			pw.print("Roll No\tName\tStandard\tDivision\tGender\tDate Of Birth\tCity");
			pw.println();	
			
			while(rs.next())
			{
				pw.print(rs.getInt(1)+"\t");
				pw.print(rs.getString(2)+"\t");
				pw.print(rs.getInt(3)+"\t");
				pw.print(rs.getString(4)+"\t");
				pw.print(rs.getString(5)+"\t");
				pw.print(rs.getString(6)+"\t");
				pw.print(rs.getString(7)+"\t");
				pw.println();	
			}
				pw.flush();
				pw.close();
				JOptionPane.showMessageDialog(this,"Data Downloaded Successfully..","Download Data", JOptionPane.PLAIN_MESSAGE);			
		}		
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Failed to Download Data...","Download Data", JOptionPane.ERROR_MESSAGE);
		}			
	}
	
	public void Delete(String rno)
	{
		try
		{
			String sql = "select * from Student where Roll_No="+ rno;
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			
			if(rs.next())
			{								
				sql="delete from Student where Roll_No="+rno;
				stmt1 = con.prepareStatement(sql);
				stmt1.executeUpdate();
        			JOptionPane.showMessageDialog(this,"Record Deleted Successfully..","Delete Record", JOptionPane.PLAIN_MESSAGE);			
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(this,"No Such Record..","Delete Record", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        	catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Unable To Delete Record..","Delete Record", JOptionPane.ERROR_MESSAGE);
		}	
	}
		
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==prev)
		{
			AdminLogin aobj = new AdminLogin();
			this.setVisible(false);
			aobj.setVisible(true);
			aobj.setSize(500,500);
		}
		else if(ae.getSource()==display)
		{
			int i=JDBCconnect();
			if(i==0)
			{
				DisplayData();
			}
			else if(i==-1)
			{
				JOptionPane.showMessageDialog(this,"Check Connection...","JDBC Connection", JOptionPane.ERROR_MESSAGE);
			}		
		}
		else if(ae.getSource()==update)
		{
			JFrame f= new JFrame();
			String RollNo=JOptionPane.showInputDialog(f,"Enter Roll Number");
			int rolno = Integer.parseInt(RollNo);
			Registration robj = new Registration();
			int i=robj.JDBCconnect();
			if(i==0)
			{
				this.setVisible(false);
				robj.setVisible(true);
				robj.setSize(600,600);
				robj.Update(rolno,value);
			}
			else if(i==-1)
			{
				JOptionPane.showMessageDialog(this,"Check Connection...","JDBC Connection", JOptionPane.ERROR_MESSAGE);
			}		
		}
		else if(ae.getSource()==delete)
		{
			JFrame f= new JFrame();
			String RollNo=JOptionPane.showInputDialog(f,"Enter Roll Number");
			
			int i=JDBCconnect();
			if(i==0)
			{
				Delete(RollNo);			
			}
			else if(i==-1)
			{
				JOptionPane.showMessageDialog(this,"Check Connection...","JDBC Connection", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
