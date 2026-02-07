package orq.neutrino.countries.domain;

import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import orq.neutrino.countries.data.CountryEntity;

@ParametersAreNonnullByDefault
public record Country(UUID id,
                      String name,
                      String code) {

  public static Country fromEntity(CountryEntity entity) {
    return new Country(
        entity.getId(),
        entity.getName(),
        entity.getCode()
    );
  }
}
