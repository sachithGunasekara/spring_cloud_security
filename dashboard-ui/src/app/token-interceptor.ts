import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Injectable, Optional } from "@angular/core";
import { Router } from "@angular/router";
import { OAuthModuleConfig, OAuthStorage } from "angular-oauth2-oidc";
import { ToastrService } from "ngx-toastr";
import { Observable } from "rxjs";
import 'rxjs/add/operator/do';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(
    private authStorage: OAuthStorage,
    private toastr: ToastrService, private router: Router,
    @Optional() private moduleConfig: OAuthModuleConfig
  ) {
  }

  private checkUrl(url: string): boolean {
    let found = this.moduleConfig.resourceServer.allowedUrls.find(u => url.startsWith(u));
    return !!found;
  }

  public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let url = req.url.toLowerCase();

    if (!this.moduleConfig) return next.handle(req);
    if (!this.moduleConfig.resourceServer) return next.handle(req);
    if (!this.moduleConfig.resourceServer.allowedUrls) return next.handle(req);
    if (!this.checkUrl(url)) return next.handle(req);

    let sendAccessToken = this.moduleConfig.resourceServer.sendAccessToken;

    if (sendAccessToken) {

      let token = this.authStorage.getItem('access_token');
      let header = 'Bearer ' + token;

      let headers = req.headers
        .set('Authorization', header);

      req = req.clone({ headers });
    }

    return next.handle(req).do(null, (err: any) => {
      console.error(err);
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401) {
          this.toastr.error('Authentication failed', 'Error!');
          this.router.navigate(['/login']);
        } else if (err.status === 403) {
          //this.toastr.error('Insufficient privileges', 'Error!');
          //this.router.navigate(['/dashboard']);
          throw new HttpErrorResponse({ error: { status: 403, message: 'Insufficient privileges' } });
        }
      }
    });

  }
}