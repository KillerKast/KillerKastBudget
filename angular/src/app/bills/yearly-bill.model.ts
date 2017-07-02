import {Bill} from "./bill.model";

export class YearlyBill extends Bill{

  private _paymentDay: number;
  private _paymentMonth: number;

  constructor(id?:string, name?: string, description?: string,
              paymentAmount?: number, paymentDay?: number,
              paymentMonth?: number){

    super(id, name, description, paymentAmount, 'yearly-bill');
    this._paymentDay = paymentDay || null;
    this._paymentMonth = paymentMonth || null;
  }

  get paymentDay(): number {
    return this._paymentDay;
  }

  set paymentDay(thePaymentDay: number) {
    this._paymentDay = thePaymentDay;
  }

  get paymentMonth(): number {
    return this._paymentMonth;
  }

  set paymentMonth(thePaymentMonth: number) {
    this._paymentMonth = thePaymentMonth;
  }

  updateBill(yb: YearlyBill){
    super.updateBill(yb);
    this.paymentDay = yb.paymentDay;
    this.paymentMonth = yb.paymentMonth;
  }

  public getBill(){
    let yearlyBill = super.getBill();
    yearlyBill['paymentDay'] = this.paymentDay;
    yearlyBill['paymentMonth'] = this.paymentMonth;
    return yearlyBill;
  }
}
