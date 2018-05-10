class CadastroProblemasController {
    constructor() {
        
        this.listaCasosTestes = [];
    }

    adicionaCasos(event) {
        event.preventDefault();
        this.listaCasosTestes.push(new CasosTestes());
        this.modificaTemplate();

    }

    modificaTemplate() {
        let casos = document.querySelector("#casosTestes");
        casos.innerHTML = `
                <div class="form-group">
                    <label for="inputDescricao">Descrição</label>
                    <textarea rows="10" class="form-control" id="inputDescricao" placeholder="Insira a Descrição do Problema"></textarea>
                </div>
        `;

    }

}