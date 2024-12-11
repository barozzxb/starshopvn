/**
 * 
 */
/*
document.addEventListener("DOMContentLoaded", function() {
    // Lấy tất cả các mục sidebar và nội dung tương ứng
    const sections = {
        accountinf: document.getElementById("accountinf"),
        changepass: document.getElementById("changepass"),
        delete: document.getElementById("delete")
    };

    // Hàm để ẩn tất cả các phần
    function hideAllSections() {
        Object.values(sections).forEach(section => {
            section.style.display = "none";
        });
    }

    // Thêm sự kiện click cho từng mục sidebar
    document.querySelector(".nav-pills").addEventListener("click", function(event) {
        const targetId = event.target.getAttribute("href").substring(1); // Lấy ID từ href của link
        if (sections[targetId]) {
            hideAllSections();
            sections[targetId].style.display = "block"; // Hiển thị phần tương ứng
        }
        event.preventDefault(); // Ngăn chặn hành động mặc định của link
    });

    // Ẩn tất cả các phần và chỉ hiển thị phần đầu tiên khi tải trang
    hideAllSections();
    sections.accountinf.style.display = "block";
});


	$(document).ready(function() {
		// Khi nhấn nút "Finish" trong modal
		$('#validate form').on('submit', function(e) {
			e.preventDefault(); // Ngăn form tự động gửi đi

			// Lấy mã OTP từ modal
			var otpCode = $('input[name="otpcode"]').val();

			// Kiểm tra OTP trước khi submit
			if (otpCode === '') {
				alert('Vui lòng nhập mã OTP');
				return;
			}

			// Thêm mã OTP vào form chính trước khi submit
			var mainForm = $('#changepass form');
			mainForm.append('<input type="hidden" name="otpcode" value="' + otpCode + '">');

			// Submit form chính
			mainForm.submit();
		});
	});



	
	//Toogle modal edit
	$(document).ready(function() {
	    // Initially disable input fields
	    $('#e-gid, #e-gname, #e-gdescription').prop('readonly', true);
	    $('#saveButton').prop('disabled', true);
	});
	
	$('#editButton').click(function() {
	    // Toggle readonly state of input fields
	    $('#e-gname, #e-gdescription').prop('readonly', false);

	    // Toggle button states
	    $('#editButton').toggle();
	    $('#saveButton').toggle();
	});
	
	$('#saveButton').click(function() {
	    var form = $('#changepass form');
	});
	
	
	document.addEventListener("DOMContentLoaded", function () {
	    const existingTab = document.getElementById("tab_existing_address");
	    const newTab = document.getElementById("tab_new_address");
	    const radios = document.getElementsByName("address_choice");

	    radios.forEach(radio => {
	        radio.addEventListener("change", function () {
	            if (this.value === "existing") {
	                existingTab.classList.remove("d-none");
	                newTab.classList.add("d-none");
	            } else if (this.value === "new") {
	                newTab.classList.remove("d-none");
	                existingTab.classList.add("d-none");
	            }
	        });
	    });
	});

	
	document.addEventListener('DOMContentLoaded', function () {
	    const viewOrderButtons = document.querySelectorAll('.view-order');
	    
	    viewOrderButtons.forEach(button => {
	        button.addEventListener('click', function () {
	            const oid = this.getAttribute('data-oid');
	            
	            // Hiển thị trạng thái loading
	            document.getElementById('orderDetailsContent').innerHTML = '<p class="text-center">Đang tải dữ liệu...</p>';
	            
	            // Gửi AJAX request
	            fetch(`${pageContext.request.contextPath}/user/order/detail?oid=${oid}`)
	                .then(response => response.text())
	                .then(data => {
	                    // Đổ dữ liệu vào modal
	                    document.getElementById('orderDetailsContent').innerHTML = data;
	                })
	                .catch(error => {
	                    document.getElementById('orderDetailsContent').innerHTML = '<p class="text-danger text-center">Không thể tải dữ liệu.</p>';
	                    console.error('Error fetching order details:', error);
	                });
	        });
	    });
	});
*/

