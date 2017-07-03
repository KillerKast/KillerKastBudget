import {Bill} from "./bill.model";
/**
 * Created by killerkast on 7/1/17.
 */

export class InterestBaringDebt extends Bill{

  private _interestBaringPaymentDate: number;
  private _interestBaringStartingBalance: number;
  private _interestBaringAPR: number;

  constructor(id?: string, name?: string, description?: string,
              paymentAmount?: number, interestBaringPaymentDate?: number, interestBaringStartingBalance?: number,
              interestBaringAPR?: number){
    super(id, name, description, paymentAmount, 'interest-baring-debt');
    this._interestBaringPaymentDate = interestBaringPaymentDate;
    this._interestBaringStartingBalance = interestBaringStartingBalance;
    this._interestBaringAPR = interestBaringAPR;
  }


  get interestBaringPaymentDate(): number {
    return this._interestBaringPaymentDate;
  }

  set interestBaringPaymentDate(value: number) {
    this._interestBaringPaymentDate = value;
  }

  get interestBaringStartingBalance(): number {
    return this._interestBaringStartingBalance;
  }

  set interestBaringStartingBalance(value: number) {
    this._interestBaringStartingBalance = value;
  }

  get interestBaringAPR(): number {
    return this._interestBaringAPR;
  }

  set interestBaringAPR(value: number) {
    this._interestBaringAPR = value;
  }

  updateBill(ibd: InterestBaringDebt){
    console.log(ibd);
    super.updateBill(ibd);
    this.interestBaringPaymentDate = ibd.interestBaringPaymentDate;
    this.interestBaringStartingBalance = ibd.interestBaringStartingBalance;
    this.interestBaringAPR = ibd.interestBaringAPR;
  }

  getBill(){
    let interestBaringDebt = super.getBill();
    interestBaringDebt['paymentDate'] = this.interestBaringPaymentDate;
    interestBaringDebt['startingBalance'] = this.interestBaringStartingBalance;
    interestBaringDebt['apr'] = this.interestBaringAPR;
    return interestBaringDebt;
  }

}
