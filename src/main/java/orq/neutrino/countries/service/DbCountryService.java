package orq.neutrino.countries.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import orq.neutrino.countries.data.CountryEntity;
import orq.neutrino.countries.data.CountryRepository;
import orq.neutrino.countries.domain.CountryJson;
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
  public List<CountryJson> all() {
    return countryRepository
        .findAll()
        .stream()
        .map(CountryJson::fromEntity)
        .toList();
  }

  @Override
  @Nonnull
  public CountryJson findByCode(String code) {
    return CountryJson.fromEntity(
        countryRepository.findByCode(code)
            .orElseThrow(
                () -> new CountryNotFoundException("Не удалось найти страну по коду: " + code))
    );
  }

  @Nonnull
  @Override
  public CountryJson save(CountryJson country) {
    if (countryRepository.findByCode(country.code()).isPresent()) {
      throw new InvalidCountryCodeException("Код страны должен быть уникальным");
    }
    Pattern pattern = Pattern.compile("^[A-Z]{2,3}$");
    Matcher matcher = pattern.matcher(country.code());
    if (!matcher.find()) {
      String msg = "Ожидается от 2 до 3 символов верхнего регистра латиницы";
      throw new InvalidCountryCodeException(msg);
    }
    return CountryJson.fromEntity(countryRepository.save(CountryEntity.fronJson(country)));
  }

  @Nonnull
  public CountryJson editNameByCode(String code, String name) {
    CountryEntity ce = CountryEntity.fronJson(findByCode(code));
    ce.setName(name);
    return CountryJson.fromEntity(countryRepository.save(ce));
  }
}
