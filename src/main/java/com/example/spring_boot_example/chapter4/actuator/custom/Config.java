package com.example.spring_boot_example.chapter4.actuator.custom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Configuration
public class Config {

    @Bean(name = "releaseNotes")
    public Collection<ReleaseNote> loadReleaseNotes() {
        var releaseNote1 = ReleaseNote.builder()
                .version("v1.2.1")
                .releaseDate(LocalDate.of(2021, 12, 30))
                .commitTag("a7d2ea3")
                .bugFixes(Set.of(
                        getReleaseItem("SBIP-123", "The name of the matching-strategy property is incorrect in the action message of the failure analysis for a PatternParseException #28839"),
                        getReleaseItem("SBIP-124", "ErrorPageSecurityFilter prevents deployment to a Servlet 3.1 compatible container #28790")))
                .build();

        var releaseNote2 = ReleaseNote.builder()
                .version("v1.2.0")
                .releaseDate(LocalDate.of(2021, 11, 20))
                .commitTag("44047f3")
                .newReleases(Set.of(getReleaseItem("SBIP-125", "Support both kebab-case and camelCase as Spring init CLI Options #28138")))
                .bugFixes(Set.of(getReleaseItem("SBIP-126", "Profiles added using @ActiveProfiles have different precedence #28724")))
                .build();

        return new LinkedHashSet<>(Set.of(releaseNote1, releaseNote2));
    }

    private ReleaseItem getReleaseItem(String itemId, String itemDescription) {
        return ReleaseItem
                .builder()
                .itemId(itemId)
                .itemDescription(itemDescription)
                .build();
    }
}
