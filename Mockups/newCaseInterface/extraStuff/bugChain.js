var bugList;

$(document).ready(function () {
    $('.hide').hide();
    parseTextEmployeeFile();
    //parseTextBugFile();
});

function parseTextEmployeeFile()
{
    var file = new ActiveXObject("Scripting.FileSystemObject");
    var ts = file.OpenTextFile('/extraStuff/nameData.js', 1);
    var go = ts.Readline()
    /*file.open("GET", 'extraStuff/nameData.txt', true);
    file.onreadystatechange = function()
    {
        
                var fileTxt = file.responseText;
                console.log(fileTxt);
        
    }*/
}

function init()
{
    populateList();
    initializeDatePickers();
    populateEmployeeDropdowns();
}

function populateList()
{
    var htmlStr = '';
    var strPanelId = 'accBugs';

    if(bugList.length <= 0)
    {
        $('.noBugs').html('<h3>No Bugs</h3>');
    }
    else
    {
        for (var i = 0; i < bugList.length; i++)
        {
            htmlStr +=
                '<div class="panel panel-default">' +
                    '<div class="panel-heading" role="tab" id="panelHeading_' + (i + 1) + '">' +
                        '<div class="bugOptions" id="bugOptions_' + (i + 1) + '">' + 
                            '<button class="btn btn-warning float-right margin-left-10px remove" id="btnToRemove_' + (i + 1) + '" onclick="toRemove(this);">Mark For Removal</button>' +
                            '<button class="btn btn-success float-right edit" id="btnToEdit_' + (i + 1) + '" onclick="toEdit(this);">Edit</button>' +
                        '</div>' +                        
                        '<h4 class="">' +                            
                            '<a role="button" data-toggle="collapse" data-parent="#' + strPanelId + '" href="#bugCollapse_' + (i + 1) + '" aria-expanded="true" aria-controls="bugCollapse_' + (i + 1) + '">' +
                                bugList[i].BugName +
                                '&nbsp&nbsp&nbsp<span class="label label-info">' + bugList[i].ReportedBy + '</span>' +
                            '</a>' +
                        '</h4>' +
                    '</div>' +
                    '<div id="bugCollapse_' + (i + 1) + '" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panelHeading_' + (i + 1) + '">' +
                        '<div class="panel-body">' +
                            '<table>' +
                                '<tbody>' +
                                    '<tr>' +
                                        '<td class=rowHeading>' +
                                            'Date Found:' +
                                        '</td>' +
                                        '<td>' +
                                            bugList[i].Date +
                                        '</td>' +
                                    '</tr>' +
                                    '<tr>' +
                                        '<td class=rowHeading>' +
                                            'Location:' +
                                        '</td>' +
                                        '<td>' +
                                            bugList[i].Location +
                                        '</td>' +
                                    '</tr>' +
                                    '<tr>' +
                                        '<td class=rowHeading>' +
                                            'Description:' +
                                        '</td>' +
                                        '<td>' +
                                            bugList[i].Description +
                                        '</td>' +
                                    '</tr>' +
                                    '<tr>' +
                                        '<td class=rowHeading>' +
                                            'Steps To Replicate:' +
                                        '</td>' +
                                        '<td>' +
                                            bugList[i].ToReplicate +
                                        '</td>' +
                                    '</tr>' +
                                    '<tr>' +
                                        '<td class=rowHeading>' +
                                            'Workaround:' +
                                        '</td>' +
                                        '<td>' +
                                            bugList[i].Solution +
                                        '</td>' +
                                    '</tr>' +
                                    '<tr>' +
                                        '<td class=rowHeading>' +
                                            'Additional Info:' +
                                        '</td>' +
                                        '<td>' +
                                            bugList[i].MoreInfo +
                                        '</td>' +
                                    '</tr>' +
                                '</tbody>' +
                            '</table>' +
                        '</div>' +
                    '</div>' +
                '</div>';
        }
        $('#' + strPanelId).html(htmlStr);
    }
}

