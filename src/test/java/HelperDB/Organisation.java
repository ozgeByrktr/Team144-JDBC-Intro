package HelperDB;

import lombok.Getter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static HelperDB.CommonData.faker;
@Getter
public class Organisation {


     private String organisation_name;
    private String code;
     private String contact_no;
     private String address;
     private String contact_person_name;
     private String contact_person_phone;
     private Timestamp created_at;
     public static List<Organisation> organisations;

    public Organisation(String organisation_name, String code, String contact_no, String address, String contact_person_name, String contact_person_phone, Timestamp created_at) {
        this.organisation_name = organisation_name;
        this.code = code;
        this.contact_no = contact_no;
        this.address = address;
        this.contact_person_name = contact_person_name;
        this.contact_person_phone = contact_person_phone;
        this.created_at = created_at;
    }
    public List<Organisation> getOrganisations() {
        return List.of(
             new Organisation(faker.company().name(),faker.number().digit(), faker.phoneNumber().phoneNumber(), faker.address().streetAddress(),faker.name().fullName(),faker.phoneNumber().phoneNumber(), Timestamp.valueOf(LocalDateTime.now())),
             new Organisation(faker.company().name(),faker.number().digit(), faker.phoneNumber().phoneNumber(), faker.address().streetAddress(),faker.name().fullName(),faker.phoneNumber().phoneNumber(), Timestamp.valueOf(LocalDateTime.now())),
             new Organisation(faker.company().name(),faker.number().digit(), faker.phoneNumber().phoneNumber(), faker.address().streetAddress(),faker.name().fullName(),faker.phoneNumber().phoneNumber(), Timestamp.valueOf(LocalDateTime.now()))


        );
    }

    public static List<Organisation>generateNewOrg(int count){
        List<Organisation> organisations=new ArrayList<>();

        for (int i = 0; i <count ; i++) {
            Organisation org = new Organisation(
                            faker.company().name(),
                            faker.number().digit(),
                            faker.phoneNumber().phoneNumber(),
                            faker.address().streetAddress(),
                            faker.name().fullName(),
                            faker.phoneNumber().phoneNumber(),
                            Timestamp.valueOf(LocalDateTime.now())
                    );
                organisations.add(org);
        }
        return organisations;
        }




    }












