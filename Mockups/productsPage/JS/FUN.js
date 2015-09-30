var graphRawWidth = 600;
var graphRawHeight = 600;
var tickMarkOffset = 7; //offset from axis on each side
var labelOffset = 20; //offset from axis for label
var paper;
var graphArr = [
    { x: -7, val_0: -343 },
    { x: -6.5, val_0: -274.625 },
    { x: -6, val_0: -216 },
    { x: -5.5, val_0: -166.375 },
    { x: -5, val_0: -125 },
    { x: -4.5, val_0: -91.125 },
    { x: -4, val_0: -64 },
    { x: -3.5, val_0: -42.875 },
    { x: -3, val_0: -27 },
    { x: -2.5, val_0: -15.625 },
    { x: -2, val_0: -8 },
    { x: -1.5, val_0: -3.375 },
    { x: -1, val_0: -1 },
    { x: -0.5, val_0: -0.125 },
    { x: 0, val_0: 0 },
    { x: 0.5, val_0: 0.125 },
    { x: 1, val_0: 1 },
    { x: 1.5, val_0: 3.375 },
    { x: 2, val_0: 8 },
    { x: 2.5, val_0: 15.625 },
    { x: 3, val_0: 27 },
    { x: 3.5, val_0: 42.875 },
    { x: 4, val_0: 64 },
    { x: 4.5, val_0: 91.125 },
    { x: 5, val_0: 125 },
    { x: 5.5, val_0: 166.375 },
    { x: 6, val_0: 216 },
    { x: 6.5, val_0: 274.625 },
    { x: 7, val_0: 343 }
];


    angular.module('app', ['ngRoute']);
    angular.module('example', ['n3-line-chart']).controller('ExampleCtrl', function ($scope) {
        $scope.data = graphArr;
        $scope.options = {
            series: [
              {
                  y: "val_0",
                  label: "f(x) = (x+A)(x+B)(x+C) + D",
                  color: "#1f77b4"
              }
            ],
            tooltip: { formatter: function (x, y, series) { return 'x = ' + x + ' : ' + 'f(x) = ' + y; } }
        };

    });

    function redraw()
    {
        graphArr2 = [
    { x: -7, val_0: -343 },
    { x: -6.5, val_0: -274.625 },
    { x: -6, val_0: -216 },
    { x: -5.5, val_0: -166.375 },
    { x: -5, val_0: -125 },
    { x: -4.5, val_0: -91.125 },
    { x: -4, val_0: -64 },
    { x: -3.5, val_0: -42.875 },
    { x: -3, val_0: -27 }
    
        ];
        $('#chart').html(
            '<div ng-app="example" ng-controller="ExampleCtrl">' +
                '<linechart data="data" options="options"></linechart>' +
            '</div>'
            );
        setTimeout(function () {
angular.module('app', ['ngRoute']);
        angular.module('example', ['n3-line-chart']).controller('ExampleCtrl', function ($scope) {
            $scope.data = graphArr2;
            $scope.options = {
                series: [
                  {
                      y: "val_0",
                      label: "f(x) = (x+A)(x+B)(x+C) + D",
                      color: "#1f77b4"
                  }
                ],
                tooltip: { formatter: function (x, y, series) { return 'x = ' + x + ' : ' + 'f(x) = ' + y; } }
            };
        });
        }, 5000);
        


    }

