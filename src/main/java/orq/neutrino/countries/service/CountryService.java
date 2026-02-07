package orq.neutrino.countries.service;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import orq.neutrino.countries.domain.Country;
import orq.neutrino.countries.domain.graphql.CountryGql;
import orq.neutrino.countries.domain.graphql.CountryInputGql;

@ParametersAreNonnullByDefault
public interface CountryService {

  @Nonnull
  List<Country> all();

  @Nonnull
  Slice<CountryGql> allGql(Pageable pageable);

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
