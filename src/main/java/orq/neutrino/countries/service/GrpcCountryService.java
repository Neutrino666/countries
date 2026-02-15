package orq.neutrino.countries.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import orq.neutrino.countries.AddedCountResponse;
import orq.neutrino.countries.CodeRequest;
import orq.neutrino.countries.CountryRequest;
import orq.neutrino.countries.CountryResponse;
import orq.neutrino.countries.CountryServiceGrpc;
import orq.neutrino.countries.domain.graphql.CountryGql;
import orq.neutrino.countries.domain.graphql.CountryInputGql;

@Service
public class GrpcCountryService extends CountryServiceGrpc.CountryServiceImplBase {

  private final CountryService countryService;

  public GrpcCountryService(CountryService countryService) {
    this.countryService = countryService;
  }

  @Override
  public void listCountries(Empty request, StreamObserver<CountryResponse> responseObserver) {
    countryService.allGql()
        .forEach(country -> setOnNextCountryResponseFromGql(country, responseObserver));
    responseObserver.onCompleted();
  }

  @Override
  public void findByCode(CodeRequest request, StreamObserver<CountryResponse> responseObserver) {
    CountryGql countryGql = countryService.findByCodeGql(request.getCode());
    setOnNextCountryResponseFromGql(countryGql, responseObserver);
    responseObserver.onCompleted();
  }

  @Override
  public void save(CountryRequest request, StreamObserver<CountryResponse> responseObserver) {
    CountryGql countryGql = countryService.saveGql(new CountryInputGql(
        request.getName(),
        request.getCode()
    ));
    setOnNextCountryResponseFromGql(countryGql, responseObserver);
    responseObserver.onCompleted();
  }

  @Override
  public StreamObserver<CountryRequest> addStreamCountries(
      StreamObserver<AddedCountResponse> responseObserver) {
    return new StreamObserver<>() {

      Integer i = 0;

      @Override
      public void onNext(CountryRequest countryRequest) {
        countryService.saveGql(new CountryInputGql(
            countryRequest.getName(),
            countryRequest.getCode()
        ));
        i++;
      }

      @Override
      public void onError(Throwable t) {
        throw new RuntimeException("Не удалось добавить страну ошибка: " + t);
      }

      @Override
      public void onCompleted() {
        AddedCountResponse count = AddedCountResponse.newBuilder()
            .setCount(i).build();
        responseObserver.onNext(count);
        responseObserver.onCompleted();
      }
    };
  }

  private void setOnNextCountryResponseFromGql(
      CountryGql country,
      StreamObserver<CountryResponse> responseObserver) {
    responseObserver.onNext(
        CountryResponse.newBuilder()
            .setId(country.id().toString())
            .setCode(country.code())
            .setName(country.name())
            .build()
    );
  }
}
