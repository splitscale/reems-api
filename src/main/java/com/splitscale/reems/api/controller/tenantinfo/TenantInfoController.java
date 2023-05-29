package com.splitscale.reems.api.controller.tenantinfo;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.splitscale.reems.tenantinfo.TenantInfoRequest;
import com.splitscale.reems.wrappers.tenantInfo.create.CreateTenantInfo;

@RequestMapping("/api/v1/tenantInfo")
public class TenantInfoController {
  CreateTenantInfo createTenantInfo;

  @ResponseBody
  @PostMapping
  public ResponseEntity<String> create(@RequestBody TenantInfoRequest request,
      @RequestHeader(value = "Authorization") String token) throws IOException, GeneralSecurityException {

    createTenantInfo.create(request);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Exception handlers
  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleIOException(IOException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(GeneralSecurityException.class)
  public ResponseEntity<String> handleGeneralSecurityException(GeneralSecurityException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
  }
}
