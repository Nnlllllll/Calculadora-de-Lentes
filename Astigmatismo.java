class Astigmatismo extends CondicaoOcular {
    private double grauCilindrico;

    public Astigmatismo(double grauEsferico, double grauCilindrico, String material, String tipoLente) {
        super(grauEsferico, material, tipoLente);
        this.grauCilindrico = grauCilindrico;
    }

    @Override
    void calcularDimensoesLente() {
        double distanciaFocalEsferica = 1 / grau; // Em metros
        double raioCurvaturaEsferica = (indiceRefracao - 1) * distanciaFocalEsferica;

        double distanciaFocalCilindrica = 1 / grauCilindrico; // Em metros
        double raioCurvaturaCilindrica = (indiceRefracao - 1) * distanciaFocalCilindrica;

        System.out.printf("\nPara astigmatismo (esférico: %.2f D, cilíndrico: %.2f D):\n", grau, grauCilindrico);
        System.out.printf("- Material: %s (índice de refração: %.2f)\n", material, indiceRefracao);
        System.out.printf("- Tipo de lente: %s\n", tipoLente);
        System.out.printf("- Distância focal esférica: %.2f cm\n", distanciaFocalEsferica * 100);
        System.out.printf("- Raio de curvatura esférica: %.2f cm\n", Math.abs(raioCurvaturaEsferica) * 100);
        System.out.printf("- Distância focal cilíndrica: %.2f cm\n", distanciaFocalCilindrica * 100);
        System.out.printf("- Raio de curvatura cilíndrica: %.2f cm\n", Math.abs(raioCurvaturaCilindrica) * 100);
        System.out.printf("(%s em %s com componente cilíndrico, face plana = infinito)\n", tipoLente, material);
    }
}