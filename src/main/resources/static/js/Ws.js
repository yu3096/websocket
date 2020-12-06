class Ws{
    constructor() {
        this.url = '';
        this.method = '';
    }
}
class wsBuilder{
    constructor() {
        this.ws = new Ws();
    }

    forUrl(url){
        this.ws.url = url;
        return this;
    }

    build(){
        return this.ws;
    }
}