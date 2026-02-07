package orq.neutrino.countries.service;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import orq.neutrino.countries.domain.Country;
import orq.neutrino.countries.domain.graphql.CountryGql;
import orq.neutrino.countries.domain.graphql.CountryInputGql;

@ParametersAreNonnullByDefault
public interface CountryService {

  @Nonnull
  List<Country> all();

  @Nonnull
  List<CountryGql> allGql();

  @Nonnull
  Country findByCode(String code);

  @Nonnull
  CountryGql findByCodeGql(String code);

  @Nonnull
  Country save(Country country);

  @Nonnull
  CountryGql saveGql(CountryInputGql input);

  @Nonnull
  Country editNameByCode(String code, String name);
}
