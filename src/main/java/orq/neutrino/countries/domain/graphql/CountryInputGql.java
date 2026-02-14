package orq.neutrino.countries.domain.graphql;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public record CountryInputGql(String name,
                              String code) {

}
