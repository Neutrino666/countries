package orq.neutrino.countries.controller;

import java.util.List;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import orq.neutrino.countries.domain.Country;
import orq.neutrino.countries.service.CountryService;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

  private final CountryService countryService;

  @Autowired
  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping
  @Nonnull
  List<Country> getAll() {
    return countryService.all();
  }

  @GetMapping("/{code}")
  @Nonnull
  public Country findByCode(@PathVariable("code") String code) {
    return countryService.findByCode(code);
  }

  @PatchMapping("/{code}")
  public Country editNameByCode(@PathVariable("code") String code, @RequestBody String name) {
    return countryService.editNameByCode(code, name);
  }

  @PostMapping
  @Nonnull
  @ResponseStatus(HttpStatus.CREATED)
  public Country save(@RequestBody Country country) {
    return countryService.save(country);
  }
}
