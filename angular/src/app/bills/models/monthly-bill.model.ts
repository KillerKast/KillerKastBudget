import {Bill} from "./bill.model";

export class MonthlyBill extends Bill{

  private _monthlyPaymentDate: number;

  constructor(id?: string, name?: string, description?: string,
              paymentAmount?: number, monthlyPaymentDate?: number){

    super(id, name, description, paymentAmount, 'monthly-bill');
    this._monthlyPaymentDate = monthlyPaymentDate || null;
  }


  get monthlyPaymentDate(): number {
    return this._monthlyPaymentDate;
  }

  set monthlyPaymentDate(value: number) {
    this._monthlyPaymentDate = value;
  }

  updateBill(mb: MonthlyBill){
    super.updateBill(mb);
    this.monthlyPaymentDate = mb.monthlyPaymentDate;
  }

  public getBill(){
    let monthlyBill = super.getBill();
    monthlyBill['paymentDate'] = this.monthlyPaymentDate;
    return monthlyBill;
  }
}
