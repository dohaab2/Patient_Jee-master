package ma.enset.patient_mvc.security.service;

import lombok.AllArgsConstructor;
import ma.enset.patient_mvc.security.entities.AppRole;
import ma.enset.patient_mvc.security.entities.AppUser;
import ma.enset.patient_mvc.security.repo.AppRoleRepository;
import ma.enset.patient_mvc.security.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    PasswordEncoder passwordEncoder;


    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPasswod) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser!=null) throw new RuntimeException(" this User already exist");
       if(!password.equals(confirmPasswod)) throw new RuntimeException("password not match");
        appUser=AppUser.builder()
               .userId(UUID.randomUUID().toString())
               .username(username)
               .password(passwordEncoder.encode(password))
               .email(email)
               .build();
      AppUser saveAppUser= appUserRepository.save(appUser);
        return saveAppUser;
    }

    @Override
    public AppRole addNewRole(String role) {

        AppRole appRole=appRoleRepository.findById(role).orElse(null);
        if(appRole!=null) throw  new RuntimeException("this role already exist");
        appRole= AppRole.builder()
                .role(role)
                .build();

        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
AppUser appUser=appUserRepository.findByUsername(username);
AppRole appRole=appRoleRepository.findById(role).get();
appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findById(role).get();
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {

        return appUserRepository.findByUsername(username);
    }
}
