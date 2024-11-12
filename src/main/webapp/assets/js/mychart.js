/**
 * 
 */
// Lấy phần tử canvas của biểu đồ
const ctx = document.getElementById('revenueChart').getContext('2d');

// Cấu hình dữ liệu và thiết lập cho biểu đồ đường doanh thu
const revenueChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6'], // Trục X - các tháng
        datasets: [{
            label: 'Doanh thu (VNĐ)',
            data: [5000000, 8000000, 7000000, 9000000, 12000000, 15000000], // Dữ liệu doanh thu cho các tháng
            borderColor: 'rgba(75, 192, 192, 1)',
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            tension: 0.4,
            fill: true
        }]
    },
    options: {
        responsive: true,
        scales: {
            x: {
                title: {
                    display: true,
                    text: 'Tháng'
                }
            },
            y: {
                title: {
                    display: true,
                    text: 'Doanh thu (VNĐ)'
                },
                beginAtZero: true
            }
        },
        plugins: {
            legend: {
                display: true,
                position: 'top'
            },
            tooltip: {
                enabled: true
            }
        }
    }
});