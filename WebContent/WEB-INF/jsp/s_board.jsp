<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/s-board.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <header>
        <div class="headbtn">
            <button class="markerbtn">生徒用</button>
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
    <div class="board" id="boardContentsDiv">
        <textarea name="text" class="text" id="textarea" readonly></textarea>
    </div>
    <div class="com">
        <div class="markcombox">
            <ul class="markcomlive" id="markcomlive">
                <!-- ここにコメントを表示 -->
            </ul>
        </div>
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
        </div>
    </div>

    <div class="newDiv" id="newDiv">
        <span class="closeBtn">×</span>
        <p id="newDivText"></p>
        <p id="markerIdText"></p>
        <div class="chart">
            <canvas id="myChart" class="myChart"></canvas>
        </div>
        <button type="button" class="reactionBtn" data-reaction="vg">よくわかる</button>
        <button type="button" class="reactionBtn" data-reaction="g">わかる</button>
        <button type="button" class="reactionBtn" data-reaction="b">わからない</button>
        <button type="button" class="reactionBtn" data-reaction="vb">よくわからない</button>
        <div class="newdivcom">
            <div id="commentsContainer"></div>
        </div>
        <div class="newDivform">
            <textarea id="newCommentText" rows="4" cols="50"></textarea>
            <button id="sendCommentBtn">送信</button>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const buttonOpen = document.getElementById('modalOpen');
            const modal = document.getElementById('easyModal');
            const buttonClose = document.getElementsByClassName('modalClose')[0];
            const textarea = document.getElementById('textarea');
            const contents = document.getElementById('contents');
            const newDiv = document.getElementById('newDiv');
            const newDivText = document.getElementById('newDivText');
            const markerIdText = document.getElementById('markerIdText');
            const closeBtn = document.querySelector('.newDiv .closeBtn');
            const submitBtn = document.getElementById('submitBtn');
            const sendCommentBtn = document.getElementById('sendCommentBtn');
            const newCommentText = document.getElementById('newCommentText');
            const markerList = document.getElementById('markerList');
            const commentsContainer = document.getElementById('commentsContainer');
            const markcomlive = document.getElementById('markcomlive');
            const allcomdiv = document.getElementById('allcomdiv');
            const boardContentsDiv = document.getElementById('boardContentsDiv');
            const reactionButtons = document.querySelectorAll('.reactionBtn');

            let currentMarkerId = null;
            let chart = null;

            buttonOpen.addEventListener('click', modalOpen);
            function modalOpen() {
                const text = textarea.value.replace(/\n/g, '<br>');
                contents.innerHTML = text;
                modal.style.display = 'block';
            }

            buttonClose.addEventListener('click', modalClose);
            function modalClose() {
                modal.style.display = 'none';
            }

            addEventListener('click', outsideClose);
            function outsideClose(e) {
                if (e.target == modal) {
                    modal.style.display = 'none';
                }
            }

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
                    data: { latestMarkers: true },
                    dataType: 'json',
                    success: function(data) {
                        markerList.innerHTML = '';
                        data.forEach(function(marker) {
                            const li = document.createElement('li');
                            const a = document.createElement('a');
                            a.textContent = marker.markerContents;
                            a.href = "#";
                            a.dataset.markerId = marker.markerId;
                            a.addEventListener('click', function(event) {
                                event.preventDefault();
                                newDivText.textContent = marker.markerContents;
                                markerIdText.textContent = marker.markerId;
                                currentMarkerId = marker.markerId;
                                newDiv.classList.add('big');
                                newDiv.classList.remove('close');
                                var markerDiv = document.getElementById('marker');
                                markerDiv.classList.add('small');
                                fetchComments(currentMarkerId);
                                fetchChart(currentMarkerId);
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

            function fetchComments(markerId) {
                $.ajax({
                    url: '/A1/MainServlet',
                    type: 'GET',
                    data: { markerId: markerId },
                    dataType: 'json',
                    success: function(data) {
                        commentsContainer.innerHTML = '';
                        data.forEach(function(comment) {
                            const p = document.createElement('p');
                            p.textContent = comment.markerComContents;
                            commentsContainer.appendChild(p);
                        });
                    },
                    error: function() {
                        console.error('Error fetching comments');
                    }
                });
            }

            function fetchChart(markerId) {
                $.ajax({
                    url: '/A1/MainServlet',
                    type: 'GET',
                    data: { markerChart: markerId },
                    dataType: 'json',
                    success: function(data) {
                        const ctx = document.getElementById('myChart').getContext('2d');
                        const total = data.veryGood + data.good + data.bad + data.veryBad;
                        const percentages = [
                            (data.veryGood / total) * 100,
                            (data.good / total) * 100,
                            (data.bad / total) * 100,
                            (data.veryBad
                            		/ total) * 100
                        ];

                        const chartData = {
                            labels: ['Total'],
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
                                }
                            ]
                        };

                        if (chart) {
                            chart.data = chartData;
                            chart.update();
                        } else {
                            chart = new Chart(ctx, {
                                type: 'bar',
                                data: chartData,
                                options: {
                                    indexAxis: 'y',
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
                                            display: false
                                        },
                                        tooltip: {
                                            callbacks: {
                                                label: function(tooltipItem) {
                                                    return tooltipItem.dataset.label + ': ' + tooltipItem.raw.toFixed(2) + '%';
                                                }
                                            }
                                        }
                                    },
                                    maintainAspectRatio: false,
                                    responsive: true,
                                    animation: {
                                        duration: 0 // アニメーションを無効にする
                                    }
                                }
                            });
                        }
                    },
                    error: function() {
                        console.error('Error fetching chart data');
                    }
                });
            }

            function updateChart() {
                if (currentMarkerId) {
                    fetchChart(currentMarkerId);
                }
            }

            $(document).ready(function() {
                function fetchLatestBoardContents() {
                    $.ajax({
                        url: '/A1/MainServlet',
                        type: 'POST',
                        data: { action: 'fetchLatestBoardContents' },
                        dataType: 'json',
                        success: function(data) {
                            $('#textarea').val(data.boardContents);
                        },
                        error: function() {
                            console.error('Error fetching board contents.');
                        }
                    });
                }

                setInterval(fetchLatestBoardContents, 1000);
            });

            sendCommentBtn.addEventListener('click', function() {
                const commentText = newCommentText.value;
                if (currentMarkerId && commentText) {
                    $.ajax({
                        url: '/A1/MarkerComServlet',
                        type: 'POST',
                        data: {
                            markerComContents: commentText,
                            markerId: currentMarkerId
                        },
                        success: function(response) {
                            if (response === 'success') {
                                newCommentText.value = '';
                                fetchComments(currentMarkerId);
                            } else {
                                alert('Error saving comment');
                            }
                        },
                        error: function() {
                            alert('Error saving comment');
                        }
                    });
                }
            });

            closeBtn.addEventListener('click', function() {
                newDiv.classList.remove('big');
                newDiv.classList.add('close');
                var markerDiv = document.getElementById('marker');
                markerDiv.classList.remove('small');
            });

            newDiv.addEventListener('animationend', function() {
                if (newDiv.classList.contains('close')) {
                    newDiv.classList.remove('close');
                    newDiv.style.zIndex = -1;
                }
            });

            function fetchAllMarkerComs() {
                $.ajax({
                    url: '/A1/MainServlet',
                    type: 'GET',
                    data: { allMarkerCom: true },
                    dataType: 'json',
                    success: function(data) {
                        markcomlive.innerHTML = '';
                        data.forEach(function(comment) {
                            const li = document.createElement('li');
                            const p = document.createElement('p');
                            p.textContent = comment.markerContents + ' : ' + comment.markerComContents + ' ♡';
                            markcomlive.appendChild(document.createElement('hr'));
                            li.appendChild(p);
                            markcomlive.appendChild(li);
                        });
                    },
                    error: function() {
                        console.error('Error fetching marker comments');
                    }
                });
            }

            function fetchAllComs() {
                $.ajax({
                    url: '/A1/MainServlet',
                    type: 'GET',
                    data: { data1: true },
                    dataType: 'json',
                    success: function(data) {
                        allcomdiv.innerHTML = '';
                        data.forEach(function(comment) {
                            const p = document.createElement('p');
                            p.textContent = comment.allComContents + ' ♡';
                            allcomdiv.appendChild(p);
                            allcomdiv.appendChild(document.createElement('hr'));
                        });
                    },
                    error: function() {
                        console.error('Error fetching all comments');
                    }
                });
            }

            $(document).ready(function() {
                fetchMarkers();
                fetchAllMarkerComs();
                fetchAllComs();
                setInterval(fetchMarkers, 1000);
                setInterval(fetchAllMarkerComs, 1000);
                setInterval(fetchAllComs, 1000);
                setInterval(updateChart, 1000);
            });

            reactionButtons.forEach(button => {
                button.addEventListener('click', function() {
                    const reaction = this.getAttribute('data-reaction');
                    if (currentMarkerId) {
                        $.ajax({
                            url: '/A1/MarkRegiServlet',
                            type: 'POST',
                            data: {
                                markerId: currentMarkerId,
                                reaction: reaction
                            },
                            success: function(response) {
                                fetchChart(currentMarkerId);
                            },
                            error: function() {
                                alert('Error saving reaction');
                            }
                        });
                    }
                });
            });
        });

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
    </script>
</body>
</html>