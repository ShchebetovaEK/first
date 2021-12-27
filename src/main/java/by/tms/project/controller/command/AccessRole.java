package by.tms.project.controller.command;

public enum AccessRole {
    ADMIN("admin"),
    PATIENT("patient"),
    DOCTOR("doctor");

    private final String role;

    AccessRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

    public static AccessRole getRoleType(String role){
        for (AccessRole accessRole:AccessRole.values()){
            if(accessRole.getRole().equals(role)){
                return accessRole;
            }
        }
        return PATIENT;
    }
}
