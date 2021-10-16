import { Injectable } from "@angular/core";
import { ActivatedRoute, ActivatedRouteSnapshot, CanActivate, Router } from "@angular/router";
import { OAuthService } from "angular-oauth2-oidc";
import { ToastrService } from "ngx-toastr";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private oauthService: OAuthService, private router: Router, private route: ActivatedRoute,private toastr: ToastrService) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    if (!this.oauthService.hasValidAccessToken()) {
      this.router.navigate(['/login']);
      return false;
    } else {
      let routeData: any = route.data;
      if (!routeData) {
        return true;
      }

      if (!routeData.roles) {
        return true;
      }

      if (!Array.isArray(routeData.roles)) {
        console.log(routeData.roles);
        return true;
      }

      let allowedRoles:string[] = routeData.roles;
      if(allowedRoles.length == 0){
        return true;
      }

      let loggedInUserRoles = this.getLoggedInUserRoles();
      if(loggedInUserRoles.length == 0){
        return false;
      }

      for(let i=0;i<allowedRoles.length;i++){
        if(loggedInUserRoles.find(val=> val ===allowedRoles[i])){
          return true;
        }
      }
      
      this.toastr.error('Insufficient privileges', 'Access Denied!');
      return false;
    }
  }

  private getLoggedInUserRoles(): string[] {
    let jwt = this.oauthService.getAccessToken();
    let jwtData = jwt.split('.')[1]
    let decodedJwtJsonData = window.atob(jwtData)
    let decodedJwtData = JSON.parse(decodedJwtJsonData)
    return decodedJwtData['realm_access']['roles'];
  }

  private isAuthorized(): boolean {

    let routeData = this.route.snapshot.data;
    if (!routeData) {
      return true;
    }

    if (!routeData.roles) {
      return true;
    }

    if (!Array.isArray(routeData.roles)) {
      console.log(routeData.roles);
      return true;
    }

    return false;
  }
}