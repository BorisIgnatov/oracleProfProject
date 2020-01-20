package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class profInsert {

    public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:ignatovdatab";
    public static final String DBUSER = "system";
    public static final String DBPASS = "1234";

    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        Statement statement = con.createStatement();

        String csvFile = "prof.txt";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            int i = 0;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] datacsv = line.split(cvsSplitBy);
                String querry = "";
                /*if(datacsv[0].charAt(0) == '"'){
                    datacsv[0] = datacsv[0].substring(1,datacsv[0].length()-1);
                }
                if(datacsv[0].contains("/")){
                    datacsv[0] = datacsv[0].replace('/',' ');
                }
                if(datacsv[0].contains(":")){
                    datacsv[0] = datacsv[0].replace(':',' ');
                }*/
                querry = "INSERT INTO professions (spec_name, prof_name) VALUES ('"+datacsv[0]+"', '"+datacsv[1]+"');";
                if(i>=1){
                    System.out.println(querry);
                    //statement.executeUpdate(querry);
                }
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        statement.close();
        con.close();
    }
}
