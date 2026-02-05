package orq.neutrino.countries.ex;

public class InvalidCountryCodeException extends RuntimeException {

  public InvalidCountryCodeException(String message) {
    super(message);
  }
}
