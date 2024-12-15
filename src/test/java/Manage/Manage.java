package Manage;

import lombok.Getter;

@Getter
public class Manage {
    //email_type, smtp_server, smtp_port, smtp_username, smtp_password, ssl_tls, smtp_auth, is_active, created_at
    String US35="Insert Into u201212290_heallifeqa.email_config (email_type, smtp_server, smtp_port, smtp_username, smtp_password, ssl_tls, smtp_auth, is_active, created_at) \n" +
            "Values (?,?,?,?,SHA2(?,256),?,?,?,?)" ;
    String US25="Select gender,email From  u201212290_heallifeqa.patients Where patient_name LIKE '%Jain%';";
    String US36="INSERT INTO u201212290_heallifeqa.organisation \n" +
            "    (organisation_name, code, contact_no, address, contact_person_name, contact_person_phone, created_at)\n" +
            "VALUES (?,?,?,?,?,?,?)";
    String US04="SELECT \n" +
            "    SUM(CASE WHEN TIME(date) < '12:00:00' THEN 1 ELSE 0 END) AS ogleden_once,\n" +
            "    SUM(CASE WHEN TIME(date) > '12:00:00' THEN 1 ELSE 0 END) AS ogleden_sonra\n" +
            "FROM u201212290_heallifeqa.appointment;";
}
