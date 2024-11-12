    <script setup>
    import {ref, onMounted, onBeforeUnmount } from 'vue'
    import SockJS from 'sockjs-client';
    import Stomp from 'stompjs';

    const codeContext = ref('');
    let stompClient;

    onMounted(() => {
      const socket = new SockJS('http://localhost:8082/websocket');
      stompClient = Stomp.over(socket);

      stompClient.connect({}, () => {
        //whenever a message is send to the below, all subscribes will get the message
        stompClient.subscribe('/topic/editor', (message) => {
          codeContext.value = message.body;
        });
      });
    });

    const sendUpdate = () => {
      if(stompClient && stompClient.connected) {
        stompClient.send('/app/edit', {}, codeContext.value);
      }
    };

    const handleInput = (event) => {
      codeContext.value = event.target.value;
      sendUpdate();
    }

    </script>

    <template>
    <textarea class="txtarea" id="text" v-model="codeContext" @input="handleInput" placeholder="Enter some code..."> </textarea>
    </template>

    <style scoped>
    .txtarea {
        height: 47rem;
        width: 50rem;
        border: 3px;
    }

    </style>
