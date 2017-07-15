<template>
  <div id="header">
    <ul class="nav nav-pills">
      <router-link to="/" tag="li" active-class="active" exact><a>Home</a></router-link>
      <router-link to="/bills" tag="li" active-class="active"><a>Bills</a></router-link>
      <router-link to="/income" tag="li" active-class="active"><a>Income</a></router-link>
      <router-link to="/budget" tag="li" active-class="active"><a>Budget</a></router-link>
    </ul>
  </div>
</template>

<script>
    export default {}
</script>

<style></style>
