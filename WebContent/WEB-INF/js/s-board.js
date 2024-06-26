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

			//モーダル全体を閉じる
            addEventListener('click', outsideClose);
            function outsideClose(e) {
                if (e.target == modal) {
                    modal.style.display = 'none';
                }
            }
			//マーカー機能
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
			//newdivが表示される
            function fetchMarkers() {
        $.ajax({
            url: '/A1/MainServlet',
            type: 'GET',
            data: { latestMarkers: true },
            dataType: 'json',
            success: function(data) {
                markerList.innerHTML = '';
                data.reverse().forEach(function(marker) {
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
                        fetchLatestReaction(currentMarkerId);
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

			//マーカーコメントが表示される
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
			//グラフの表示
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
                    (data.veryBad / total) * 100
                ];

                const chartData = {
                    labels: ['Total'],
                    datasets: [
                        {
                            label: 'とてもよくわかる',
                            data: [percentages[0]],
                            backgroundColor: 'rgba(255,0,0, 0.5)',
                            borderWidth: 1
                        },
                        {
                            label: 'わかる',
                            data: [percentages[1]],
                            backgroundColor: 'rgba(255, 255, 0, 0.5)',
                            borderWidth: 1
                        },
                        {
                            label: 'わからない',
                            data: [percentages[2]],
                            backgroundColor: 'rgba(34, 139, 34, 0.5)',
                            borderWidth: 1
                        },
                        {
                            label: 'とてもわからない',
                            data: [percentages[3]],
                            backgroundColor: 'rgba(65, 105, 225, 0.5)',
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
            function fetchLatestReaction(markerId) {
                $.ajax({
                    url: '/A1/MainServlet',
                    type: 'GET',
                    data: { userReaction: markerId },
                    dataType: 'json',
                    success: function(data) {
                        const reactionText = document.getElementById('reactionText');
                        let reaction = "未回答";
                        if (data.reaction === 'vg') {
                            reaction = "とてもよくわかる";
                        } else if (data.reaction === 'g') {
                            reaction = "わかる";
                        } else if (data.reaction === 'b') {
                            reaction = "あまりわからない";
                        } else if (data.reaction === 'vb') {
                            reaction = "わからない";
                        }
                        reactionText.textContent = reaction;
                    },
                    error: function() {
                        console.error('Error fetching latest reaction');
                    }
                });

            }
			//グラフの更新
            function updateChart() {
                if (currentMarkerId) {
                    fetchChart(currentMarkerId);
                }
            }
			//板書の更新
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
            function fetchUserMarkers() {
                $.ajax({
                    url: '/A1/MainServlet',
                    type: 'GET',
                    data: { userMarkers: true },
                    dataType: 'json',
                    success: function(userMarkerIds) {
                        updateMarkersWithUserReactions(userMarkerIds);
                    },
                    error: function() {
                        console.error('Error fetching user markers');
                    }
                });
            }


			//マーカーコメント全体の表示
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
			//全体コメントの表示
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
			//1秒ごとの交信
            $(document).ready(function() {
                fetchMarkers();
                fetchAllMarkerComs();
                fetchAllComs();
                setInterval(fetchMarkers, 1000);
                setInterval(fetchAllMarkerComs, 1000);
                setInterval(fetchAllComs, 1000);
                setInterval(updateChart, 1000);
                setInterval(function() {
                    if (currentMarkerId) {
                        fetchLatestReaction(currentMarkerId);
                    }
                }, 1000);
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
		//全体コメントの送信
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