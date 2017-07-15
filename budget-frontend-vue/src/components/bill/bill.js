const billTypes = [
  {name: 'Monthly bill', value: 'monthly-bill'},
  {name: 'One Time bill', value: 'one-time-bill'},
  {name: 'Yearly bill', value: 'yearly-bill'},
  {name: 'Interest Baring Debt', value: 'interest-baring-debt'},
  {name: 'Interest Free Debt', value: 'interest-free-debt'},
  {name: 'Payment Plan', value: 'payment-plan'}
]

const billNames = [
  {name: 'TestBill1', value: 'test-bill-1'},
  {name: 'TestBill2', value: 'test-bill-2'},
  {name: 'TestBill3', value: 'test-bill-3'},
  {name: 'TestBill4', value: 'test-bill-4'}
]

const billPaymentIntervals = [
  {name: 'Every', value: 'every'},
  {name: 'Every Other', value: 'every-other'},
  {name: 'One Time', value: 'one-time'},
]

const billPaymentPeriods = [
  {name: 'Day', value: 'day'},
  {name: 'Week', value: 'week'},
  {name: 'Month', value: 'month'},
  {name: 'Year', value: 'year'},
]

const payment = {
  amount: '',
  interval: '',
  period: '',
  next: new Date()
}

const debt = {
  currentBalance: '',
  hasAnnualPercentageRate: false,
  annualPercentageRate: '0.00'
}

const bill = {
  name: '',
  description: '',
  payment,
  debt
}

const data = () => {
  return {
    billType: 'bill',
    billNames,
    billPaymentIntervals,
    billPaymentPeriods,
    bill,
    isNew: true,
    newOrEdit: 'Edit Bills'
  }
}

export default {
  data: data,
  methods: {
    changeNewOrEdit () {
      this.newOrEdit = this.isNew ? 'New Bills' : 'Edit Bills'
      this.isNew = !this.isNew
    },
    submit () {
      if(this.billType === 'bill')
        this.submitBill();
      else(this.billType === 'debt')
        this.submitDebt();
    },
    submitBill () {
      this.isNew ? this.createBill() : this.updateBill()
    },
    createBill () {

    },
    updateBill () {

    },

  },
  watch: {

  }
}

