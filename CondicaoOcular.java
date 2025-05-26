public abstract class CondicaoOcular {
    protected double grauEsferico;
    protected double indiceRefracao;
    protected String tipoLente;
    protected String material;
    protected double espessuraCentral = 0.002; // 2 mm como padrão
    protected double diametroLente = 0.05; // 50 mm como padrão

    public CondicaoOcular(double grauEsferico, String material, String tipoLente) {
        this.grauEsferico = grauEsferico;
        this.material = material;
        this.tipoLente = tipoLente;
        switch (material.toLowerCase()) {
            case "resina":
                this.indiceRefracao = 1.50;
                break;
            case "policarbonato":
                this.indiceRefracao = 1.59;
                break;
            case "vidro":
                this.indiceRefracao = 1.52;
                break;
            default:
                this.indiceRefracao = 1.50;
                this.material = "Resina";
        }
    }

    public abstract void calcularDimensoesLente();
}
