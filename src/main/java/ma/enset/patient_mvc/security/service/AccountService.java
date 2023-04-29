package ma.enset.patient_mvc.security.service;

import ma.enset.patient_mvc.security.entities.AppRole;
import ma.enset.patient_mvc.security.entities.AppUser;

public interface AccountService  {
    AppUser addNewUser(String username,String password,String email,String confirmPasswod);
    AppRole addNewRole(String role);
    void  addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String role);
    AppUser loadUserByUsername(String username);
}
