import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { Observable, catchError, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private urlServeurApi = environment.urlServiceApi;
  constructor(private http:HttpClient) { }

  public getUser():Observable<User[]>{  
    return this.http.get<User[]>(this.urlServeurApi + 'all');
  }

  public createUser(user:any){
    return this.http.post<User>(this.urlServeurApi + 'create', user);
  }

  public updateUser(user:User){
    return this.http.put<User>(this.urlServeurApi + user.id, user);
  }

  public deleteUser(id:number){
    return this.http.delete<User>(this.urlServeurApi + id);
  }

  httpHandler() {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT,DELETE',
      'Accept': 'application/json',
    });
  }

  

}
