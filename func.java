package sample;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.ArrayList;

public class func {
    public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:ignatovdatab";
    public static final String DBUSER = "system";
    public static final String DBPASS = "1234";

    ;


    public ArrayList<String> findProf(String first, String second)throws SQLException {
        ArrayList<String> out = new ArrayList<>();
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        CallableStatement stmt = conn.prepareCall("BEGIN FINDPROF(?,?,?); END;");
        stmt.setString(1, first);
        stmt.setString(2, second);// DEPTNO
        stmt.registerOutParameter(3, OracleTypes.CURSOR); //REF CURSOR
        stmt.execute();
        ResultSet rs = ((OracleCallableStatement)stmt).getCursor(3);
        while (rs.next()) {
            out.add(rs.getString(1));
            //System.out.println(rs.getString("outputline"));
        }
        rs.close();
        rs = null;
        stmt.close();
        stmt = null;
        conn.close();
        conn = null;

        return out;
    }
    public ArrayList<String> findUniv(String prof)throws SQLException {
        ArrayList<String> out = new ArrayList<>();
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        CallableStatement stmt = conn.prepareCall("BEGIN FINDUNIV(?,?); END;");
        stmt.setString(1, prof);
        stmt.registerOutParameter(2, OracleTypes.CURSOR); //REF CURSOR
        stmt.execute();
        ResultSet rs = ((OracleCallableStatement)stmt).getCursor(2);
        //System.out.println(rs.getString("outputline"));
        while (rs.next()) {
            out.add(rs.getString(1));
            //System.out.println(rs.getString(1));
            //System.out.println(rs.getString("outputline"));
        }
        rs.close();
        rs = null;
        stmt.close();
        stmt = null;
        conn.close();
        conn = null;

        return out;
    }

}
