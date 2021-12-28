package by.tms.project.model.entity;

import java.time.LocalDate;

public class Patient  extends User {
 private boolean insurance;
 private long account;

    public Patient() {
    }

    public Patient(long id, Role role, String login, String password, String firstName, String lastName, LocalDate dataBirthday,
                   String address, String phoneNumber, String email, boolean insurance, long account) {
        super(id, role, login, password, firstName, lastName, dataBirthday, address, phoneNumber, email);
        this.insurance = insurance;
        this.account = account;
    }

    public Patient clone(Patient temp) {
        return new Patient.PatientBuilder()
                .setId(temp.getId())
                .setRole(temp.getRole())
                .setLogin(temp.getLogin())
                .setPassword(temp.getPassword())
                .setFirstName(temp.getFirstName())
                .setLastName(temp.getLastName())
                .setDataBirthday(temp.getDataBirthday())
                .setAddress(temp.getAddress())
                .setPhoneNumber(temp.getPhoneNumber())
                .setEmail(temp.getEmail())
                .setInsurance(temp.isInsurance())
                .setAccount(temp.getAccount())
                .createPatient();
    }


    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Patient patient = (Patient) o;

        if (insurance != patient.insurance) return false;
        return account == patient.account;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (insurance ? 1 : 0);
        result = 31 * result + (int) (account ^ (account >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Patient{");
        sb.append("insurance=").append(insurance);
        sb.append(", account=").append(account);
        sb.append('}');
        return sb.toString();
    }


    public static class PatientBuilder {
        private long id;
        private Role role;
        private String login;
        private String password;
        private String firstName;
        private String lastName;
        private LocalDate dataBirthday;
        private String address;
        private String phoneNumber;
        private String email;
        private boolean insurance;
        private long account;


        public PatientBuilder() {

        }

        public Patient.PatientBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public Patient.PatientBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Patient.PatientBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Patient.PatientBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Patient.PatientBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Patient.PatientBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Patient.PatientBuilder setDataBirthday(LocalDate dataBirthday) {
            this.dataBirthday = dataBirthday;
            return this;
        }

        public Patient.PatientBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Patient.PatientBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Patient.PatientBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Patient.PatientBuilder setInsurance(Boolean insurance) {
            this.insurance = insurance;
            return this;
        }

        public Patient.PatientBuilder setAccount(Long account) {
            this.account =account;
            return this;
        }

        public Patient createPatient() {
            return new Patient(id, role, login, password, firstName, lastName,
                    dataBirthday, address, phoneNumber, email,insurance,account);
        }
    }
}
