var api = 'api';
var defaultSize = 16;
var spinner = '<i class="fa fa-spinner fa-pulse"></i>'
var editor = ace.edit('editor');
var requestParam = {input:'', args:'', code:''};

editor.setOptions({
    fontFamily:"Fira Code",
    highlightActiveLine:false,
    enableBasicAutocompletion: true,
    enableSnippets: true,
    enableLiveAutocompletion: true
});

editor.setTheme('ace/theme/solarized_dark');

editor.session.setMode('ace/mode/java');

editor.setShowPrintMargin(false);

editor.setFontSize(defaultSize);

editor.commands.addCommand({
    name: 'Run',
    bindKey: {win: 'Ctrl-S',  mac: 'Command-S'},
    exec: runClick,
    readOnly: false
});

function runClick(self) {
    requestParam.code = $.trim(editor.getValue());
    requestParam.args = $('#args').val();
    requestParam.input = $('#input').val();
    if(requestParam.code.length != 0) {
        self.disabled = true;
        render(0, '<p>' + spinner + '<span>正在准备结果</span></p>');
        $.get(api, requestParam, function (data) {
            render(data.code, data.result);
            self.disabled = false;
        });
    }
}

$('#editor').bind('mousewheel', function(e){
    if(e.originalEvent.wheelY > 0){
        editor.setFontSize(++defaultSize);
    }else{
        defaultSize = (defaultSize - 1) <= 1 ? 1 : defaultSize - 1;
        editor.setFontSize(defaultSize);
    }
});


function render(code, html) {
    $('.code-console-result').html(html);
    $('.code-console-result').attr('id',(code == 0) ? 'success' : 'error');
}