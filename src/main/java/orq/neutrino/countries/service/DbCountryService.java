package orq.neutrino.countries.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import orq.neutrino.countries.data.CountryEntity;
import orq.neutrino.countries.data.CountryRepository;
import orq.neutrino.countries.domain.Country;
import orq.neutrino.countries.domain.graphql.CountryGql;
import orq.neutrino.countries.domain.graphql.CountryInputGql;
import orq.neutrino.countries.ex.CountryNotFoundException;
import orq.neutrino.countries.ex.InvalidCountryCodeException;

@Component
@ParametersAreNonnullByDefault
public class DbCountryService implements CountryService {

  private final CountryRepository countryRepository;

  @Autowired
  public DbCountryService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @Override
  @Nonnull
  public List<Country> all() {
    return countryRepository
        .findAll()
        .stream()
        .map(Country::fromEntity)
        .toList();
  }

  @Nonnull
  @Override
  public Page<CountryGql> allGql(Pageable pageable) {
    return countryRepository
        .findAll(pageable)
        .map(CountryGql::fromEntity);
  }

  @Override
  @Nonnull
  public Country findByCode(String code) {
    return Country.fromEntity(
        countryRepository.findByCode(code)
            .orElseThrow(
                () -> new CountryNotFoundException("Не удалось найти страну по коду: " + code))
    );
  }

  @Override
  @Nonnull
  public CountryGql findByCodeGql(String code) {
    Country country = findByCode(code);
    return new CountryGql(
        country.id(),
        country.name(),
        country.code()
    );
  }

  @Nonnull
  @Override
  public Country save(Country country) {
    checkCode(country.code());
    return Country.fromEntity(countryRepository.save(CountryEntity.fromJson(country)));
  }

  @Override
  public CountryGql saveGql(CountryInputGql input) {
    checkCode(input.code());
    CountryEntity ce = new CountryEntity();
    ce.setCode(input.code());
    ce.setName(input.name());
    return CountryGql.fromEntity(countryRepository.save(ce));
  }

  @Nonnull
  public Country editNameByCode(String code, String name) {
    CountryEntity ce = CountryEntity.fromJson(findByCode(code));
    ce.setName(name);
    return Country.fromEntity(countryRepository.save(ce));
  }

  private void checkCode(String code) {
    if (countryRepository.findByCode(code).isPresent()) {
      throw new InvalidCountryCodeException("Код страны должен быть уникальным");
    }
    Pattern pattern = Pattern.compile("^[A-Z]{2,3}$");
    Matcher matcher = pattern.matcher(code);
    if (!matcher.find()) {
      String msg = "Ожидается от 2 до 3 символов верхнего регистра латиницы";
      throw new InvalidCountryCodeException(msg);
    }
  }
}
