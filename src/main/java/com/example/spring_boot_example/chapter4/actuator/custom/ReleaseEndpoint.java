package com.example.spring_boot_example.chapter4.actuator.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@Endpoint(id = "releaseNotes")
public class ReleaseEndpoint {

    private final Collection<ReleaseNote> releaseNotes;

    @Autowired
    public ReleaseEndpoint(Collection<ReleaseNote> releaseNotes) {
        this.releaseNotes = releaseNotes;
    }

    @ReadOperation
    public Iterable<ReleaseNote> releaseNotes(){
        return releaseNotes;
    }

    @ReadOperation
    public Object selectCourse(@Selector String version){
        Optional<ReleaseNote> releaseNoteOptional = releaseNotes.stream()
                .filter(releaseNote -> version.equals(releaseNote.getVersion()))
                .findFirst();

        if(releaseNoteOptional.isPresent()) return releaseNoteOptional.get();
        return String.format("No such release version exists : %s", version);
    }

//    @WriteOperation
    @DeleteOperation
    public void removeReleaseVersion(@Selector String version){
        Optional<ReleaseNote> releaseNoteOptional = releaseNotes.stream()
                .filter(releaseNote -> version.equals(releaseNote.getVersion()))
                .findFirst();

        releaseNoteOptional.ifPresent(releaseNotes::remove);
    }
}
