/*
 * Extension for validating OTP during transactions.
 * @author Allan Vieira (allancnfx.vieira@gmail.com)
 * @version 1.0
 */
package com.droplink.keycloak.otp.web.context;

import org.keycloak.models.UserModel;

public class UserContext {
  private static final ThreadLocal<UserModel> currentUser = new ThreadLocal<>();

  public static void setUser(UserModel user) {
    currentUser.set(user);
  }

  public static UserModel getUser() {
    return currentUser.get();
  }

  public static void clear() {
    currentUser.remove();
  }
}
