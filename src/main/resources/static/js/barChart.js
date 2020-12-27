var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        const data = JSON.parse(xhttp.responseText);

        var densityCanvas = document.getElementById("myGraphic");

        Chart.defaults.global.defaultFontFamily = "Serif";
        Chart.defaults.global.defaultFontStyle = 'Bold';
        Chart.defaults.global.defaultFontColor = 'black';
        Chart.defaults.global.defaultFontSize = 18;

        var year = new Date().getFullYear();

        let reservas = [];

        for (let i = 1; i < 13; i++) {
            if(data[i]) {
                reservas.push(data[i]);
            } else {
                reservas.push(0);
            }
        }

        var densityData = {
            label: 'Número de reservas ' + year.toString(),
            data: reservas,
            backgroundColor: [
                'rgba(240, 99, 132, 0.6)',
                'rgba(240, 99, 132, 0.6)',
                'rgba(240, 99, 132, 0.6)',
                'rgba(240, 99, 132, 0.6)',
                'rgba(240, 99, 132, 0.6)',
                'rgba(240, 99, 132, 0.6)',
                'rgba(240, 99, 132, 0.6)',
                'rgba(240, 99, 132, 0.6)',
                'rgba(240, 99, 132, 0.6)',
                'rgba(240, 99, 132, 0.6)',
                'rgba(240, 99, 132, 0.6)',
                'rgba(240, 99, 132, 0.6)'
            ],
            borderColor: [
                'rgba(240, 99, 132, 1)',
                'rgba(240, 99, 132, 1)',
                'rgba(240, 99, 132, 1)',
                'rgba(240, 99, 132, 1)',
                'rgba(240, 99, 132, 1)',
                'rgba(240, 99, 132, 1)',
                'rgba(240, 99, 132, 1)',
                'rgba(240, 99, 132, 1)',
                'rgba(240, 99, 132, 1)',
                'rgba(240, 99, 132, 1)',
                'rgba(240, 99, 132, 1)',
                'rgba(240, 99, 132, 1)'
            ],
            borderWidth: 2,
            hoverBorderWidth: 0
        };

        var barChart = new Chart(densityCanvas, {
            type: 'bar',
            data: {
                labels: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto" , "Septiembre", "Octubre", "Noviembre", "Diciembre"],
                datasets: [densityData]
            }
        });
    }
};

xhttp.open("GET", "http://localhost:8080/data/charts-data", true);
xhttp.send();
