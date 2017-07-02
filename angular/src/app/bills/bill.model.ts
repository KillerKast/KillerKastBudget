export abstract class Bill{

  constructor(protected _id?: string, protected _name?: string, protected _description?: string,
              protected _paymentAmount?: number, protected _type?: string) {

  }

  get id(): string {
    return this._id;
  }

  set id(id: string) {
    this._id = id;
  }

  get name(): string {
    return this._name;
  }

  set name(theName: string) {
    this._name = theName;
  }

  get description(): string {
    return this._description;
  }

  set description(theDescription: string) {
    this._description = theDescription;
  }

  get paymentAmount(): number {
    return this._paymentAmount;
  }

  set paymentAmount(thePaymentAmount: number) {
    this._paymentAmount = thePaymentAmount;
  }


  get type(): string {
    return this._type;
  }

  set type(value: string) {
    this._type = value;
  }

  updateBill(b: Bill) {
    this.id = b.id;
    this.name = b.name;
    this.description = b.description;
    this.paymentAmount = b.paymentAmount;
  }

  get bill(){
    return {
      id : this.id,
      name: this.name,
      description: this.description,
      paymentAmount: this.paymentAmount,
      type: this.type
    }
  }

  public getBill(){
    return this.bill;
  }



}
