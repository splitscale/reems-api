package com.splitscale.reems.api.controller.tenantinfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
      @RequestHeader(value = "Authorization") String token) {

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
