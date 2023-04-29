package ma.enset.patient_mvc.security.service;

import ma.enset.patient_mvc.security.entities.AppRole;
import ma.enset.patient_mvc.security.entities.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailServiceImp implements UserDetailsService {
private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser= accountService.loadUserByUsername(username);
        if(appUser==null) throw new UsernameNotFoundException(String.format("user %s not found",username));
      String[] roles=appUser.getRoles().stream().map(u->u.getRole()).toArray(String[]::new);
        UserDetails userDetails= User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(roles)
                .build();
        return userDetails;
    }
}
