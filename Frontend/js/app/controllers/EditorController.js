

class EditorController {

    constructor() {

        this._editor = new Editor("monokai","javascript");
        this._linguagens = document.querySelector("#comboLanguages");

        this.init();

        this.enviaDados();
    }

    init() {


        this._linguagens.addEventListener("change", event => this._editor.alterarLinguagem(this._linguagens.value));

    }

    enviaDados(event) {
        console.log("Iniciando Requisição...")

        let submit = new Submit();
        submit.code = this._editor.codigo;
        submit.linguagem = this._editor.linguagem;

        fetch("http://localhost:8080/EditorWebService/webresources/editor", {
            method: 'post',
            body: JSON.stringify(submit),
        }).then(response => {
            return response.text()
        }).then(data => {
            console.log(data);
            console.log("Fim da Requisição");
        }).catch(error => {
            console.log(error);
        })


    }


}