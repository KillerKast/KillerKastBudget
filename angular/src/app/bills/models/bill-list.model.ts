import {Bill} from "./bill.model";
import {SelectItem} from "primeng/primeng";
import {MonthlyBill} from "./monthly-bill.model";
import {YearlyBill} from "./yearly-bill.model";
import {OneTimeBill} from "./one-time-bill.model";
import {InterestBaringDebt} from "./interest-baring-debt.model";
import {NoInterestDebt} from "./no-interest-debt";
import {PaymentPlan} from "./payment-plan.model";
import {variable} from "@angular/compiler/src/output/output_ast";

/**
 * Created by Jonathan on 6/4/2017.
 */

export class BillList {
  bills: Bill[] = [];
  billOptions: SelectItem[] = [{label:'Please select bill', value: null}];

  constructor(private billType?: string, private billEntry?: any[]) {
    this.generateBillsArray(billType, billEntry);
  }

  generateBillsArray(billType: string, billEntry: any) {

    for (let bill in billEntry) {
      let tempBill;
      if (billType === 'monthly-bill') {
        tempBill = new MonthlyBill(
          billEntry[bill].id,
          billEntry[bill].name,
          billEntry[bill].description,
          billEntry[bill].paymentAmount,
          billEntry[bill].paymentDate
        );
      }
      else if (billType === 'yearly-bill') {
        tempBill = new YearlyBill(
          billEntry[bill].id,
          billEntry[bill].name,
          billEntry[bill].description,
          billEntry[bill].paymentAmount,
          billEntry[bill].paymentMonth,
          billEntry[bill].paymentDay
        );
      }
      else if (billType === 'one-time-bill') {
        tempBill = new OneTimeBill(
          billEntry[bill].id,
          billEntry[bill].name,
          billEntry[bill].description,
          billEntry[bill].paymentAmount,
          new Date(billEntry[bill].paymentDate)
        );
      }
      else if (billType === 'interest-baring-debt') {
        tempBill = new InterestBaringDebt(
          billEntry[bill].id,
          billEntry[bill].name,
          billEntry[bill].description,
          billEntry[bill].paymentAmount,
          billEntry[bill].paymentDate,
          billEntry[bill].startingBalance,
          billEntry[bill].apr
        )
      }
      else if (billType === 'no-interest-debt') {
        tempBill = new NoInterestDebt(
          billEntry[bill].id,
          billEntry[bill].name,
          billEntry[bill].description,
          billEntry[bill].paymentAmount,
          billEntry[bill].paymentDate,
          billEntry[bill].startingBalance,
        )
      }
      else if (billType === 'payment-plan') {
        tempBill = new PaymentPlan(
          billEntry[bill].id,
          billEntry[bill].name,
          billEntry[bill].description,
          billEntry[bill].paymentAmount,
          billEntry[bill].paymentDate,
          billEntry[bill].startingBalance,
          billEntry[bill].lastPaymentDate
        )
      }
      this.bills.push(tempBill || null);
      this.billOptions.push({label: tempBill.name, value: tempBill.id});
    }
  }

  get options() {
    return this.billOptions;
  }

  getSingleBill(id: string) {
    let bill: Bill;

    if (this.bills.length === 0) {
      if (this.billType === 'monthly-bill') {
        bill = new MonthlyBill();
      }
      if (this.billType === 'yearly-bill') {
        bill = new YearlyBill();
      }
      if (this.billType === 'one-time-bill') {
        bill = new OneTimeBill();
      }
      if (this.billType === 'interest-baring-debt') {
        bill = new InterestBaringDebt();
      }
      if (this.billType === 'no-interest-debt') {
        bill = new NoInterestDebt();
      }
      if (this.billType === 'payment-plan') {
        bill = new PaymentPlan();
      }
    } else {
      bill = this.bills.find(bill => bill.id === id);
    }
    return bill;
  }

  getBillOptions(id: string){
    let billOptions;

    let bill = this.bills.find(bill => bill.id === id);
    console.log(typeof bill);
    if(typeof bill  === 'undefined'){
      console.log("ID equals null.");
      billOptions = {label: 'Please select a bill', value: null};
    } else {
      billOptions = {label: bill.name, value: bill.id };
    }
    return billOptions;
  }
}


