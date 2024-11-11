<script setup>

function generateLink() {
    
    const uniqueId = Math.random().toString(36).substr(2,9);
    const editorUrl = `${window.location.origin}/${uniqueId}`;
    window.open(editorUrl, '_blank');
}

</script>

<template>
<div class="main-body">
  <h1 class="welcome-text">Welcome to online Code Editor </h1>
  <h3 class="new-session-text">Start a new session </h3>
</div>

<div class="main-body__generate-link-section">
  <button class="generate-link-text" @click="generateLink">Generate Link </button> 
</div>
</template>

<style scoped>
.main-body {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 50%;
}

.main-body__generate-link-section {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 20%;
}

.generate-link-text {
    width: 10rem;
}
.welcome-text {
    margin-bottom: 1rem;
    font-size: 2rem;
}

.new-session-text {
    margin-bottom: 1rem;
    font-size: 1.5rem;
    color: #555;
}
</style>
