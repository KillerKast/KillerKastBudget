/**
 * Created by Jonathan on 7/10/2017.
 */
import Vue from 'vue'
import Header from '@/components/Header.vue'

describe('Header.vue', () => {
  it('should render correct contents', () => {
    const Constructor = Vue.extend(Header)
    const vm = new Constructor().$mount()
    expect(vm.$el.querySelector('router-link').textContent)
      .to.equal('Home')
    expect(vm.$el.querySelector('router-link:nth-child(2)').textContent)
      .to.equal('Bills')
    expect(vm.$el.querySelector('router-link:nth-child(3)').textContent)
      .to.equal('Income')
    expect(vm.$el.querySelector('router-link:nth-child(4)').textContent)
      .to.equal('Budget')
  })
})
