/*
 * Extension for validating OTP during transactions.
 * @author Allan Vieira (allancnfx.vieira@gmail.com)
 * @version 1.0
 */
package com.droplink.keycloak.otp.extensions.resources.factories;

import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

import com.droplink.keycloak.otp.extensions.resources.implementations.TwoFactorValidationProvider;



public class TwoFactorValidationProviderFactory implements RealmResourceProviderFactory {
    public static final String ID = "2fa-validation";

    @Override
    public RealmResourceProvider create(KeycloakSession session) {
      return new TwoFactorValidationProvider(session);
    }

    @Override
    public void init(Config.Scope config) {}

    @Override
    public void postInit(KeycloakSessionFactory factory) {}

    @Override
    public void close() {}

    @Override
    public String getId() {
      return ID;
    }
}
