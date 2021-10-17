// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  serviceURL:"http://localhost:8081/order-service",
  security:{
    issuer:'http://localhost:8080/auth/realms/microservice',
    redirectUri:'',
    clientId:'angular-ui',
    responseType:'code',
    scope:'openid profile email offline_access',
    silentRefreshRedirectUri:'/assets/silent-refresh.html',
    showDebugInformation:true,
    resourceServerAllowedUrls:['http://localhost:8081/order-service']
  }
};

