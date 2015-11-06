

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
	 PreparedStatement ps;
	 ResultSet rs;
	 
	 JSONObject jsonOb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Done");	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			jsonOb=null;
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
           String username=(String)object.get("username");
           String password=(String)object.get("password");
           String loginType=(String)object.get("loginType");
         
           System.out.println(object);
           
           
           sin.close();
         
           
           
           
     try {
    	 Class.forName("com.mysql.jdbc.Driver");
    	  conn= DriverManager.getConnection("jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/jbossews","adminm7Q58M7","JnftT1RCWbkQ");
    	 String query= "select * from Register_Table where email=? and password=?";
    	  ps=conn.prepareStatement(query);
    	 ps.setString(1, username);
    	 ps.setString(2, password);
    	 
    	 PrintWriter out = response.getWriter();
   rs = ps.executeQuery();
   
         while (rs.next())
         {
             String name = rs.getString(1);
             String email= rs.getString(2);
             long mobile= rs.getLong(3);
             String dob=rs.getString(4);
             String city=rs.getString(5);
             
         	 jsonOb  =new JSONObject();
               
             
             jsonOb.put("name", name);
             jsonOb.put("email", email);
             jsonOb.put("mobile", mobile);
             jsonOb.put("dob", dob);
             jsonOb.put("city", city);
             jsonOb.put("password", password);
             
             
             System.out.println(""+jsonOb);
             
    	 
             
            		
	}
         }catch (Exception e) {
		e.printStackTrace();
	}   
     
     finally{
   	 conn.close();
     ps.close();
     rs.close();
 

     }

                }
		catch(Exception e){e.printStackTrace();}
		
		if(jsonOb==null){
			response.getWriter().write("failure");
            
		}
            else{
            	response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(""+jsonOb);
            }
            	
		
		}
	}


