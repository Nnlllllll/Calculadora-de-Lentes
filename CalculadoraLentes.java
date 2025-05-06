import java.util.Scanner;

public class CalculadoraLentes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Calculadora de Lentes para Condições Oculares");

        // 1. Escolha da condição ocular primeiro
        System.out.println("\nQual é a condição ocular?");
        System.out.println("1 - Miopia");
        System.out.println("2 - Astigmatismo");
        System.out.print("Digite o número da opção: ");
        int opcaoCondicao = scanner.nextInt();

        // 2. Coleta dos graus com base na condição
        CondicaoOcular condicao;
        double grau = 0;
        double grauCilindrico = 0;
        if (opcaoCondicao == 1) {
            System.out.print("Digite o grau da miopia (exemplo: -2.5): ");
            grau = scanner.nextDouble();
        } else if (opcaoCondicao == 2) {
            System.out.print("Digite o grau esférico (exemplo: -2.5): ");
            grau = scanner.nextDouble();
            System.out.print("Digite o grau cilíndrico (exemplo: -1.0): ");
            grauCilindrico = scanner.nextDouble();
        } else {
            System.out.println("Opção inválida! Programa encerrado.");
            scanner.close();
            return;
        }

        // 3. Escolha do material
        System.out.println("\nQual é o material da lente?");
        System.out.println("1 - Resina (índice de refração: 1.50)");
        System.out.println("2 - Policarbonato (índice de refração: 1.59)");
        System.out.println("3 - Vidro (índice de refração: 1.52)");
        System.out.print("Digite o número da opção: ");
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
                System.out.println("Opção inválida, usando Resina como padrão.");
        }

        // 4. Escolha do tipo de lente
        System.out.println("\nQual é o tipo de lente?");
        System.out.println("1 - Plano-côncava (comum para miopia)");
        System.out.println("2 - Biconcava (alternativa para miopia ou astigmatismo)");
        System.out.println("3 - Tórica (específica para astigmatismo)");
        System.out.print("Digite o número da opção: ");
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
                tipoLente = "Plano-côncava";
                System.out.println("Opção inválida, usando Plano-côncava como padrão.");
        }

        // Instanciação da condição ocular com os parâmetros coletados
        if (opcaoCondicao == 1) {
            condicao = new Miopia(grau, material, tipoLente);
        } else {
            condicao = new Astigmatismo(grau, grauCilindrico, material, tipoLente);
        }

        condicao.calcularDimensoesLente();
        scanner.close();
    }
}