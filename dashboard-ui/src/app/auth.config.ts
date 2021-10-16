import { AuthConfig } from "angular-oauth2-oidc";
import { environment } from "../environments/environment";

export const authCodeFlowConfig: AuthConfig = {
  issuer: environment.security.issuer,
  redirectUri: window.location.origin + environment.security.redirectUri,
  clientId: environment.security.clientId,
  responseType: environment.security.responseType,
  scope: environment.security.scope,
  silentRefreshRedirectUri: window.location.origin + environment.security.silentRefreshRedirectUri,
  showDebugInformation: true,
  requireHttps:false
};