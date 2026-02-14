package orq.neutrino.countries.domain.graphql;

import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import orq.neutrino.countries.data.CountryEntity;

@ParametersAreNonnullByDefault
public record CountryGql(UUID id,
                         String name,
                         String code) {

  public static CountryGql fromEntity(CountryEntity ce) {
    return new CountryGql(
        ce.getId(),
        ce.getName(),
        ce.getCode()
    );
  }
}
