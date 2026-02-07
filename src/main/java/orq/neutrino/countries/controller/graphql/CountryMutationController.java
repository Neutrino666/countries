package orq.neutrino.countries.controller.graphql;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import orq.neutrino.countries.domain.graphql.CountryGql;
import orq.neutrino.countries.domain.graphql.CountryInputGql;
import orq.neutrino.countries.service.CountryService;

@Controller
@ParametersAreNonnullByDefault
public class CountryMutationController {

  private final CountryService countryService;

  @Autowired
  public CountryMutationController(CountryService countryService) {
    this.countryService = countryService;
  }

  @MutationMapping
  @Nonnull
  CountryGql addCountry(@Argument CountryInputGql input) {
    return countryService.saveGql(input);
  }
}
