class Editor{
    constructor(tema,modo){
        this._linguagem = modo;
        this._tema = tema;
        this._aceEditor = ace.edit("editor");

        this.initEditor();

    }

    get linguagem(){
        return this._linguagem;
    }

    get codigo(){
        return this._aceEditor.getValue();
    }

    initEditor() {
        this._aceEditor.setTheme("ace/theme/".concat(this._tema));
        this._aceEditor.session.setMode("ace/mode/".concat(this._linguagem));
    }

    alterarLinguagem(linguagem){
        this._linguagem = linguagem;
        this._aceEditor.session.setMode("ace/mode/".concat(linguagem));
    }
}