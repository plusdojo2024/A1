<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/t-board.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header>
        <div class="headbtn">
            <button class="markerbtn">先生用</button>
            <button id="modalOpen" class="markerbtn">マーカー</button>
            <button class="markerbtn">
                <span class=center></span>ファイル添付
            </button>
        </div>
        <div id="easyModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="modalClose">×</span>
                </div>
                <div class="modal-body">
                    <div class="contents" id="contents">
                        <!-- ここにテキストエリアの内容が表示されます -->
                    </div>
                    <form id="markerForm">
                        <div id="markerContentsContainer">
                            <textarea name="markerContents" id="markerContents" rows="4" cols="50"></textarea>
                        </div>
                        <button type="button" id="submitBtn" class="btn btn-primary mt-3">送信</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="boardcomit">
            <p class="center">板書切り替え</p>
        </div>
        <a href="/A1/RecordServlet">
            <div class="pagechange">
                <p class="center">履歴</p>
            </div>
        </a>
    </header>
    <div class="board">
        <textarea name="text" class="text" id="textarea"></textarea>
    </div>
    <div class="com">
        <ul class="markcomlive">
            <li class="markcombox">
                <div class="marklivecom1">
                    <p class="marktext">こんにちは</p>
                    <p class=markre>♡</p>
                </div>
            </li>
            <li class="markcombox">
                <div class="marklivecom2">
                    <p class="marktext">おはよう</p>
                    <p class=markre>♡</p>
                </div>
            </li>
            <li class="markcombox">
                <div class="marklivecom3">
                    <p class="marktext">さよなら</p>
                    <p class=markre>♡</p>
                </div>
            </li>
        </ul>
    </div>
    <div class="allcom">
        <div class="allcomtext" id="allcomdiv">
            <!-- ここにコメントを表示 -->
        </div>
        <div class="allcomform">
            <input type="text" name="allComContents" class="allcomsend" id="allcom">
            <input type="button" name="submit" value="送信" class="allcombtn" onclick="goAjax()">
        </div>
    </div>
    <div class="marker" id="marker">
        <div class="markercontents">
            <ul id="markerList"></ul>
            <div>
                <canvas id="myChart"></canvas>
            </div>
        </div>
    </div>

    <div class="newDiv" id="newDiv">
        <span class="closeBtn">×</span>
        <p id="newDivText"></p>
        <div>
            <canvas id="myChart"></canvas>
        </div>
    </div>
    <div class="comform">
        <!-- <input type="text" name="sendcomtext" class="comsend">
        <input type="submit" id="search" name="submit" value="送信" class="combtn"> -->
    </div>

    <script>
        // 使用するidの取得
        const buttonOpen = document.getElementById('modalOpen');
        const modal = document.getElementById('easyModal');
        const buttonClose = document.getElementsByClassName('modalClose')[0];
        const textarea = document.getElementById('textarea');
        const contents = document.getElementById('contents');
        const newDiv = document.getElementById('newDiv');
        const newDivText = document.getElementById('newDivText');
        const closeBtn = document.querySelector('.newDiv .closeBtn');
        const submitBtn = document.getElementById('submitBtn');
        const markerList = document.getElementById('markerList');

        // ボタンがクリックされた時
        buttonOpen.addEventListener('click', modalOpen);
        function modalOpen() {
            const text = textarea.value.replace(/\n/g, '<br>');
            contents.innerHTML = text;
            modal.style.display = 'block';
        }

        // バツ印がクリックされた時
        buttonClose.addEventListener('click', modalClose);
        function modalClose() {
            modal.style.display = 'none';
        }

        // モーダルコンテンツ以外がクリックされた時
        addEventListener('click', outsideClose);
        function outsideClose(e) {
            if (e.target == modal) {
                modal.style.display = 'none';
            }
        }

        // テキストを選択してマーカーを引く機能
        contents.addEventListener('mouseup', function(event) {
            const selection = window.getSelection();
            if (!selection.isCollapsed) {
                const range = selection.getRangeAt(0);
                const selectedText = selection.toString();

                const span = document.createElement('span');
                span.classList.add('highlight');
                range.surroundContents(span);

                const markerContents = document.getElementById('markerContents');
                markerContents.value = selectedText;
            }
        });

        submitBtn.addEventListener('click', function() {
            const markerContents = document.getElementById('markerContents').value;
            $.ajax({
                url: '/A1/MarkServlet',
                type: 'POST',
                data: { markerContents: markerContents },
                success: function(response) {
                    if (response.status === 'success') {
                        fetchMarkers();
                        modalClose();
                    } else {
                        alert('Error saving marker');
                    }
                },
                error: function() {
                    alert('Error saving marker');
                }
            });
        });

        function fetchMarkers() {
            $.ajax({
                url: '/A1/MainServlet',
                type: 'GET',
                data: { markerData: true },
                dataType: 'json',
                success: function(data) {
                    markerList.innerHTML = '';
                    data.forEach(function(marker) {
                        const li = document.createElement('li');
                        const a = document.createElement('a');
                        a.textContent = marker.markerContents;
                        a.href = "#";
                        a.addEventListener('click', function(event) {
                            event.preventDefault();
                            newDivText.textContent = marker.markerContents;
                            newDiv.classList.add('big');
                            newDiv.classList.remove('close');
                        });
                        li.appendChild(a);
                        markerList.appendChild(li);
                    });
                },
                error: function() {
                    console.error('Error fetching markers');
                }
            });
        }

        closeBtn.addEventListener('click', function() {
            newDiv.classList.remove('big');
            newDiv.classList.add('close');
        });

        newDiv.addEventListener('animationend', function() {
            if (newDiv.classList.contains('close')) {
                newDiv.classList.remove('close');
                newDiv.style.zIndex = -1;
            }
        });

        $(document).ready(function() {
            fetchMarkers();
            setInterval(fetchMarkers, 1000); // 1秒ごとにデータを取得
        });
    </script>
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
<script>
    function goAjax() {
        let allcom = document.getElementById('allcom').value;
        let postData = { data1: allcom };

        $.ajax({
            url: '/A1/AllComServlet',
            type: 'POST',
            dataType: 'json',
            data: postData,
            success: function(data) {
                document.getElementById("test").innerText = data.message;
            },
            error: function() {
                alert("データの追加に失敗しました。");
            }
        });
    }

    // 1秒ごとに非同期でデータを取得し、画面を更新する
    function fetchComments() {
        $.ajax({
            url: '/A1/MainServlet',
            type: 'GET',
            dataType: 'json',
            data: { data1: 0 },
            success: function(data) {
                console.log(data); // コンソールにデータを表示
                let allcomdiv = document.getElementById('allcomdiv');
                allcomdiv.innerHTML = ''; // 既存の内容をクリア
                //data.reverse().
                data.forEach(function(comment) { // 最新のコメントが上に来るように逆順にする
                    let p = document.createElement('p');
                    p.textContent = comment.allComContents + ' ♡';
                    allcomdiv.appendChild(p);
                    allcomdiv.appendChild(document.createElement('hr'));
                });
            },
            error: function() {
                console.error('データの取得に失敗しました。');
            }
        });
    }

    // ページロード時に初回のデータ取得を行う
    $(document).ready(function() {
        fetchComments();
        setInterval(fetchComments, 1000); // 1秒ごとにデータを取得
    });
</script>
</body>
</html>