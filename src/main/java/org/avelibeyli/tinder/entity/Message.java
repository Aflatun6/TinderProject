package org.avelibeyli.tinder.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Message {
    private final int id;
    private final int who;
    private final int whom;
    private final String content;
    private final Timestamp date;

    public String goodDate(){
        return date.toString();
    }
}
