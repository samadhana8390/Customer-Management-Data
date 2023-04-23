package customerData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class customer
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sam","sam");
		//create table customerD (c_name varchar(10),c_Id number(10),Email varchar(20),contact number(10), city varchar(10));
		
		int choise=0;
		String ch="Y";
		do
		{
			int c_id;
			int contact;
			String c_name;
			String Email;
			String city;
			System.out.println(" add data Customer          = 1");
			System.out.println(" Display All Customer data  = 2");
			System.out.println(" remove Customer Sp         = 3");
			System.out.println(" Update contact no customer = 4");
			System.out.println(" Sarch Sp custemer          = 5");
			
			System.out.println("++++++++++ Enter Your Choise +++++++++++");
			choise=sc.nextInt();
			
			switch(choise)
			{
				
				case 1:
						System.out.println(" Enter customer id");
						c_id=sc.nextInt();
						System.out.println(" Enter customer name ");
						c_name=sc.next();
						System.out.println(" enter the Custemer email ");
						Email=sc.next();
						System.out.println(" eneter the customer contact");
						contact=sc.nextInt();
						System.out.println(" enter the customer city");
						city=sc.next();						
						String s="insert into customerD values(?,?,?,?,?)";
						PreparedStatement sq=con.prepareStatement(s);
						sq.setString(1,c_name);
						sq.setInt(2,c_id);
						sq.setString(3,Email);
						sq.setInt(4,contact);
						sq.setString(5,city);
						
						int r = sq.executeUpdate();
						if (r > 0)
						{
							System.out.println("Data Added");
						}
					
						
						break;
				case 2:
					
						String sql1 = "select * from customerD";
						PreparedStatement ps1 = con.prepareStatement(sql1);
						ResultSet rs = ps1.executeQuery();
	
						System.out.println(" Name +++++ c_Nid +++++ Email +++++ contact ++++ city");
						while (rs.next()) 
						{
	
							System.out.print(rs.getString(1) + " ");
							System.out.print(rs.getInt(2) + " ");
							System.out.println(rs.getString(3) + " ");
							System.out.println(rs.getInt(4) + " ");
							System.out.println(rs.getString(5) + "  ");
	
						}
	
						rs.close();

						break;
				case 3:
						
							String sql2="delete from customerD where c_Id=? ";
							PreparedStatement ps2=con.prepareStatement(sql2);
							System.out.println("enter the id for delete");
							int id=sc.nextInt();
							ps2.setInt(1,id);
							
							int q = ps2.executeUpdate();
							if (q > 0)
							{
								System.out.println("Data Delete");
							}
					
						break;
				case 4:
							String sql3="Update customerD set contact=? where c_Id=?";
							PreparedStatement ps4=con.prepareStatement(sql3);
							System.out.println(" Update New contact");
							int contact1=sc.nextInt();
							System.out.println(" which Id contact Update");
							int D=sc.nextInt();
							ps4.setInt(1, contact1);
							ps4.setInt(2, D);
							
							int a=ps4.executeUpdate();
							if(a>0)
							{
								System.out.println(" UPDATED ");
							}
							
					
					
						break;
						
				case 5:
					
							String sql4="select * from customerD where c_name=?";
							PreparedStatement ps3=con.prepareStatement(sql4);
							System.out.println("enter the name");
							String name=sc.next();
							ps3.setString(1, name);
							

							ResultSet rs1 = ps3.executeQuery();
							System.out.println(" Name +++++ c_Nid +++++ Email +++++ contact ++++ city");
							while (rs1.next()) 
							{
		
								System.out.print(rs1.getString(1) + " ");
								System.out.print(rs1.getInt(2) + " ");
								System.out.println(rs1.getString(3) + " ");
								System.out.println(rs1.getInt(4) + " ");
								System.out.println(rs1.getString(5) + "  ");
		
							}
		
							rs1.close();

						break;
				
				
						 
			}
			System.out.println("++++ Do you Whant to continus Y/ N ++++");
			ch=sc.next();	
		}
		while(ch!="n");
	}	
		

}