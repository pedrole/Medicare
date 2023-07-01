import { Injectable } from '@angular/core';
import { Credentials } from './models/credentials';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  baseUrl = 'http://localhost:8082';

  constructor(private http: HttpClient) { }

  public getToken() {
    return localStorage.getItem('token');
  }

  //generate token
  public generateToken(credentials: Credentials): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/api/auth/login`, credentials);
  }

  //login user: set token in local storage
  public  loginUser(token: any) {
    
//    localStorage.clear();
    localStorage.setItem('token', token);
    
    
    return true;
  }

  //current user: user which is logged in
  public getCurrentUser(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/api/auth/current-user`);
  }

  //set user details in local storage
  public setUserDetails(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }
  //is logged in: user is logged in or not
  public isLoggedIn() {
    let tokenStr = localStorage.getItem('token');
    
    
    if (tokenStr == undefined || tokenStr == null || tokenStr == '') {
      return false;
    } else {
      return true;
    }
  }

  public getUserRole() : string{
    let user = this.getUserDetails();
    if(user)
    return user.authorities[0].authority;
    return '';
  }

  public getUserDetails() {
    let user = localStorage.getItem('user');
    if (user != null) {
      return JSON.parse(user);
    } else {
      
      
      //this.logout();
      return null;
    }

  }
  public logout() {
    
    
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    //localStorage.clear();
    return true;
  }
}
