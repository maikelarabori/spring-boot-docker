package org.example;

import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableAsync;

import static org.owasp.security.logging.util.SecurityUtil.*;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.boot.SpringApplication.run;

@EnableAsync
@SpringBootApplication
@PropertySources({
    @PropertySource("classpath:application.properties")
})
public class Application {

  private static final Logger log = getLogger(Application.class.getName());

  public static void main(final String[] args) {
    initialisationLogs(args);
    run(Application.class, args);
  }

  private static void initialisationLogs(final String[] args) {
    log.info("Printing initial application args");
    logCommandLineArguments(args);

    log.info("Printing initial shell environment variables.");
    logShellEnvironmentVariables();

    log.info("Printing initial Java system properties.");
    logJavaSystemProperties();
  }

  @Bean
  public ApplicationListener<ContextRefreshedEvent> startupLoggingListener() {
    return event -> log.info("Application is open for e-business!");
  }
}
