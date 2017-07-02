import {Bill} from "./bill.model";
/**
 * Created by killerkast on 7/2/17.
 */
export class NoInterestDebt extends Bill{
  private _paymentDate;
  private _startingBalance;

  constructor(id?: string, name?: string, description?: string,
              paymentAmount?: number, paymentDate?: number, startingBalance?: number){
    super(id, name, description, paymentAmount, 'interest-baring-debt');
    this._paymentDate = paymentAmount;
    this._startingBalance = startingBalance;
  }

  get paymentDate() {
    return this._paymentDate;
  }

  set paymentDate(value) {
    this._paymentDate = value;
  }

  get startingBalance() {
    return this._startingBalance;
  }

  set startingBalance(value) {
    this._startingBalance = value;
  }

  updateBill(nid: NoInterestDebt){
    super.updateBill(nid);
    this.paymentDate = nid.paymentDate;
    this.startingBalance = nid.startingBalance;
  }

  getBill(){
    let noInterestDebt = super.getBill();
    noInterestDebt['paymentDate'] = this.paymentDate;
    noInterestDebt['startingBalance'] = this.startingBalance;
    return noInterestDebt;
  }
}
