import {Bill} from "./bill.model";

export class PaymentPlan extends Bill{

  private _paymentDate: number;
  private _startingBalance: number;
  private _lastPaymentDate: Date;

  constructor(id?: string, name?: string, description?: string,
              paymentAmount?: number, paymentDate?: number,
              startingBalance?: number, lastPaymentDate?: Date){

    super(id, name, description, paymentAmount, 'monthly-bill');
    this._paymentDate = paymentDate || null;
    this._startingBalance = startingBalance || null;
    this._lastPaymentDate = lastPaymentDate || null;
  }

  get paymentDate(): number {
    return this._paymentDate;
  }

  set paymentDate(thePaymentDate: number) {
    this._paymentDate = thePaymentDate;
  }

  get startingBalance(): number {
    return this._startingBalance;
  }

  set startingBalance(value: number) {
    this._startingBalance = value;
  }

  get lastPaymentDate(): Date {
    return this._lastPaymentDate;
  }

  set lastPaymentDate(value: Date) {
    this._lastPaymentDate = value;
  }

  updateBill(pp: PaymentPlan){
    super.updateBill(pp);
    this.paymentDate = pp.paymentDate;
    this.startingBalance = pp.startingBalance;
    this.lastPaymentDate = pp.lastPaymentDate;
  }

  public getBill(){
    let paymentPlan = super.getBill();
    paymentPlan['paymentDate'] = this.paymentDate;
    paymentPlan['startingBalance'] = this.startingBalance;
    paymentPlan['lastPaymentDate'] = this.lastPaymentDate;
    return paymentPlan;
  }
}
