package mentorship.reactivesport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SportAWS implements Serializable {

    private int id;
    private String type;
    private Attributes attributes;
}