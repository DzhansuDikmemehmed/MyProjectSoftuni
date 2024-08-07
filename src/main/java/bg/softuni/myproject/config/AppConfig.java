package bg.softuni.myproject.config;

import bg.softuni.myproject.repo.UserRepository;
import bg.softuni.myproject.repo.UserRolesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }


    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource,
                                                       UserRolesRepository userRoleRepository,
                                                       ResourceLoader resourceLoader){
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);


        if (userRoleRepository.count()==0){
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

            populator.addScript(resourceLoader.getResource("classpath:data.sql"));
            initializer.setDatabasePopulator(populator);

        }
        return initializer;
    }
}
