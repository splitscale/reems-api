package com.splitscale.reems.api.controller.tenantinfo;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.splitscale.reems.core.tenantinfo.TenantInfo;
import com.splitscale.reems.core.tenantinfo.TenantInfoRequest;
import com.splitscale.reems.security.wrappers.tenantInfo.create.CreateTenantInfo;
import com.splitscale.reems.security.wrappers.tenantInfo.read.ReadTenantInfo;

@RequestMapping("/api/v1/tenantInfo")
public class TenantInfoController {
  CreateTenantInfo createTenantInfo;
  ReadTenantInfo readTenantInfo;

  @ResponseBody
  @PostMapping
  public ResponseEntity<String> create(@RequestBody TenantInfoRequest request,
      @RequestParam(value = "token") String token, @RequestParam(value = "userId") String userId)
      throws IOException, GeneralSecurityException {

    createTenantInfo.create(request, token, userId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @ResponseBody
  @PostMapping
  public ResponseEntity<List<TenantInfo>> read(@RequestParam(value = "token") String token,
      @RequestParam(value = "userId") String userId)
      throws IOException, GeneralSecurityException {

    List<TenantInfo> tenants = readTenantInfo.getAllTenantInfo(token, userId);

    return new ResponseEntity<>(tenants, HttpStatus.OK);
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
