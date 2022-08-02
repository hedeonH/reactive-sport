package mentorship.reactivesport.mapper;

import mentorship.reactivesport.document.SportDocument;
import mentorship.reactivesport.dto.SportAWS;
import mentorship.reactivesport.dto.SportDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SportMapper {

    SportDto toDTO(SportDocument sportDocument);

    @Mapping(source = "attributes.name", target = "name")
    SportDocument toDocument(SportAWS data);
}
