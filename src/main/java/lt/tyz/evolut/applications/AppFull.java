package lt.tyz.evolut.applications;

import lt.tyz.evolut.repositories.utils.CreateTablesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
@ComponentScan(value = "lt.tyz.evolut")
public class AppFull implements CommandLineRunner {

    @Autowired
    private CreateTablesUtils createTablesUtils;

    @Autowired
    private ConsoleInputManager inputManager;


    public static void main(String[] args) {
        SpringApplication.run(AppFull.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException, SQLException, IOException {
        createTablesUtils.createTables();
        inputManager.flow();
        System.out.println("THE END.");
    }
}
