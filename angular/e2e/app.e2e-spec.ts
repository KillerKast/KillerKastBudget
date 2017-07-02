import { BudgetApplicationPage } from './app.po';

describe('budget-application App', () => {
  let page: BudgetApplicationPage;

  beforeEach(() => {
    page = new BudgetApplicationPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