$(document).ready(function () {
    /*
    initSliders();
    initNumSpinners();
    //updateCoordPlane();
    calculateGraph();
    */

    /*
    var x1=0;
    var x2=0;
    var x3=0;
    var xVal = 0;
    var yVal = 0;
    var pointVal = 0;
    var factor = 1;
    var posx = 1.01;
    var posy = 1.01;
    */

    //paper = new Raphael(document.getElementById('canvas_container'), graphRawWidth, graphRawHeight);

    //Initialize Slider settings
    //initSliders();
    //initNumSpinners();
    //updateCoordPlane();
    //calculateGraph();
    /*$("#pointslider").val(0);
    $("#X1slider").val(0);
    $("#X2slider").val(0);
    $("#X3slider").val(0);
    */
    //Plot default graph
    //redrawGraph(100, 100);
    //plotPoint(0, 100, 100);




    /* $(".pointslider").on("input", function(){
         pointVal = $("#pointslider").val();
         xVal = $("#zoomXslider").val();
         yVal = $("#zoomYslider").val();

         redrawGraph(xVal, yVal);
         plotPoint(pointVal, xVal, yVal);
         $("#pointsliderlabel").text(pointVal);
     });

     $("#X1slider").on("input", function(){
         var XsliderVal = $("#X1slider").val();
         $("#X1sliderlabel").text(XsliderVal);
         xVal = $("#zoomXslider").val();
         yVal = $("#zoomYslider").val();

         redrawGraph(xVal, yVal);
         plotPoint(pointVal, xVal, yVal);
     });

     $("#X2slider").on("input", function(){
         var XsliderVal = $(this).val();
         $("#X2sliderlabel").text(XsliderVal);
         xVal = $("#zoomXslider").val();
         yVal = $("#zoomYslider").val();

         redrawGraph(xVal, yVal);
         plotPoint(pointVal, xVal, yVal);
     });

     $("#X3slider").on("input", function(){
         var XsliderVal = $(this).val();
         $("#X3sliderlabel").text(XsliderVal);
         xVal = $("#zoomXslider").val();
         yVal = $("#zoomYslider").val();

         redrawGraph(xVal, yVal);
         plotPoint(pointVal, xVal, yVal);
     });

     function redrawGraph(xZoom, yZoom){
         var XzoomInt = parseInt(xZoom);
         var YzoomInt = parseInt(yZoom);
         var pathStr = "M ";
         var pathStr2 = "M ";
         var circleCount = 0;
         var circleCount2 = 0;
         x1=$("#X1slider").val();
         x2=$("#X2slider").val();
         x3=$("#X3slider").val();

         paper.clear();
         // M 300 0 L 300 600 M 0 300 L 600 300
         paper.path("M " + graphRawWidth/2 + " 0 L " + graphRawWidth/2 + " " + graphRawHeight + " M 0 " + graphRawHeight/2 + " L " + graphRawWidth + " " + graphRawHeight/2).attr({"stroke": "red"});
         // Plot graph
         for(var x=graphRawWidth*-1.0;x<graphRawWidth;x += .33) {
             posx = GetX(x,XzoomInt);

             posy = GetY(x, YzoomInt,x1, x2, x3);

             posy = Math.round(posy) + graphRawWidth/2;
             posx = Math.round(posx) + graphRawHeight/2;

             var xStr = posx.toString();
             var yStr = posy.toString();

             if(posx > 0 && posy > 0 && posx < graphRawWidth && posy+300 < graphRawHeight+300){
                 circleCount +=1;
                 pathStr = pathStr +  xStr + " " + yStr + " L ";
             }
             //circleCount2 +=1;
             pathStr2 = pathStr2 +  xStr + " " + yStr + " L ";
         }
         //paper.path(pathStr2).attr({stroke: "orange"});
         paper.path(pathStr2).attr({stroke: "black"});

         //alert(pathStr2);
         //alert("Path1 count=" + circleCount + "\nPath2 count = " + circleCount2);
     }
     function plotPoint(x, xZoom, yZoom){
         XzoomInt = $("#zoomXslider").val();
         YzoomInt = $("#zoomYslider").val();
         x1=$("#X1slider").val();
         x2=$("#X2slider").val();
         x3=$("#X3slider").val();

         posx = GetX(x, XzoomInt);
         posy = GetY(x, YzoomInt, x1, x2, x3);
         $("#XCoor").text(posx);
         $("#YCoor").text(posy*-1);

         posy = Math.round(posy) + graphRawWidth/2;
         posx = Math.round(posx) + graphRawHeight/2;

         var plottedPoint = paper.circle(posx,posy,5).attr({fill: "blue"});

         /* Draw Grid */
    /*var pathStr = "";
    for(i=graphRawWidth/10; i<graphRawWidth;i+=graphRawWidth/10){
        var xTop = (graphRawWidth/2)-7;
        var xBottom = xTop + 14;
        pathStr = "M " + xTop + " " + i + " L " + xBottom + " " + i;
        paper.path(pathStr).attr({stroke:"purple"});

        //var XCoorStr = Math.round((graphRawWidth/-2 + i)*100/xZoom); //Works good
        //var YCoorStr = Math.round((graphRawHeight/2 - i)*yZoom/100); //Works good

        var XCoorStr = GetX(i, XzoomInt) - graphRawWidth/2 * xZoom/100;
        //XCoorStr = Math.round(XCoorStr) + graphRawWidth/2; // Modified

        var YCoorStr = (GetX(i, yZoom) - graphRawHeight/2 * yZoom/100) * -1;

        if(i != graphRawWidth/2){
            paper.text(i, graphRawWidth/2+20, XCoorStr);
            paper.text(graphRawHeight/2+20,i, YCoorStr);
        }

        var yTop = (graphRawHeight/2)-7;
        var yBottom = yTop + 14;
        pathStr = "M " + i.toString() + " " + yTop + " L " + i + " " + yBottom;
        paper.path(pathStr).attr({stroke:"green"});
    }
}
function GetX(Xin, zoomVal){
    return Math.round(Xin * (zoomVal/100));

}//Needs to be finished
function GetY(Xin, zoomVal, x1,x2,x3){
    return Math.round(((Xin + x1) * (Xin + x2) * (Xin + x3)) * (zoomVal/100) * -1);

}//Needs to be finished*/
});

