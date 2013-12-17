package practice;

import java.sql.*;
import java.util.HashMap;

class testRL{
    private static void clearData(String pathname) throws Exception{
        String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
        String url = "jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=" + pathname;
        Connection conn = null;
        Statement stmt = null;

        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "","");
            stmt = conn.createStatement();
            String sql = "delect * from IP";
            stmt.executeUpdate(sql);
        }catch(ClassNotFoundException ex){
            throw new RuntimeException("Access database driver error");
        }catch( SQLException ex){
            throw new RuntimeException("Access database driver error");
        }finally{
            close(stmt);
            close(conn);
        }
    }

    private static void close(Connection c){
        if(c!=null){
            try{
                c.close();
            }catch(Exception e){
            }
        }
    }
    private static void close(Statement d){
        if(d!=null){
            try{
                d.close();
            }catch(Exception e){
            }
        }
    }

    public static void main(String args[]){
    	HashMap<String, Integer> testmap = new HashMap<String, Integer>();
    	Integer intx = new Integer(0);
    	//int x = intx.intValue();
    	int p = ((Integer)testmap.get("xx")).intValue();
    	System.out.println(p);
    }
}
