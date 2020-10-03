import java.util.concurrent.Semaphore;
import java.util.*;

public class SolucaoSemaforo {

    private static int quantFilosofos;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] nomes= {"Aristóteles", "Sócrates", "Platão", "Descartes", "Nietzsche", "Rousseau", "Agostinho", "Kant", "Pascal"};

        System.out.println("Solução com Semaforos: ");
        System.out.println("Digite quantos filosofos: ");
        quantFilosofos = sc.nextInt();
        System.out.println("");

        SharedMemory sm = new SharedMemory(quantFilosofos);

        for (int i = 0; i < quantFilosofos; i++){
            new Filosofo(sm, nomes[i], i);
        }








    }

}





