class Problema{
    constructor(titulo,descricao,pontuacao){
        this.titulo = titulo;
        this.descricao = descricao;
        this.pontuacao = pontuacao;
        this.casosTestes = [];
    }

    addCasoTeste(casoTeste){
        this.casosTestes.push(casoTeste);
    }
}