abstract class CondicaoOcular {
    protected double grau;
    protected double indiceRefracao;
    protected String tipoLente;
    protected String material;

    public CondicaoOcular(double grau, String material, String tipoLente) {
        this.grau = grau;
        this.material = material;
        this.tipoLente = tipoLente;
        // Definindo índice de refração com base no material
        switch (material.toLowerCase()) {
            case "resina":
                this.indiceRefracao = 1.5;
                break;
            case "policarbonato":
                this.indiceRefracao = 1.59;
                break;
            case "vidro":
                this.indiceRefracao = 1.52;
                break;
            default:
                this.indiceRefracao = 1.5; // Resina como padrão
        }
    }

    abstract void calcularDimensoesLente();
}