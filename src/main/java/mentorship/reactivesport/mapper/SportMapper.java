package mentorship.reactivesport.mapper;

import mentorship.reactivesport.document.SportDocument;
import mentorship.reactivesport.dto.Datum;
import mentorship.reactivesport.dto.SportDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SportMapper {

    SportDocument toDocument(SportDto sportDto);

    SportDto toDTO(SportDocument sportDocument);


    SportDocument toDocument(Datum data);
}
