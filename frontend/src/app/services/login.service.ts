import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private urlServeurApi = environment.urlServiceApi;
  constructor(private http:HttpClient) { }

  public loginUser(user: User):Observable<any>{
    return this.http.post(this.urlServeurApi + 'login', user);
  }
  public getToken(){
    return localStorage.getItem('token')?true:false;
  }
}
