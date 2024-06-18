<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="css/t-board.css">
    
    </head>
    <body>
        <header>
            <div class="headbtn">
                <button class="markerbtn">コメント</button>
                <button id="modalOpen" class="markerbtn">マーカー</button>
                <button class="markerbtn"><span class=center></span>ファイル添付</button>
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
                        <form action="YourServlet" method="POST" id="enqueteForm">
                            <div class="enquete" id="enquete"></div>
                            <button type="submit" id="submitBtn" class="btn btn-primary mt-3">送信</button>
                        </form>
                    </div>
                </div>
            </div>
    
            <div class="boardcomit"><p class="center">板書切り替え</p></div>
            <a href="/A1/RecordServlet">
            <div class="pagechange"><p class="center">履歴</p></div>
        </a>
        </header>
        <div class="board">
            <textarea name="text" class="text" id="textarea"></textarea>
        </div>
        <div class="com">
            <ul class="markcomlive">
                <li class="markcombox">
                    <div class="marklivecom1"><p class="marktext">こんにちは</p><p class=markre>♡</p></div>
                </li>
                <li class="markcombox">
                    <div class="marklivecom2"><p class="marktext">おはよう</p><p class=markre>♡</p></div>
                </li>
                <li class="markcombox">
                    <div class="marklivecom3"><p class="marktext">さよなら</p><p class=markre>♡</p></div>
                </li>
            </ul>
        </div>
        <div class="allcom">
            <div class="allcomtext">
                <p>こんにちは ♡</p>
                <p>こんにちは</p>
                <p>こんにちは</p>
            </div>
            <div class="allcomform">
                <input type="text" name="allcomtext" class="allcomsend">
                <input type="submit" id="search" name="submit" value="送信" class="allcombtn">
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
            <div class="markerlevel"></div>
        <div class="comform">
            <!--<input type="text" name="sendcomtext" class="comsend">
            <input type="submit" id="search" name="submit" value="送信" class="combtn"> -->
        </div>
    </div>
    </body>
    </html>
    
    <script>
    // 使用するidの取得
    // modalを開くためのボタン
    const buttonOpen = document.getElementById('modalOpen');
    // modalを開いたときのdiv
    const modal = document.getElementById('easyModal');
    // modalを閉じるためのバツ
    const buttonClose = document.getElementsByClassName('modalClose')[0];
    // テキストを入力するためのエリア
    const textarea = document.getElementById('textarea');
    // modalが開いたときに入力されている内容
    const contents = document.getElementById('contents');
    // マーカーで指定された単語をクリックしたときに出てくるdiv(右下)
    const newDiv = document.getElementById('newDiv');
    // マーカーで指定された単語をクリックしたときに出てくるdivに表示されるマーカーで指定された単語
    const newDivText = document.getElementById('newDivText');
    // マーカーで指定された単語をクリックしたときに出てくるdivを閉じるためのバツ
    const closeBtn = document.querySelector('.newDiv .closeBtn');
    
    // ボタンがクリックされた時
    buttonOpen.addEventListener('click', modalOpen);
    function modalOpen() {
        // テキストエリアの内容を取得して改行を<br>タグに変換
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
    
    document.addEventListener('DOMContentLoaded', function() {
        var enquete = document.getElementById('enquete');
    
        contents.addEventListener('mouseup', function(event) {
            // テキストの選択範囲を取得
            var selection = window.getSelection();
            if (!selection.isCollapsed) {
                var range = selection.getRangeAt(0);
                var selectedText = selection.toString();
    
                // 選択された範囲を包むspan要素を作成
                var span = document.createElement('span');
                span.classList.add('highlight');
                range.surroundContents(span);
                // コメントを表示する要素を作成
                var comment = document.createElement('div');
                comment.classList.add('comment');
                comment.textContent = 'あいうえお'; // 初期コメント
    
                span.appendChild(comment);
    
                // 選択されたテキストをチェックボックスとしてenqueteに追加
                var checkboxWrapper = document.createElement('div');
                var checkbox = document.createElement('input');
                checkbox.type = 'checkbox';
                checkbox.name = 'selectedTexts';
                checkbox.value = selectedText;
                checkbox.checked = true; // チェックボックスを最初からチェックした状態にする
                checkboxWrapper.appendChild(checkbox);
    
                var label = document.createElement('label');
                label.textContent = selectedText;
                checkboxWrapper.appendChild(label);
    
                // 削除ボタンを追加
                var deleteButton = document.createElement('button');
                deleteButton.textContent = '削除';
                deleteButton.classList.add('btn', 'btn-danger', 'btn-sm', 'ml-2');
                deleteButton.addEventListener('click', function() {
                    // チェックボックスの親要素を削除
                    checkboxWrapper.remove();
                    // テキストの背景色を元に戻す
                    span.outerHTML = span.innerHTML;
                });
                checkboxWrapper.appendChild(deleteButton);
    
                enquete.appendChild(checkboxWrapper);
    
                // 選択を解除
                selection.removeAllRanges();
            }
        });
    
        // 送信ボタンが押されたときの処理
        var submitBtn = document.getElementById('submitBtn');
        submitBtn.addEventListener('click', function(event) {
            event.preventDefault(); // フォームのデフォルトの送信を防ぐ
            var markerList = document.getElementById('markerList');
            var checkboxes = enquete.querySelectorAll('input[type="checkbox"]:checked');
            checkboxes.forEach(function(checkbox) {
                var li = document.createElement('li');
                var a = document.createElement('a');
                a.textContent =checkbox.value;
                a.href = "#";
                a.addEventListener('click', function(event) {
                    event.preventDefault();
                    newDivText.textContent = checkbox.value;
                    var markerDiv = document.getElementById('marker');
                    markerDiv.classList.add('small'); // markerのサイズを半分にする
                    newDiv.classList.add('big'); // newDivを表示する
                    newDiv.classList.remove('close');
                });
                li.appendChild(a);
                markerList.appendChild(li);
            });
            enquete.innerHTML = ''; // フォームをクリア
        });
    
        // newDivのバツボタンが押されたときの処理
        closeBtn.addEventListener('click', function() {
            newDiv.classList.remove('big'); // bigクラスを削除
            newDiv.classList.add('close'); // closeアニメーションを追加
            var markerDiv = document.getElementById('marker');
            markerDiv.classList.remove('small'); // markerのサイズを元に戻す
        });
    
        // アニメーション終了時にnewDivを非表示にする処理を追加
        newDiv.addEventListener('animationend', function() {
            if (newDiv.classList.contains('close')) {
                newDiv.classList.remove('close');
                newDiv.style.zIndex = -1;
            }
        });
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