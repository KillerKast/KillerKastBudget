/**
 * Created by Jonathan on 6/3/2017.
 */
import {Bill} from "./bill.model";

export class OneTimeBill extends Bill{

  private _oneTimePaymentDate: Date;

  constructor(id?: string, name?: string, description?: string,
              paymentAmount?: number, oneTimePaymentDate?: Date){

    super(id, name, description, paymentAmount, 'one-time-bill');
    this._oneTimePaymentDate = oneTimePaymentDate || null;
  }

  get oneTimePaymentDate(): Date {
    return this._oneTimePaymentDate;
  }

  set oneTimePaymentDate(value: Date) {
    this._oneTimePaymentDate = value;
  }

  updateBill(otb: OneTimeBill){
    console.log(otb);
    super.updateBill(otb);
    this.oneTimePaymentDate = otb.oneTimePaymentDate;
  }

  public getBill(){
    let oneTimeBill = super.getBill();
    oneTimeBill['paymentDate'] = this.oneTimePaymentDate;
    return oneTimeBill;
  }
}
