package orq.neutrino.countries.data;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.springframework.data.jpa.repository.JpaRepository;

@ParametersAreNonnullByDefault
public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {

  @Nonnull
  Optional<CountryEntity> findByCode(String code);
}
