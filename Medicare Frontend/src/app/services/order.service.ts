import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseUrl = 'http://localhost:8082/api/order/';
  constructor(private http: HttpClient) { }


  create(data:any ):Observable<any>{
    return this.http.post(this.baseUrl,data).pipe();
  }
}
