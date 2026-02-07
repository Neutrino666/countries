package orq.neutrino.countries.domain.graphql;

import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import orq.neutrino.countries.data.CountryEntity;

@ParametersAreNonnullByDefault
public record CountryGql(UUID id,
                         String name,
                         String code) {

  public static CountryGql fromEntity(CountryEntity cj) {
    return new CountryGql(
        cj.getId(),
        cj.getName(),
        cj.getCode()
    );
  }
}
