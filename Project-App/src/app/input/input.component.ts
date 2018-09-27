import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { CommonService } from "src/app/common.service";

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {
  
  constructor(private cs:CommonService) { }//private _http:HttpClient
  phone:"0";
  dispmessage: any= "No phone number"
  ngOnInit() {
  }
  
  checkCust(phone){
    console.log("Recieved phone number "+phone)
    //  let url = "http://localhost:8585/PhoneBanking/rest/customer/"+phone
    //  this._http.get(url).subscribe((response)=>{console.log(response)})
    this.cs.sayHello(phone).subscribe((response)=>{this.dispmessage=response})
    

  }
}
