public class GramETSISI {
    private int maxNodos;             // Tamaño máximo de la tabla.
    private int numVertices;          // Número de vértices.
    private boolean matrizAdy[][];    // Matriz de adyacencias del grafo.
    private Persona contactos[];


    public GramETSISI(int n) { //construye una matriz de nxn sin arcos
        maxNodos = n;
        numVertices = 0;
        matrizAdy = new boolean[n][n];
        contactos = new Persona[n];
    }

    public int getMaxNodos() {
        return maxNodos;
    }

    public int getNumVertices() {
        return numVertices;
    }

    // ------------------------------------

    // MÉTODOS PARA INSERTAR Y ELIMINAR VERTICES

    // ------------------------------------


    public void insertaVertice() {
        if (maxNodos == numVertices)
            System.out.println("Error, se supera el número de nodos máximo del grafo");
        else {
            for (int i = 0; i <= numVertices; i++) {
                //simplemento añado el valor false a las celdas
                matrizAdy[i][numVertices] = matrizAdy[numVertices][i] = false;
            }
            Persona nuevo = new Persona();
            nuevo.leerDatos();
            contactos[numVertices] = nuevo;
            numVertices++;
        }
    }

    public void insertaVertice(Persona persona) {
        if (maxNodos == numVertices)
            System.out.println("Error, se supera el número de nodos máximo del grafo");
        else {
            for (int i = 0; i <= numVertices; i++) {
                matrizAdy[i][numVertices] = matrizAdy[numVertices][i] = false;
            }
            contactos[numVertices] = persona;
            numVertices++;
        }
    }

    public void eliminarVertice(int v) {
        if (v < numVertices && v >= 0) {
            numVertices--;
            int i;
            for (i = 0; i < v; i++) {
                for (int j = v; j < numVertices; j++) {
                    matrizAdy[i][j] = matrizAdy[i][j + 1];
                }
            }
            for (i = v; i < numVertices; i++) {
                contactos[i] = contactos[i + 1];
                int j;
                for (j = 0; j < v; j++) {
                    matrizAdy[i][j] = matrizAdy[i + 1][j];
                }
                for (j = v; j < numVertices; j++) {
                    matrizAdy[i][j] = matrizAdy[i + 1][j + 1];
                }
            }
        } else System.out.println("Error, fuera de rango");
    }

    // ------------------------------------

    // MÉTODOS PARA INSERTAR Y ELIMINAR ARISTAS

    // ------------------------------------

    public void insertaArista(int i, int j) {
        if ((i >= numVertices) || (j >= numVertices))
            System.out.println("Error, los vertices no se encuentran en el grafo.");
        else {
            matrizAdy[i][j] = true;
            matrizAdy[j][i] = matrizAdy[i][j];
        }
    }

    public boolean existeArista(int i, int j) {
        if ((i >= numVertices) || (j >= numVertices)) {
            System.out.println("Error, los vertices no se encuentran en el grafo.");
            return false;
        }
        return matrizAdy[i][j];
    }

    // ------------------------------------

    // MOSTRAR LA INFORMACIÓN

    // ------------------------------------

    public void mostrarRed() {
        System.out.println("Existen " + numVertices + " contactos");
        for (int i = 0; i <numVertices; i++){
            System.out.print(i + " : ");
            contactos[i].mostrarPersona();
        }
        System.out.println();
        System.out.println("Contenido de la matriz: ");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (matrizAdy[i][j]) System.out.print("1 ");
                else System.out.print("0 ");
            }
            System.out.println();
        }
    }

    public int contarGrupos(){
        int grupos = 0;
        boolean[] visitados = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visitados[i] = false;
        }
        for (int i = 0; i < numVertices; i++) {
            if (!visitados[i]){
                System.out.println("Grupo del " + i);
                this.profundidadVerticeRec(i, visitados);
                grupos++;
            }
        }
        return grupos;
    }

    private void profundidadVerticeRec(int v, boolean[] visitados) {
        visitados[v] = true;
        // Vértices adyacentes a v
        for (int i = 0; i < numVertices; i++) {
            if (this.existeArista(v, i) && !visitados[i])
                this.profundidadVerticeRec(i, visitados);
        }
    }

    public void mostrarAmigos(String nombre){
        int posicion = 0;
        boolean encontrado = false;
        while(posicion < numVertices && !encontrado) {
            if (contactos[posicion].getNombre().equalsIgnoreCase(nombre)){
                encontrado = true;
            } else {
                posicion++;
            }
        }
        System.out.println("Amigos de " + nombre);
        if (encontrado){
            for (int i = 0; i < numVertices; i++){
                if (this.existeArista(posicion, i)) {
                    contactos[i].mostrarPersona();
                }
            }
        } else {
            System.out.println("No tiene");
        }
    }

    public void mayorGrupo(){
        int mayor = 0;
        int origen = 0;
        boolean[] visitados = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visitados[i] = false;
        }
        for (int i = 0; i < numVertices; i++) {
            if (!visitados[i]){
                int numPersonas = this.tamañoGrupo(i, visitados);
                if (numPersonas > mayor){
                    mayor = numPersonas;
                    origen = i;
                }
            }
        }
        if (mayor > 0) {
            for (int i = 0; i < numVertices; i++) {
                visitados[i] = false;
            }
            System.out.println("El grupo mayor tiene " + mayor + " elementos y está formado por:");
            this.verGrupo(origen, visitados);
        } else {
            System.out.println("No existen grupos");
        }
    }

    private int tamañoGrupo(int v, boolean[] visitados) {
        int numPersonas = 1;
        visitados[v] = true;
        // Vértices adyacentes a v
        for (int i = 0; i < numVertices; i++) {
            if (this.existeArista(v, i) && !visitados[i])
                numPersonas = numPersonas + this.tamañoGrupo(i, visitados);
        }
        return numPersonas;
    }

    private void verGrupo(int v, boolean[] visitados) {
        visitados[v] = true;
        contactos[v].mostrarPersona();
        for (int i = 0; i < numVertices; i++) {
            if (this.existeArista(v, i) && !visitados[i])
                this.verGrupo(i, visitados);
        }
    }
}
