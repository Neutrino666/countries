package orq.neutrino.countries.ex;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class CountryNotFoundException extends RuntimeException {

  public CountryNotFoundException(String message) {
    super(message);
  }
}
