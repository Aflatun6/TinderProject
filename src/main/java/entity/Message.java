package entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Message {
    private final int who;
    private final int whom;
    private final String content;
    private final Timestamp date;
}
