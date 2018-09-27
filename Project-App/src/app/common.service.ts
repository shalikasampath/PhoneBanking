import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(private _http:HttpClient) { }

  sayHello(phone){
    let url = "http://localhost:8585/PhoneBanking/rest/customer/"+phone
    return this._http.get(url,{responseType: 'text'})
  }
}

