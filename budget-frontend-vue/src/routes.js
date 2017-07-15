import HomeComponent from './components/Home'
import BillComponent from './components/Bills'
import BudgetComponent from './components/Budget'
import IncomeComponent from './components/Income'

export const routes = [
  { path: '', component: HomeComponent },
  { path: '/bills', component: BillComponent },
  { path: '/income', component: IncomeComponent },
  { path: '/budget', component: BudgetComponent }
]
