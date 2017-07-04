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
import {PaymentPlan} from "./models/payment-plan.model";

@Component({
  selector: 'budget-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css'],
  providers: [BillService]
})
export class BillComponent {

  private id: string;

  billType: string;
  bill: Bill;
  billForm: FormGroup;
  newEditButtonLabel: string = 'New';


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
      billInfo: [''],
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
        this.billForm.addControl('monthlyPaymentDate', new FormControl((<MonthlyBill>this.bill).monthlyPaymentDate, Validators.required));
        break;
      case 'yearly-bill':
        this.bill = new YearlyBill();
        this.billForm.addControl('yearlyPaymentMonth', new FormControl((<YearlyBill>this.bill).yearlyPaymentMonth, Validators.required));
        this.billForm.addControl('yearlyPaymentDay', new FormControl((<YearlyBill>this.bill).yearlyPaymentDay, Validators.required));
        break;
      case 'one-time-bill':
        this.bill = new OneTimeBill();
        this.billForm.addControl('oneTimePaymentDate', new FormControl((<OneTimeBill>this.bill).oneTimePaymentDate, Validators.required));
        break;
      case 'no-interest-debt':
        this.bill = new NoInterestDebt();
        this.billForm.addControl('noInterestPaymentDate', new FormControl((<NoInterestDebt>this.bill).noInterestPaymentDate, Validators.required));
        this.billForm.addControl('noInterestStartingBalance', new FormControl((<NoInterestDebt>this.bill).noInterestStartingBalance, Validators.required));
        break;
      case 'interest-baring-debt':
        this.bill = new InterestBaringDebt();
        this.billForm.addControl('interestBaringPaymentDate', new FormControl((<InterestBaringDebt>this.bill).interestBaringPaymentDate, Validators.required));
        this.billForm.addControl('interestBaringStartingBalance', new FormControl((<InterestBaringDebt>this.bill).interestBaringStartingBalance, Validators.required));
        this.billForm.addControl('interestBaringAPR', new FormControl((<InterestBaringDebt>this.bill).interestBaringAPR, Validators.required));
        break;
      case 'payment-plan':
        this.bill = new PaymentPlan();
        this.billForm.addControl('paymentPlanPaymentDate', new FormControl((<PaymentPlan>this.bill).paymentPlanPaymentDate, Validators.required));
        this.billForm.addControl('paymentPlanStartingBalance', new FormControl((<PaymentPlan>this.bill).paymentPlanStartingBalance, Validators.required));
        this.billForm.addControl('paymentPlanLastPaymentDate', new FormControl((<PaymentPlan>this.bill).paymentPlanLastPaymentDate, Validators.required));
        break;
      default:
        break;
    }
  }

  deleteFormControls() {
    /**Delete Monthly Bill Items**/
    if (this.billForm.contains('monthlyPaymentDate')) {
      this.billForm.removeControl('monthlyPaymentDate');
    }
    /**Delete One Time Bill Items**/
    if (this.billForm.contains('oneTimePaymentDate')) {
      this.billForm.removeControl('oneTimePaymentDate');
    }

    /**Delete Yearly Bill Items**/
    if (this.billForm.contains('yearlyPaymentMonth')) {
      this.billForm.removeControl('yearlyPaymentMonth');
    }
    if (this.billForm.contains('yearlyPaymentDay')) {
      this.billForm.removeControl('yearlyPaymentDay');
    }

    /**Delete No Interest Items**/
    if (this.billForm.contains('noInterestPaymentDate')) {
      this.billForm.removeControl('noInterestPaymentDate');
    }
    if (this.billForm.contains('noInterestStartingBalance')) {
      this.billForm.removeControl('noInterestStartingBalance');
    }

    /**Delete Interest Baring Items**/
    if (this.billForm.contains('interestBaringPaymentDate')) {
      this.billForm.removeControl('interestBaringPaymentDate');
    }
    if (this.billForm.contains('interestBaringStartingBalance')) {
      this.billForm.removeControl('interestBaringStartingBalance');
    }
    if (this.billForm.contains('interestBaringAPR')) {
      this.billForm.removeControl('interestBaringAPR');
    }

    /**Delete PaymentPlan Items**/
    if (this.billForm.contains('paymentPlanPaymentDate')) {
      this.billForm.removeControl('paymentPlanPaymentDate');
    }
    if (this.billForm.contains('paymentPlanStartingBalance')) {
      this.billForm.removeControl('paymentPlanStartingBalance');
    }
    if (this.billForm.contains('paymentPlanLastPaymentDate')) {
      this.billForm.removeControl('paymentPlanLastPaymentDate');
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
    let that = this;
    this.billService.readBills(this.billType).subscribe(
      data => {
        this.bills = new BillList(this.billType, data);
        this.configureBillType();
        that.onBillsChange();
      },
      error => {
        console.log("failure");
        console.log(error);
      }
    );
  }


  newEditButtonClicked() {
    this.isNew = !this.isNew;
    this.billForm.reset();
    if (this.isNew) {
      this.newEditButtonLabel = 'Edit';
    } else {
      this.newEditButtonLabel = 'New';
    }
  }

  onSubmit() {
    let savingBill: any = this.billForm.value;
    let tempId: string = this.bill.id;
    this.bill.updateBill(savingBill);
    console.log(this.bill);
    if (this.isNew) {
      let that = this;
      this.bill.id = null;
      this.billService.createBill(this.billType, this.bill).subscribe(
        data => {
          that.id = data.id;
          that.newEditButtonClicked();
          that.getBills();
        },
        error => console.error(error)
      );
    } else if (!this.isNew) {

      this.bill.id = tempId;
      this.billService.updateBill(this.billType, this.bill).subscribe(
        data => console.log(data),
        error => console.error(error)
      );
    }
    // this.bill.updateBill(this.billForm.value);

  }

  onBillsChange() {
    let billInfo = this.id || this.billForm.value.billInfo || 0;

    this.bill = this.bills.getSingleBill(billInfo);
    let bi = this.bills.getBillOptions(billInfo);
    this.billForm.controls['billInfo'].patchValue(this.bills.getBillOptions(billInfo));

    if (this.billForm.value.billInfo !== null) {
      this.billForm.controls['name'].patchValue(this.bill.name);
      this.billForm.controls['description'].patchValue(this.bill.description);
      this.billForm.controls['paymentAmount'].patchValue(this.bill.paymentAmount);

      if (this.billType === 'monthly-bill') {
        this.billForm.controls['monthlyPaymentDate'].patchValue((<MonthlyBill>this.bill).monthlyPaymentDate);
      }

      if (this.billType === 'one-time-bill') {
        this.billForm.controls['oneTimePaymentDate'].patchValue((<OneTimeBill>this.bill).oneTimePaymentDate);
      }

      if (this.billType === 'yearly-bill') {
        this.billForm.controls['yearlyPaymentMonth'].patchValue((<YearlyBill>this.bill).yearlyPaymentMonth);
        this.billForm.controls['yearlyPaymentDay'].patchValue((<YearlyBill>this.bill).yearlyPaymentDay);
      }

      if (this.billType === 'no-interest-debt') {
        this.billForm.controls['noInterestPaymentDate'].patchValue((<NoInterestDebt>this.bill).noInterestPaymentDate);
        this.billForm.controls['noInterestStartingBalance'].patchValue((<NoInterestDebt>this.bill).noInterestStartingBalance);
      }

      if (this.billType === 'interest-baring-debt') {
        this.billForm.controls['interestBaringPaymentDate'].patchValue((<InterestBaringDebt>this.bill).interestBaringPaymentDate);
        this.billForm.controls['interestBaringStartingBalance'].patchValue((<InterestBaringDebt>this.bill).interestBaringStartingBalance);
        this.billForm.controls['interestBaringAPR'].patchValue((<InterestBaringDebt>this.bill).interestBaringAPR);
      }

      if (this.billType === 'payment-plan') {
        this.billForm.controls['paymentPlanPaymentDate'].patchValue((<PaymentPlan>this.bill).paymentPlanPaymentDate);
        this.billForm.controls['paymentPlanStartingBalance'].patchValue((<PaymentPlan>this.bill).paymentPlanStartingBalance);
        this.billForm.controls['paymentPlanLastPaymentDate'].patchValue((<PaymentPlan>this.bill).paymentPlanLastPaymentDate);
      }
    }
    this.billForm.markAsPristine();

  }

  deleteBill() {
    this.billService.deleteBill(this.billType, this.bill.id).subscribe(
      data => {
        this.getBills();
        this.billForm.reset();
        console.log("This has been deleted");
      },
      error => console.error(error)
    );
  }


}

