<script>
export default {
  page: {
    // All subcomponent titles will be injected into this template.
    titleTemplate(title) {
      title = typeof title === 'function' ? title(this.$store) : title
      return title ? `${title} | DATN` : 'DATN'
    },
  },
}
</script>

<style>
@import '/assets/plugins/bootstrap/css/bootstrap.min.css';
@import '/assets/css/style.css';
@import '/assets/css/transparent-style.css';
@import '/assets/css/skin-modes.css';
@import '/assets/css/custom.css';
@import '/assets/css/bootstrap-custom.css';
@import '/assets/css/icons.css';
@import '/assets/colors/color1.css';
@import '/assets/css/nprogress.css';
@import '/assets/css/colors.css';
@import '/assets/css/roboto.css';
</style>

<template>
  <div id="app">
    <!--
    Even when routes use the same component, treat them
    as distinct and create the component again.
    -->
    <RouterView :key="$route.fullPath" />
  </div>
</template>

<!-- This should generally be the only global CSS in the app. -->
