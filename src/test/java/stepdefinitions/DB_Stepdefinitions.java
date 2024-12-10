package stepdefinitions;

import HelperDB.CommonData;
import Manage.Manage;
import io.cucumber.java.en.Given;

import java.sql.SQLException;
import java.util.HashMap;

import static HelperDB.JDBC_Structure_Methods.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DB_Stepdefinitions extends Manage {
    CommonData data=new CommonData();

    @Given("Database connection established")
    public void database_connection_established() {
        createConnection();
    }
    @Given("Database closed")
    public void database_closed() {
        closeConnection();
    }

    @Given("I insert the new data to the email_config table")
    public void i_insert_the_new_data_to_the_email_config_table() throws SQLException {
    query=getUS35();
    preparedStatement=getPraperedStatement(query);
        //email_type, smtp_server, smtp_port, smtp_username, smtp_password, ssl_tls, smtp_auth, is_active, created_at
    preparedStatement.setString(1,data.getEmail_type());
    preparedStatement.setString(2,data.getStmp_server());
    preparedStatement.setString(3,data.getSmtp_port());
    preparedStatement.setString(4,data.getSmtp_username());
    preparedStatement.setString(5,data.getSmtp_password());
    preparedStatement.setString(6,data.getSsl_tls());
    preparedStatement.setString(7,data.getSmtp_auth());
    preparedStatement.setString(8,data.getIs_active());
    preparedStatement.setTimestamp(9,data.getCreated_at());

    }
    @Given("Verify that {int} added to the table")
    public void verify_that_added_to_the_table(int row) {
    int rowCount=0;
        try {
            rowCount= preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertEquals(row,rowCount);

    }
                        /**   US25      */

    @Given("Query prepared into the patients table")
    public void query_prepared_into_the_patients_table() throws SQLException {
       query=getUS25();
       resultSet=getStatement().executeQuery(query);
    }
    @Given("Verify result is returned")
    public void verify_result_is_returned() throws SQLException {
       data.patientsEmailGender =new HashMap<>();
       while (resultSet.next()) {
           data.patientsEmailGender.put(resultSet.getString("gender"),resultSet.getString("email"));

       }
        if(!data.patientsEmailGender.isEmpty()){
            for (int i = 0; i <data.patientsEmailGender.size() ; i++) {
                // buraya expected Liste karşılaşması yazılır.
                System.err.println( data.patientsEmailGender.get(i));
            }
        }else{
            assertFalse("Resultset is EMPTY", resultSet.next());
        }
    }

}
