package mentorship.reactivesport.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "name")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias("sport")
public class SportDocument {

    @Id
    BigInteger id;

    String name;
}
