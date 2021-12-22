package by.tms.project.entity;

import java.time.LocalDate;

public class Doctor extends User {
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
        return "Doctor{" +
                "category='" + category + '\'' +
                ", experience='" + experience + '\'' +
                ", speciality=" + speciality +
                '}';
    }
}
