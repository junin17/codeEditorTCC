class EditorService {
    constructor() {
        this._service = new RestService();
    }

    /**
     * Pega o código padrão de cada linguagem
     * @param {string} linguagem 
     */
    getCodigoPadrao(linguagem) {
        console.log(linguagem);
        return this._service.get("http://localhost:8080/EditorWebService/webresources/editor/padrao/".concat(linguagem));
    }

    /**
     * Envia Submissão para o Servidor 
     * @param {Submit} submit 
     */
    enviaCodigo(submit) {
        return this._service.post("http://localhost:8080/EditorWebService/webresources/editor", submit);
    }
}