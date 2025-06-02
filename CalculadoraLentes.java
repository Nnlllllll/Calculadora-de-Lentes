import java.util.Locale;
import java.util.Scanner;

public class CalculadoraLentes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("=== Calculadora de Lentes para Fabricação ===");
        
        // Escolha da condição ocular
        System.out.println("\nSelecione a condição ocular:");
        System.out.println("1 - Miopia");
        System.out.println("2 - Astigmatismo");
        System.out.print("\nDigite o número da opção: ");
        int opcaoCondicao = scanner.nextInt();

        // Coleta dos graus
        CondicaoOcular condicao;
        double grauEsferico = 0;
        double grauCilindrico = 0;
        int eixo = 0;
        if (opcaoCondicao == 1) {
            System.out.print("\nDigite o grau da miopia (exemplo: -2.5): ");
            grauEsferico = scanner.nextDouble();
            if (grauEsferico > 0 || grauEsferico < -20) {
                System.out.println("Erro: Grau de miopia deve estar entre -20.0 e 0.0 D.");
                scanner.close();
                return;
            }
        } else if (opcaoCondicao == 2) {
            System.out.print("Digite o grau esférico (exemplo: -2.5): ");
            grauEsferico = scanner.nextDouble();
            System.out.print("Digite o grau cilíndrico (exemplo: -1.0): ");
            grauCilindrico = scanner.nextDouble();
            System.out.print("Digite o eixo do cilindro (0 a 180 graus): ");
            eixo = scanner.nextInt();
            if (grauEsferico < -20 || grauEsferico > 20 || grauCilindrico > 0 || grauCilindrico < -6 || eixo < 0 || eixo > 180) {
                System.out.println(\n"Erro: Graus devem estar entre -20.0 e +20.0 D (esférico), -6.0 e 0.0 D (cilíndrico), e eixo entre 0 e 180 graus.");
                scanner.close();
                return;
            }
        } else {
            System.out.println("\nOpção inválida! Programa encerrado.");
            scanner.close();
            return;
        }

        // Escolha do material
        System.out.println("\nSelecione o material da lente:");
        System.out.println("1 - Resina (índice de refração: 1.50)");
        System.out.println("2 - Policarbonato (índice de refração: 1.59)");
        System.out.println("3 - Vidro (índice de refração: 1.52)");
        System.out.print("\nDigite o número da opção: ");
        int opcaoMaterial = scanner.nextInt();
        String material;
        switch (opcaoMaterial) {
            case 1:
                material = "Resina";
                break;
            case 2:
                material = "Policarbonato";
                break;
            case 3:
                material = "Vidro";
                break;
            default:
                material = "Resina";
                System.out.println("\nOpção inválida, usando Resina como padrão.");
        }

        // Escolha do tipo de lente
        System.out.println("\nSelecione o tipo de lente:");
        System.out.println("1 - Plano-côncava (comum para miopia)");
        System.out.println("2 - Biconcava (alternativa para miopia)");
        System.out.println("3 - Tórica (específica para astigmatismo)");
        System.out.print("\nDigite o número da opção: ");
        int opcaoLente = scanner.nextInt();
        String tipoLente;
        switch (opcaoLente) {
            case 1:
                tipoLente = "Plano-côncava";
                break;
            case 2:
                tipoLente = "Biconcava";
                break;
            case 3:
                tipoLente = "Tórica";
                break;
            default:
                tipoLente = opcaoCondicao == 2 ? "Tórica" : "Plano-côncava";
                System.out.println("\nOpção inválida, usando " + tipoLente + " como padrão.");
        }

        // Instanciação da condição ocular
        if (opcaoCondicao == 1) {
            condicao = new Miopia(grauEsferico, material, tipoLente);
        } else {
            condicao = new Astigmatismo(grauEsferico, grauCilindrico, eixo, material, tipoLente);
        }

        condicao.calcularDimensoesLente();
        scanner.close();
    }
}
