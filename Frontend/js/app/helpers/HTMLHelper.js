class HTMLHelper{
    static alertSucesso(mensagem){
        return `
        <div class="alert alert-success" role="alert">
               ${mensagem}
        </div>
        `
    }

    static alertErro(mensagem){
        return `
        <div class="alert alert-danger" role="alert">
               ${mensagem}
        </div>
        `
    }

    /**
     * Cria Alert do Tipo Warning
     * @param {String} mensagem 
     */
    static alertWarning(mensagem){
        return `
        <div class="alert alert-warning" role="alert">
               ${mensagem}
        </div>
        `
    }

    /**
     * Cria um Elemento do tipo Input
     * @param {String} id 
     * @param {String} type 
     * @param {String} placeHolder 
     * @param {*} classes
     * @returns {Element}  
     */
    static createInputField(id,type,placeHolder,...classes){
        let input = document.createElement("input");

        if (id != null && id.length > 0){
            input.id = id;
        }

        input.placeholder = placeHolder;
        input.classList.add(...classes);
        return input;
    }

    static createDivField(id,...classes){
        let div = document.createElement("div");

        if (id != null && id.length > 0){
            div.id = id;
        }
        
        div.classList.add(...classes);
        return div;
    }
}