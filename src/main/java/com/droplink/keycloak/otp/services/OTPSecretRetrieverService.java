/*
 * Extension for validating OTP during transactions.
 * @author Allan Vieira (allancnfx.vieira@gmail.com)
 * @version 1.0
 */
package com.droplink.keycloak.otp.services;

import org.keycloak.credential.CredentialProvider;
import org.keycloak.credential.OTPCredentialProvider;
import org.keycloak.credential.OTPCredentialProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.OTPCredentialModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.BadRequestException;

public class OTPSecretRetrieverService {
  public String exec(UserModel user, RealmModel realm, KeycloakSession session) {
      
        OTPCredentialProvider otpCredentialProvider = (OTPCredentialProvider) session.getProvider(CredentialProvider.class,
        OTPCredentialProviderFactory.PROVIDER_ID);

        OTPCredentialModel otpCredential = otpCredentialProvider.getDefaultCredential(session, realm, user);
       
        if (otpCredential == null) {
          throw new IllegalStateException("OTP credential not found.");
        }
        
        String secretData = otpCredential.getSecretData();

        if (secretData == null || secretData.isEmpty()) {
          throw new BadRequestException("User does not have 2FA configured");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(secretData).get("value").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing OTP secret data", e);
        }
    }
}
