package orq.neutrino.countries.domain;

import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import orq.neutrino.countries.data.CountryEntity;

@ParametersAreNonnullByDefault
public record CountryJson(UUID id,
                          String name,
                          String code) {

  public static CountryJson fromEntity(CountryEntity entity) {
    return new CountryJson(
        entity.getId(),
        entity.getName(),
        entity.getCode()
    );
  }
}
