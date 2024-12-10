/*
 * Extension for validating OTP during transactions.
 * @author Allan Vieira (allancnfx.vieira@gmail.com)
 * @version 1.0
 */
package com.droplink.keycloak.otp.extensions.resources.implementations;

import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;

import com.droplink.keycloak.otp.web.controllers.TwoFactorValidationController;


public class TwoFactorValidationProvider implements RealmResourceProvider {
    protected final KeycloakSession keycloakSession;
    private final TwoFactorValidationController RESOURCE_SINGLETON;

    public TwoFactorValidationProvider(KeycloakSession keycloakSession) {
      this.keycloakSession = keycloakSession;
      this.RESOURCE_SINGLETON = new TwoFactorValidationController(keycloakSession);
    }

    @Override
    public Object getResource() {
        return RESOURCE_SINGLETON;
    }

    @Override
    public void close() {}
}
