<script setup>
import {ref, onMounted, onBeforeUnmount } from 'vue'
import * as monaco from 'monaco-editor'
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

//allows us to create a reactive reference to const
const editorContainer = ref(null);

onMounted(() => {
    //Initialize monaco editor
    editor = monaco.editor.create(editorContainer.value, {
        value: "//start coding here",
        language: "javascript",
        theme: "vs-dark"
    })
    
    //connect to spring boot
    const socket = new SockJS('');
    stompClient = Stomp.over(socket);
    
    stompClient.connect({}, () => {
        stompClient.subscribe('/topic/receiveCode', (message) => {
            editor.setValue(message.body)
        });
    });

    editor.onDidChangeModelContent(() => {
        const code = editor.getValue();
        sendCodeToBackend(code);
    })


});

onBeforeUnmount(() => {
    if(editor) {
        editor.dispose();
    }

    if(stompClient) {
        stompClient.disconnect();
    }
});

//function to send code to backend
const sendCodeToBackend = (code) => {
    stompClient.send('/app/sendCode', {}, code);
}

</script>

<template>
<div class='code-editor' ref="editorContainer"></div>
</template>

<style scoped>
.code-editor {
    height: 50rem;
    width: 50rem;
    border: 3px;
}


</style>
