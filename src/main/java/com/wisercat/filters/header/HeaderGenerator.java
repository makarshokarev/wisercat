package com.wisercat.filters.header;

import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class HeaderGenerator {

  public HttpHeaders getHeadersForSuccessGetMethod() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Type", "application/json");
    return httpHeaders;
  }

  public HttpHeaders getHeadersForError() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Type", "application/problem+json");
    return httpHeaders;
  }

  public HttpHeaders getHeadersForSuccessPostMethod(HttpServletRequest request) {
    HttpHeaders httpHeaders = new HttpHeaders();
    try {
      httpHeaders.setLocation(new URI(request.getRequestURI()));
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    httpHeaders.add("Content-Type", "application/json");
    return httpHeaders;
  }
}
