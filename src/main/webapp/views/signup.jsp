<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<div class="container d-flex justify-content-center align-items-center min-vh-100 border">
    <div class="col-md-6 mx-auto">
        <h3 class="text-center mb-4">Create Account</h3>
        <c:if test="${not empty alert}">
            <div class="alert alert-danger text-center" role="alert">
                ${alert}
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/signup" method="post">
            <div class="form-group mb-3">
                <label for="userid"><i class="fa fa-user"></i> User ID</label>
                <input type="text" class="form-control" id="userid" placeholder="Enter your User ID" name="userid" required>
            </div>
            <div class="form-group mb-3">
                <label for="email"><i class="fa fa-envelope"></i> Email</label>
                <input type="email" class="form-control" id="email" placeholder="Enter your Email" name="email" required oninput="validateEmail()">
                <span id="email-error" class="text-danger" style="display:none;">Please enter a valid email address</span>
            </div>
            <div class="form-group mb-3">
                <label for="name"><i class="fa fa-user"></i> Name</label>
                <input type="text" class="form-control" id="name" placeholder="Enter your Name" name="name" required>
            </div>
            <div class="form-group mb-3">
                <label for="dob"><i class="fa fa-calendar"></i> Date of Birth</label>
                <input type="date" class="form-control" id="dob" name="dob" required>
            </div>
            <div class="form-group mb-3">
                <label for="gender"><i class="fa fa-transgender"></i> Gender</label>
                <select class="form-control" id="gender" name="gender">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            <div class="form-group mb-3">
                <label for="phonenum"><i class="fa fa-phone"></i> Phone Number</label>
                <input type="text" class="form-control" id="phonenum" placeholder="Enter your Phone Number" name="phonenum" required>
            </div>
            <div class="form-group mb-3">
		    <label for="address"><i class="fa fa-map-marker"></i> Address</label>
		    <input type="text" class="form-control" id="address" placeholder="Enter your Address" name="address" required oninput="suggestAddress()">
		    <div id="address-suggestions" style="display:none; position:absolute; background-color:white; border:1px solid #ccc;"></div>
			</div>
			<div class="form-group mb-3">
				<label for="password"><i class="fa fa-lock"></i> Password</label>
				<input type="password" class="form-control" id="password" placeholder="Enter your Password" name="password">
			</div>
			<div class="form-group mb-3">
				<label for="c-password"><i class="fa fa-lock"></i> Confirm Password</label>
				<input type="password" class="form-control" id="c-password" placeholder="Confirm your Password" name="c-password">
			</div>
            <div class="form-group mb-3">
                <button type="submit" class="btn btn-primary btn-block w-100">Create account</button>
            </div>
            <div class="form-group mb-3">
                <p class="text-center">Already have an account? Let's <a href="${pageContext.request.contextPath}/login" id="log">Log in</a></p>
            </div>
        </form>
    </div>
</div>

<script>
    // Function to validate email
    function validateEmail() {
        var email = document.getElementById('email').value;
        var regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        var emailError = document.getElementById('email-error');
        if (regex.test(email)) {
            emailError.style.display = 'none';
        } else {
            emailError.style.display = 'block';
        }
    }

 // Thêm biến để kiểm tra thời gian gửi yêu cầu
    var lastRequestTime = 0;

    // Function to suggest address while typing
    function suggestAddress() {
        var address = document.getElementById('address').value;
        var currentTime = new Date().getTime();

        // Kiểm tra nếu địa chỉ dài hơn 3 ký tự và đảm bảo không gửi yêu cầu quá nhanh (mỗi 2 giây)
        if (address.length > 3 && (currentTime - lastRequestTime > 1500)) {
            lastRequestTime = currentTime;  // Cập nhật thời gian yêu cầu gửi đi

            // Tạo request GET đến Nominatim API với tham số countrycodes=VN (giới hạn chỉ trong Việt Nam)
            var xhr = new XMLHttpRequest();
            var url = 'https://nominatim.openstreetmap.org/search?format=json&q=' + address + '&addressdetails=1&limit=5&countrycodes=VN';
            xhr.open('GET', url, true);
            
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var results = JSON.parse(xhr.responseText);  // Parse dữ liệu JSON từ API
                    var suggestions = document.getElementById('address-suggestions');
                    suggestions.innerHTML = "";  // Clear các gợi ý trước đó

                    // Nếu có kết quả, tạo các gợi ý
                    if (results.length > 0) {
                        results.forEach(function(result) {
                            var suggestion = document.createElement('div');
                            suggestion.innerHTML = result.display_name; // Hiển thị tên địa chỉ
                            suggestion.onclick = function() {
                                document.getElementById('address').value = result.display_name;  // Gán giá trị cho input khi chọn
                                suggestions.style.display = 'none';  // Ẩn danh sách gợi ý
                            };
                            suggestions.appendChild(suggestion);
                        });
                        suggestions.style.display = 'block'; // Hiển thị danh sách gợi ý
                    } else {
                        suggestions.style.display = 'none'; // Ẩn nếu không có gợi ý
                    }
                }
            };
            xhr.send(); // Gửi request
        } else if (address.length <= 3) {
            // Ẩn gợi ý nếu địa chỉ quá ngắn
            document.getElementById('address-suggestions').style.display = 'none';
        }
    }



</script>
