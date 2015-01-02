/**
 * Creates a line chart
 * @param domId ID of the DOM element where the chart will be displayed
 * @param chartAttributes
 * @constructor
 */
Line = function (domId, chartAttributes) {

    var currencySymbol = chartAttributes.numberPrefix || '';

    this.chart = new Highcharts.Chart({
        chart: {
            renderTo: domId,
            type: 'line',
            marginRight: 10,
            animation: Highcharts.svg
        },
        title: {
            text: chartAttributes.caption
        },

        subtitle: {
            text: chartAttributes.subcaption
        },

        xAxis: {
            title: {
                text: chartAttributes.xAxisTitle
            },
            type: 'datetime'
        },
        yAxis: {
            labels: {
                formatter: function () {
                    return currencySymbol + this.axis.defaultLabelFormatter.call(this);
                }
            },
            title: {
                text: chartAttributes.yAxisTitle
            },
            plotLines: [
                {
                    value: 0,
                    width: 1,
                    color: '#808080'
                }
            ]
        },
        tooltip: {
            formatter: function () {
                return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    currencySymbol + Highcharts.numberFormat(this.y, chartAttributes.tooltipDecimals);
            }
        },
        legend: {
            enabled: false
        },
        credits: {
            enabled: false
        },
        plotOptions: {
            series: {
                states: {
                    hover: {
                        lineWidthPlus: 0
                    }
                },
                lineWidth: 1,
                marker: {
                    enabled: false,
                    radius: 3
                }
            }
        },
        series: [
            {
                color: chartAttributes.primaryColor,
                data: chartAttributes.series
            }
        ]
    });

    // track the timestamp of the most recent datapoint, so we never show the same point twice
    this.mostRecentData = {timestamp: 0};
};

/**
 * Append data to a chart
 * @param data latest datum in JSON format
 */
Line.prototype.appendData = function (data) {

    if (data.value != null && data.timestamp > this.mostRecentData.timestamp) {

        this.chart.series[0].addPoint([data.timestamp, data.value], true, true);
        this.mostRecentData = data;
    }
};