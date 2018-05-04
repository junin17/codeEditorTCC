class EditorController {

    /**
     * Construtor de EditorController
     */
    constructor() {
        
        this._editorView = new EditorView("monokai","javascript");
        this._linguagens = seletor("#comboLanguages");
        this._service = new EditorService();
        this.selecionaLinguagem();

    }

   
    /**
     * Listener que seta linguagem selecionada para o Editor
     * @param {event} event 
     */
    selecionaLinguagem(event){
        this._editorView.alterarLinguagem(this._linguagens.value);

        this._service.getCodigoPadrao(this._linguagens.value)
        .then(res => {
            let resposta = new Resposta(res);
            this._editorView.modificaTexto(resposta.saida);
        })
        .catch(error => console.error(error));

    }

    /**
     * Listener que envia a submissão para o Serviço
     * @param {event} event 
     */
    enviaSubmissao(event) {

        let submit = new Submit();
        submit.code = this._editorView.codigo;
        submit.linguagem = this._editorView.linguagem;

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
        saida.innerHTML = resposta.saidaFormatada;
    }


}