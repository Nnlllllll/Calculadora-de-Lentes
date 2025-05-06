class Miopia extends CondicaoOcular {
    public Miopia(double grau, String material, String tipoLente) {
        super(grau, material, tipoLente);
    }

    @Override
    void calcularDimensoesLente() {
        double distanciaFocal = 1 / grau; // Em metros
        double raioCurvatura = (indiceRefracao - 1) * distanciaFocal;

        System.out.printf("\nPara %.2f D de miopia:\n", grau);
        System.out.printf("- Material: %s (índice de refração: %.2f)\n", material, indiceRefracao);
        System.out.printf("- Tipo de lente: %s\n", tipoLente);
        System.out.printf("- Distância focal: %.2f cm\n", distanciaFocal * 100);
        System.out.printf("- Raio de curvatura da face côncava: %.2f cm\n", Math.abs(raioCurvatura) * 100);
        System.out.printf("(%s em %s, face plana = infinito)\n", tipoLente, material);
    }
}