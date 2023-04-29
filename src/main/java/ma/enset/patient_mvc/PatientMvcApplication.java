package ma.enset.patient_mvc;

import ma.enset.patient_mvc.entites.Patient;
import ma.enset.patient_mvc.repositories.PatientRepository;
import ma.enset.patient_mvc.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {



    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }
   // @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository)
    {
        return  args -> {
            patientRepository.save(
                    new Patient(null,"hassan",new Date(),false,129)
            );
            patientRepository.save(
                    new Patient(null,"Mohamed",new Date(),false,312)
            );
            patientRepository.save(
                    new Patient(null,"Yasmine",new Date(),false,657)
            );
            patientRepository.save(
                    new Patient(null,"ali",new Date(),false,132)
            );

            patientRepository.findAll().forEach(p->{System.out.println(p.getNom());});
        };
    }
   // @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager)
    {
        PasswordEncoder passwordEncoder=passwordEncoder();
        return args -> {
            UserDetails u1=jdbcUserDetailsManager.loadUserByUsername("user21");
            if (u1==null){
                        jdbcUserDetailsManager.createUser(
                     User.withUsername("user21").password(passwordEncoder().encode("1234")).roles("USER").build()
                        );}
            UserDetails u2=jdbcUserDetailsManager.loadUserByUsername("user31");
            if (u2==null){
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user31").password(passwordEncoder().encode("1234")).roles("USER").build()
            );}
            UserDetails u3=jdbcUserDetailsManager.loadUserByUsername("admin2");
            if (u3==null){
            jdbcUserDetailsManager.createUser(
                    User.withUsername("admin2").password(passwordEncoder().encode("1234")).roles("ADMIN","USER").build()
            );}

        };
    }
    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService)
    {
        return args ->{
    accountService.addNewRole("USER");
    accountService.addNewRole("ADMIN");
    accountService.addNewUser("user1","1234","user1@gmail.com","1234");
    accountService.addNewUser("admin","1234","admin@gmail.com","1234");
    accountService.addRoleToUser("user1","USER");
    accountService.addRoleToUser("admin","ADMIN");
    accountService.addRoleToUser("admin","USER");
        };
    }
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }
}
