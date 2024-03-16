package ru.mts;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.mts.properties.AnimalsProperties;

@SpringBootConfiguration
@EnableConfigurationProperties(AnimalsProperties.class)
@EnableScheduling
@ComponentScan
public class Starter {
}
