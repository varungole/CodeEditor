<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

const codeContext = ref('');
let stompClient;

onMounted(() => {
  const url = new URL(window.location.href);
  const uniqueID = url.pathname.split('/').pop(); // Extract `uniqueID` dynamically based on URL structure
  const socket = new SockJS('http://localhost:8082/websocket');
  stompClient = Stomp.over(socket);

  stompClient.connect({}, () => {
    // Subscribe to a unique topic based on the uniqueID
    stompClient.subscribe(`/topic/editor/${uniqueID}`, (message) => {
      codeContext.value = message.body;
    });
  });
});

const sendUpdate = () => {
  if (stompClient && stompClient.connected) {
    const url = new URL(window.location.href);
    const uniqueID = url.pathname.split('/').pop();
    stompClient.send(`/app/edit/${uniqueID}`, {}, codeContext.value);
  }
};

const handleInput = (event) => {
  codeContext.value = event.target.value;
  sendUpdate();
};
</script>

<template>
  <textarea
    class="txtarea"
    id="text"
    v-model="codeContext"
    @input="handleInput"
    placeholder="Enter some code..."
  ></textarea>
</template>

<style scoped>
.txtarea {
  height: 47rem;
  width: 50rem;
  border: 3px solid black;
}
</style>
