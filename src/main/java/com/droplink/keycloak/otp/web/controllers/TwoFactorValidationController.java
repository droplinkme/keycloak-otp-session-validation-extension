/*
 * Extension for validating OTP during transactions.
 * @author Allan Vieira (allancnfx.vieira@gmail.com)
 * @version 1.0
 */
package com.droplink.keycloak.otp.web.controllers;


import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

import com.droplink.keycloak.otp.services.OtpValidationService;
import com.droplink.keycloak.otp.web.annotations.Authenticated;
import com.droplink.keycloak.otp.web.context.UserContext;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/2fa-validation")
public class TwoFactorValidationController {
  public TwoFactorValidationController() {
  }
  
  public TwoFactorValidationController(KeycloakSession session){
    this.session = session;
    this.service = new OtpValidationService();
  }

  @Context
  private KeycloakSession session;
  private OtpValidationService service;

  @POST
  @Path("/validate")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Authenticated
  public Response validate(TwoFactorValidationRequestBody body)  {
    UserModel user = UserContext.getUser();
    RealmModel realm = this.session.getContext().getRealm();

  boolean valid = this.service.exec(user, realm, session, body.getOtp());
  return Response.ok()
    .type(MediaType.APPLICATION_JSON)
    .entity("{\"valid\": " + valid + "}")
    .status(200)
    .build();
  }
}