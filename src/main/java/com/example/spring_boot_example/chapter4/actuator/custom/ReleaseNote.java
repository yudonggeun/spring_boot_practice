package com.example.spring_boot_example.chapter4.actuator.custom;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Builder
@Getter
public class ReleaseNote {
    private String version;
    private LocalDate releaseDate;
    private String commitTag;
    private Set<ReleaseItem> newReleases;
    private Set<ReleaseItem> bugFixes;

    @Override
    public int hashCode() {
        return Objects.hash(version);
    }
}