// Khi DOM đã được tải xong
document.addEventListener("DOMContentLoaded", function() {
    // Lấy tất cả các mục sidebar và nội dung tương ứng
    const sections = {
        accountinf: document.getElementById("accountinf"),
        changepass: document.getElementById("changepass"),
        delete: document.getElementById("delete")
    };

    // Hàm để ẩn tất cả các phần
    function hideAllSections() {
        Object.values(sections).forEach(section => {
            section.style.display = "none";
        });
    }

    // Thêm sự kiện click cho từng mục sidebar
    document.querySelector(".nav-pills").addEventListener("click", function(event) {
        const targetId = event.target.getAttribute("href").substring(1); // Lấy ID từ href của link
        if (sections[targetId]) {
            hideAllSections();
            sections[targetId].style.display = "block"; // Hiển thị phần tương ứng
        }
        event.preventDefault(); // Ngăn chặn hành động mặc định của link
    });

    // Ẩn tất cả các phần và chỉ hiển thị phần đầu tiên khi tải trang
    hideAllSections();
    sections.accountinf.style.display = "block";
});

// jQuery đoạn mã xử lý form
$(document).ready(function() {
    // Khi nhấn nút "Finish" trong modal
    $('#validate form').on('submit', function(e) {
        e.preventDefault(); // Ngăn form tự động gửi đi

        // Lấy mã OTP từ modal
        var otpCode = $('input[name="otpcode"]').val();

        // Kiểm tra OTP trước khi submit
        if (otpCode === '') {
            alert('Vui lòng nhập mã OTP');
            return;
        }

        // Thêm mã OTP vào form chính trước khi submit
        var mainForm = $('#changepass form');
        mainForm.append('<input type="hidden" name="otpcode" value="' + otpCode + '">');

        // Submit form chính
        mainForm.submit();
    });
    
    // Toggle modal edit
    $('#editButton').click(function() {
        // Toggle readonly state of input fields
        $('#e-gname, #e-gdescription').prop('readonly', false);

        // Toggle button states
        $('#editButton').toggle();
        $('#saveButton').toggle();
    });

    // Lưu thông tin khi nhấn Save
    $('#saveButton').click(function() {
        var form = $('#changepass form');
    });
});

// Xử lý toggle giữa các tab cho địa chỉ
document.addEventListener("DOMContentLoaded", function () {
    const existingTab = document.getElementById("tab_existing_address");
    const newTab = document.getElementById("tab_new_address");
    const radios = document.getElementsByName("address_choice");

    radios.forEach(radio => {
        radio.addEventListener("change", function () {
            if (this.value === "existing") {
                existingTab.classList.remove("d-none");
                newTab.classList.add("d-none");
            } else if (this.value === "new") {
                newTab.classList.remove("d-none");
                existingTab.classList.add("d-none");
            }
        });
    });
});

// Xử lý chi tiết đơn hàng
document.addEventListener('DOMContentLoaded', function () {
    const viewOrderButtons = document.querySelectorAll('.view-order');
    
    viewOrderButtons.forEach(button => {
        button.addEventListener('click', function () {
            const oid = this.getAttribute('data-oid');
            
            // Hiển thị trạng thái loading
            document.getElementById('orderDetailsContent').innerHTML = '<p class="text-center">Đang tải dữ liệu...</p>';
            
            // Gửi AJAX request
            fetch(`${pageContext.request.contextPath}/user/order/detail?oid=${oid}`)
                .then(response => response.text())
                .then(data => {
                    // Đổ dữ liệu vào modal
                    document.getElementById('orderDetailsContent').innerHTML = data;
                })
                .catch(error => {
                    document.getElementById('orderDetailsContent').innerHTML = '<p class="text-danger text-center">Không thể tải dữ liệu.</p>';
                    console.error('Error fetching order details:', error);
                });
        });
    });
});

	