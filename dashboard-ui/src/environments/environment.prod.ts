export const environment = {
  production: true,
  serviceURL:"http://localhost:8080/broker-client",
  security:{
    issuer:'http://localhost:8080/auth/realms/microservice',
    redirectUri:'/ui-app',
    clientId:'angular-ui',
    responseType:'code',
    scope:'openid profile email offline_access',
    silentRefreshRedirectUri:'/assets/silent-refresh.html',
    showDebugInformation:true,
    resourceServerAllowedUrls:['http://localhost:8080/broker-client']
  }
};

