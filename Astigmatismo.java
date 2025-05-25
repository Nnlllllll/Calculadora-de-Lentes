class Astigmatismo extends CondicaoOcular {
    private double grauCilindrico;
    private int eixo;

    public Astigmatismo(double grauEsferico, double grauCilindrico, int eixo, String material, String tipoLente) {
        super(grauEsferico, material, tipoLente);
        this.grauCilindrico = grauCilindrico;
        this.eixo = eixo;
    }

    @Override
    void calcularDimensoesLente() {
        if (grauCilindrico >= 0 || eixo < 0 || eixo > 180) {
            System.out.println("Erro: Grau cilíndrico deve ser negativo e eixo entre 0 e 180 graus.");
            return;
        }

        // Potências nos meridianos
        double potenciaEsferica = grauEsferico;
        double potenciaCilindrica = grauEsferico + grauCilindrico;

        // Distâncias focais
        double distanciaFocalEsferica = 1 / potenciaEsferica;
        double distanciaFocalCilindrica = 1 / potenciaCilindrica;

        // Raios de curvatura (tórica: diferentes curvaturas em meridianos)
        double R1_esf, R2_esf, R1_cil, R2_cil;
        if (tipoLente.equalsIgnoreCase("Tórica")) {
            R1_esf = (indiceRefracao - 1) * distanciaFocalEsferica;
            R2_esf = Double.POSITIVE_INFINITY; // Simplificação: face traseira plana
            R1_cil = (indiceRefracao - 1) * distanciaFocalCilindrica;
            R2_cil = Double.POSITIVE_INFINITY;
        } else {
            System.out.println("Tipo de lente inválido para astigmatismo. Usando Tórica.");
            R1_esf = (indiceRefracao - 1) * distanciaFocalEsferica;
            R2_esf = Double.POSITIVE_INFINITY;
            R1_cil = (indiceRefracao - 1) * distanciaFocalCilindrica;
            R2_cil = Double.POSITIVE_INFINITY;
        }

        // Calcular espessura nas bordas
        double espessuraBorda = espessuraCentral + (Math.pow(diametroLente, 2) / (8 * (indiceRefracao - 1))) * Math.abs(1 / R1_esf);

        System.out.printf("\n=== Lente para Astigmatismo ===\n");
        System.out.printf("Grau esférico: %.2f D\n", grauEsferico);
        System.out.printf("Grau cilíndrico: %.2f D (eixo: %d°)\n", grauCilindrico, eixo);
        System.out.printf("Material: %s (índice de refração: %.2f)\n", material, indiceRefracao);
        System.out.printf("Tipo de lente: %s\n", tipoLente);
        System.out.printf("Distância focal esférica: %.2f cm\n", distanciaFocalEsferica * 100);
        System.out.printf("Distância focal cilíndrica: %.2f cm\n", distanciaFocalCilindrica * 100);
        System.out.printf("Raio de curvatura R1 (esférico): %.2f mm\n", R1_esf * 1000);
        System.out.printf("Raio de curvatura R2 (esférico): %s\n", R2_esf == Double.POSITIVE_INFINITY ? "Plano" : String.format("%.2f mm", R2_esf * 1000));
        System.out.printf("Raio de curvatura R1 (cilíndrico): %.2f mm\n", R1_cil * 1000);
        System.out.printf("Raio de curvatura R2 (cilíndrico): %s\n", R2_cil == Double.POSITIVE_INFINITY ? "Plano" : String.format("%.2f mm", R2_cil * 1000));
        System.out.printf("Espessura central: %.2f mm\n", espessuraCentral * 1000);
        System.out.printf("Espessura nas bordas: %.2f mm\n", espessuraBorda * 1000);
        System.out.printf("Diâmetro recomendado: %.2f mm\n", diametroLente * 1000);
        System.out.println("Recomendações: Alinhe o eixo do cilindro com precisão durante o polimento. Use máquina CNC para lentes tóricas.");
    }
}
