package orq.neutrino.countries.controller.graphql;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import orq.neutrino.countries.domain.graphql.CountryGql;
import orq.neutrino.countries.service.CountryService;

@Controller
@ParametersAreNonnullByDefault
public class CountryQueryController {

  private final CountryService countryService;

  @Autowired
  public CountryQueryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @QueryMapping
  @Nonnull
  List<CountryGql> countries() {
    return countryService.allGql();
  }

  @QueryMapping
  @Nonnull
  public CountryGql country(@Argument String code) {
    return countryService.findByCodeGql(code);
  }
}
