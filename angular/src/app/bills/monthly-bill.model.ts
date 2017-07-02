import {Bill} from "./bill.model";

export class MonthlyBill extends Bill{

  private _paymentDate: number;

  constructor(id?: string, name?: string, description?: string,
              paymentAmount?: number, paymentDate?: number){

    super(id, name, description, paymentAmount, 'monthly-bill');
    this._paymentDate = paymentDate || null;
  }

  get paymentDate(): number {
    return this._paymentDate;
  }

  set paymentDate(thePaymentDate: number) {
    this._paymentDate = thePaymentDate;
  }

  updateBill(mb: MonthlyBill){
    super.updateBill(mb);
    this.paymentDate = mb.paymentDate;
  }

  public getBill(){
    let monthlyBill = super.getBill();
    monthlyBill['paymentDate'] = this.paymentDate;
    return monthlyBill;
  }
}
