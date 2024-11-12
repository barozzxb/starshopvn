/**
 * 
 */
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
	
	
	