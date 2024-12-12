google.charts.load("current", {packages: ['corechart']});
google.charts.setOnLoadCallback(drawStats);
google.charts.setOnLoadCallback(drawCounter);

function drawStats() {
    var data = google.visualization.arrayToDataTable([
        ['Статус', 'Количество', {role: 'style'}, {role: 'annotation'}],
        ['Ожидание', waiting, "red", waiting],
        ['Активно', active, "brown", active],
        ['Закрыто', sold, "green", sold],
    ]);

    var options = {
        hAxis: {title: 'Статус'},
        vAxis: {title: 'Количество'},
        bar: {groupWidth: "80%"},
        legend: {position: "none"}
    };
    var chart = new google.visualization.ColumnChart(document.getElementById('stats'));
    chart.draw(data, options);
}

function drawCounter() {
    let res = [['Реклама', 'Количество']];

    for (let i = 0; i < counterString.length; i++) {
        res.push([counterString[i], counterInt[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Топ-5 по просмотрам',
        hAxis: {title: 'Реклама'},
        vAxis: {title: 'Количество'},
        bar: {groupWidth: "80%"},
        legend: {position: "none"}
    };
    var chart = new google.visualization.ColumnChart(document.getElementById('drawCounter'));
    chart.draw(data, options);
}