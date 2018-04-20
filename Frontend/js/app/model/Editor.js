class Editor{
    constructor(tema,modo){
        this._modo = modo;
        this._tema = tema;
        this._aceEditor = ace.edit("editor");

        this.initEditor();

    }

    get modo(){
        return this._modo;
    }

    get codigo(){
        return this._aceEditor.getValue();
    }

    initEditor() {
        this._aceEditor.setTheme("ace/theme/".concat(this._tema));
        this._aceEditor.session.setMode("ace/mode/".concat(this._modo));
    }

    alterarLinguagem(linguagem){
        console.log("aqui");
        this._aceEditor.session.setMode("ace/mode/".concat(linguagem));
    }
}