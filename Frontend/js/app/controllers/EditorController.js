class EditorController {

    constructor() {

        
        this._editor = new Editor("monokai","javascript");
        this._linguagens = document.querySelector("#comboLanguages");
        this._service = new RestService();

        this.init();

    }

    init() {


        this._linguagens.addEventListener("change", event => this._editor.alterarLinguagem(this._linguagens.value));

    }

    enviaDados(event) {
        console.log("Iniciando Requisição...")

        let submit = new Submit();
        submit.code = this._editor.codigo;
        submit.linguagem = this._editor.linguagem;

        this._service.post("http://localhost:8080/EditorWebService/webresources/editor",submit)
        .then(res => {
            let resposta = new Resposta(res);
            console.log(resposta);
            this.exibeResposta(resposta);
        })
        .catch(error => console.error(error));
        

    }

    exibeResposta(resposta){
        console.log("here");
        let saida = document.querySelector("#saida");
        saida.innerHTML = resposta.saidaFormatada;
    }


}