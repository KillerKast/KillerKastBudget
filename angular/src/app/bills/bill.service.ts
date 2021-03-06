import {Bill} from "./models/bill.model";
import {Headers, Http, Response} from "@angular/http";
import 'rxjs/Rx';
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {MonthlyBill} from "./models/monthly-bill.model";
import {YearlyBill} from "./models/yearly-bill.model";
import {OneTimeBill} from "./models/one-time-bill.model";
import {InterestBaringDebt} from "./models/interest-baring-debt.model";
import {NoInterestDebt} from "./models/no-interest-debt";
import {PaymentPlan} from "./models/payment-plan.model";

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
    return this.http.put(this.apiUrl + '/update/' + billType, body, {headers: headers})
      .map((response: Response) => response.json())
      .catch((error: Response) => Observable.throw(error.json()));
  }

  deleteBill(billType: string, id: string){
      return this.http.delete(this.apiUrl + '/delete/' + billType + "/" + id)
        .catch((error: Response) => Observable.throw(error.json()));
  }




  private getBody(billType: String, bill: Bill){
    let body: String;
    console.log(billType);
    if(billType=='monthly-bill'){
      body = JSON.stringify((<MonthlyBill>bill).getBill());
    } else if(billType === 'yearly-bill'){
      body = JSON.stringify((<YearlyBill>bill).getBill());
    } else if(billType === 'one-time-bill'){
      body = JSON.stringify((<OneTimeBill>bill).getBill());
    } else if (billType === 'interest-baring-debt'){
      body = JSON.stringify((<InterestBaringDebt>bill).getBill());
    } else if (billType === 'no-interest-debt'){
      body = JSON.stringify((<NoInterestDebt>bill).getBill());
    } else if (billType === 'payment-plan'){
      body = JSON.stringify((<PaymentPlan>bill).getBill());
    }
    return body;
  }




}
