package Main;

import org.apache.log4j.Logger;

import javax.mail.Session;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Connector.java
 *
 * @author g106108
 * @since 9/10/18
 */
public class Connector {


    final static Logger logger = Logger.getLogger( Connector.class );

    public static void main(String[] args) throws Exception {
        getConnection();
        getAllCustomer();
        sendingEmail();


    }

    /**
     * Generate an email that lists of customer.
     * Update the customer record with a time-stamp showing when the email was sent.
     */

    public static void sendingEmail() throws Exception {


        String smtServer = "exchange.heb.com";
        // email ID of Recipient.
        String emailID = "gebremedhin.mehert@heb.com";
        // Getting system properties
        Properties props = System.getProperties();
        props.put( "mail.smtp.host", smtServer );
        // creating session object to get properties
        Session session = Session.getInstance( props, null );
        // converting List to String
        List<Customer> customersList = getAllCustomer();
        String list = customersList.toString().replace( "[", "" )
                .replace( ",", "" ).replace( "]", "" );
        EmailService.sendEmail( session, emailID, "Email sending Testing ", list );


        System.out.println( "Email send succesfully....." );

    }

    /**
     * Getting a list of customer fro customer table.
     */
    public static List<Customer> getAllCustomer() throws ClassNotFoundException, SQLException {
        List<Customer> customersList = new ArrayList<>();
        try {
            Connection connection = getConnection();

            Statement stm;
            stm = connection.createStatement();
            String updateTime = "UPDATE mydb.customer " +
                    "set update_time = NOW() WHERE id IN (1,2,3,4,5)";
            stm.executeUpdate( updateTime );
            String sql = "Select * From mydb.customer";
            ResultSet result = stm.executeQuery( sql );


            while (result.next()) {
                // ... get column values from this record
                Address address = new Address();
                Customer customer = new Customer( address );
                customer.setId( result.getInt( "id" ) );
                customer.setFirstName( result.getString( "first_name" ) );
                customer.setLastName( result.getString( "last_Name" ) );
                customer.setEmail( result.getString( "email" ) );
                customer.getAdd().setAddress( result.getString( "address" ) );
                customer.getAdd().setCity( result.getString( "city" ) );
                customer.getAdd().setState( result.getString( "state" ) );
                customer.getAdd().setState( result.getString( "state" ) );
                customer.getAdd().setZipCode( result.getInt( "zip_code" ) );
                customersList.add( customer );

            }

            result.close();

        } catch (Exception e) {
            logger.error( "Got an exception! " );
            logger.error( e.getMessage() );
        }
        System.out.println( customersList.toString().replace( "[", "" ).replace( ",", "" ).replace( "]", "" ) );
        return customersList;
    }


    /**
     * creating sql database connection
     */
    public static Connection getConnection() throws Exception {
        try {

            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/customer";
            String userName = "root";
            String password = "me0911304720";
            Class.forName( driver );
            Connection con = DriverManager.getConnection( url, userName, password );
            return con;

        } catch (Exception e) {
            logger.error( e.getMessage() );
        }
        return null;

    }
}