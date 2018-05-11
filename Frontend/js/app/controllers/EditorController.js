class EditorController {

    /**
     * Construtor de EditorController
     */
    constructor() {
        this._linguagem = "javascript";
        this._tema = "monokai";
        this._aceEditor = ace.edit("editor");
        this._linguagens = seletor("#comboLanguages");
        this._service = new EditorService();
        this.selecionaLinguagem();

        this.initEditor();

    }

    get codigo(){
        return this._aceEditor.getValue();
    }

     /**
     * Inicia as configurações do Editor
     */
    initEditor() {
        this._aceEditor.setTheme("ace/theme/".concat(this._tema));
        this._aceEditor.session.setMode("ace/mode/".concat(this._linguagem));
    }

    /**
     * Listener que seta linguagem selecionada para o Editor
     * @param {event} event 
     */
    selecionaLinguagem(event){
        this.alterarLinguagem(this._linguagens.value);

        this._service.getCodigoPadrao(this._linguagens.value)
        .then(res => {
            let resposta = new Resposta(res);
            this.modificaTexto(resposta.saida);
        })
        .catch(error => console.error(error));

    }

    /**
     * Listener que envia a submissão para o Serviço
     * @param {event} event 
     */
    enviaSubmissao(event) {

        let submit = new Submit();
        submit.code = this.codigo;
        submit.linguagem = this._linguagem;

        this._service.enviaCodigo(submit)
        .then(res => {
            let resposta = new Resposta(res);
            this.exibeResposta(resposta);
        })
        .catch(error => console.error(error));
        

    }
    
    /**
     * Método que exibe a Resposta da execução do código
     * @param {Resposta} resposta 
     */
    exibeResposta(resposta){
        let saida = document.querySelector("#saida");
        saida.innerHTML = resposta.status === 0 ? HTMLHelper.alertSucesso(resposta.saidaFormatada) : HTMLHelper.alertErro(resposta.saidaFormatada);
    }

    
   

    /**
     * Altera a Linguagem do Editor
     * @param {string} linguagem 
     */
    alterarLinguagem(linguagem){
        this._linguagem = linguagem;
        this._aceEditor.session.setMode("ace/mode/".concat(linguagem));
    }

    /**
     * Modifica o Texto do Editor
     * @param {string} texto 
     */
    modificaTexto(texto){
        this._aceEditor.setValue(texto,-1);
        this._aceEditor.clearSelection();
    }

}