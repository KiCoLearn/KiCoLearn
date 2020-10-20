import Vue from 'vue';
import HelloWorld from './HelloWorld.vue';

test('HelloWorld Component', () => {
    const component = new Vue(HelloWorld).$mount();
    expect(component.message).toBe('Hello');
});
