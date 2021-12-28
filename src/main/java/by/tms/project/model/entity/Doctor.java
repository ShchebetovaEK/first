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


    public Doctor clone(Doctor temp) {
        return new Doctor.DoctorBuilder()
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
                .setCategory(temp.getCategory())
                .setExperience(temp.getExperience())
                .setSpeciality(temp.getSpeciality())
                .createDoctor();
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
        private String category;
        private String experience;
        private int speciality;


        public DoctorBuilder() {

        }

        public Doctor.DoctorBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public Doctor.DoctorBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Doctor.DoctorBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Doctor.DoctorBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Doctor.DoctorBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Doctor.DoctorBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        Doctor.DoctorBuilder setDataBirthday(LocalDate dataBirthday) {
            this.dataBirthday = dataBirthday;
            return this;
        }

        public Doctor.DoctorBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Doctor.DoctorBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Doctor.DoctorBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Doctor.DoctorBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Doctor.DoctorBuilder setExperience(String experience) {
            this.experience = experience;
            return this;
        }

        public Doctor.DoctorBuilder setSpeciality(int speciality) {
            this.speciality = speciality;
            return this;
        }

        public Doctor createDoctor() {
            return new Doctor(id, role, login, password, firstName, lastName,
                    dataBirthday, address, phoneNumber, email, category, experience, speciality);
        }
    }

}
