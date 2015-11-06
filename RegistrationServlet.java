
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



@WebServlet("/Hos_info")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
//    public Hos_info() {
//        // TODO Auto-generated constructor stub
//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Done");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("not");
			int length = request.getContentLength();
            byte[] input = new byte[length];
            ServletInputStream sin = request.getInputStream();
            int c, count = 0 ;
           
           int i;
           while ((c = sin.read(input, count, input.length-count)) != -1) 
           {
              
           }

           String recievedString = new String(input);
           System.out.println(recievedString);
         
           JSONParser parser = new JSONParser();
           Object obj = null;
           try
           {
           obj = parser.parse(recievedString);
           }
           catch(Exception pe)
           {
        	   System.out.println(pe);
           }
           JSONObject object = (JSONObject) obj;
           String name=(String)object.get("name");
           String email=(String)object.get("email");
           String city=(String)object.get("city");
           String dob=(String)object.get("dob");
           String password=(String)object.get("password");
           Long mobile=(Long)object.get("mobile");
           //System.out.println(name+"   "+number);
           System.out.println(object);
           
           
           sin.close();
           
           JSONObject jsonObject=new JSONObject();
           //jsonObject.get(arg0)
           
           
           response.getWriter().write("success");
           
     try {
    	 Class.forName("com.mysql.jdbc.Driver");
    	 Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/client_database","root","root");
    	 String query= "insert into Register_Table(name,email,mobile,dob,city,password) values(?,?,?,?,?,?)";
    	 PreparedStatement ps=conn.prepareStatement(query);
    	 ps.setString(1, name);
    	 ps.setString(2, email);
    	 ps.setLong(3, mobile);
    	 ps.setString(4, dob);
    	 ps.setString(5, city);
    	 ps.setString(6, password);
    	 
    	 ps.execute();
    	 conn.close();
    	 ps.close();
    	 
		
	} catch (Exception e) {
		System.out.println(e);
	}   
           }
		catch(Exception e){e.printStackTrace();}
		}
	

}
