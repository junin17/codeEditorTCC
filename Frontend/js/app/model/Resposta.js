class Resposta{
    constructor(objeto){
        this.saida = "";
        this.status = 0;

        for (var prop in objeto){
            this[prop] = objeto[prop];
        }
    }

   get saidaFormatada(){
       return this.saida.split('\n').join('<br/>');
   }
}