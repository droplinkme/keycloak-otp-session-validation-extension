/*
 * Extension for validating OTP during transactions.
 * @author Allan Vieira (allancnfx.vieira@gmail.com)
 * @version 1.0
 */
package com.droplink.keycloak.otp.services;

import org.keycloak.models.KeycloakSession;
import org.keycloak.models.OTPPolicy;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.OTPCredentialModel;
import org.keycloak.models.utils.CredentialValidation;


public class OtpValidationService {

  private final OTPSecretRetrieverService otpSecretRetrieverService;

  public OtpValidationService() {
     this.otpSecretRetrieverService= new OTPSecretRetrieverService();
  }
  public boolean exec(UserModel user, RealmModel realm, KeycloakSession session, String otp) {
    String secret = otpSecretRetrieverService.exec(user, realm, session);
    OTPPolicy policy = realm.getOTPPolicy();
    OTPCredentialModel credentialModel = OTPCredentialModel.createFromPolicy(realm, secret, "DEFAULT");
    return CredentialValidation.validOTP(otp, credentialModel, policy.getLookAheadWindow());
  }
}
