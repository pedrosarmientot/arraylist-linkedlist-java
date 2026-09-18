public class Main{

    public static int solveJosephus(IList<Integer> list, int n, int k){
        if(n <= 0 || k <= 0){
            throw new IllegalArgumentException("Parametros invalidos: N y K deben ser mayores que 0.");
        }

        list.clear();
        for(int i=1; i<=n; i++){
            list.add(i);
        }

        int idx = 0;
        while(list.size() > 1){
            idx = (idx + k - 1) % list.size();
            list.remove(idx);
        }

        return list.get(0);
    }

    public static void testStructure(IList<Integer> list, String name){
        System.out.println("=== PRUEBAS DE ESTRUCTURA: " + name + " ===");

        list.clear();

        list.add(10);
        list.add(30);
        list.add(20, 1);
        System.out.println("[Positivo] Insercion e indice (Esperado: 20): " + list.get(1));

        list.add(40, list.size());
        System.out.println("[Positivo] Insercion al final por indice (Esperado: 40): " + list.get(3));

        int removed = list.remove(1);
        System.out.println("[Positivo] Elemento eliminado (Esperado: 20): " + removed);
        System.out.println("[Positivo] Nuevo elemento en pos 1 (Esperado: 30): " + list.get(1));
        System.out.println("[Positivo] Tamano actual (Esperado: 3): " + list.size());

        list.clear();
        System.out.println("[Positivo] Lista vacia tras clear (Esperado: true): " + list.isEmpty());

        for(int i=0; i<15; i++){
            list.add(i);
        }
        System.out.println("[Positivo] Expansion superando capacidad inicial (Esperado: 15): " + list.size());

        try{
            list.get(-1);
            System.out.println("[ERROR NO CAPTURADO] get(-1) debio fallar.");
        } catch(Exception e){
            System.out.println("[Negativo Exitoso] get(-1) lanzo: " + e.getMessage());
        }

        try{
            list.get(list.size());
            System.out.println("[ERROR NO CAPTURADO] get(size) debio fallar.");
        } catch(Exception e){
            System.out.println("[Negativo Exitoso] get(size) lanzo: " + e.getMessage());
        }

        try{
            list.remove(100);
            System.out.println("[ERROR NO CAPTURADO] remove(100) debio fallar.");
        } catch(Exception e){
            System.out.println("[Negativo Exitoso] remove(100) lanzo: " + e.getMessage());
        }

        try{
            list.add(999, -5);
            System.out.println("[ERROR NO CAPTURADO] add() con indice negativo debio fallar.");
        } catch(Exception e){
            System.out.println("[Negativo Exitoso] add(val, -5) lanzo: " + e.getMessage());
        }

        try{
            list.add(999, list.size() + 1);
            System.out.println("[ERROR NO CAPTURADO] add() con indice mayor que size debio fallar.");
        } catch(Exception e){
            System.out.println("[Negativo Exitoso] add(val, size+1) lanzo: " + e.getMessage());
        }

        System.out.println();
    }

    public static void testJosephus(IList<Integer> list, String name){
        System.out.println("=== JOSEFO: " + name + " ===");
        System.out.println("[Positivo] N=7, K=3 (Esperado: 4): " + solveJosephus(list, 7, 3));
        System.out.println("[Positivo] N=1, K=3 (Esperado: 1): " + solveJosephus(list, 1, 3));
        System.out.println("[Positivo] N=6, K=100 (Esperado: 6): " + solveJosephus(list, 6, 100));

        try{
            solveJosephus(list, 0, 3);
            System.out.println("[ERROR NO CAPTURADO] N=0 debio fallar.");
        } catch(Exception e){
            System.out.println("[Negativo Exitoso] N=0 lanzo: " + e.getMessage());
        }

        try{
            solveJosephus(list, 7, 0);
            System.out.println("[ERROR NO CAPTURADO] K=0 debio fallar.");
        } catch(Exception e){
            System.out.println("[Negativo Exitoso] K=0 lanzo: " + e.getMessage());
        }

        System.out.println();
    }

    public static void main(String args[]){
        IList<Integer> arrayList = new ArrayList<Integer>();
        IList<Integer> linkedList = new LinkedList<Integer>();

        testStructure(arrayList, "ArrayList");
        testStructure(linkedList, "LinkedList");

        testJosephus(arrayList, "ArrayList");
        testJosephus(linkedList, "LinkedList");
    }
}