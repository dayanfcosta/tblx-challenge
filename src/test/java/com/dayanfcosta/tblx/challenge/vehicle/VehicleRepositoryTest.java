package com.dayanfcosta.tblx.challenge.vehicle;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

import com.dayanfcosta.tblx.challenge.AbstractRepositoryTest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleRepositoryTest extends AbstractRepositoryTest {

  private static final String OPERATOR_ID = "OPERATOR";

  private VehicleRepository repository;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    repository = new VehicleRepository(getMongoTemplate());
  }

  @Test
  void whenFindAllNotAtStop_returnAllVehiclesFound() {
    final List<Integer> vehicles = repository.findAll(now(), now().plusDays(2), OPERATOR_ID, false);

    assertThat(vehicles).isNotNull();
    assertThat(vehicles).isNotEmpty();
    assertThat(vehicles).hasSize(1);
  }

  @Test
  void whenFindAllAtStop_returnAllVehiclesFound() {
    final List<Integer> vehicles = repository.findAll(now(), now().plusDays(2), OPERATOR_ID, true);

    assertThat(vehicles).isNotNull();
    assertThat(vehicles).isNotEmpty();
    assertThat(vehicles).hasSize(2);
  }
}