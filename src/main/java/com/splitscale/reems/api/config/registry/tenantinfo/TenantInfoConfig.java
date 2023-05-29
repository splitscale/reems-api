package com.splitscale.reems.api.config.registry.tenantinfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.reems.ShieldImpl;
import com.splitscale.reems.core.repositories.TenantRepository;
import com.splitscale.reems.core.tenantinfo.create.CreateTenantInfoInteractor;
import com.splitscale.reems.driver.DatabaseDriver;
import com.splitscale.reems.repositories.TenantRepositoryInteractor;
import com.splitscale.reems.security.services.SecurityService;
import com.splitscale.reems.security.wrappers.tenantInfo.create.CreateTenantInfo;

@Configuration
public class TenantInfoConfig {
  @Bean
  public CreateTenantInfo getCreateTenantInfo(SecurityService securityService, CreateTenantInfoInteractor interactor) {
    return new CreateTenantInfo(securityService, interactor);
  }

  @Bean
  public SecurityService getSecurityService() {
    return new ShieldImpl("http://localhost:8080");
  }

  @Bean
  public CreateTenantInfoInteractor getCreateTenantInfoInteractor(TenantRepository repository) {
    return new CreateTenantInfoInteractor(repository);
  }

  @Bean
  public TenantRepository getTenantRepository(DatabaseDriver db) {
    return new TenantRepositoryInteractor(db);
  }

  @Bean
  public DatabaseDriver getDatabaseDriver() throws IOException {
    return new DatabaseDriver(Path.of("src", "main", "resources", "db.properties").toString());
  }
}