
class CadastroController{
    constructor(){
        this._service = new RestService();
    }

    cadastraUsuario(event){
        event.preventDefault();

        let nome = seletor("#inputNome");
        let sobrenome = seletor("#inputSobrenome");
        let email = seletor("#inputEmail");
        let login = seletor("#inputLogin");
        let senha = seletor("#inputPassword");
        let confirmaSenha = seletor("#inputPassword2");

        if (senha.value !== confirmaSenha.value){
            alert("Senhas não conferem");
            return;
        }

        let usuario = new Usuario(nome.value,sobrenome.value,email.value,login.value,senha.value)
        console.log(usuario);
        this._service.post("http://localhost:8080/EditorWebService/webresources/usuarioCadastro/cadastro",usuario)
        .then(res => {
            let resposta = new Resposta(res);
            console.log(resposta);
        })
        .catch(error => console.error(error));


    }
}