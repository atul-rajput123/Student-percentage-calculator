import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
class FDemo1 extends Frame implements ActionListener
{
    Button b1,b2;
	TextArea t;
    TextField tx[]=new TextField[7];	
	Label l1[]=new Label[10];
	TextField t1;
	Label l;
	int i;
    FDemo1()
    {
        setLayout(null);
        Font f=new Font(" ",Font.BOLD,25);
		
        // Font f1=new Font(" ",Font.BOLD,35);

         l1[0]=new Label("Enter Name =>");
         l=new Label("Search Name =>");
         l1[1]=new Label("Enter Sub..1 =>");
         l1[2]=new Label("Enter Sub..2 =>");
         l1[3]=new Label("Enter Sub..3 =>");
         l1[4]=new Label("Enter Sub..4 =>");
         l1[5]=new Label("Enter Sub..5 =>");
         l1[6]=new Label("Percentage =>");
		 l1[7]=new Label("STUDET GRADE CALCULATOR");
		 
		 int x=50,y=100;
		for(i=0;i<7;i++)
		{
       l1[i].setSize(180,50);
       l1[i].setLocation(x,y);
       l1[i].setFont(f);
       add(l1[i]);
	   y=y+80;
		}
       l1[6].setLocation(50,700);
       l1[7].setLocation(300,40);
       l1[7].setSize(400,50);
       l1[7].setFont(f);
	   l1[7].setBackground(Color.red);
       add(l1[7]);
	   l.setLocation(550,100);
       l.setSize(180,50);
       l.setFont(f);
       add(l);


	   int x1=300,y1=100;
       for( i=0;i<7;i++)
	   {
       tx[i]=new TextField(10);
       tx[i].setLocation(x1,y1);
       tx[i].setSize(150,50);
       tx[i].setFont(f);
       add(tx[i]);
	   y1=y1+80;
	   }
	   tx[6].setLocation(300,700);
	   
	   t1=new TextField(10);
       t1.setLocation(800,100);
       t1.setSize(150,50);
       t1.setFont(f);
       add(t1);
	   
	    t=new TextArea();
       t.setLocation(600,250);
       t.setSize(250,250);
       t.setFont(f);
       add(t);


       b1=new Button("Click");
       b1.setLocation(200,600);
       b1.setSize(100,60); 
       b1.setFont(f);
       add(b1);
	   b2=new Button("Result!");
       b2.setLocation(700,180);
       b2.setSize(150,60); 
       b2.setFont(f);
       add(b2);
       b1.addActionListener(this);
       b2.addActionListener(this);
    }
	String s[]=new String[10];
	int sum=0;
    public void actionPerformed(ActionEvent e)
    {
	String s1=t1.getText();
		Button b=(Button)e.getSource();

		    try
			{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false","root","root"); 
		if(b==b1)
		{
		   for(i=0;i<6;i++)
		  {
            s[i]=tx[i].getText();
		   } 

			PreparedStatement st=con.prepareStatement("insert into percent value(?,?,?,?,?,?)");
            int j=1;
			for(i=0;i<6;i++)
			{
			st.setString(j,s[i]);
			j++;
			}
			st.executeUpdate();
		}   
		if(b==b2)
		{
			PreparedStatement st1=con.prepareStatement("Select * from percent where UNAME=?");
			st1.setString(1,s1);
			ResultSet rs=st1.executeQuery();
			while(rs.next())
			{
			  t.setText("Name=>"+rs.getString(1)+"\nSub.1=>"+rs.getString(2)+"\nSub.2=>"+rs.getString(3) +"\nSub.3=>"+ rs.getString(4)+"\nSub.4=>"+rs.getString(5)+"\nSub.5=>"+rs.getString(6));  
			}
		}
            con.close(); 
			
        }
        catch(Exception e1)
        {
           System.out.println(e1);
        } 
		for(i=1;i<6;i++)
		{
			sum=sum+Integer.parseInt(s[i]);
		}
		int t1=sum;
		 float r1=sum/5f;
		 tx[6].setText(" "+r1);
		 for(i=0;i<6;i++)
		{
         tx[i].setText(" ");
			
		}
		 sum=0;
		 
	
	}
}
class Demo1 
{
    public static void main(String ar[])
    {
         FDemo1 f=new FDemo1();
         f.setVisible(true);
         f.setSize(1000,800);
         f.setLocation(8900,200);
         f.setBackground(Color.yellow);
    }

}
