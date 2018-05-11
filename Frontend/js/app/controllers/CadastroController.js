
class CadastroController {
    constructor() {
        this._service = new RestService();
        this._alert = seletor("#mensagemSaida");
        this._usuario = {};
    }

    /**
     * Submit do Formulário
     * @param {event} event 
     */
    cadastraUsuario(event) {
        

        let nome = seletor("#inputNome");
        let sobrenome = seletor("#inputSobrenome");
        let email = seletor("#inputEmail");
        let login = seletor("#inputLogin");
        let senha = seletor("#inputPassword");
        let confirmaSenha = seletor("#inputPassword2");
        this.usuario = new Usuario(nome.value, sobrenome.value, email.value, login.value, senha.value);

        if (senha.value !== confirmaSenha.value) {
            this._alert.innerHTML = HTMLHelper.alertWarning("Senhas não conferem");
            return;
        }


        //Enviando Requisição pro Servidor
        this._service.post("http://localhost:8080/EditorWebService/webresources/usuarioCadastro/cadastro", this.usuario)
            .then(res => {
                let resposta = new Resposta(res);

                if (resposta.status === 0) {
                    this._alert.innerHTML = HTMLHelper.alertSucesso(resposta.saidaFormatada);
                }
                else {
                    this._alert.innerHTML = HTMLHelper.alertErro(resposta.saidaFormatada);
                }

            })
            .catch(error => {
                console.error(error)
                this._alert.innerHTML = HTMLHelper.alertErro(error);
            });


    }


}