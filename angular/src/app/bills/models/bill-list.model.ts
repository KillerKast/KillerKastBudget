import {Bill} from "./bill.model";
import {SelectItem} from "primeng/primeng";
import {MonthlyBill} from "./monthly-bill.model";
import {YearlyBill} from "./yearly-bill.model";
import {OneTimeBill} from "./one-time-bill.model";
import {InterestBaringDebt} from "./interest-baring-debt.model";
import {NoInterestDebt} from "./no-interest-debt";

/**
 * Created by Jonathan on 6/4/2017.
 */

export class BillList {
  bills: Bill[] = [];
  billOptions: SelectItem[] = [];

  constructor(private billType?: string, private billEntry?: any[]) {
    this.generateBillsArray(billType, billEntry);
  }

  generateBillsArray(billType: string, billEntry: any) {

    for (let bill in billEntry) {
      let tempBill;
      if (billType === 'monthly-bill') {
        tempBill = new MonthlyBill(
          billEntry[bill]._id,
          billEntry[bill].name,
          billEntry[bill].description,
          billEntry[bill].paymentAmount,
          billEntry[bill].paymentDate
        );
      }
      else if (billType === 'yearly-bill') {
        tempBill = new YearlyBill(
          billEntry[bill]._id,
          billEntry[bill].name,
          billEntry[bill].description,
          billEntry[bill].paymentAmount,
          billEntry[bill].paymentMonth,
          billEntry[bill].paymentDay
        );
      }
      else if (billType === 'one-time-bill') {
        tempBill = new OneTimeBill(
          billEntry[bill]._id,
          billEntry[bill].name,
          billEntry[bill].description,
          billEntry[bill].paymentAmount,
          new Date(billEntry[bill].paymentDate)
        );
      }
      else if(billType === 'interest-baring-debt') {
        tempBill = new InterestBaringDebt(
          billEntry[bill]._id,
          billEntry[bill].name,
          billEntry[bill].description,
          billEntry[bill].paymentAmount,
          billEntry[bill].paymentDate,
          billEntry[bill].startingBalance,
          billEntry[bill].apr
        )
      }
      else if(billType === 'no-interest-debt') {
        tempBill = new NoInterestDebt(
          billEntry[bill]._id,
          billEntry[bill].name,
          billEntry[bill].description,
          billEntry[bill].paymentAmount,
          billEntry[bill].paymentDate,
          billEntry[bill].startingBalance,
        )
      }
      this.bills.push(tempBill || null);
      this.billOptions.push({label: tempBill.name, value: bill});
    }
  }

  get options() {
    return this.billOptions;
  }

  getSingleBill(id: number) {
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
      if(this.billType === 'interest-baring-debt'){
        bill = new InterestBaringDebt();
      }
      if(this.billType === 'no-interest-debt'){
        bill = new NoInterestDebt();
      }
    } else {
      bill = this.bills[id]
    }
    return bill;
  }
}
