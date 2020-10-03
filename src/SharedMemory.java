import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SharedMemory {

    private ArrayList<String> estados;
    private ArrayList<Semaphore> filosofos;
    private int quantFilosofos;
    private Semaphore mutex;
    private String[] nomes= {"Aristóteles", "Sócrates", "Platão", "Descartes", "Nietzsche", "Rousseau", "Agostinho", "Kant", "Pascal"};

    public SharedMemory(int quantFilosofos){
        this.quantFilosofos = quantFilosofos;
        mutex = new Semaphore(1);
        filosofos = criarSemaforoFilosofos(quantFilosofos);
        estados = criarEstados(quantFilosofos);
    }

    private static ArrayList<Semaphore> criarSemaforoFilosofos(int quant){
        ArrayList<Semaphore> lista = new ArrayList<>();
        for (int i = 0; i < quant; i++){
            Semaphore filosofo  = new Semaphore(0);
            lista.add(filosofo);
        }
        return lista;
    }

    private static ArrayList<String> criarEstados(int quant){
        ArrayList<String> lista = new ArrayList<>();
        for (int j = 0; j < quant; j++){
            lista.add("Pensando");
        }
        return lista;
    }

    public boolean podeComer(int id){
        return !estados.get(direita(id)).equals("Comendo") || !estados.get(esquerda(id)).equals("Comendo");
    }

    public boolean direitaEsperando(int id){
        return estados.get(direita(id)).equals("Com Fome") && !estados.get(direita(direita(id))).equals("Comendo");
    }

    public boolean esquerdaEsperando(int id){
        return estados.get(esquerda(id)).equals("Com fome") && !estados.get(esquerda(esquerda(id))).equals("Comendo");
    }

    public int esquerda(int id){
        if(id == quantFilosofos-1){
            return 0;
        }else{
            return id+1;
        }
    }
    public int direita(int id){
        if(id == 0){
            return quantFilosofos-1;
        }else{
            return id-1;
        }
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public ArrayList<String> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<String> estados) {
        this.estados = estados;
    }

    public ArrayList<Semaphore> getFilosofos() {
        return filosofos;
    }

    public void setFilosofos(ArrayList<Semaphore> filosofos) {
        this.filosofos = filosofos;
    }

    public void printEstados(){
        for(int k = 0; k < quantFilosofos; k++){
            System.out.println(nomes[k] +": " +  estados.get(k));
        }
        System.out.println("");
    }
}