function initSliders() {
    var sliderColl = $(".slider");

    //set defaultValue of sliders class
    $(sliderColl).each(function () {
        var max = parseInt($(this).attr('max'));
        var min = parseInt($(this).attr('min'));
        var go = (max + min);
        $(this).attr('defaultValue', (max + min) / 2);
        $(this).attr('value', (max + min) / 2);
        updateSliderCounter($(this).attr('id'));
    });

    //add eventListener to slider class
    $(".slider").mouseup(function () {
        updateSliderCounter($(this).attr('id'));
        updateCoordPlane();
        calculateGraph();
        /*var xVal = $("#zoomXslider").val();
        var yVal = $("#zoomYslider").val();

        $("#zoomXsliderlabel").text(xVal);
        $("#zoomYsliderlabel").text(yVal);*/

        //plotPoint(pointVal, xVal, yVal);
    });
}

function initNumSpinners() {
    $('.spinner').spinner();
    /*{
        stop: function (event, ui) {
            calculateGraph();
        }
    }*/
    $('.spinner').blur(function () { calculateGraph(); });
}

function updateSliderCounter(sliderIdStr) {
    var label = 'lbl' + sliderIdStr[0].toUpperCase() + sliderIdStr.substring(1);
    $('#' + label).text($('#' + sliderIdStr).val());
}

function updateCoordPlane() {
    var domainLower = parseInt($('#sliderDomainLower').val());
    var domainUpper = parseInt($('#sliderDomainUpper').val());
    var rangeLower = parseInt($('#sliderRangeLower').val());
    var rangeUpper = parseInt($('#sliderRangeUpper').val());
    var domainSpan = Math.abs(domainLower - domainUpper);
    var rangeSpan = Math.abs(rangeLower - rangeUpper);
    var axisYOffset = Math.abs(getAxisOffset(domainLower, domainSpan, graphRawWidth));
    var axisXOffset = getAxisOffset(rangeUpper, rangeSpan, graphRawHeight);
    var axesPathStr = "M " + axisYOffset + " 0 L " + axisYOffset + " " + graphRawHeight + " M 0 " + axisXOffset + " L " + graphRawWidth + " " + axisXOffset;

    paper.clear();
    //draw axes - M 300 0 L 300 600 M 0 300 L 600 300
    drawAxes(axesPathStr);
    drawIntervalsX(axisXOffset, domainLower, domainUpper);
    drawIntervalsY(axisYOffset, rangeLower, rangeUpper);
}

