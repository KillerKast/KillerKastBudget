import {Bill} from "./bill.model";
/**
 * Created by killerkast on 7/2/17.
 */
export class NoInterestDebt extends Bill{
  private _noInterestPaymentDate;
  private _noInterestStartingBalance;

  constructor(id?: string, name?: string, description?: string,
              paymentAmount?: number, noInterestPaymentDate?: number, noInterestStartingBalance?: number){
    super(id, name, description, paymentAmount, 'interest-baring-debt');
    this._noInterestPaymentDate = noInterestPaymentDate;
    this._noInterestStartingBalance = noInterestStartingBalance;
  }

  get noInterestPaymentDate() {
    return this._noInterestPaymentDate;
  }

  set noInterestPaymentDate(value) {
    this._noInterestPaymentDate = value;
  }

  get noInterestStartingBalance() {
    return this._noInterestStartingBalance;
  }

  set noInterestStartingBalance(value) {
    this._noInterestStartingBalance = value;
  }

  updateBill(nid: NoInterestDebt){
    super.updateBill(nid);
    this.noInterestPaymentDate = nid.noInterestPaymentDate;
    this.noInterestStartingBalance = nid.noInterestStartingBalance;
  }

  getBill(){
    let noInterestDebt = super.getBill();
    noInterestDebt['paymentDate'] = this.noInterestPaymentDate;
    noInterestDebt['startingBalance'] = this.noInterestStartingBalance;
    return noInterestDebt;
  }
}
