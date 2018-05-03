class RestService{

    _handleErrors(res){
        if (!res.ok) throw new Error (res.statusText);
        return res;
        
    }

    /**
     * Faz uma requisição do tipo GET
     * @param {string} url 
     */
    get(url) {
        return fetch(url)
        .then(res => this._handleErrors(res))
        .then(res => res.json());

    }

    /**
     * Faz uma requisição do tipo POST
     * @param {string} url 
     * @param {*} dado 
     */
    post(url, dado) {

        return fetch(url,{
            method: "post",
            body: JSON.stringify(dado)
        })
        .then(res => this._handleErrors(res))
        .then(res => res.json());
        
    }
}