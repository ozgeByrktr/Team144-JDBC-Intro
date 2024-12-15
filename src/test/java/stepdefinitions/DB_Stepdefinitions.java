package stepdefinitions;

import HelperDB.CommonData;
import HelperDB.Organisation;
import Manage.Manage;
import io.cucumber.java.en.Given;

import java.sql.SQLException;
import java.util.HashMap;

import static HelperDB.CommonData.ogleden_once;
import static HelperDB.CommonData.ogleden_sonra;
import static HelperDB.JDBC_Structure_Methods.*;
import static HelperDB.Organisation.generateNewOrg;
import static HelperDB.Organisation.organisations;
import static org.junit.Assert.*;

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
    @Given("Add {int} data to organisations table at the same time")
    public void add_data_to_organisations_table_at_the_same_time(int count) throws SQLException {
      query=getUS36();
      preparedStatement=getPraperedStatement(query);
        organisations=generateNewOrg(count);
        // (organisation_name, code, contact_no, address, contact_person_name, contact_person_phone, created_at)
        int flag=0;
        for(Organisation org:organisations){
            preparedStatement.setString(1,organisations.get(flag).getOrganisation_name());
            preparedStatement.setString(2,organisations.get(flag).getCode());
            preparedStatement.setString(3,organisations.get(flag).getContact_no());                preparedStatement.setString(1,organisations.get(flag).getOrganisation_name());                preparedStatement.setString(1,organisations.get(flag).getOrganisation_name());
            preparedStatement.setString(4,organisations.get(flag).getAddress());
            preparedStatement.setString(5,organisations.get(flag).getContact_person_name());
            preparedStatement.setString(6,organisations.get(flag).getContact_person_phone());
            preparedStatement.setTimestamp(7,organisations.get(flag).getCreated_at());
            preparedStatement.addBatch();
            flag++;
            if(flag ==organisations.size()){
               CommonData.result = preparedStatement.executeBatch();
            }
    }
        }
    @Given("{int} Enter the data in bulk. Check that it is added to the table.")
    public void enter_the_data_in_bulk_check_that_it_is_added_to_the_table(int row) {
        System.err.println("******"+CommonData.result.length);
        assertEquals(row,CommonData.result.length);

    }
    @Given("prepare query List appointment table")
    public void prepare_query_list_appointment_table() throws SQLException {
      query=getUS04();
      resultSet=getStatement().executeQuery(query);
    }
    @Given("Verify the list results {int} {int} obtained")
    public void verify_the_list_results_obtained(Integer ogledenOnce, Integer ogledenSonra) throws SQLException {

       while (resultSet.next()){
           ogleden_once=resultSet.getInt("ogleden_once");
           CommonData.ogleden_sonra= resultSet.getInt("ogleden_sonra");

       }
        System.err.println(ogleden_once);
        System.err.println(ogleden_sonra);
        assertEquals(ogledenOnce,ogleden_once);
        assertTrue(ogleden_once<ogleden_sonra);   /** bu test failed */

    }






}
