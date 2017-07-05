import {Bill} from "./models/bill.model";
import {MonthlyBill} from "./models/monthly-bill.model";
import {YearlyBill} from "./models/yearly-bill.model";
import {OneTimeBill} from "./models/one-time-bill.model";
import {NoInterestDebt} from "./models/no-interest-debt";
import {InterestBaringDebt} from "./models/interest-baring-debt.model";
import {PaymentPlan} from "./models/payment-plan.model";
import {Injectable} from "@angular/core";
/**
 * Created by Jonathan on 7/5/2017.
 */

@Injectable()
export class BillFactoryService{
  getBill(billType: string){
      let bill : Bill;
      switch (billType) {
        case 'monthly-bill':
          bill = new MonthlyBill();
          break;
        case 'yearly-bill':
          bill = new YearlyBill();
          break;
        case 'one-time-bill':
          bill = new OneTimeBill();
          break;
        case 'no-interest-debt':
          bill = new NoInterestDebt();
          break;
        case 'interest-baring-debt':
          console.log("Creating Interest Baring Debt");
          bill = new InterestBaringDebt();
          break;
        case 'payment-plan':
          bill = new PaymentPlan();
          break;
        default:
          break;
      }
    return bill;
  }
}
