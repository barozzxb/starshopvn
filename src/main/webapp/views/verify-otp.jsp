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
    // Ensure the #timer element is accessible
    const timerElement = document.getElementById("timer");

    // Make sure the timer element exists before updating it
    if (timerElement) {
        // Initial update to show the timer starting value
        timerElement.textContent = `Resend OTP in ${timeLeft} seconds`;

        // Clear any existing timer (if any) before starting a new one
        if (timer) {
            clearInterval(timer);
        }

        // Start the countdown
        timer = setInterval(function() {
            timeLeft--;  // Decrease time by 1 second
            timerElement.textContent = `Resend OTP in ${timeLeft} seconds`;  // Update the timer text content

            // If the time is up, stop the countdown and enable the button
            if (timeLeft <= 0) {
                clearInterval(timer);  // Stop the timer
                timerElement.textContent = "You can resend OTP now";  // Show message after countdown ends
                document.getElementById("sendOtp").disabled = false;  // Enable "Send OTP" button
            }
        }, 1000);  // Run every 1000ms (1 second)
    } else {
        console.error('Timer element not found');
    }
}

// Event listener for the "Send OTP" button
document.getElementById("sendOtp").addEventListener("click", function() {
    // Disable button and start the timer
    this.disabled = true;
    timeLeft = 60;  // Reset the countdown timer to 60 seconds each time
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