import {Bill} from "./bill.model";
/**
 * Created by killerkast on 7/1/17.
 */

export class InterestBaringDebt extends Bill{

  private _paymentDate: number;
  private _startingBalance: number;
  private _apr: number;

  constructor(id?: string, name?: string, description?: string,
              paymentAmount?: number, paymentDate?: number, startingBalance?: number,
              apr?: number){
    super(id, name, description, paymentAmount, 'interest-baring-debt');
    this._paymentDate = paymentAmount;
    this._startingBalance = startingBalance;
    this._apr = apr;
  }


  get paymentDate(): number {
    return this._paymentDate;
  }

  set paymentDate(value: number) {
    this._paymentDate = value;
  }

  get startingBalance(): number {
    return this._startingBalance;
  }

  set startingBalance(value: number) {
    this._startingBalance = value;
  }

  get apr(): number {
    return this._apr;
  }

  set apr(value: number) {
    this._apr = value;
  }

  updateBill(ibd: InterestBaringDebt){
    super.updateBill(ibd);
    this.paymentDate = ibd.paymentDate;
    this.startingBalance = ibd.startingBalance;
    this.apr = ibd.apr;
  }

  getBill(){
    let interestBaringDebt = super.getBill();
    interestBaringDebt['paymentDate'] = this.paymentDate;
    interestBaringDebt['startingBalance'] = this.startingBalance;
    interestBaringDebt['apr'] = this.apr;
    return interestBaringDebt;
  }

}
