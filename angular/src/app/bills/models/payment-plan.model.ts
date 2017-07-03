import {Bill} from "./bill.model";

export class PaymentPlan extends Bill{

  private _paymentPlanPaymentDate: number;
  private _paymentPlanStartingBalance: number;
  private _paymentPlanLastPaymentDate: Date;

  constructor(id?: string, name?: string, description?: string,
              paymentAmount?: number, paymentPlanPaymentDate?: number,
              paymentPlanStartingBalance?: number, paymentPlanLastPaymentDate?: Date){

    super(id, name, description, paymentAmount, 'monthly-bill');
    this._paymentPlanPaymentDate = paymentPlanPaymentDate || null;
    this._paymentPlanStartingBalance = paymentPlanStartingBalance || null;
    this._paymentPlanLastPaymentDate = paymentPlanLastPaymentDate || null;
  }


  get paymentPlanPaymentDate(): number {
    return this._paymentPlanPaymentDate;
  }

  set paymentPlanPaymentDate(value: number) {
    this._paymentPlanPaymentDate = value;
  }

  get paymentPlanStartingBalance(): number {
    return this._paymentPlanStartingBalance;
  }

  set paymentPlanStartingBalance(value: number) {
    this._paymentPlanStartingBalance = value;
  }

  get paymentPlanLastPaymentDate(): Date {
    return this._paymentPlanLastPaymentDate;
  }

  set paymentPlanLastPaymentDate(value: Date) {
    this._paymentPlanLastPaymentDate = value;
  }

  updateBill(pp: PaymentPlan){
    super.updateBill(pp);
    this.paymentPlanPaymentDate = pp.paymentPlanPaymentDate;
    this.paymentPlanStartingBalance = pp.paymentPlanStartingBalance;
    this.paymentPlanLastPaymentDate = pp.paymentPlanLastPaymentDate;
  }

  public getBill(){
    let paymentPlan = super.getBill();
    paymentPlan['paymentDate'] = this.paymentPlanPaymentDate;
    paymentPlan['startingBalance'] = this.paymentPlanStartingBalance;
    paymentPlan['lastPaymentDate'] = this.paymentPlanLastPaymentDate;
    return paymentPlan;
  }
}
