import {Component} from "@angular/core";
import {Bill} from "./models/bill.model";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "ng2-validation";
import {ActivatedRoute} from "@angular/router";
import {MonthlyBill} from "./models/monthly-bill.model";
import {YearlyBill} from "./models/yearly-bill.model";
import {OneTimeBill} from "./models/one-time-bill.model";
import {BillService} from "./bill.service";
import {BillList} from "./models/bill-list.model";
import {InterestBaringDebt} from "./models/interest-baring-debt.model";
import {NoInterestDebt} from "./models/no-interest-debt";

@Component({
  selector: 'budget-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css'],
  providers: [BillService]
})
export class BillComponent {

  billType: string;
  bill: Bill;
  billForm: FormGroup;

  isNew: boolean = false;

  bills: BillList = new BillList();

  constructor(private route: ActivatedRoute, private fb: FormBuilder, private billService: BillService) {
    this.billType = route.snapshot.params['billType'] || 'monthly-bill';
    this.setupForm();
    this.routeListener();
  }

  setupForm() {
    this.buildBaseForm();
    this.configureBillType();
  }


  buildBaseForm() {
    this.billForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', [<any>Validators.required]],
      paymentAmount: ['', [Validators.required, CustomValidators.number]],
    });
  }

  configureBillType() {
    this.deleteFormControls();
    switch (this.billType) {
      case 'monthly-bill':
        this.bill = new MonthlyBill();
        this.billForm.addControl('paymentDate', new FormControl((<MonthlyBill>this.bill).paymentDate, Validators.required));
        break;
      case 'yearly-bill':
        this.bill = new YearlyBill();
        this.billForm.addControl('paymentDay', new FormControl((<YearlyBill>this.bill).paymentDay, Validators.required));
        this.billForm.addControl('paymentMonth', new FormControl((<YearlyBill>this.bill).paymentMonth, Validators.required));
        break;
      case 'one-time-bill':
        this.bill = new OneTimeBill();
        this.billForm.addControl('oneTimePaymentDate', new FormControl((<OneTimeBill>this.bill).oneTimePaymentDate, Validators.required));
        break;
      case 'interest-baring-debt':
        this.bill = new InterestBaringDebt();
        this.billForm.addControl('interestBaringPaymentDate', new FormControl((<InterestBaringDebt>this.bill).paymentDate, Validators.required));
        this.billForm.addControl('interestBaringStartingBalance', new FormControl((<InterestBaringDebt>this.bill).startingBalance, Validators.required));
        this.billForm.addControl('apr', new FormControl((<InterestBaringDebt>this.bill).apr, Validators.required));
      case 'no-interest-debt':
        this.bill = new NoInterestDebt();
        this.billForm.addControl('noInterestPaymentDate', new FormControl((<NoInterestDebt>this.bill).paymentDate, Validators.required));
        this.billForm.addControl('noInterestStartingBalance', new FormControl((<NoInterestDebt>this.bill).startingBalance, Validators.required));
      default:
        break;
    }
  }

  deleteFormControls(){
    if (this.billForm.contains('paymentMonth')) {
      this.billForm.removeControl('paymentMonth');
    }
    if (this.billForm.contains('paymentDay')) {
      this.billForm.removeControl('paymentDay');
    }
    if (this.billForm.contains('oneTimePaymentDate')) {
      this.billForm.removeControl('oneTimePaymentDate');
    }
    if (this.billForm.contains('paymentDate')) {
      this.billForm.removeControl('paymentDate');
    }
    if(this.billForm.contains('interestBaringPaymentDate')) {
      this.billForm.removeControl('interestBaringPaymentDate');
    }
    if(this.billForm.contains('apr')){
      this.billForm.removeControl('apr');
    }
    if(this.billForm.contains('interestBaringStartingBalance')) {
      this.billForm.removeControl('interestBaringStartingBalance');
    }
  }


  routeListener() {
    this.route.params.subscribe(params => {
      this.billType = params['billType'];
      this.setupForm();
      this.getBills();
    });
  };

  getBills() {
    this.billService.readBills(this.billType).subscribe(
      data => {
        this.bills = new BillList(this.billType, data);
        this.configureBillType();
      },
      error => {
        console.log("failure");
        console.log(error);
      }
    );
  }


  onNewEditClicked() {
    this.isNew = !this.isNew;
    this.billForm.reset();
    if (!this.isNew) {
      this.onBillsChange();
    }
  }

  onSubmit() {
    let savingBill: any = this.billForm.value;
    if (!this.isNew) {
      savingBill.id = this.bills.getSingleBill(savingBill.name).id;
      savingBill.name = this.bills.getSingleBill(savingBill.name).name;
    }

    this.bill.updateBill(savingBill);

    if (this.isNew) {
      this.billService.createBill(this.billType, this.bill).subscribe(
        data => console.log(data),
        error => console.error(error)
      );
    } else if (!this.isNew) {
      this.billService.updateBill(this.billType, this.bill).subscribe(
        data => console.log(data),
        error => console.error(error)
      );
      console.log(this.bill);
    }
    // this.bill.updateBill(this.billForm.value);

  }

  onBillsChange() {
    let id = this.billForm.value.name || 0;

    this.bill = this.bills.getSingleBill(id);

    this.billForm.controls['description'].patchValue(this.bill.description);
    this.billForm.controls['paymentAmount'].patchValue(this.bill.paymentAmount);
    if (this.billType === 'monthly-bill') {
      this.billForm.controls['paymentDate'].patchValue((<MonthlyBill>this.bill).paymentDate);
    }
    if (this.billType === 'yearly-bill') {
      this.billForm.controls['paymentDay'].patchValue((<YearlyBill>this.bill).paymentDay);
      this.billForm.controls['paymentMonth'].patchValue((<YearlyBill>this.bill).paymentMonth);
    }
    if (this.billType === 'one-time-bill') {
      this.billForm.controls['oneTimePaymentDate'].patchValue((<OneTimeBill>this.bill).oneTimePaymentDate);
    }
    if(this.billType === 'interest-baring-debt'){
      this.billForm.controls['interestBaringPaymentDate'].patchValue((<InterestBaringDebt>this.bill).paymentDate);
      this.billForm.controls['startingBalance'].patchValue((<InterestBaringDebt>this.bill).startingBalance);
      this.billForm.controls['apr'].patchValue((<InterestBaringDebt>this.bill).apr);
    }
    this.billForm.markAsPristine();
  }


}

