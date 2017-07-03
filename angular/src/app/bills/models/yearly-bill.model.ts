import {Bill} from "./bill.model";

export class YearlyBill extends Bill{

  private _yearlyPaymentDay: number;
  private _yearlyPaymentMonth: number;

  constructor(id?:string, name?: string, description?: string,
              paymentAmount?: number, yearlyPaymentDay?: number,
              yearlyPaymentMonth?: number){

    super(id, name, description, paymentAmount, 'yearly-bill');
    this._yearlyPaymentDay = yearlyPaymentDay || null;
    this._yearlyPaymentMonth = yearlyPaymentMonth || null;
  }

  get yearlyPaymentDay(): number {
    return this._yearlyPaymentDay;
  }

  set yearlyPaymentDay(value: number) {
    this._yearlyPaymentDay = value;
  }

  get yearlyPaymentMonth(): number {
    return this._yearlyPaymentMonth;
  }

  set yearlyPaymentMonth(value: number) {
    this._yearlyPaymentMonth = value;
  }

  updateBill(yb: YearlyBill){
    super.updateBill(yb);
    this.yearlyPaymentDay = yb.yearlyPaymentDay;
    this.yearlyPaymentMonth = yb.yearlyPaymentMonth;
  }

  public getBill(){
    let yearlyBill = super.getBill();
    yearlyBill['paymentDay'] = this.yearlyPaymentDay;
    yearlyBill['paymentMonth'] = this.yearlyPaymentMonth;
    return yearlyBill;
  }
}
