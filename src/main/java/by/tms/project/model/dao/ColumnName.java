package by.tms.project.model.dao;

import java.rmi.StubNotFoundException;

public class ColumnName {

    public static final String USERS_ID = "users.id";
    public static final String USERS_ROLE = "users.role";
    public static final String USERS_LOGIN = "users.login";
    public static final String USERS_PASSWORD = "users.password";
    public static final String USERS_FIRST_NAME = "users.first_name";
    public static final String USERS_LAST_NAME = "users.last_name";
    public static final String USERS_DATA_BIRTHDAY = "users.data_birthday";
    public static final String USERS_ADDRESS = "users.address";
    public static final String USERS_PHONE_NUMBER = "users.phone_number";
    public static final String USERS_EMAIL = "users.email";

    public static final String PATIENTS_INSURANCE = "patients.insurance";
    public static final String PATIENTS_ACCOUNT = "patients.account";
    public static final String PATIENTS_USERS_ID = "patients.users_id";

    public static final String DOCTORS_CATEGORY = "doctors.category";
    public static final String DOCTORS_EXPERIENCE = "doctors.experience";
    public static final String DOCTORS_USERS_ID = "doctors.users_id";
    public static final String DOCTORS_SPECIALITIES_ID_SPECIALITY = "doctors.speciality_id_speciality";

    public static final String CAPABILITIES_CAPABILITY_ID = "capabilities.capability_id";
    public static final String CAPABILITIES_CAPABILITY_NAME = "capabilities.capability_name";
    public static final String CAPABILITIES_CAPABILITY_COST = "capabilities.capability_cost";
    public static final String CAPABILITIES_PROTOCOLS_PROTOCOL_ID = "capabilities.protocols_protocol_id";
    public static final String CAPABILITIES_SPECIALITIES_ID_SPECIALITY = "capabilities.specialities_id_speciality";

    public static final String SPECIALITIES_SPECIALITY_ID = "specialities.speciality_id";
    public static final String SPECIALITIES_SPECIALTY_NAME = "specialities.speciality_name";

    public static final String PROTOCOLS_PROTOCOL_ID = "protocols.protocol_id";
    public static final String PROTOCOLS_PROTOCOL_DATA = "protocols.protocol_data";
    public static final String PROTOCOLS_PROTOCOL_PAYER = "protocols.protocol_payer";
    public static final String PROTOCOLS_PATIENTS_USERS_ID = "protocols.patients_users_id";
    public static final String PROTOCOLS_DOCTORS_USER_ID = "protocols.doctors_users_id";

    private ColumnName(){

    }

}
