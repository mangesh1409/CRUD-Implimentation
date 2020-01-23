import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.sql.*;

class Registration extends JFrame implements ActionListener
{
	public ResultSet rs=null;
	public Statement stmt=null;
	public Connection con=null;
	public PreparedStatement stmt1=null;
	public int no,i;
	public String str;
	private Container c; 
   	private JLabel title, rollno, std, div, gender, dob, add, name;
   	private JTextField tname, trollno, tstd, tdiv, tadd; 
   	private ButtonGroup gengp; 				//////  to select one out of all
    	private JComboBox date,month,year; 
    	private JCheckBox term, male,female; 
    	private JButton sub, reset, prev; 
    	private String dates[] = { "","1", "2", "3", "4", "5","6", "7", "8", "9", "10","11", "12", "13", "14", "15","16", "17", "18", "19", "20", 
            						   "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }; 
	private String months[] = {"", "Jan", "feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec" }; 
    	private String years[]= { "","1995", "1996", "1997", "1998","1999", "2000", "2001", "2002","2003", "2004", "2005", "2006", 
					                 "2007", "2008", "2009", "2010","2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018","2019" }; 
  	public String st="10";
  	
	Registration()
	{
		setTitle("Registration Form"); 
        	setBounds(300, 90, 900, 600); 
        	setResizable(false); 
  
        	c = getContentPane(); 
        	c.setLayout(null); 
  
        	title = new JLabel("Registration Form"); 
        	title.setFont(new Font("Arial", Font.PLAIN, 25)); 
        	title.setSize(300, 30); 
        	title.setLocation(180, 30); 
        	c.add(title); 
  
  		rollno = new JLabel("Roll No."); 
        	rollno.setFont(new Font("Arial", Font.PLAIN, 15)); 
        	rollno.setSize(100, 20); 
       	 	rollno.setLocation(65, 100); 
        	c.add(rollno); 
        	
        	trollno = new JTextField(); 
        	trollno.setFont(new Font("Arial", Font.PLAIN, 15)); 
        	trollno.setSize(100, 20); 
        	trollno.setLocation(140, 100); 
        	c.add(trollno); 
        	//////////////////////////////////////////////////////////////////////////////////////
        	name = new JLabel("Name"); 
        	name.setFont(new Font("Arial", Font.PLAIN, 15)); 
        	name.setSize(100, 20); 
       	 	name.setLocation(65, 130); 
        	c.add(name); 
  
        	tname = new JTextField(); 
        	tname.setFont(new Font("Arial", Font.PLAIN, 15)); 
        	tname.setSize(300, 20); 
        	tname.setLocation(140, 130); 
        	c.add(tname); 
  		/////////////////////////////////////////////////////////////////////////////////////////
        	std = new JLabel("Standard"); 
        	std.setFont(new Font("Arial", Font.PLAIN, 15)); 
       	 	std.setSize(100, 20); 
        	std.setLocation(65, 160); 
        	c.add(std); 
  
        	tstd = new JTextField(); 
        	tstd.setFont(new Font("Arial", Font.PLAIN, 15)); 
        	tstd.setText(st);
        	tstd.setEditable(false);
        	tstd.setSize(100, 20); 
        	tstd.setLocation(140, 160); 
        	c.add(tstd); 
  		////////////////////////////////////////////////////////////////////////////////////////
  		div = new JLabel("Division"); 
        	div.setFont(new Font("Arial", Font.PLAIN, 15)); 
       	 	div.setSize(100, 20); 
        	div.setLocation(65, 190); 
        	c.add(div); 
  
        	tdiv = new JTextField(); 
        	tdiv.setFont(new Font("Arial", Font.PLAIN, 15)); 
        	tdiv.setSize(100, 20); 
        	tdiv.setLocation(140, 190); 
        	c.add(tdiv); 
        	/////////////////////////////////////////////////////////////////////////////////////////
        	gender = new JLabel("Gender"); 
        	gender.setFont(new Font("Arial", Font.PLAIN, 15)); 
        	gender.setSize(100, 20); 
        	gender.setLocation(65, 220); 
        	c.add(gender); 
  		
	        male = new JCheckBox("Male"); 
	        male.setFont(new Font("Arial", Font.PLAIN, 15)); 
	        male.setSize(75, 20); 
	        male.setLocation(135, 220); 
	        c.add(male); 
  
	        female = new JCheckBox("Female"); 
	        female.setFont(new Font("Arial", Font.PLAIN, 15)); 
	        female.setSize(80, 20); 
	        female.setLocation(135, 240); 
	        c.add(female); 
	  	
	        gengp = new ButtonGroup(); 				
	        gengp.add(male); 
	        gengp.add(female); 
	  	//////////////////////////////////////////////////////////////////////////////////////
	        dob = new JLabel("Date Of Birth"); 
	        dob.setFont(new Font("Arial", Font.PLAIN, 15)); 
	        dob.setSize(100, 20); 
	        dob.setLocation(65, 270); 
	        c.add(dob); 
	  
	        date = new JComboBox(dates); 
	        date.setFont(new Font("Arial", Font.PLAIN, 15)); 
	        date.setSize(50, 20); 
	        date.setLocation(170, 270); 
	        c.add(date); 
	  
	        month = new JComboBox(months); 
	        month.setFont(new Font("Arial", Font.PLAIN, 15)); 
	        month.setSize(60, 20); 
	        month.setLocation(225, 270); 
	        c.add(month); 
	  
	        year = new JComboBox(years); 
	        year.setFont(new Font("Arial", Font.PLAIN, 15)); 
	        year.setSize(75, 20); 
	        year.setLocation(288, 270); 
	        c.add(year); 
	  	//////////////////////////////////////////////////////////////////////////////
	        add = new JLabel("Address"); 
	        add.setFont(new Font("Arial", Font.PLAIN, 15)); 
	        add.setSize(100, 20); 
	        add.setLocation(65, 300); 
	        c.add(add); 
	  
	        tadd = new JTextField(); 
	        tadd.setFont(new Font("Arial", Font.PLAIN, 15)); 
	        tadd.setSize(150, 20); 
	        tadd.setLocation(135, 300); 
	        add.setToolTipText("Enter Only name of city");
	        c.add(tadd); 
	  	//////////////////////////////////////////////////////////////////////////////////
	        term = new JCheckBox("Accept Terms And Conditions."); 
	        term.setFont(new Font("Arial", Font.PLAIN, 15)); 
	        term.setSize(275, 20); 
	        term.setLocation(90, 340); 
	        c.add(term); 
	  
	        sub = new JButton("SUBMIT"); 
	        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
	        sub.setSize(100, 20); 
	        sub.setLocation(90, 370); 
	        sub.addActionListener(this); 
	        c.add(sub); 
	  
	        reset = new JButton("RESET"); 
	        reset.setFont(new Font("Arial", Font.PLAIN, 15)); 
	        reset.setSize(100, 20); 
	        reset.setLocation(200, 370); 
	        reset.addActionListener(this); 
	        c.add(reset); 
	        
	        prev = new JButton("BACK"); 
	        prev.setFont(new Font("Arial", Font.PLAIN, 15)); 
	        prev.setSize(100, 20); 
	        prev.setLocation(320, 370); 
	        prev.addActionListener(this); 
	        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        c.add(prev); 
	  
	  	//setVisible(true); 
	        //JDBCconnect();
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
	
	public void InsertData()
	{
		Object obj1,obj2,obj3;
		try
		{	
			String gender1;
			String roll=trollno.getText();	
			int rolno = Integer.parseInt(roll);
			String name=(tname.getText().toUpperCase());
			String std1=tstd.getText();
			int std= Integer.parseInt(std1);
			String div=(tdiv.getText().toUpperCase());
			if (male.isSelected()) 
			{
                   		gender1 ="Male";
                   	}	 
                	else
                    	{
                    		gender1 ="Female";
                         } 
			obj1=date.getSelectedItem();
			String date=(String)obj1;
			obj2=month.getSelectedItem();
			String month=(String)obj2;
			obj3=year.getSelectedItem();
			String year=(String)obj3;
			String DOB=	date+"/"+month+"/"+year;
			String add=(tadd.getText().toUpperCase());
			
			String sql = "insert into Student(Roll_No, Name, Standard, Division, Gender, DOB, Address) values(?, ?, ?, ?, ?, ?, ?)";
			stmt1 = con.prepareStatement(sql);

			stmt1.setInt(1, rolno);
			stmt1.setString(2, name);
			stmt1.setInt(3, std);
			stmt1.setString(4, div);
			stmt1.setString(5, gender1);
			stmt1.setString(6, DOB);
			stmt1.setString(7, add);

			stmt1.executeUpdate();
        		if(i==1)
        		{
        			JOptionPane.showMessageDialog(this,"Information Updated Successfully..","Registration Form", JOptionPane.PLAIN_MESSAGE);
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(this,"Registration Done Successfully..","Registration Form", JOptionPane.PLAIN_MESSAGE);
        		}
		}
		
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Unable to Register ...","Registration Form", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	public void Update(int Rno,String value)
	{
		no=1;
		str=value;
		String rno=Integer.toString(Rno);
		try
		{
			String sql = "select * from Student where Roll_No="+ rno;
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			
			if(rs.next())
			{
				
				trollno.setText(rs.getString(1));
				tname.setText(rs.getString(2));
				tstd.setText(rs.getString(3));
				tdiv.setText(rs.getString(4));
				male.setSelected(false);  
				female.setSelected(false);  
				date.setSelectedItem("");
				month.setSelectedItem("");
				year.setSelectedItem("");
				tadd.setText(rs.getString(7));
										
				sql="delete from Student where Roll_No="+rno;
				stmt1 = con.prepareStatement(sql);
				stmt1.executeUpdate();
        			i=1;
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(this,"No Such Record..","Registration Form", JOptionPane.ERROR_MESSAGE);
        			AdminPage page = new AdminPage(str);
				this.setVisible(false);
				page.setVisible(true);
				page.setSize(500, 500);
        		}
        	}
        	catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Unable To Update..","Upatate Information", JOptionPane.ERROR_MESSAGE);
		}			
	}
	
	public void actionPerformed(ActionEvent e) 
    	{ 
        	if(e.getSource() == sub) 
        	{ 
            		if((term.isSelected())&&((!trollno.getText().isEmpty()))&&(!(tname.getText().isEmpty()))&&(!(tstd.getText().isEmpty()))&&(!(tdiv.getText().isEmpty()))&&(((male.isSelected()))||((female.isSelected())))&&(!(tadd.getText().isEmpty())&&(month.getSelectedItem()!="")&&(date.getSelectedItem()!="")&&(year.getSelectedItem()!="")))
            		{ 
            			term.setSelected(false);  
            			InsertData();
            			
            			if(no!=1)
            			{
            				FirstPage Lframe=new FirstPage();
            				this.setVisible(false);
            				Lframe.setSize(500,500);
					Lframe.setVisible(true);
				}
				else
				{
					AdminPage page = new AdminPage(str);
					this.setVisible(false);
					page.setVisible(true);
					page.setSize(500, 500);
				}
            		} 		
            	       else	
            	       { 
                		JOptionPane.showMessageDialog(this,"Fill all entries","Registration Form", JOptionPane.ERROR_MESSAGE);
            		} 
        	}	 
  
        	else if (e.getSource() == reset) 
        	{ 
            		String def = ""; 
            		trollno.setText(def);
            		tname.setText(def); 
            		tstd.setText(def); 
           	 	tdiv.setText(def); 
           	 	date.setSelectedIndex(0); 
            		month.setSelectedIndex(0); 
            		year.setSelectedIndex(0); 
           	 	tadd.setText(def); 
           	 	term.setSelected(false);  
           	 	JOptionPane.showMessageDialog(this,"Fill the data again","Registration Form", JOptionPane.ERROR_MESSAGE);
        	}	        	
        	else if (e.getSource() == prev) 
        	{ 
            		if(no!=1)
            		{
            			FirstPage Lframe=new FirstPage();
            			this.setVisible(false);
            			Lframe.setSize(500,500);
				Lframe.setVisible(true);
			}
			else
			{
				AdminPage page = new AdminPage(str);
				this.setVisible(false);
				page.setVisible(true);
				page.setSize(500, 500);
			}
        	}	 
    	} 
}
