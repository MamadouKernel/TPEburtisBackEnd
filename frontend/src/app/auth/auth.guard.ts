import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../services/login.service';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  user:User=new User();
  constructor(private loginService: LoginService, private router:Router) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let islogin = this.loginService.getToken();
    if(islogin){
      this.router.navigate(['/home']);
      return true;
    }else{
      this.router.navigate(['/connexion']);
      return false;
    } 
  }
}


