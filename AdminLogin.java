import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AdminLogin extends Template implements ActionListener
{
	JButton submit, Prev;
	JLabel label1,label2,TopLabel;
	final JTextField text1,text2;
	private static int attemp = 3;

	AdminLogin()
	{
		TopLabel = new JLabel();
		TopLabel.setHorizontalAlignment(SwingConstants.CENTER);			
		TopLabel.setText("Welcome to Admin Portal....");
		TopLabel.setForeground(Color.BLUE);

		Dimension topsize = TopLabel.getPreferredSize();
		TopLabel.setBounds(50,40, topsize.width, topsize.height);
		header.add(TopLabel);

		label1 = new JLabel();
		label1.setText("Username:");
		label1.setForeground(Color.white);
		Dimension size = label1.getPreferredSize();
		label1.setBounds(50,135,size.width,size.height);
		label1.setHorizontalAlignment(SwingConstants.CENTER);

		text1 = new JTextField(15);
		Dimension tsize = text1.getPreferredSize();
		text1.setBounds(200,135, tsize.width, tsize.height);
		text1.setToolTipText("ENTER USERNAME");

		label2 = new JLabel();
		label2.setText("Password:");
		label2.setBounds(50,175, size.width, size.height);
		label2.setForeground(Color.white);
		label2.setHorizontalAlignment(SwingConstants.CENTER);

		text2 = new JPasswordField(15);
		text2.setBounds(200,175, tsize.width, tsize.height);
		text2.setToolTipText("ENTER PASSWORD");

		submit=new JButton("SUBMIT");
		submit.setHorizontalAlignment(SwingConstants.CENTER);
		Dimension ssize = submit.getPreferredSize();
		submit.setBounds(200,220,ssize.width,ssize.height );
		
		Prev=new JButton("BACK");
		Prev.setHorizontalAlignment(SwingConstants.CENTER);
		ssize = Prev.getPreferredSize();
		Prev.setBounds(50,220,ssize.width,ssize.height );

		content.add(label1);
		content.add(text1);
		content.add(label2);
		content.add(text2);
		content.add(submit);
		content.add(Prev);

		ClockHome();
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		submit.addActionListener(this);
		Prev.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae)
	{
		String value1=text1.getText();
		String value2=text2.getText();

		if(ae.getSource()==submit)
		{					
			if (value1.equals("123")&&value2.equals("123"))
			{
				AdminPage page = new AdminPage(value1);
				int i=page.JDBCconnect();
				if(i==0)
				{
					this.setVisible(false);
					page.setVisible(true);
					page.setSize(500,500);
				}
				else if(i==-1)
				{
					JOptionPane.showMessageDialog(this,"Check Connection...","JDBC Connection", JOptionPane.ERROR_MESSAGE);
				}	
			}
			else
			{
				attemp--;
				if(attemp == 0)
				{
					JOptionPane.showMessageDialog(this, "Number of attempts finished","Welcome to Admin Portal....", JOptionPane.ERROR_MESSAGE);
					this.dispose();
					System.exit(0);
				}	
				JOptionPane.showMessageDialog(this, "Incorrect login or password", "Error", JOptionPane.ERROR_MESSAGE);
			}			
		}
		else if(ae.getSource()==Prev)
		{
			FirstPage Lframe=new FirstPage();
			Lframe.setVisible(true);
			this.setVisible(false);		
		}
	}
}
