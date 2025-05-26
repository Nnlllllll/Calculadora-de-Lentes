 public class Miopia extends CondicaoOcular {
    public Miopia(double grauEsferico, String material, String tipoLente) {
        super(grauEsferico, material, tipoLente);
    }

    @Override
    public void calcularDimensoesLente() {
        if (grauEsferico >= 0) {
            System.out.println("Erro: Miopia requer grau esférico negativo.");
            return;
        }

        double distanciaFocal = 1 / grauEsferico; // Em metros
        double R1, R2;

        if (tipoLente.equalsIgnoreCase("Plano-côncava")) {
            R1 = (indiceRefracao - 1) * distanciaFocal; // Face côncava
            R2 = Double.POSITIVE_INFINITY; // Face plana
        } else if (tipoLente.equalsIgnoreCase("Biconcava")) {
            // Dividir a curvatura igualmente para minimizar aberrações
            R1 = -2 * (indiceRefracao - 1) * distanciaFocal; // Face côncava
            R2 = -R1; // Outra face côncava
        } else {
            System.out.println("Tipo de lente inválido para miopia. Usando Plano-côncava.");
            R1 = (indiceRefracao - 1) * distanciaFocal;
            R2 = Double.POSITIVE_INFINITY;
        }

        // Calcular espessura nas bordas (para lentes negativas)
        double espessuraBorda = espessuraCentral + (Math.pow(diametroLente, 2) / (8 * (indiceRefracao - 1))) * Math.abs(1 / R1 - 1 / R2);

        System.out.printf("\n=== Lente para Miopia ===\n");
        System.out.printf("Grau esférico: %.2f D\n", grauEsferico);
        System.out.printf("Material: %s (índice de refração: %.2f)\n", material, indiceRefracao);
        System.out.printf("Tipo de lente: %s\n", tipoLente);
        System.out.printf("Distância focal: %.2f cm\n", distanciaFocal * 100);
        System.out.printf("Raio de curvatura R1: %.2f mm\n", R1 * 1000);
        System.out.printf("Raio de curvatura R2: %s\n", R2 == Double.POSITIVE_INFINITY ? "Plano" : String.format("%.2f mm", R2 * 1000));
        System.out.printf("Espessura central: %.2f mm\n", espessuraCentral * 1000);
        System.out.printf("Espessura nas bordas: %.2f mm\n", espessuraBorda * 1000);
        System.out.printf("Diâmetro recomendado: %.2f mm\n", diametroLente * 1000);
        System.out.println("Recomendações: Use máquina de polimento de alta precisão para raios de curvatura.");
    }
}
