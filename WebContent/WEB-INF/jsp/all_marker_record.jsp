<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/all-marker-record.css">
</head>
<body>
    <a href="/A1/MainServlet">
        <div class="boardpage" href= >板書</div>
    </a>
    <div class="tab">
        <a href="/A1/RecordServlet">
        <div class="tab1">
            板書履歴
        </div>
        </a>
        <a href="/A1/MyMarkLevelServlet">
        <div class="tab2">
            理解度履歴
        </div>
        </a>
    </div>
<div class="markchart">
    <canvas id="myChart"></canvas>
</div>
</body>
</html>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
const ctx = document.getElementById('myChart').getContext('2d');

// 各カテゴリのデータ
const data = [30, 3, 3, 5,];

// データの合計を計算
const total = data.reduce((sum, value) => sum + value, 0);

// 各データの割合を計算
const percentages = data.map(value => (value / total) * 100);

new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Total'],  // 横棒グラフのラベルは「Total」のみ
        datasets: [
            {
                label: 'とてもよくわかる',
                data: [percentages[0]],
                backgroundColor: 'rgba(255, 99, 132, 0.5)',
                borderWidth: 1
            },
            {
                label: 'わかる',
                data: [percentages[1]],
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderWidth: 1
            },
            {
                label: 'わからない',
                data: [percentages[2]],
                backgroundColor: 'rgba(255, 206, 86, 0.5)',
                borderWidth: 1
            },
            {
                label: 'とてもわからない',
                data: [percentages[3]],
                backgroundColor: 'rgba(75, 192, 192, 0.5)',
                borderWidth: 1
            },
            
        ]
    },
    options: {
        indexAxis: 'y',  // 横棒グラフに設定
        scales: {
            x: {
                stacked: true,
                ticks: {
                    beginAtZero: true,
                    callback: function(value) {
                        return value + "%";
                    }
                }
            },
            y: {
                stacked: true
            }
        },
        plugins: {
            legend: {
                display: false // 凡例を非表示にする
            },
            tooltip: {
                callbacks: {
                    label: function(tooltipItem) {
                        return tooltipItem.dataset.label + ': ' + tooltipItem.raw.toFixed(2) + '%';
                    }
                }
            }
        },
        maintainAspectRatio: false, // グラフの横幅を調整するための設定
        responsive: true // グラフのレスポンシブ対応
    }
});
</script>