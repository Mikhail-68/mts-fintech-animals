package ru.mts.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "animal")
public class AnimalsProperties {
    private List<String> names;

    public List<String> getNames() {
        return new ArrayList<>(names);
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
