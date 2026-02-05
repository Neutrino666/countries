package orq.neutrino.countries.service;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import orq.neutrino.countries.domain.CountryJson;

@ParametersAreNonnullByDefault
public interface CountryService {

  @Nonnull
  List<CountryJson> all();

  @Nonnull
  CountryJson findByCode(String code);

  @Nonnull
  CountryJson save(CountryJson country);

  @Nonnull
  CountryJson editNameByCode(String code, String name);
}
