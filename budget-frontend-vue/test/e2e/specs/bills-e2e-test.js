/**
 * Created by Jonathan on 7/11/2017.
 */
module.exports = {
  'bill-test': function (browser) {
    // automatically uses dev Server port from /config.index.js
    // default: http://localhost:8080
    // see nightwatch.conf.js
    const devServer = browser.globals.devServerURL
    browser.url(devServer)
    browser.click('li:nth-child(2)')
    browser.waitForElementVisible('#bills', 5000)
    browser.waitForElementVisible('#bill-name', 5000)
    browser.setValue('#bill-name', 'test')
    browser.waitForElementVisible('#bill-name-test', 5000)
    browser.expect.element('#bill-name-test').text.to.contain('test')
    browser.waitForElementVisible('#bill-description', 5000)
    browser.setValue('#bill-description', 'test description')
    browser.waitForElementVisible('#bill-description-test', 5000)
    browser.expect.element('#bill-description-test').text.to.contain('test description')
    browser.waitForElementVisible('#bill-payment-amount', 5000)
    browser.setValue('#bill-payment-amount', 'test description')
    browser.waitForElementVisible('#bill-payment-amount-test', 5000)
    browser.expect.element('#bill-payment-amount-test').text.to.contain('test description')
    browser.waitForElementVisible('#bill-payment-amount', 5000)
    browser.setValue('#bill-payment-amount', 'test description')
    browser.waitForElementVisible('#bill-payment-amount-test', 5000)
    browser.expect.element('#bill-payment-amount-test').text.to.contain('test description')
    browser.end()
  }
}
