package ru.mts.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

//@ConfigurationProperties(prefix = "application")
@Component
public class AnimalsProperties {
    @Value("${name}")
    public List<String> names;
}