function getAxisOffset(lowerBound, graphSpan, graphRawDimension) {
    return (graphRawDimension / graphSpan) * lowerBound;
    //return lowerBound + ((graphRawDimension / graphSpan) * lowerBound);
}

function drawAxes(pathStr) {
    paper.path(pathStr).attr({ "stroke": "red" });
}

function drawIntervalsX(yPos, pointLower, pointUpper) {
    var span = Math.abs(pointLower - pointUpper);
    var counter = 1;
    var interval = 0;

    for (var i = 0; i < graphRawWidth; i++) {
        var pointLabel = Math.round((pointLower + counter) * 100) / 100;
        interval += (graphRawWidth / span);

        if (pointLabel != 0 && pointLabel != pointUpper) {
            var pathStr = '';

            pathStr = "M " + interval + " " + (yPos - tickMarkOffset) + " " + " L " + interval + " " + (yPos + tickMarkOffset);
            paper.path(pathStr).attr({ stroke: "purple" });
            paper.text(interval, (yPos + labelOffset), pointLabel);
        }
        counter++;
    }
}

function drawIntervalsY(xPos, pointLower, pointUpper) {
    var span = Math.abs(pointLower - pointUpper);
    var counter = 1;
    var interval = 0;

    for (var i = graphRawHeight; i > 0; i--)
        //for (var i = 0; i <= graphRawHeight; i++) 
    {
        var pointLabel = Math.round((pointUpper - counter) * 100) / 100;
        interval += (graphRawHeight / span);

        if (pointLabel != 0 && pointLabel != pointLower) {
            var pathStr = '';

            pathStr = "M " + (xPos - tickMarkOffset) + " " + interval + " " + " L " + (xPos + tickMarkOffset) + " " + interval;
            paper.path(pathStr).attr({ stroke: "purple" });
            paper.text((xPos + labelOffset), interval, pointLabel);
        }
        counter++;
    }






    /*
    var i = graphRawHeight / 10;
    var lowerY = parseInt($('#sliderRangeLower').val());

    //get distance between lower and upper bound of range
    var yDist = Math.abs(lowerY - parseInt($('#sliderRangeUpper').val()));

    for(i; i < graphRawHeight; i += (graphRawHeight / 10))
    {
        lowerY += (yDist / 10);
        if(i != graphRawHeight / 2)
        {
            var pathStr = '';

            pathStr = "M " + tickMarkP1 + " " + i + " L " + tickMarkP2 + " " + i;
            paper.path(pathStr).attr({stroke:"purple"});

            //var XCoorStr = Math.round((graphRawWidth/-2 + i)*100/xZoom); //Works good
            //var YCoorStr = Math.round((graphRawHeight/2 - i)*yZoom/100); //Works good


            paper.text((graphRawWidth / 2) + 20, i, Math.round(lowerY * 100) / 100);
        }
    }*/
}

function calculateGraph() {
    var termA = parseInt($('#spnrA').val());
    var termB = parseInt($('#spnrB').val());
    var termC = parseInt($('#spnrC').val());
    var termD = parseInt($('#spnrD').val());
    var domainLower = parseInt($('#sliderDomainLower').val());
    var domainUpper = parseInt($('#sliderDomainUpper').val());
    var domainSpan = Math.abs(domainLower - domainUpper);
    /*
    var rangeLower = parseInt($('#sliderRangeLower').val());
    var rangeUpper = parseInt($('#sliderRangeUpper').val());
    */

    graphArr = [];

    for (var i = domainLower; i <= domainUpper; i += (domainSpan / 100)) {
        var pointY = (i + termA) * (i + termB) * (i + termC) + termD;
        var pointArr = [];

        pointArr["x"] = i;
        pointArr["val_0"] = pointY;

        graphArr.push(pointArr);
        /*if (pointY >= rangeLower && pointY <= rangeUpper) {
            var pointArr = [];

            pointArr.push(i);
            pointArr.push(pointY);
            graphArr.push([i, pointY]);
        }*/
    }
    console.log(graphArr);
    graphData = graphArr;
}

function updateGraph() {

}




/*Product2*/




