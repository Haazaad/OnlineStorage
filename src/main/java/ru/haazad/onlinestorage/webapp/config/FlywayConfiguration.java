package ru.haazad.onlinestorage.webapp.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {
    private DatabaseConfiguration databaseConfiguration;

    @Autowired
    public FlywayConfiguration(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    public void flywayMigrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(databaseConfiguration.getDbUrl(), databaseConfiguration.getDbUser(), databaseConfiguration.getDbPassword())
                .load();
        flyway.migrate();
    }
}
