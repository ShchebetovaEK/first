package by.tms.project.entity.user;

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
}