function initializeDatePickers()
{
    /*newBug Datepicker*/
    $('#inputDate').datepicker({
        showAnim: 'slideDown'
    });    
    /*end newBug Datepicker*/

    $('.datepicker').datepicker('setDate', new Date()); //all DatePickers pre-populated with today's date
}

function populateEmployeeDropdowns()
{
    $('.ddEmployee').each(function ()
    {
        var htmlStr = '';

        for (var i = 0; i < nameList.length; i++)
        {
            htmlStr += '<li class="optWorker"><a href="#">' + nameList[i].Name + '</a></li>';
        }
        $(this).html(htmlStr);
    });

    $('.optWorker').click(function () {
        $(this).closest('.ddEmployeeTxtGrp').find('.ddEmployeeTxt').val($(this).text());
    });
}

function toggleAddNewBug(element)
{
    switch($(element).attr('id'))
    {
        case 'btnAddNewBugDiv':
            $('#addNewDiv').removeClass('hide').show();
            $('#btnCancelNewBug').removeClass('hide').show();
            $('#btnSaveBug').removeClass('hide').show();
            $('#btnReset').removeClass('hide').show();
            $('#btnAddNewBugDiv').hide();
            break;
        case 'btnSaveBug':
            addDetailsToFile();
            resetBugForm();
            $('#btnAddNewBugDiv').show();
            $('#btnSaveBug').hide();
            $('#btnCancelNewBug').hide();
            $('#addNewDiv').hide();
            $('#btnReset').hide();
            break;
        case 'btnCancelNewBug':
            resetBugForm();
            $('#btnAddNewBugDiv').show();
            $('#btnSaveBug').hide();
            $('#btnCancelNewBug').hide();
            $('#addNewDiv').hide();
            $('#btnReset').hide();
            break;
        case 'btnReset':
            resetBugForm();
            break;
    }
}

function addDetailsToFile()
{
    var formArr = $('#newBugForm').serializeArray();
    console.log(formArr);
}

function resetBugForm()
{
    $('#newBugForm').find('input[type=text], textarea').val('');
    $('.datepicker').datepicker('setDate', new Date());
}

function toRemove(element)
{
    var panelIdStr = $('.panel-heading').attr('id').split('_')[0];
    var bugNumber = $(element).attr('id').split('_');

    bugNumber = bugNumber[bugNumber.length - 1];
    panelIdStr = panelIdStr + '_' + bugNumber;

    switch ($(element).hasClass('remove'))
    {
        case true:
            $('#' + panelIdStr).parent().addClass('selected');
            $(element).removeClass('remove');
            break;
        case false:
            $('#' + panelIdStr).parent().removeClass('selected');
            $(element).addClass('remove');
            break;
    }
    toggleButton('remove', element);
}

function toEdit(element)
{
    var panelIdStr = $('.panel-heading').attr('id').split('_')[0];
    var bugNumber = $(element).attr('id').split('_');

    bugNumber = bugNumber[bugNumber.length - 1];
    panelIdStr = panelIdStr + '_' + bugNumber;

    switch ($(element).hasClass('edit'))
    {
        case true:
            $('#' + panelIdStr).parent().addClass('selected');
            $(element).removeClass('edit');
            break;
        case false:
            $('#' + panelIdStr).parent().removeClass('selected');
            $(element).addClass('edit');
            break;
    }
    toggleButton('edit', element);
}

function toggleButton(key, element)
{
    var previousButtonText = $(element).text();
    var newButtonText;

    switch (key)
    {
        case 'remove':
            if (previousButtonText == "Mark For Removal")
            {
                newButtonText = 'Don\'t Remove';
            }
            else if (previousButtonText == 'Don\'t Remove')
            {
                newButtonText = 'Mark For Removal';
            }            
            break;
        case 'edit':
            if (previousButtonText == "Edit") {
                newButtonText = 'Save Changes';
            }
            else if (previousButtonText == 'Save Changes') {
                newButtonText = 'Edit';
            }
            break;
        case 'addNew':
            if (previousButtonText == "Add New") {
                newButtonText = 'Save Bug';
            }
            else if (previousButtonText == 'Save Bug') {
                newButtonText = 'Add New';
            }
            break;
    }
    $(element).text(newButtonText);
}