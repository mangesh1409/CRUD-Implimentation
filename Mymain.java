import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class FirstPage extends Template implements ActionListener
{
	JButton student, admin;
	JLabel TopLabel;
	
	FirstPage()
	{
		TopLabel = new JLabel();
		TopLabel.setHorizontalAlignment(SwingConstants.CENTER);			
		TopLabel.setText("Welcome To Student Databse");
		TopLabel.setForeground(Color.BLUE);
		Dimension topsize = TopLabel.getPreferredSize();
		TopLabel.setBounds(50,40, topsize.width, topsize.height);
		header.add(TopLabel);

		student=new JButton("Add Information");
		student.setHorizontalAlignment(SwingConstants.CENTER);
		Dimension ssize = student.getPreferredSize();
		student.setBounds(85,125,ssize.width,ssize.height );
		
		admin=new JButton("Admin Login");
		admin.setHorizontalAlignment(SwingConstants.CENTER);
		ssize = student.getPreferredSize();
		admin.setBounds(85,200,ssize.width,ssize.height );

		content.add(student);
		content.add(admin);
		ClockHome();
		setVisible(true);
		this.setSize(500,500);
		this.setResizable(false);
		setLocationRelativeTo(null);
		student.addActionListener(this);
		admin.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==student)
		{			
			Registration robj = new Registration();
			int i=robj.JDBCconnect();
			if(i==0)
			{
				this.setVisible(false);
				robj.setVisible(true);
				robj.setSize(600,600);
			}
			else if(i==-1)
			{
				JOptionPane.showMessageDialog(this,"Check Connection...","JDBC Connection", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(ae.getSource()==admin)
		{
			AdminLogin aobj = new AdminLogin();
			this.setVisible(false);
			aobj.setVisible(true);
			aobj.setSize(500,500);
		}
	}
}

class Mymain
{
	public static void main(String arg[])
	{
		try
		{
			FirstPage Lframe=new FirstPage();
			Lframe.setVisible(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Student Database", JOptionPane.ERROR_MESSAGE);
		}
	}
}
