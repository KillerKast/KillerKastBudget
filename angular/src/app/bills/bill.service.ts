import {Bill} from "./bill.model";
import {Headers, Http, Response} from "@angular/http";
import 'rxjs/Rx';
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {MonthlyBill} from "./monthly-bill.model";
import {YearlyBill} from "./yearly-bill.model";
import {OneTimeBill} from "./one-time-bill.model";
import {InterestBaringDebt} from "./interest-baring-debt.model";

@Injectable()
export class BillService{

  private apiUrl : string = 'http://localhost:8080/api/bill';

  constructor(private http: Http){}

  createBill(billType: string, bill: Bill) {
    const body = this.getBody(billType, bill);
    const headers = new Headers({'Content-Type':'application/json'});
    return this.http.post(this.apiUrl + '/create/' + billType, body, {headers: headers})
      .map((response: Response) => response.json())
      .catch((error: Response) => Observable.throw(error.json()));
  }

  readBills(billType){
    return this.http.get(this.apiUrl + '/readAll/' + billType)
      .map((response: Response) => response.json())
      .catch((error: Response) => Observable.throw(error.json()));
  }


  updateBill(billType: string, bill: Bill){
    const body = this.getBody(billType, bill);
    const headers = new Headers({'Content-Type':'application/json'});
    return this.http.patch(this.apiUrl + '/update/' + billType, body, {headers: headers})
      .map((response: Response) => response.json())
      .catch((error: Response) => Observable.throw(error.json()));
  }




  private getBody(billType: String, bill: Bill){
    let body: String;
    if(billType=='monthly-bill'){
      body = JSON.stringify((<MonthlyBill>bill).getBill());
    } else if(billType == 'yearly-bill'){
      body = JSON.stringify((<YearlyBill>bill).getBill());
    } else if(billType == 'one-time-bill'){
      body = JSON.stringify((<OneTimeBill>bill).getBill());
    } else if (billType == 'interest-baring-debt'){
      body = JSON.stringify((<InterestBaringDebt>bill).getBill());
    }
    return body;
  }




}
