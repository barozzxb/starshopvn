<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- Nút Chat -->
<button id="chat-button">💬</button>

<!-- Giao diện Chat -->
<div id="chat-container" style="display: none;">
    <!-- Danh sách người dùng -->
    <div id="user-list">
        <h3>Danh sách người dùng</h3>
        <ul id="users"></ul>
    </div>
    <!-- Cửa sổ chat -->
    <div id="chat-window">
        <div id="chat-header">Tin nhắn</div>
        <div id="chat-messages"></div>
        <div id="chat-input">
            <textarea id="message-input" placeholder="Nhập tin nhắn..."></textarea>
            <button id="send-button">Gửi</button>
        </div>
    </div>
</div>


<style>
/* Nút Chat */
#chat-button {
    position: fixed;
    bottom: 20px;
    right: 20px;
    width: 60px;
    height: 60px;
    border: none;
    border-radius: 50%;
    background-color: #4CAF50;
    color: white;
    font-size: 24px;
    cursor: pointer;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

/* Giao diện Chat Container */
#chat-container {
    position: fixed;
    bottom: 100px;
    right: 20px;
    width: 350px;
    max-height: 500px;
    background-color: #3C5950;
    color: white;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
}

/* Danh sách người dùng */
#user-list {
    background-color: #2A4F41;
    padding: 10px;
    overflow-y: auto;
}

#user-list ul {
    list-style: none;
    padding: 0;
    margin: 0;
}

#user-list li {
    padding: 10px;
    border-bottom: 1px solid #ccc;
    cursor: pointer;
    position: relative;
}

#user-list li:hover {
    background-color: #3C5950;
}

#user-list li span {
    display: block;
    font-size: 12px;
    color: #ccc;
    margin-top: 5px;
}

/* Giao diện Chat Window */
#chat-window {
    display: none;
    flex: 1;
    flex-direction: column;
    background-color: #3C5950;
}

#chat-header {
    padding: 10px;
    background-color: #2A4F41;
    font-size: 16px;
    text-align: center;
}

#chat-messages {
    flex: 1;
    padding: 10px;
    overflow-y: auto;
    background-color: #FFFFFF;
    color: #000;
}

#chat-input {
    display: flex;
    padding: 10px;
    background-color: #2A4F41;
}

#chat-input textarea {
    flex: 1;
    padding: 10px;
    border: none;
    border-radius: 5px;
    resize: none;
}

#chat-input button {
    margin-left: 10px;
    padding: 10px 20px;
    border: none;
    background-color: #4CAF50;
    color: white;
    border-radius: 5px;
    cursor: pointer;
}

</style>

<script>
const chatButton = document.getElementById("chat-button");
const chatContainer = document.getElementById("chat-container");
const chatWindow = document.getElementById("chat-window");
let selectedUser = null;

// Hiển thị/Ẩn khung chat
chatButton.addEventListener("click", () => {
    if (chatContainer.style.display === "none") {
        chatContainer.style.display = "flex";
    } else {
        chatContainer.style.display = "none";
    }
});

// Hiển thị danh sách người dùng
function fetchUsers() {
    fetch("/api/online-users")
        .then((response) => response.json())
        .then((users) => {
            const userList = document.getElementById("users");
            userList.innerHTML = "";
            users.forEach((user) => {
                const userItem = document.createElement("li");
                userItem.textContent = user.name;
                userItem.dataset.userid = user.userid;

                // Hiển thị tin nhắn mới nhất
                const latestMessage = document.createElement("span");
                latestMessage.textContent = user.latestMessage || "Không có tin nhắn.";
                userItem.appendChild(latestMessage);

                userItem.addEventListener("click", () => {
                    selectedUser = user.userid;
                    highlightUser(userItem);
                    openChatWindow(user.name);
                });
                userList.appendChild(userItem);
            });
        });
}

function highlightUser(selectedItem) {
    document.querySelectorAll("#users li").forEach((item) => item.classList.remove("active"));
    selectedItem.classList.add("active");
}

function openChatWindow(userName) {
    chatWindow.style.display = "flex";
    const chatHeader = document.getElementById("chat-header");
    chatHeader.textContent = `Tin nhắn với ${userName}`;
    document.getElementById("chat-messages").innerHTML = ""; // Reset nội dung chat
}

// Tự động tải danh sách user mỗi 5 giây
setInterval(fetchUsers, 5000);

// Gửi tin nhắn
document.getElementById("send-button").addEventListener("click", () => {
    const messageInput = document.getElementById("message-input");
    const message = messageInput.value.trim();

    if (message && selectedUser) {
        socket.send(JSON.stringify({ to: selectedUser, message }));
        appendMessage(`Bạn: ${message}`);
        messageInput.value = "";
    } else {
        alert("Vui lòng chọn người dùng trước khi gửi tin nhắn!");
    }
});

function appendMessage(content) {
    const messages = document.getElementById("chat-messages");
    const messageDiv = document.createElement("div");
    messageDiv.textContent = content;
    messages.appendChild(messageDiv);
    messages.scrollTop = messages.scrollHeight;
}

</script>

