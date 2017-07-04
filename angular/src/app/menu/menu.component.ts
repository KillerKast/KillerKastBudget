import {Component, OnInit} from "@angular/core";
import {MenuItem} from "primeng/primeng";

@Component({
  selector: 'budget-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  public items: MenuItem[];

  constructor() {
  }

  ngOnInit() {
    this.items = [{
      label: 'Bills',
      items: [
          {label: 'Monthly Bill', routerLink: ['bill','monthly-bill']},
          {label: 'One Time Bill', routerLink: ['bill', 'one-time-bill']},
          {label: 'Yearly Bill', routerLink: ['bill', 'yearly-bill']},
          {label: 'Interest Baring Debt', routerLink: ['bill','interest-baring-debt']},
          {label: 'No Interest Debt', routerLink: ['bill', 'no-interest-debt']},
          {label: 'Payment Plan', routerLink: ['bill', 'payment-plan']},
      ]},
    ]
  }
}
