function initRevenueCharts(monthlyOrderSummary, yearlyOrderSummary) {
    // Monthly Revenue Chart
    const months = monthlyOrderSummary.map(order => order.month);
    const totalCostsMonthly = monthlyOrderSummary.map(order => order.totalCost);

    const ctxMonthly = document.getElementById('monthlyRevenueChart').getContext('2d');
    const monthlyRevenueChart = new Chart(ctxMonthly, {
        type: 'line',
        data: {
            labels: months,
            datasets: [{
                label: 'Total Revenue (VND)',
                data: totalCostsMonthly,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    // Yearly Revenue Chart
    const years = yearlyOrderSummary.map(order => order.year);
    const totalCostsYearly = yearlyOrderSummary.map(order => order.totalCost);

    const ctxYearly = document.getElementById('yearlyRevenueChart').getContext('2d');
    const yearlyRevenueChart = new Chart(ctxYearly, {
        type: 'bar',
        data: {
            labels: years,
            datasets: [{
                label: 'Total Revenue (VND)',
                data: totalCostsYearly,
                borderColor: 'rgba(153, 102, 255, 1)',
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                fill: true,
                tension: 0.4
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}
