package mentorship.reactivesport.document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "name")
@Getter
@Setter
@Builder
public class SportDocument {

    @Id
    Integer id;

    String name;
}
