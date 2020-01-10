package com.FirstSpingApp.demo.config;

import com.FirstSpingApp.demo.domain.User;
import com.FirstSpingApp.demo.resources.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MySimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private ObjectMapper objectMapper =
      new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    response.setStatus(HttpStatus.OK.value());
    User user = (User) authentication.getPrincipal();
    UserDto userDTO = new UserDto(user);
    response.getWriter().write(objectMapper.writeValueAsString(userDTO));
    clearAuthenticationAttributes(request);
  }
}
