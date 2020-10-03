public class Filosofo implements Runnable {

    private SharedMemory sm;
    private final String nome;
    private final int id;

    public Filosofo(SharedMemory sm, String nome, int id){
        this.sm = sm;
        this.nome = nome;
        this.id = id;
        new Thread((Runnable) this, nome).start();
    }

    public void run(){
        while(true){
            pegarTalheres(id);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            soltarTalheres(id);
        }
    }

    private void pegarTalheres(int id){
        try {
            sm.getMutex().acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sm.getEstados().set(id, "Com Fome");
        if(sm.podeComer(id)) {
            sm.getFilosofos().get(id).release();
            sm.getEstados().set(id, "Comendo");
            sm.printEstados();

        }

        sm.getMutex().release();
        try {
            sm.getFilosofos().get(id).acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void soltarTalheres(int id){
        try {
            sm.getMutex().acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sm.getEstados().set(id, "Pensando");
        sm.printEstados();

        if(sm.direitaEsperando(id)){
            sm.getEstados().set(sm.direita(id), "Comendo");
            sm.printEstados();
            sm.getFilosofos().get(sm.direita(id)).release();
        }
        if(sm.esquerdaEsperando(id)){
            sm.getEstados().set(sm.esquerda(id), "Comendo");
            sm.printEstados();
            sm.getFilosofos().get(sm.esquerda(id)).release();
        }

        sm.getMutex().release();

    }
}
