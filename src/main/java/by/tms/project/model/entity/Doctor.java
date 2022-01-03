package by.tms.project.model.entity;

import java.time.LocalDate;

public class Doctor extends User  {
    private String category;
    private String experience;
    private int speciality;

    public Doctor() {
    }


    public Doctor(long id, Role role, String login, String password, String firstName, String lastName, LocalDate dataBirthday,
                  String address, String phoneNumber, String email, String category, String experience, int speciality) {
        super(id, role, login, password, firstName, lastName, dataBirthday, address, phoneNumber, email);
        this.category = category;
        this.experience = experience;
        this.speciality = speciality;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getSpeciality() {
        return speciality;
    }

    public void setSpeciality(int speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Doctor doctor = (Doctor) o;

        if (speciality != doctor.speciality) return false;
        if (!category.equals(doctor.category)) return false;
        return experience.equals(doctor.experience);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + experience.hashCode();
        result = 31 * result + speciality;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Doctor{");
        sb.append("category='").append(category).append('\'');
        sb.append(", experience='").append(experience).append('\'');
        sb.append(", speciality=").append(speciality);
        sb.append('}');
        return sb.toString();
    }

    public static class DoctorBuilder {
        private Doctor doctor = new Doctor();

        public DoctorBuilder() {
        }

        public DoctorBuilder setId(long id) {
            doctor.setId(id);
            return this;
        }

        public DoctorBuilder setRole(Role role) {
            doctor.setRole(role);
            return this;
        }

        public DoctorBuilder setLogin(String login) {
            doctor.setLogin(login);
            return this;
        }

        public DoctorBuilder setPassword(String password) {
            doctor.setPassword(password);
            return this;
        }

        public DoctorBuilder setFirstName(String firstName) {
            doctor.setFirstName(firstName);
            return this;
        }

        public DoctorBuilder setLastName(String lastName) {
            doctor.setLastName(lastName);
            return this;
        }

        public DoctorBuilder setDataBirthday(LocalDate dataBirthday) {
            doctor.setDataBirthday(dataBirthday);
            return this;
        }

        public DoctorBuilder setAddress(String address) {
            doctor.setAddress(address);
            return this;
        }

        public DoctorBuilder setPhoneNumber(String phoneNumber) {
            doctor.setPhoneNumber(phoneNumber);
            return this;
        }

        public DoctorBuilder setEmail(String email) {
            doctor.setEmail(email);
            return this;
        }

        public DoctorBuilder setCategory(String category) {
            doctor.setCategory(category);
            return this;
        }

        public DoctorBuilder setExperience(String experience) {
            doctor.setExperience(experience);
            return this;
        }

        public DoctorBuilder setSpeciality(int speciality) {
            doctor.setSpeciality(speciality);
            return this;
        }

        public Doctor buildDoctor() {
            return doctor;
        }
    }
}