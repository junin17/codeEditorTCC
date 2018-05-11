class CadastroProblemasController {
    constructor() {
        this._service = new RestService();
        this._divCasosTestes = document.querySelector("#casosTestes");
        this._casosTestes = [];
        this._inputTitulo = document.querySelector("#inputTitulo");
        this._inputDescricao = document.querySelector("#inputDescricao");
        this._inputPontuacao = document.querySelector("#inputPontuacao");
        
    }

    salvarProblema(event){
        event.preventDefault();
        let problema = new Problema(this._inputTitulo.value,this._inputDescricao.value,this._inputPontuacao.value);


        this._casosTestes.forEach(casoTeste => {
            let entrada = document.querySelector("#".concat(casoTeste.entrada));
            let saida = document.querySelector("#".concat(casoTeste.saida));
            problema.addCasoTeste(new CasosTestes(entrada.value,saida.value));
        })
        
        console.log(problema);

        this._service.post("http://localhost:8080/EditorWebService/webresources/problemaCadastro/salvar",problema)
        .then(res => {
            console.log(res);
        })
        .catch(error => console.error(error));
    }

    adicionaCasos(event) {
        
        let idEntrada = "entrada".concat(this._casosTestes.length);
        let idSaida = "saida".concat(this._casosTestes.length);

        this._casosTestes.push({entrada: idEntrada, saida: idSaida});

        let entrada = HTMLHelper.createInputField(idEntrada,"text","Entrada","form-control");
        let divColEntrada = HTMLHelper.createDivField(null,"col");
        divColEntrada.appendChild(entrada);

        let saida = HTMLHelper.createInputField(idSaida,"text","Saida","form-control");
        let divColSaida = HTMLHelper.createDivField(null,"col");
        divColSaida.appendChild(saida);

        let divRow = HTMLHelper.createDivField(null,"form-row","linha");
        divRow.appendChild(divColEntrada);
        divRow.appendChild(divColSaida);

        this._divCasosTestes.appendChild(divRow);

    }

}