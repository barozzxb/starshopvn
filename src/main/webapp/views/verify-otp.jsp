<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<div class="container d-flex justify-content-center align-items-center min-vh-100 border">
    <div class="col-md-6 mx-auto">
        <h3 class="text-center mb-4">Verify OTP</h3>
        <c:if test="${not empty alert}">
            <div class="alert alert-danger text-center" role="alert">
                ${alert}
            </div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/verify-otp" method="post">
            <div class="form-group mb-3">
                <label for="otp"><i class="fa fa-key"></i> Enter OTP</label>
                <input type="text" class="form-control" id="otp" name="otp" required>
            </div>
            <div class="form-group mb-3 text-center">
                <button type="button" id="sendOtp" class="btn btn-primary w-100">Send OTP</button>
                <span id="timer" class="d-block mt-2"></span>
            </div>
            <div class="form-group mb-3">
                <button type="submit" class="btn btn-success w-100">Verify OTP</button>
            </div>
        </form>
    </div>
</div>

<script>
    let timer; 
    let timeLeft = 60;  // Time for OTP (60 seconds countdown)

    // Function to start the timer and update the countdown
    function startTimer() {
        timer = setInterval(function() {
            document.getElementById("timer").textContent = `Resend OTP in ${timeLeft} seconds`;
            timeLeft--;
            if (timeLeft < 0) {
                clearInterval(timer);
                document.getElementById("timer").textContent = "You can resend OTP now";
                document.getElementById("sendOtp").disabled = false;
            }
        }, 1000);
    }

    // Event listener for the "Send OTP" button
    document.getElementById("sendOtp").addEventListener("click", function() {
        // Disable button and start the timer
        this.disabled = true;
        startTimer();

        // Send OTP to the user (AJAX request)
        fetch("${pageContext.request.contextPath}/send-otp", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email: '${sessionScope.tempAccount.email}' })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("OTP sent successfully!");
            } else {
                alert("Error sending OTP.");
            }
        });
    });
</script>