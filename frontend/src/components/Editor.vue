    <script setup>
    import {ref, onMounted, onBeforeUnmount } from 'vue'
    import SockJS from 'sockjs-client';
    import Stomp from 'stompjs';

    //allows us to create a reactive reference to const
    const codeContent = ref('');
    let stompClient;

    onMounted(() => {

    //connect to spring boot
        const socket = new SockJS('http://localhost:8081/ws');
        stompClient = Stomp.over(socket);
        
        stompClient.connect({}, () => {
            stompClient.subscribe('/topic/receiveCode', (message) => {
                codeContent.value = message.body
            });
        });

    watch(codeContent, (newCode) => {
        sendCodeToBackend(newCode);
    })

    });

    //function to send code to backend
    const sendCodeToBackend = (code) => {
        if (stompClient && stompClient.connected) {
         stompClient.send('/app/sendCode', {}, code);
        }
    }

    </script>

    <template>
    <textarea class="txtarea" id="Text" value="" v-model="codeContext" placeholder="Enter some code..."> </textarea>
    </template>

    <style scoped>
    .txtarea {
        height: 47rem;
        width: 50rem;
        border: 3px;
    }

    </style>
