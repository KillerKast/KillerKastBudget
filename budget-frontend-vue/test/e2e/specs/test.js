// For authoring Nightwatch tests, see
// http://nightwatchjs.org/guide#usage
module.exports = {
  'default e2e tests': function (browser) {
    // automatically uses dev Server port from /config.index.js
    // default: http://localhost:8080
    // see nightwatch.conf.js
    const devServer = browser.globals.devServerURL
    browser.url(devServer)
    this.notSetYetTests = function (browser) {
      browser.waitForElementVisible('#app', 5000)
      browser.waitForElementVisible('#header', 5000)
      browser.click('li:nth-child(3)')
      browser.waitForElementVisible('#income', 5000)
      browser.click('li:nth-child(4)')
      browser.waitForElementVisible('#budget', 5000)
    }
    this.homeTest = function (browser) {
      browser.click('li:nth-child(1)')
      browser.waitForElementVisible('#home', 5000)
    }

    this.notSetYetTests(browser)
    this.homeTest(browser)
    browser.end()
  }

}
